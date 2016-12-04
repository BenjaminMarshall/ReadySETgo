package readySETgo.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Manager for creating file hashes, used in detecting duplicate images before import
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class HashManager {

	private HashMap imageHashMap;
	private static HashManager manager = new HashManager();
	
	private HashManager() {
		imageHashMap = FileManager.loadImageMap();
	}
	
	/**
	 * Returns the manager's HashMap
	 * @return The manager's HashMap
	 */
	public static HashMap getMap() {
		return manager.imageHashMap;
	}
	
	/**
	 * Computes the hash string for a given file
	 * @param f The File to hash
	 * @return The File's hash string
	 */
    public static String computeFileHashString(File f) {
    	String result = "Error";

    	try {
	        String filePath = f.getPath();
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        FileInputStream stream = new FileInputStream(filePath);
	        byte[] buffer = new byte[1024];
	        int offset = 0;
	        int len = stream.read(buffer);
	        // Compute hash
	        while (len != -1) {
	        	md.update(buffer, offset, len); 
	        	len = stream.read(buffer);
	        }
	        // Finalize it 
	        byte[] rawResultArray = md.digest();
	        // Convert raw byte result into a hex string
	        StringBuffer stringSoFar = new StringBuffer();
	        for (byte b : rawResultArray) {
	            stringSoFar.append(String.format("%02X", b));
	        }
	        result = stringSoFar.toString();
    	}
    	catch(IOException e) {}
    	catch(NoSuchAlgorithmException e) {}
        
        return result;
    }
}

