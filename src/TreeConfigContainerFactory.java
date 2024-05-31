package FunnyJsonExplorer.src;

public class TreeConfigContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Component style = new TreeStyle();
        Component icon = new ConfigIconFamily();
        return new Container(style, icon);
    }
}