package backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ksenia Belikova
 * @version 11/3/2016.
 */
public class Stage implements Serializable {
    private String name;
    private List<Asset> assets = new ArrayList<Asset>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
