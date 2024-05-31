package FunnyJsonExplorer.FactoryMethod;
import java.util.ArrayList;
import java.util.Map;

public class TreeStyle extends Style {
    public void draw(Map<String, Object> json, IconFamily iconFamily) {
        ArrayList<Boolean> boolArray = new ArrayList<>();
        printTree(json, 0, iconFamily, boolArray);
    }

    private void printTree(Map<String, Object> map, int level, IconFamily iconFamily, ArrayList<Boolean> boolArray) {
        String indent = "\u2502  ".repeat(level); // Calculate indentation based on the level
        int entryCount = map.size();
        int currentIndex = 0;
        boolArray.add(false);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            currentIndex++;
            boolean isLastEntry = currentIndex == entryCount;

            // Determine icon based on whether the entry is a leaf or a subtree
            String icon = (entry.getValue() instanceof Map) ? iconFamily.getMiddleIcon() : iconFamily.getLeafIcon();
            String connector = isLastEntry ? "\u2514\u2500" : "\u251C\u2500";

            // Adjust indent for subsequent entries at the same level
            indent = "";
            if (!isLastEntry) {
            } else {
                boolArray.set(level, true);
            }
            for(int i=0;i<level;++i){
                if(boolArray.get(i)){
                    indent += "   ";
                }
                else{
                    indent += "\u2502  ";
                }
            }

            if(indent == "  ")
                System.out.println(11111);
            System.out.print(indent + connector + icon + entry.getKey()); // Print key with icon

            if (entry.getValue() instanceof Map) {
                // If the value is a Map, print a new line and recursively print the subtree
                System.out.println();
                printTree((Map<String, Object>) entry.getValue(), level + 1, iconFamily, boolArray);
            } else {
                if(entry.getValue()!=null)
                // If the value is not a Map, print the value on the same line
                System.out.print(": " + entry.getValue());
                System.out.println("");
            }
        }
    }
           
}
