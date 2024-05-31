package FunnyJsonExplorer.AbstractFactory;

public class TreePokerContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Style style = new TreeStyle();
        IconFamily icon = new PokerIconFamily();
        return new Container(style, icon);
    }
}