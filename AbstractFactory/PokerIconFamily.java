package FunnyJsonExplorer.AbstractFactory;

public class PokerIconFamily extends IconFamily{
    public String getLeafIcon() {
        return "♢ "; // Poker face icon for intermediate nodes
    }
    public String getMiddleIcon() {
        return "♤ "; // Poker face icon for intermediate nodes
    }
}
