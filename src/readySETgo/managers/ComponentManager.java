package readySETgo.managers;

import java.awt.Component;
import java.util.HashMap;

/**
 * 
 * Stores and provides references to components
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class ComponentManager {

	private HashMap<String, Component> components;
	
	private static ComponentManager manager = new ComponentManager();

	private ComponentManager() {
		this.components = new HashMap<String, Component>();
	}

	/**
	 * Registers a component to be provided by the manager
	 * @param s An identifier string which will be used to retrieve the component
	 * @param c The component
	 */
    public static void registerComp(String s, Component c) {
    	manager.components.put(s.toLowerCase(), c);
    }
    
    /**
     * Returns component registered with specified string
     * @param s The desired component's identifier string
     * @return A component
     */
    public static Component getComp(String s) {
    	return manager.components.get(s.toLowerCase());
    }
}