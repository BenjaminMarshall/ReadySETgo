package readySETgo.models;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import readySETgo.components.panels.StagePanel;
import readySETgo.dialogs.EditTextDialog;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.UndoManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.TextBox;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class Stage {
    private String name;
    private List<Asset> assets;
    private List<FlyRail> flyRails;
    private Image stageImage;
    private double width;
    private double length;
    
    private String filePath;
    
    public Stage(){
    	this.name = "New Stage";
    	this.assets = new ArrayList<Asset>();
    	this.flyRails =  FileManager.getListOfFlyRails();
    	try {
    	this.stageImage = ImageIO.read(new File("res/stage.png"));
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	this.width = 77 * 12; // Width in Inches
    	this.length = 45 * 12; // Len in inches //TODO
    }
    
    public double getScale() {
    	StagePanel stagePanel = (StagePanel) ComponentManager.getComp("StagePanel");
    	double scale = (stagePanel.getWidth() / this.width);
    	return scale;
    }
    
    public void setFilePath(String s) {
    	this.filePath = s;
    }
    
    public String getFilePath() {
    	return this.filePath;
    }
    
    public void draw(Graphics g){
    	StagePanel stagePanel = (StagePanel) ComponentManager.getComp("StagePanel");
    	double scale = (stagePanel.getWidth() / this.width);
    	g.drawImage(stageImage, 0, 0, (int) (width*scale), (int) (length*scale), null);
    	for(Asset a: assets){
    		a.draw(g, scale);
    		if(UserManager.getSelectedState() == UserManager.SelectedState.SELECTED && UserManager.getSelected() == a) {
    			//Highlight the selected object by drawing a dashed border around it
    			int topLeftX = (int)(a.getxPos() - 2);
    			int topLeftY = (int)(a.getyPos() - 2);
    			int topRightX = (int)(a.getxPos() + a.getPhysicalWidth() + 2);
    			int topRightY = (int)(a.getyPos() - 2);
    			int botLeftX = (int)(a.getxPos() - 2);
    			int botLeftY = (int)(a.getyPos() + a.getPhysicalLength() + 2);
    			int botRightX = (int)(a.getxPos() + a.getPhysicalWidth() + 2);
    			int botRightY = (int)(a.getyPos() + a.getPhysicalLength() + 2);
    			Graphics2D g2 = (Graphics2D) g.create();    	        
    	        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[]{5}, 0);
    	        g2.setStroke(dashed);
    	        g2.drawLine(topLeftX,topLeftY,topRightX,topRightY);
    	        g2.drawLine(topRightX,topRightY,botRightX,botRightY);
    	        g2.drawLine(botRightX,botRightY,botLeftX,botLeftY);
    	        g2.drawLine(botLeftX,botLeftY,topLeftX,topLeftY);
    	        g2.dispose();
    		}
    	}
    	for(FlyRail f: flyRails){
    		if(f.isFlownIn()) {
    			Asset a = f.getDrawable();
    			a.draw(g, scale);
    		}
    	}
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void trashAsset(Asset a) {
    	this.assets.remove(a);
    }
    
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

	public List<FlyRail> getFlyRails() {
		return flyRails;
	}

	public void setFlyRails(List<FlyRail> flyRails) {
		this.flyRails = flyRails;
	}

	public Asset eventOnObject(MouseEvent e) {
		for(Asset a : assets) {
			double scale = this.getScale();
			double assetX = a.getxPos() * scale;
			double assetY = a.getyPos() * scale;
			double assetHeight = a.getPhysicalLength() * scale;
			double assetWidth = a.getPhysicalWidth() * scale;
			double clickX = e.getX();
			double clickY = e.getY();
			
			boolean insideXBounds = (clickX > assetX) && (clickX < (assetX + assetWidth));
			boolean insideYBounds = (clickY > assetY) && (clickY < (assetY + assetHeight));
			boolean inside = insideXBounds && insideYBounds;
			
			if(inside){ return a; }
		}
		return null;
	}
	
	public void copySelected(){
		if(UserManager.getSelected() != null) {
			UserManager.setClipboard(UserManager.getSelected().copyOf());
		}
	}
	
	public void cutSelected(){
		if(UserManager.getSelected() != null) {
			UndoManager.get().storeCut(UserManager.getSelected(), UserManager.getSelectedState(), UserManager.getSelected());
			UserManager.setClipboard(UserManager.getSelected().copyOf());
			this.trashAsset(UserManager.getSelected());
		}
	}
	
	public void deleteSelected(){
		if(UserManager.getSelected() != null) {
			UndoManager.get().storeDelete(UserManager.getSelected(), UserManager.getSelectedState(), UserManager.getSelected());
			this.trashAsset(UserManager.getSelected());
			UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
			UserManager.setSelected(null);
		}
	}

	public void pasteFromClipboard(){
		if(UserManager.getClipboard() != null) {
			Asset a = UserManager.getClipboard().copyOf();
			UndoManager.get().storePaste(a, UserManager.getSelectedState(), UserManager.getSelected());
			double pasteOffset = 10.0;
			if(UserManager.getSelected() != null) {
				a.setyPos(UserManager.getSelected().getyPos());
				a.setxPos(UserManager.getSelected().getxPos());
			}
			a.incrementXPos(pasteOffset);
			a.incrementYPos(pasteOffset);
			UserManager.setSelected(a);
			UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
			this.getAssets().add(a);
		}
	}
	
	public void createTextBox(double xPos, double yPos){
		TextBox a = new TextBox();
		a.setxPos(xPos);
		a.setyPos(yPos);
		UndoManager.get().storeObjectPlacement(a);
		UserManager.setSelected(a);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		this.getAssets().add(a);
	}
	
	public void pasteFromClipboard(double xPos, double yPos){
		if(UserManager.getClipboard() != null) {
			Asset a = UserManager.getClipboard().copyOf();
			UndoManager.get().storePaste(a, UserManager.getSelectedState(), UserManager.getSelected());
			a.setyPos(yPos);
			a.setxPos(xPos);
			UserManager.setSelected(a);
			UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
			this.getAssets().add(a);
		}
	}

	public void editSelectedTextBox() {
		EditTextDialog.createAndShow((TextBox) UserManager.getSelected());
	}
	
}
