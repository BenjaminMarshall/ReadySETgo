package backend.models;

import java.io.Serializable;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class Document implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
