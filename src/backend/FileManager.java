package backend;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import backend.models.Asset;
import backend.models.Stage;
import backend.models.StageObject;
import backend.models.TextBox;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class FileManager {
    public static void saveDocument(Stage stage, File file) {
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
        	
        	//TODO same thing with rails
            
        	root.appendChild(assets);
        	root.appendChild(rails);
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

    public static Stage loadDocumentFromFile(File file) {
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
        			StageObject o = new StageObject(width, length, xpos, ypos, angle, name, imageRef);
        			stageAssets.add(o);
        		} else {
        			String text = e.getTextContent();
        			TextBox tb = new TextBox(width, length, xpos, ypos, angle, text);
        			stageAssets.add(tb);
        		}
        	}
        	
        	//TODO same for flyrails
        	
        	
        	stage = new Stage();
        	stage.setAssets(stageAssets);
        	
            
        } catch (Exception ex) {
            ex.printStackTrace();
           
        }
        return stage;
    }
}
