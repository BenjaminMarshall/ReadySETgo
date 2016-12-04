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
			
			
			
			objects.add(podium);
			objects.add(table);
			objects.add(chair);
			objects.add(risers);
			objects.add(taperSpeaker);
			objects.add(tallSpeaker);
			objects.add(tinySpeaker);
		
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
							  "res/no.png"};
		
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
			rails.add(new FlyRail("Cyc", 640, 5, 115, 10,  false, "res/black.png"));
			rails.add(new FlyRail("Upstage Traveler", 660, 12, 115, 20,  false, "res/curtain.png"));
			rails.add(new FlyRail("4th Legs", 840, 12, 25, 50,  false, "res/legs.png"));
			rails.add(new FlyRail("5th Electric", 640, 2, 115, 60,  false, "res/black.png"));
			rails.add(new FlyRail("4th Electric", 640, 2, 115, 80,  false, "res/black.png"));
			rails.add(new FlyRail("3rd Legs", 840, 12, 25, 105,  false, "res/legs.png"));
			rails.add(new FlyRail("3rd Electric", 640, 2, 115, 120,  false, "res/black.png"));
			rails.add(new FlyRail("2nd Legs", 840, 12, 25, 140,  false, "res/legs.png"));
			rails.add(new FlyRail("Mid Traveler", 660, 12, 115, 160,  false, "res/curtain.png"));
			rails.add(new FlyRail("Screen", 200, 5, 315, 170,  false, "res/black.png"));
			//rails.add(new FlyRail("Banners", 640, 12, 115, 170,  false, "res/curtain.png"));
			rails.add(new FlyRail("2nd Electric", 640, 2, 115, 190,  false, "res/black.png"));
			rails.add(new FlyRail("1st Legs", 840, 12, 25, 200,  false, "res/legs.png"));
			rails.add(new FlyRail("1st Electric", 640, 2, 115, 230,  false, "res/black.png"));
			rails.add(new FlyRail("Main Curtain", 660, 12, 115, 320,  false, "res/curtain.png"));

			
			
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
