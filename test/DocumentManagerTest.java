import backend.DocumentManager;
import backend.models.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class DocumentManagerTest {
    @Test
    public void testSaveLoadDocument() {
        Document document = new Document();
        document.setName("testDoc1");
        DocumentManager.saveDocument(document, new File("bin\\document.dat"));
        Document document2 = DocumentManager.loadDocumentFromFile(new File("bin\\document.dat"));
        Assert.assertNotNull(document2);
        Assert.assertEquals(document.getName(), document2.getName());
    }
}
