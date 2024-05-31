package FunnyJsonExplorer.FactoryMethod;

public class TreeStyleFactory implements StyleFactory {
    public Style createStyle() {
        return new TreeStyle();
    }
}

