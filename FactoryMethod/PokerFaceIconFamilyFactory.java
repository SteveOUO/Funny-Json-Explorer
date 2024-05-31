package FunnyJsonExplorer.FactoryMethod;

public class PokerFaceIconFamilyFactory implements IconFamilyFactory {
    public IconFamily createIconFamily() {
        return new PokerFaceIconFamily();
    }
}
