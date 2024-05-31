package FunnyJsonExplorer.src;

public class Director {
    private ContainerFactory factory;
    public Director(ContainerFactory factory){
        this.factory = factory;
    }
    public Container getProduct(){
        return this.factory.createContainer();
    }
}
