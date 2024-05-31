package FunnyJsonExplorer.src;

public class TreeDefaultContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Component style = new TreeStyle();
        Component icon = new DefaultIconFamily();
        return new Container(style, icon);
    }
}
