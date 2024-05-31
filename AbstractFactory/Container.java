package FunnyJsonExplorer.AbstractFactory;

import java.util.Map;

import javax.swing.Icon;

public class Container {
    private Style style;
    private IconFamily Icon;
    public Container(Style style,IconFamily iconFamily){
        this.style = style;
        this.Icon = iconFamily;
    }
    public void draw(Map<String, Object> json){
        this.style.draw(json, this.Icon);
    };
}
