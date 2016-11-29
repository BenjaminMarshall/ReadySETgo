package backend;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JFrame;

import readySETgo.panels.StagePanel;

public class ComponentManager {

	private HashMap<String, Component> components;
	
	private static ComponentManager manager = new ComponentManager();

	public ComponentManager() {
		this.components = new HashMap<String, Component>();
	}

    public static void registerComp(String s, Component c) {
    	manager.components.put(s.toLowerCase(), c);
    }
    
    public static Component getComp(String s) {
    	return manager.components.get(s.toLowerCase());
    }
}