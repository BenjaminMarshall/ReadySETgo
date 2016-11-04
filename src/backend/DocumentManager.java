package backend;

import backend.models.Document;

import java.io.*;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class DocumentManager {
    public static void saveDocument(Document document, File file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(document);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document loadDocumentFromFile(File file) {
        Document loadedDocument;
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            loadedDocument = (Document) ois.readObject();
            ois.close();
            return loadedDocument;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
