package readySETgo.managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import readySETgo.components.panels.FlyRailPanel;
import readySETgo.components.panels.ObjectPanel;
import readySETgo.components.panels.StagePanel;
import readySETgo.models.FlyRail;
import readySETgo.models.Stage;
import readySETgo.models.assets.Asset;
import readySETgo.models.assets.StageObject;
import readySETgo.models.assets.TextBox;

//TODO Make XML File paths named constants

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class FileManager {
    public static void saveStage(Stage stage, File file) {
        try {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.newDocument();
        	
        	Element root = doc.createElement("root");
        	
        	Element assets = doc.createElement("assets");
        	
        	for(Asset a: stage.getAssets()){
        		Element asset = doc.createElement("asset");
        		
        		
        		Attr xpos = doc.createAttribute("x");
        		xpos.setValue("" + a.getxPos());
        		Attr ypos = doc.createAttribute("y");
        		ypos.setValue("" + a.getyPos());
        		Attr width = doc.createAttribute("width");
        		width.setValue("" + a.getPhysicalWidth());
        		Attr length = doc.createAttribute("length");
        		length.setValue("" + a.getPhysicalLength());
        		Attr angle = doc.createAttribute("angle");
        		angle.setValue("" + a.getAngle());
        		
        		Attr type = doc.createAttribute("type");
        		//Attr name = doc.createAttribute("name");
        		if(a instanceof StageObject){
        			type.setValue("StageObject");
        			Attr imageRef = doc.createAttribute("ref");
        			imageRef.setValue(((StageObject) a).getImageRef());
        			asset.setAttributeNode(imageRef);
        			asset.appendChild(doc.createTextNode(((StageObject) a).getName()));

        		} else {
        			type.setValue("TextBox");
        			asset.appendChild(doc.createTextNode(((TextBox) a).getText()));
        		}
        		
        		asset.setAttributeNode(type);
        		asset.setAttributeNode(xpos);
        		asset.setAttributeNode(ypos);
        		asset.setAttributeNode(width);
        		asset.setAttributeNode(length);
        		asset.setAttributeNode(angle);
        		assets.appendChild(asset);
        	}
        	
        	Element rails = doc.createElement("rails");
        	
        	for(FlyRail r: stage.getFlyRails()){
        		Element rail = doc.createElement("rail");
        		
        		rail.setAttribute("w", "" + r.getW());
        		rail.setAttribute("l", "" + r.getL());
        		rail.setAttribute("x", "" + r.getPosx());
        		rail.setAttribute("y", "" + r.getPosy());
        		rail.setAttribute("ref", r.getImageRef());
        		rail.setAttribute("isFlownIn", "" + r.isFlownIn());
        		rail.appendChild(doc.createTextNode(r.getName()));
        		
        		rails.appendChild(rail);
        	}
        	
            
        	root.appendChild(assets);
        	root.appendChild(rails);
        	
        	doc.appendChild(root);
        	 // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage loadStageFromFile(File file) {
        Stage stage = null;
        try {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.parse(file);
        	doc.getDocumentElement().normalize();
        	
        	ArrayList<Asset> stageAssets = new ArrayList<Asset>();
        	
        	NodeList assets = doc.getElementsByTagName("asset");
        	for(int i = 0; i < assets.getLength(); i++){
        		Node n = assets.item(i);
        		Element e = (Element) n;
        		
        		String type = e.getAttribute("type");
        		double xpos = Double.parseDouble(e.getAttribute("x"));
        		double ypos = Double.parseDouble(e.getAttribute("y"));
        		double width = Double.parseDouble(e.getAttribute("width"));
        		double length = Double.parseDouble(e.getAttribute("length"));
        		double angle = Double.parseDouble(e.getAttribute("angle"));
        		
        		if(type.equals("StageObject")){
        			String imageRef = e.getAttribute("imageRef");
        			String name = e.getTextContent();
        			StageObject o = new StageObject(name, width, length, xpos, ypos, angle, imageRef);
        			stageAssets.add(o);
        		} else {
        			String text = e.getTextContent();
        			TextBox tb = new TextBox(width, length, xpos, ypos, angle, text);
        			stageAssets.add(tb);
        		}
        	}
        	
        	ArrayList<FlyRail> flyRails = new ArrayList<FlyRail>();
        	
        	NodeList rails = doc.getElementsByTagName("rail");
        	for(int i = 0; i < rails.getLength(); i++){
        		Node n = rails.item(i);
        		Element e = (Element) n;
        		
        		String name = e.getTextContent();
        		double w = Double.parseDouble(e.getAttribute("w"));
        		double l = Double.parseDouble(e.getAttribute("l"));
        		double xpos = Double.parseDouble(e.getAttribute("x"));
        		double ypos = Double.parseDouble(e.getAttribute("y"));
        		boolean isFlownIn = Boolean.parseBoolean(e.getAttribute("isFlownIn"));
        		String imageRef = e.getAttribute("ref");
        		
        		FlyRail f = new FlyRail(name, w, l, xpos, ypos, isFlownIn, imageRef);
        		flyRails.add(f);
        	}
        	
        	
        	stage = new Stage();
        	stage.setAssets(stageAssets);
        	stage.setFlyRails(flyRails);
            
        } catch (Exception ex) {
            ex.printStackTrace();
           
        }
        return stage;
    }
    
    public static void saveListOfObjects(ArrayList<StageObject> list){
    	
    	try {
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.newDocument();
        	
        	Element root = doc.createElement("root");
        	
        	Element objects = doc.createElement("objects");
        	
        	for(StageObject a: list){
        		Element obj = doc.createElement("object");
        		
        		obj.setAttribute("width", "" + a.getPhysicalWidth());
        		obj.setAttribute("length", "" + a.getPhysicalLength());
        		obj.setAttribute("ref", a.getImageRef());
        		obj.appendChild(doc.createTextNode(a.getName()));
        		
        		objects.appendChild(obj);
        	}
        	
        	root.appendChild(objects);
        	doc.appendChild(root);
        	
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("res/Objects.xml"));
            transformer.transform(source, result);
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public static ArrayList<StageObject> getListOfObjects(){
    	ArrayList<StageObject> objects = null;
    	
    	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	Document doc =  builder.parse(new File("res/Objects.xml"));
	    	doc.getDocumentElement().normalize();
	    	
	    	
	    	objects = new ArrayList<StageObject>();
	    	
	    	NodeList n = doc.getElementsByTagName("object");
	    	for(int i = 0; i < n.getLength(); i++){
	    		Element e = (Element) n.item(i);
	    		
	    		double width = Double.parseDouble(e.getAttribute("width"));
	    		double length = Double.parseDouble(e.getAttribute("length"));
	    		String ref = e.getAttribute("ref");
	    		String name = e.getTextContent();
	    		
	    		StageObject o = new StageObject(name, width, length, 0, 0, 0, ref);
	    		objects.add(o);
	    	}	    	
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return objects;
    }

    public static void saveListOfFlyRails(ArrayList<FlyRail> list){
    	
    	try {
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.newDocument();
        	
        	Element root = doc.createElement("root");
        	
        	Element rails = doc.createElement("rails");
        	
        	for(FlyRail a: list){
        		Element obj = doc.createElement("rail");
        		
        		obj.setAttribute("name", a.getName());
        		obj.setAttribute("w", "" + a.getW());
        		obj.setAttribute("l", "" + a.getL());
        		obj.setAttribute("posx", "" + a.getPosx());
        		obj.setAttribute("posy", "" + a.getPosy());
        		obj.setAttribute("flownin", "" + a.isFlownIn());
        		obj.setAttribute("ref", a.getImageRef());
        		
        		rails.appendChild(obj);
        	}
        	
        	root.appendChild(rails);
        	doc.appendChild(root);
        	
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("res/Rails.xml"));
            transformer.transform(source, result);
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    public static ArrayList<FlyRail> getListOfFlyRails(){
    	ArrayList<FlyRail> rails = null;
    	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	Document doc =  builder.parse(new File("res/Rails.xml"));
	    	doc.getDocumentElement().normalize();
	    	
	    	rails = new ArrayList<FlyRail>();
	    	
	    	NodeList n = doc.getElementsByTagName("rail");
	    	for(int i = 0; i < n.getLength(); i++){
	    		Element e = (Element) n.item(i);

	    		String name = e.getAttribute("name");
	    		double w = Double.parseDouble(e.getAttribute("w"));
	    		double l = Double.parseDouble(e.getAttribute("l"));
	    		double posX = Double.parseDouble(e.getAttribute("posx"));
	    		double posY = Double.parseDouble(e.getAttribute("posy"));
	    		Boolean isFlownIn =  Boolean.parseBoolean(e.getAttribute("flownin"));
	    		String ref = e.getAttribute("ref");
	    		
	    		FlyRail f = new FlyRail(name, w, l, posX, posY, isFlownIn, ref);
	    		rails.add(f);
	    	}	    	
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return rails;
    }

    public static boolean attemptSaveSilently() {
    	Stage s = StageManager.get().getStage();
    	String path = s.getFilePath();
    	if(path != null) {
    		File f = new File(path);
    		FileManager.saveStage(s, f);
    		return true;
    	}
    	else {
    		return FileManager.displaySavePrompt();
    	}
    }
    
    
    public static boolean displaySavePrompt() {
    	JFileChooser menu = new JFileChooser();
    	int retCode = menu.showSaveDialog(ComponentManager.getComp("MainFrame"));
    	
    	if (retCode == JFileChooser.APPROVE_OPTION) {
    		File f = menu.getSelectedFile();
    		String path = f.getAbsolutePath();
    		Stage s = StageManager.get().getStage();
    		s.setFilePath(path);
    		FileManager.saveStage(s, f);
    		UndoManager.get().registerSave();
    		return true;
        }
    	return false;
    }
    
    public static void displayOpenPrompt() {
    	JFileChooser menu = new JFileChooser();
    	int retCode = menu.showOpenDialog(ComponentManager.getComp("MainFrame"));
    	
    	if (retCode == JFileChooser.APPROVE_OPTION) {
    		Stage loaded = FileManager.loadStageFromFile(menu.getSelectedFile());
    		StagePanel sPanel = (StagePanel) ComponentManager.getComp("StagePanel");
    		sPanel.loadStage(loaded);
    		FlyRailPanel fPanel = (FlyRailPanel) ComponentManager.getComp("FlyRailPanel");
    		fPanel.loadFlyRails(loaded);
    		ObjectPanel oPanel = (ObjectPanel) ComponentManager.getComp("ObjectPanel");
    		oPanel.loadObjects(loaded);
    		UndoManager.get().reset();
          }
    }    
    
    public static void addObjectToDefaults(String objName, double objWidth, double objLength, String objImageRef) {
    	ArrayList<StageObject> objects = FileManager.getListOfObjects();
    	if(objImageRef != null && !objImageRef.equals("")) {
    		File original = new File(objImageRef);
        	String newPath = "res/" + original.getName();
        	File ourCopy = new File(newPath);
        	try {
            	Files.copy(original.toPath(), ourCopy.toPath());
            	StageObject newObj = new StageObject(objName, objWidth, objLength, 0, 0, 0, newPath);
            	objects.add(newObj);
        	}
        	catch(IOException e) {
        		System.out.println("TODO - Add error handling code");
        	}
    	}
    	else {
    		StageObject newObj = new StageObject(objName, objWidth, objLength, 0, 0, 0, "");
        	objects.add(newObj);
    	}
    	FileManager.saveListOfObjects(objects);
		ObjectPanel oPanel = (ObjectPanel) ComponentManager.getComp("ObjectPanel");
		oPanel.loadObjects(StageManager.get().getStage());
    }
    
    public static void removeObjectFromDefaults(StageObject obj) {
    	ArrayList<StageObject> objects = FileManager.getListOfObjects();
    	objects.remove(obj);
    	FileManager.saveListOfObjects(objects);
    	ObjectPanel oPanel = (ObjectPanel) ComponentManager.getComp("ObjectPanel");
    	oPanel.loadObjects(StageManager.get().getStage());    	
    }
    
    public static void replaceObjectInDefaults(StageObject obj, String objName, double objWidth, double objLength, String objImageRef) {
    	FileManager.removeObjectFromDefaults(obj);
    	FileManager.addObjectToDefaults(objName, objWidth, objLength, objImageRef);
    }
}
