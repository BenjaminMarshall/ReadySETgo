package backend.models;

import java.awt.BasicStroke;
import java.awt.Color;
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

import backend.FileManager;
import readySETgo.User;

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
    
    public Stage(){
    	this.name = "New Stage";
    	this.assets = new ArrayList<Asset>();
    	this.flyRails =  FileManager.getListOfFlyRails();
    	try {
    	this.stageImage = ImageIO.read(new File("res/stage.png"));
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	this.width = 924;
    	this.length = 552; //TODO
    }
    
    public void draw(Graphics g){
    	g.drawImage(stageImage, 0, 0, (int) width, (int) length, null);
    	for(Asset a: assets){
    		a.draw(g, a.getxPos(), a.getyPos());
    		if(User.getSelectedState() == User.SelectedState.SELECTED && User.getSelected() == a) {
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
    			a.draw(g, a.getxPos(), a.getyPos());
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
		for(Asset a: assets){
			if(e.getX() > a.getxPos() && e.getX() < a.getxPos() + a.getPhysicalWidth()){
				if(e.getY() > a.getyPos() && e.getY() < a.getyPos() + a.getPhysicalLength()){
					return a;
				}
			}
		}
		return null;
	}
}
