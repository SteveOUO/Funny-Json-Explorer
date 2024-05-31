package FunnyJsonExplorer.BuilderPattern;

public abstract class Builder {
    private Container container;
    public void BuildStyle(){};
    public void BuildIcon(){};
    public Builder(){
        container = new Container(null, null);
    }
    public Container getContainer(){
        return container;
    }
}
