package FunnyJsonExplorer.AbstractFactory;
import java.util.Map;

public abstract class Style {
    public abstract void draw(Map<String, Object> json, IconFamily iconFamily);
}


