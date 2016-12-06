package readySETgo.managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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

/**
 * 
 * Manager for loading and saving files
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class FileManager {
	
	/**
	 * Saves specified Stage to specified File
	 * @param stage The Stage to save
	 * @param file The File to save to
	 */
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
        			Attr multiplier = doc.createAttribute("multiplier");
            		multiplier.setValue("" + ((TextBox) a).getFontScale());
            		asset.setAttributeNode(multiplier);
            		
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
        	JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error saving the stage to a file: \r\n" + e.getMessage(), "Error saving stage.", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads Stage from File
     * @param file The File to load from
     * @return The loaded Stage
     */
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
        			String imageRef = e.getAttribute("ref");
        			String name = e.getTextContent();
        			StageObject o = new StageObject(name, width, length, xpos, ypos, angle, imageRef);
        			stageAssets.add(o);
        		} else {
        			String text = e.getTextContent();
        			double multiplier = Double.parseDouble(e.getAttribute("multiplier"));
        			TextBox tb = new TextBox(width, length, xpos, ypos, angle, text, multiplier);
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
            
        	String path = file.getAbsolutePath();
    		stage.setFilePath(path);

    		UserManager.setSelectedState(UserManager.SelectedState.EMPTY);
    		UserManager.setSelected(null);
    		UndoManager.reset();
        	StageManager.registerStage(stage);
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error loading the stage from a file: \r\n" + ex.getMessage(), "Error loading stage.", JOptionPane.ERROR_MESSAGE);
           
        }
        return stage;
    }
    
    /**
     * Saves List of StageObjects to Objects file
     * @param list The List to save
     */
    public static void saveListOfObjects(ArrayList<StageObject> list) {
    	
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
    		JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error saving the list of objects: \r\n" + e.getMessage(), "Error saving objects.", JOptionPane.ERROR_MESSAGE);
    	}
    	
    }
    
    /**
     * Loads List of StageObjects from the Objects file
     * @return ArrayList of StageObjects
     */
    public static ArrayList<StageObject> loadListOfObjects() {
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
	    	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error loading the default objects xml: \r\n" + e.getMessage(), "Error loading objects.", JOptionPane.ERROR_MESSAGE);
		}
    	
    	return objects;
    }

    /**
     * Saves specified List of FlyRails to FlyRails file
     * @param list The List of FlyRails to save
     */
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
    		JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error saving the list of flyrails: \r\n" + e.getMessage(), "Error saving flyrails.", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    /**
     * Loads list of FlyRails from the FlyRails file
     * @return ArrayList of the FlyRails
     */
    public static ArrayList<FlyRail> loadListOfFlyRails(){
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
	    	
		} catch (Exception e){
			JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error loading the list of flyrails: \r\n" + e.getMessage(), "Error loading flyrails.", JOptionPane.ERROR_MESSAGE);
		}
    	
    	return rails;
    }

    /**
     * Attempts to save, prompting the user if needed
     * @return Whether Stage was saved
     */
    public static boolean attemptSaveSilently() {
    	Stage s = StageManager.getStage();
    	String path = s.getFilePath();
    	if(path != null) {
    		File f = new File(path);
    		FileManager.saveStage(s, f);
    		UndoManager.registerSave();
    		return true;
    	}
    	else {
    		return FileManager.displaySavePrompt();
    	}
    }
    
    /**
     * Displays save prompt to user
     * @return Whether stage was saved
     */
    public static boolean displaySavePrompt() {
    	JFileChooser menu = new JFileChooser();
    	menu.setFileFilter(new FileNameExtensionFilter("Stage Plan Files", "stg"));
    	int retCode = menu.showSaveDialog(ComponentManager.getComp("MainFrame"));
    	
    	if (retCode == JFileChooser.APPROVE_OPTION) {
    		File f = menu.getSelectedFile();
    		String path = f.getAbsolutePath();
    		if(!path.endsWith(".stg")) { path += ".stg"; }
    		f = new File(path);
    		Stage s = StageManager.getStage();
    		s.setFilePath(path);
    		FileManager.saveStage(s, f);
    		UndoManager.registerSave();
    		return true;
        }
    	return false;
    }
    
    /**
     * Displays open Stage file prompt
     */
    public static void displayOpenPrompt() {
    	JFileChooser menu = new JFileChooser();
    	menu.setFileFilter(new FileNameExtensionFilter("Stage Plan Files", "stg"));
    	int retCode = menu.showOpenDialog(ComponentManager.getComp("MainFrame"));
    	
    	if (retCode == JFileChooser.APPROVE_OPTION) {
    		Stage loaded = FileManager.loadStageFromFile(menu.getSelectedFile());
    		StagePanel sPanel = (StagePanel) ComponentManager.getComp("StagePanel");
    		sPanel.loadStage(loaded);
    		FlyRailPanel fPanel = (FlyRailPanel) ComponentManager.getComp("FlyRailPanel");
    		fPanel.loadFlyRails(loaded);
    		UndoManager.reset();
          }
    }    
    
    /**
     * Loads the image HashMap from file
     * @return The loaded HashMap
     */
    public static HashMap loadImageMap(){
    	HashMap map = new HashMap();
    	try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 	DocumentBuilder builder = factory.newDocumentBuilder();
		 	Document doc = builder.parse(new File("res/ImgMap.xml"));
		 	doc.getDocumentElement().normalize();
		 	
		 	NodeList entries = doc.getElementsByTagName("entries");
		 	for(int i = 0; i < entries.getLength(); i++) {
		 		Node n = entries.item(i);
		 		Element e = (Element) n;
			
		 		String hash = e.getAttribute("hash");
		 		String ref = e.getAttribute("ref");
		 		map.put(hash, ref);
		 	}             
    	}
    	catch (Exception e) { e.printStackTrace(); }
        return map;
    }
    
    /**
     * Saves given HashMap to file
     * @param _map The map to save
     */
    public static void saveImageMap(HashMap _map) {
    	try {
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.newDocument();
        	
        	Element root = doc.createElement("root");
        	Element entries = doc.createElement("entries");
        	
        	Set set = _map.entrySet();
            Iterator i = set.iterator();
            
            while(i.hasNext()) {
            	Map.Entry entry = (Map.Entry) i.next();
            	
        		Element xmlEntry = doc.createElement("entry");
        		
        		xmlEntry.setAttribute("hash", (String) (entry.getKey()));
        		xmlEntry.setAttribute("ref", (String) (entry.getValue()));
        		
        		entries.appendChild(xmlEntry);
        	}
        	
        	root.appendChild(entries);
        	doc.appendChild(root);
        	
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("res/ImgMap.xml"));
            transformer.transform(source, result);
    	}
    	catch (Exception e){ e.printStackTrace(); }
    }
    
    /**
     * Adds a StageObject to the Objects file
     * @param objName The object's name
     * @param objWidth The object's width
     * @param objLength The object's length
     * @param objImageRef The object's image ref
     */
    public static void addObjectToDefaults(String objName, double objWidth, double objLength, String objImageRef) {
    	ArrayList<StageObject> objects = FileManager.loadListOfObjects();
    	if(objImageRef != null && !objImageRef.equals("")) {
    		File original = new File(objImageRef);
    		
    		String fileHash = HashManager.computeFileHashString(original);
    		boolean fileAlreadyImported = HashManager.getMap().containsKey(fileHash);
    		if(fileAlreadyImported) {
    			String existingFilePath = (String) (HashManager.getMap().get(fileHash));
    			StageObject newObj = new StageObject(objName, objWidth, objLength, 0, 0, 0, existingFilePath);
            	objects.add(newObj);
    		}
    		else {
    			String newPath = "res/" + original.getName();
            	File ourCopy = new File(newPath);
            	int i = 0;
            	while(ourCopy.exists()) {
            		newPath = "res/" + i + original.getName();
                	ourCopy = new File(newPath);
                	i++;
            	}
            	try {
                	Files.copy(original.toPath(), ourCopy.toPath());
                	StageObject newObj = new StageObject(objName, objWidth, objLength, 0, 0, 0, newPath);
                	HashManager.getMap().put(fileHash, newPath);
                	FileManager.saveImageMap(HashManager.getMap());
                	objects.add(newObj);
            	}
            	catch(IOException e) {
            		e.printStackTrace();
            	}
    		}
    	}
    	else {
    		StageObject newObj = new StageObject(objName, objWidth, objLength, 0, 0, 0, "");
        	objects.add(newObj);
    	}
    	FileManager.saveListOfObjects(objects);
		ObjectPanel oPanel = (ObjectPanel) ComponentManager.getComp("ObjectPanel");
		oPanel.refresh();
    }
    
    /**
     * Removes specified StageObject from Objects file
     * @param obj The StageObject to remove
     */
    public static void removeObjectFromDefaults(StageObject obj) {
    	ArrayList<StageObject> objects = FileManager.loadListOfObjects();
    	objects.remove(obj);
    	FileManager.saveListOfObjects(objects);
    	ObjectPanel oPanel = (ObjectPanel) ComponentManager.getComp("ObjectPanel");
    	oPanel.refresh();    	
    }
    
    /**
     * Replaces given StageObject with a new one that will be created based on given params
     * @param obj The StageObject to remove
     * @param objName The new StageObject's name
     * @param objWidth The new StageObject's width
     * @param objLength The new StageObject's Length
     * @param objImageRef The new StageObject's image ref
     */
    public static void replaceObjectInDefaults(StageObject obj, String objName, double objWidth, double objLength, String objImageRef) {
    	FileManager.removeObjectFromDefaults(obj);
    	FileManager.addObjectToDefaults(objName, objWidth, objLength, objImageRef);
    }
}
