package FunnyJsonExplorer.AbstractFactory;

public class RectanglePokerContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Style style = new RectangleStyle();
        IconFamily icon = new PokerIconFamily();
        return new Container(style, icon);
    }
}
