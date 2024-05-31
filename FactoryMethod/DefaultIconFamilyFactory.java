package FunnyJsonExplorer.FactoryMethod;

public class DefaultIconFamilyFactory implements IconFamilyFactory {
    public IconFamily createIconFamily() {
        return new DefaultIconFamily();
    }
}
