package test;

import java.io.IOException;
import java.util.ArrayList;

import backend.FileManager;
import backend.models.FlyRail;
import backend.models.StageObject;

public class FileManagerTest {

	
	public static void objectTest() {
		ArrayList<StageObject> objects = new ArrayList<StageObject>();
		
		
		try {
			StageObject o1 = new StageObject("Chair", 12.4, 12.5692, 0, 0, 0, "res/chair.png");
			StageObject o2 = new StageObject("Table", 100, 1000000, 0, 0, 0, "res/chair.png");
			StageObject o3 = new StageObject("Other Thing", 2.56, 103, 0, 0 , 0, "res/chair.png");
			
			objects.add(o1);
			objects.add(o2);
			objects.add(o3);
			
			System.out.println("Saving...");
			FileManager.saveListOfObjects(objects);
			System.out.println("Saved.");
			
			System.out.println("Loading...");
			objects = FileManager.getListOfObjects();
			System.out.println("Loaded.");
			
			for(StageObject o: objects){
				System.out.println(o.getName() + ": " + o.getPhysicalWidth() + ", " + o.getPhysicalLength());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void flyRailTest() {
		ArrayList<FlyRail> rails = new ArrayList<FlyRail>();
		
		
		try {
			
			FlyRail f1 = new FlyRail("Curtain 1", 0.0, 0.0, false, "res/test.png");
			FlyRail f2 = new FlyRail("Curtain 2", 0.0, 0.0, true, "res/test.png");
			FlyRail f3 = new FlyRail("Curtain 3", 0.0, 0.0, false, "res/test.png");
			FlyRail f4 = new FlyRail("Curtain 4", 0.0, 0.0, true, "res/test.png");
			FlyRail f5 = new FlyRail("Curtain 5", 0.0, 0.0, false, "res/test.png");

			rails.add(f1);
			rails.add(f2);
			rails.add(f3);
			rails.add(f4);
			rails.add(f5);
			
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
