package FunnyJsonExplorer.src;

public class RectangleDefaultContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Component style = new RectangleStyle();
        Component icon = new DefaultIconFamily();
        return new Container(style, icon);
    }
}
