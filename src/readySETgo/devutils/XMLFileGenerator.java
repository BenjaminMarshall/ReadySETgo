package readySETgo.devutils;

import java.util.ArrayList;

import readySETgo.managers.FileManager;
import readySETgo.models.FlyRail;
import readySETgo.models.assets.StageObject;

public class XMLFileGenerator {

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
			objects = FileManager.getListOfObjects();
			System.out.println("Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
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
			rails = FileManager.getListOfFlyRails();
			System.out.println("Loaded.");
			
			for(FlyRail f: rails){
				System.out.println(f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args){
		generateFlyRailFile();
		generateObjectFile();
	}
}
