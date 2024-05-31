package FunnyJsonExplorer.AbstractFactory;

public class TreeConfigContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Style style = new TreeStyle();
        IconFamily icon = new ConfigIconFamily();
        return new Container(style, icon);
    }
}