package FunnyJsonExplorer.AbstractFactory;

import javax.swing.Icon;

public class RectangleDefaultContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Style style = new RectangleStyle();
        IconFamily icon = new DefaultIconFamily();
        return new Container(style, icon);
    }
}
