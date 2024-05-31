package FunnyJsonExplorer.BuilderPattern;

public class RectanglePokerContainerBuilder extends Builder{
    private Container container;
    public RectanglePokerContainerBuilder(){
        this.container = new Container(null, null);
    }
    public void BuildStyle(){
        this.container.style = new RectangleStyle();
    };
    public void BuildIcon(){
        this.container.Icon = new PokerIconFamily();
    };
    public Container getContainer(){
        return container;
    }
}
