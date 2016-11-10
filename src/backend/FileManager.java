package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

import backend.models.Asset;
import backend.models.FlyRail;
import backend.models.Stage;
import backend.models.StageObject;
import backend.models.TextBox;

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
        		double xpos = Double.parseDouble(e.getAttribute("x"));
        		double ypos = Double.parseDouble(e.getAttribute("y"));
        		boolean isFlownIn = Boolean.parseBoolean(e.getAttribute("isFlownIn"));
        		String imageRef = e.getAttribute("ref");
        		
        		FlyRail f = new FlyRail(name, xpos, ypos, isFlownIn, imageRef);
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
}
