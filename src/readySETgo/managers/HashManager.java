package readySETgo.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * RSG
 */
public class HashManager {

	private HashMap imageHashMap;
	private static HashManager manager = new HashManager();
	
	private HashManager() {
		imageHashMap = FileManager.loadImageMap();
	}
	
	public static HashMap getMap() {
		return manager.imageHashMap;
	}
	
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

