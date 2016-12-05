package readySETgo.devutils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import readySETgo.managers.FileManager;
import readySETgo.managers.HashManager;
import readySETgo.models.FlyRail;
import readySETgo.models.assets.StageObject;

/**
 * 
 * Development class used for generating XML data files
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class XMLFileGenerator {

	/**
	 * Creates the Objects data file
	 */
	public static void generateObjectFile() {
		ArrayList<StageObject> objects = new ArrayList<StageObject>();
		
		
		try {
			StageObject podium = new StageObject("Podium", 0, 0, 0, 0, 0, "");
			StageObject table = new StageObject("Table", 0, 0, 0, 0, 0, "");
			StageObject chair = new StageObject("Chair", 0, 0, 0, 0, 0, "res/chair.png");
			StageObject risers = new StageObject("Stage Risers", 0, 0, 0, 0, 0, "");
			StageObject taperSpeaker = new StageObject("Tapered Speaker", 0, 0, 0, 0, 0, "");
			StageObject tallSpeaker = new StageObject("Tall Square Speaker", 0, 0, 0, 0, 0, "");
			StageObject tinySpeaker = new StageObject("Tiny Speaker", 0, 0, 0, 0, 0, "");
			
			StageObject piano = new StageObject("Piano", 0, 0, 0, 0, 0, "res/piano.png");
			StageObject micStand = new StageObject("Mic Stand", 0, 0, 0, 0, 0, "res/mic_stand.png");
			
			
			podium.setPhysicalWidth(3, 2);
			podium.setPhysicalLength(2, 2);
				
			table.setPhysicalWidth(6, 0);
			table.setPhysicalLength(2, 7);
			
			chair.setPhysicalWidth(1, 9);
			chair.setPhysicalLength(1, 5);
			
			risers.setPhysicalWidth(8, 0);
			risers.setPhysicalLength(4, 0);
			
			taperSpeaker.setPhysicalWidth(2, 0);
			taperSpeaker.setPhysicalLength(2, 0);
			
			tallSpeaker.setPhysicalWidth(1, 11);
			tallSpeaker.setPhysicalLength(2, 2);
			
			tinySpeaker.setPhysicalWidth(1, 0);
			tinySpeaker.setPhysicalLength(1, 9);
			
			piano.setPhysicalWidth(5, 0);
			piano.setPhysicalLength(8, 6);
			
			micStand.setPhysicalWidth(2, 5);
			micStand.setPhysicalLength(2, 5);
			
			
			objects.add(podium);
			objects.add(table);
			objects.add(chair);
			objects.add(risers);
			objects.add(taperSpeaker);
			objects.add(tallSpeaker);
			objects.add(tinySpeaker);
			objects.add(micStand);
			objects.add(piano);
			
			
			System.out.println("Saving...");
			FileManager.saveListOfObjects(objects);
			System.out.println("Saved.");
			
			System.out.println("Loading...");
			objects = FileManager.loadListOfObjects();
			System.out.println("Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Creates the imageMap file used for hashing imported images
	 */
	public static void generateImageMapFile() {
		String[] imageRefs = {"res/black.png",
							  "res/chair.png",
							  "res/curtain.png",
							  "res/legs.png",
							  "res/logo.png",
							  "res/stage.png",
							  "res/stripes.png",
							  "res/no.png",
							  "res/mic_stand.png",
							  "res/piano.png"};
		
		HashMap map = new HashMap();
		
		for(String ref : imageRefs) {
			String hash = HashManager.computeFileHashString(new File(ref));
			map.put(hash, ref);
		}

		FileManager.saveImageMap(map);
	}
	
	/**
	 * Creates the Flyrail data fiole
	 */
	public static void generateFlyRailFile() {
		ArrayList<FlyRail> rails = new ArrayList<FlyRail>();
		
		
		try {
//			w l x y
			
			int curtainXoffset = 160;
			int legsXoffset = 95;
			int elecXoffset = 90;
			
			rails.add(new FlyRail("Cyc", 604, 5, curtainXoffset, 10,  false, "res/black.png"));
			rails.add(new FlyRail("Upstage Traveler", 604, 12, curtainXoffset, 20,  false, "res/curtain.png"));
			rails.add(new FlyRail("4th Legs", 720, 12, legsXoffset, 47,  false, "res/legs.png"));
			rails.add(new FlyRail("5th Electric", 742, 2, elecXoffset, 62,  false, "res/black.png"));
			rails.add(new FlyRail("4th Electric", 742, 2, elecXoffset, 80,  false, "res/black.png"));
			rails.add(new FlyRail("3rd Legs", 720, 12, legsXoffset, 105,  false, "res/legs.png"));
			rails.add(new FlyRail("3rd Electric", 742, 2, elecXoffset, 120,  false, "res/black.png"));
			rails.add(new FlyRail("2nd Legs", 720, 12, legsXoffset, 140,  false, "res/legs.png"));
			rails.add(new FlyRail("Mid Traveler", 604, 12, curtainXoffset, 160,  false, "res/curtain.png"));
			rails.add(new FlyRail("Screen", 256, 5, 315, 175,  false, "res/black.png"));
			rails.add(new FlyRail("Banners", 458, 5, 215, 175,  false, "res/banners.png"));
			rails.add(new FlyRail("2nd Electric", 742, 2, elecXoffset, 190,  false, "res/black.png"));
			rails.add(new FlyRail("1st Legs", 720, 12, legsXoffset, 200,  false, "res/legs.png"));
			rails.add(new FlyRail("1st Electric", 742, 2, elecXoffset, 230,  false, "res/black.png"));
			rails.add(new FlyRail("Main Curtain", 604, 12, curtainXoffset, 320,  false, "res/curtain.png"));

			
			
			System.out.println("Saving...");
			FileManager.saveListOfFlyRails(rails);
			System.out.println("Saved.");
			
			System.out.println("Loading...");
			rails = FileManager.loadListOfFlyRails();
			System.out.println("Loaded.");
			
			for(FlyRail f: rails){
				System.out.println(f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		generateFlyRailFile();
		generateObjectFile();
		generateImageMapFile();
	}
}
