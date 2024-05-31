package FunnyJsonExplorer.BuilderPattern;

public class TreePokerContainerBuilder extends Builder{
    private Container container;
    public TreePokerContainerBuilder(){
        this.container = new Container(null, null);
    }
    public void BuildStyle(){
        container.style = new TreeStyle();
    };
    public void BuildIcon(){
        container.Icon = new PokerIconFamily();
    };
    public Container getContainer(){
        return container;
    }
}
