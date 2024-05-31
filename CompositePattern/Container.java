package FunnyJsonExplorer.CompositePattern;

import java.util.Map;

public class Container {
    private Component style;
    private Component Icon;
    public Container(){}
    public void add(Component component){
        if(component.getType() == "Style"){
            this.style = component;
        }
        else if(component.getType() == "IconFamily"){
            this.Icon = component;
        }
        else{
            System.out.println(11111);
        }
    }
    public void draw(Map<String, Object> json){
        if(style==null||Icon==null){
            throw new IllegalStateException("Missing style or icon component");
        }
        this.style.draw(json, this.Icon);
    }
}
