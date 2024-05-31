package FunnyJsonExplorer.BuilderPattern;

public class TreeDefaultContainerBuilder extends Builder{
    private Container container;
    public TreeDefaultContainerBuilder(){
        this.container = new Container(null, null);
    }
    public void BuildStyle(){
        this.container.style = new TreeStyle();
    };
    public void BuildIcon(){
        this.container.Icon = new DefaultIconFamily();
    };
    public Container getContainer(){
        return container;
    }
}
