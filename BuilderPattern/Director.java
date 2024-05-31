package FunnyJsonExplorer.BuilderPattern;

public class Director {
    private Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }
    public Container getProduct(){
        this.builder.BuildStyle();
        this.builder.BuildIcon();
        return this.builder.getContainer();
    }
}
