package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import backend.models.Stage;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class DocumentManager {
    public static void saveDocument(Stage document, File file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            //TODO change to XML
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage loadDocumentFromFile(File file) {
        Stage loadedDocument;
        try {
            FileInputStream fin = new FileInputStream(file);
            loadedDocument = new Stage();
            //TODO XML stuff
            return loadedDocument;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
