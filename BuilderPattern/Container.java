package FunnyJsonExplorer.BuilderPattern;

import java.util.Map;

public class Container {
    public Style style;
    public IconFamily Icon;
    public Container(Style style,IconFamily iconFamily){
        this.style = style;
        this.Icon = iconFamily;
    }
    public void draw(Map<String, Object> json){
        this.style.draw(json, this.Icon);
    };
}
