package test;

import java.util.ArrayList;

import readySETgo.managers.FileManager;
import readySETgo.models.FlyRail;
import readySETgo.models.assets.StageObject;

public class FileManagerTest {

	
	public static void objectTest() {
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
	
	public static void flyRailTest() {
		ArrayList<FlyRail> rails = new ArrayList<FlyRail>();
		
		
		try {

			for(int i = 0; i < 4; i++) {
				rails.add(new FlyRail("Curtain "+(i+1), 640, 12, 115, (i*100),  (i % 2 == 0), "res/curtain.png"));
			}
			
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
		flyRailTest();
	}
}
