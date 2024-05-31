package FunnyJsonExplorer.CompositePattern;

import java.util.Map;

public interface Component {
    public String getType();
    public abstract void draw(Map<String, Object> json, Component iconFamily);
    public abstract String getLeafIcon();
    public abstract String getMiddleIcon();
}
