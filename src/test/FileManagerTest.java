package test;

import java.io.IOException;
import java.util.ArrayList;

import backend.FileManager;
import backend.models.StageObject;

public class FileManagerTest {

	public static void main(String[] args){
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
}
