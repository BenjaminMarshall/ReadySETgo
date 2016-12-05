package readySETgo.models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import readySETgo.components.panels.StagePanel;
import readySETgo.dialogs.EditLabelDialog;
import readySETgo.dialogs.RotateDialog;
import readySETgo.dialogs.RotateSelectionErrorDialog;
import readySETgo.managers.ComponentManager;
import readySETgo.managers.FileManager;
import readySETgo.managers.PrintManager;
import readySETgo.managers.UndoManager;
import readySETgo.managers.UserManager;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.TextBox;

/**
 * 
 * Represents a stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class Stage implements Printable {
    private String name;
    private List<Asset> assets;
    private List<FlyRail> flyRails;
    private Image stageImage;
    private double width;
    private double length;
        
    private String filePath;
    
    /**
     * Default constructor
     */
    public Stage(){
    	this.name = "New Stage";
    	PrintManager.register(this);
    	this.assets = new ArrayList<Asset>();
    	this.flyRails =  FileManager.loadListOfFlyRails();
    	try {
    	this.stageImage = ImageIO.read(new File("res/stage.png"));
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	this.width = 77 * 12; // Width in Inches
    	this.length = 45 * 12; // Len in inches
    }
    
    /**
     * Returns the scale relative to its containing panel
     * @return The scale
     */
    public double getScale() {
    	StagePanel stagePanel = (StagePanel) ComponentManager.getComp("StagePanel");
    	double scale = (stagePanel.getWidth() / this.width);
    	return scale;
    }
    
    /**
     * Sets the stage's file path
     * @param path The path to set
     */
    public void setFilePath(String path) {
    	this.filePath = path;
    }
    
    /**
     * Returns the stage's file path
     * @return The file path
     */
    public String getFilePath() {
    	return this.filePath;
    }
    
    /**
     * Draws the stage
     * @param g The Graphics object to draw with
     */
    public void draw(Graphics g){
    	StagePanel stagePanel = (StagePanel) ComponentManager.getComp("StagePanel");
    	double scale = this.getScale();
    	g.drawImage(stageImage, 0, 0, (int) (width*scale), (int) (length*scale), null);
    	for(Asset a : assets) {
    		a.draw(g, scale, UserManager.getSelectedState() == UserManager.SelectedState.SELECTED && UserManager.getSelected() == a);
    	}
    	for(FlyRail f: flyRails){
    		if(f.isFlownIn()) {
    			Asset a = f.getDrawable();
    			a.draw(g, scale, false);
    		}
    	}
    }
    
    /**
     * Draws the stage
     * @param g The Graphics object to draw with
     */
    public void printDraw(Graphics g, double printScale){
    	StagePanel stagePanel = (StagePanel) ComponentManager.getComp("StagePanel");
    	double scale = printScale;
    	g.drawImage(stageImage, 0, 0, (int) (width*scale), (int) (length*scale), null);
    	for(Asset a : assets) {
    		a.draw(g, scale, UserManager.getSelectedState() == UserManager.SelectedState.SELECTED && UserManager.getSelected() == a);
    	}
    	for(FlyRail f: flyRails){
    		if(f.isFlownIn()) {
    			Asset a = f.getDrawable();
    			a.draw(g, scale, false);
    		}
    	}
    }
    
    
    /**
     * Return the stage's name
     * @return The stage's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the stage's name
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Stage's list of Assets
     * @return The list of Assets
     */
    public List<Asset> getAssets() {
        return assets;
    }

    /**
     * Removes an Asset from the Stage
     * @param a The Asset to remove
     */
    public void trashAsset(Asset a) {
    	this.assets.remove(a);
    }
    
    /**
     * Sets the stage's Asset list
     * @param assets The list to set
     */
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    /**
     * Returns the Stage's list of FlyRails
     * @return The list of FlyRails
     */
	public List<FlyRail> getFlyRails() {
		return this.flyRails;
	}

	/**
	 * Sets the Stage's list of FlyRails
	 * @param flyRails The list to set
	 */
	public void setFlyRails(List<FlyRail> flyRails) {
		this.flyRails = flyRails;
	}

	/**
	 * Returns the asset that the MouseEvent is on top of, or null
	 * @param e The MouseEvent to check
	 * @return The Asset or null
	 */
	public Asset eventOnObject(MouseEvent e) {
		try{
		for(Asset a : assets) {
			double scale = this.getScale();
			
			double x = a.getxPos() * scale;
			double y = a.getyPos() * scale;
			double w = a.getPhysicalWidth() * scale;
			double l = a.getPhysicalLength() * scale;
			
			if(a instanceof TextBox){
				//Apply font scaling
				w *= ((TextBox) a).getFontScale();
				l *= ((TextBox) a).getFontScale();
			}
			Rectangle r = new Rectangle((int)x,(int) y,(int) w,(int) l);
			AffineTransform rotateTransform = new AffineTransform();			
			rotateTransform.rotate(Math.toRadians(a.getAngle()), (int) (x), (int) (y));
			Point2D dest = new Point();
			rotateTransform.inverseTransform(e.getPoint(), dest);

			if(r.contains(dest)) {
				return a;
			}

		
		}}catch (Exception ex){}
		return null;
	}
	
	/**
	 * Copies the selected Asset to the clipboard
	 */
	public void copySelected(){
		if(UserManager.getSelected() != null) {
			Asset copied = UserManager.getSelected().copyOf();
			UserManager.setClipboard(copied);
		}
	}
	
	/**
	 * Cuts the selected Asset to the clipboard
	 */
	public void cutSelected(){
		if(UserManager.getSelected() != null) {
			UndoManager.storeCut(UserManager.getSelected(), UserManager.getSelectedState(), UserManager.getSelected());
			UserManager.setClipboard(UserManager.getSelected().copyOf());
			this.trashAsset(UserManager.getSelected());
		}
	}
	
	/**
	 * Deletes the selected Asset
	 */
	public void deleteSelected(){
		if(UserManager.getSelected() != null) {
			UndoManager.storeDelete(UserManager.getSelected(), UserManager.getSelectedState(), UserManager.getSelected());
			this.trashAsset(UserManager.getSelected());
			UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
			UserManager.setSelected(null);
		}
	}

	/**
	 * Pastes the Asset in the clipboard to the Stage
	 */
	public void pasteFromClipboard(){
		if(UserManager.getClipboard() != null) {
			Asset a = UserManager.getClipboard().copyOf();
			UndoManager.storePaste(a, UserManager.getSelectedState(), UserManager.getSelected());
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
	
	/**
	 * Creates a new TextBox on the Stage
	 * @param xPos The x pos
	 * @param yPos The y pos
	 */
	public void createTextBox(double xPos, double yPos){
		TextBox a = new TextBox();
		a.setxPos(xPos / getScale());
		a.setyPos(yPos / getScale());
		UndoManager.storeObjectPlacement(a);
		UserManager.setSelected(a);
		UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
		this.getAssets().add(a);
	}
	
	/**
	 * Pastes the Asset in the clipboard to a specified position on the Stage
	 * @param xPos The x pos
	 * @param yPos The y pos
	 */
	public void pasteFromClipboard(double xPos, double yPos) {
		if(UserManager.getClipboard() != null) {
			Asset a = UserManager.getClipboard().copyOf();
			UndoManager.storePaste(a, UserManager.getSelectedState(), UserManager.getSelected());
			a.setyPos(yPos);
			a.setxPos(xPos);
			
			UserManager.setSelected(a);
			UserManager.setSelectedState(UserManager.SelectedState.SELECTED);
			this.getAssets().add(a);
		}
	}

	
    /**
     * Prints the stage
     * @param graphics The Graphics object used for printing
     * @param pageFormat The printing PageFormat
     * @param pageIndex The printing page index
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

		if (pageIndex > 0){ return Printable.NO_SUCH_PAGE; }
		
		Graphics2D g2 = (Graphics2D) graphics;
		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		
		AffineTransform old = g2.getTransform();
		AffineTransform rotateTransform = new AffineTransform();
		rotateTransform.translate(pageFormat.getImageableWidth(), 0);
		rotateTransform.rotate(Math.toRadians(90));
		g2.transform(rotateTransform);
		
		double printScale = this.width / (pageFormat.getImageableHeight() * 1.5 );
		
		this.printDraw(g2, printScale);
		
		g2.transform(old);
		return Printable.PAGE_EXISTS;
    }
	
	
	/**
	 * Show the dialog to edit the selected TextBox
	 */
	public void editSelectedTextBox() {
		EditLabelDialog.createAndShow((TextBox) UserManager.getSelected());
	}

	/**
	 * Show the dialog to rotate the selected Asset
	 */
	public void rotateSelectedAsset() {
		if(UserManager.getSelectedState() == UserManager.SelectedState.SELECTED && UserManager.getSelected() != null) {
			RotateDialog.createAndShow(UserManager.getSelected());
		}
		else {
			RotateSelectionErrorDialog.createAndShow();
		}
	}
}
