package FunnyJsonExplorer.BuilderPattern;

public class RectangleDefaultContainerBuilder extends Builder{
    private Container container;
    public RectangleDefaultContainerBuilder(){
        this.container = new Container(null, null);
    }
    public void BuildStyle(){
        container.style = new RectangleStyle();
    };
    public void BuildIcon(){
        container.Icon = new DefaultIconFamily();
    };
    public Container getContainer(){
        return container;
    }
}

