package FunnyJsonExplorer.src;

public class RectanglePokerContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Component style = new RectangleStyle();
        Component icon = new PokerIconFamily();
        return new Container(style, icon);
    }
}
