package FunnyJsonExplorer.src;
import java.util.Map;

public class RectangleStyle extends Style{
    public String getType(){
        return "Style";
    }
    public void draw(Map<String, Object> json, Component iconFamily) {
        int isParentEnd = -1;
        printRectangle(json, 0, iconFamily, isParentEnd);
    }

    private void printRectangle(Map<String, Object> map, int level, Component iconFamily, int isParentEnd) {
        String indent = "   ".repeat(level); // Calculate indentation based on the level
        int entryCount = map.size();
        int currentIndex = 0;
        int Rectanglelen = 60;
        int currentlen = 0;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            currentIndex++;
            boolean isLastEntry = currentIndex == entryCount;
            currentlen = 0;

            // Adjust indent for subsequent entries at the same level
            if(isParentEnd==-1){
                indent = "\u250C\u2500\u2500";
            }else if(isParentEnd==1&&isLastEntry){
                indent = "\u2514\u2500\u2500";
            }else{
                indent = "\u2502  ".repeat(level);
            }

            // Determine icon based on whether the entry is a leaf or a subtree
            String icon = (entry.getValue() instanceof Map) ? iconFamily.getMiddleIcon() : iconFamily.getLeafIcon();
            String connector = indent=="\u2514\u2500\u2500" ? "\u2514\u2500\u2500" : "\u251C\u2500\u2500";
            if(isParentEnd==-1){
                connector = "\u2500\u2500\u2500";
            }
            if(level==0){
                if(isParentEnd==-1) connector = "";
                isParentEnd = isLastEntry?1:0;
            }

            System.out.print(indent + connector + icon + entry.getKey()); // Print key with icon
            currentlen += (indent + connector + icon + entry.getKey()).length();

            if (entry.getValue() instanceof Map) {
                // If the value is a Map, print a new line and recursively print the subtree
                String endStr = "\u2500".repeat(Rectanglelen-currentlen);
                if(indent=="\u250C\u2500\u2500"){
                    endStr+="\u2510";
                }
                else if(indent=="\u2514\u2500\u2500"){
                    endStr+="\u2518";
                }
                else{
                    endStr+="\u2524";
                }
                System.out.print(endStr);
                System.out.println();
                if(isParentEnd==1&&isLastEntry)
                    printRectangle((Map<String, Object>) entry.getValue(), level + 1, iconFamily, 1);
                else{
                    printRectangle((Map<String, Object>) entry.getValue(), level + 1, iconFamily, 0);
                }
            } else {
                // If the value is not a Map, print the value on the same line
                if(entry.getValue()!=null){
                    System.out.print(": " + entry.getValue());
                    currentlen += 2 + (entry.getValue()).toString().length();
                }
                String endStr = "\u2500".repeat(Rectanglelen-currentlen);
                if(indent=="\u250C\u2500\u2500"){
                    endStr+="\u2510";
                }
                else if(indent=="\u2514\u2500\u2500"){
                    endStr+="\u2518";
                }
                else{
                    endStr+="\u2524";
                }
                System.out.println(endStr);
            }
        }
    }
    public String getMiddleIcon(){
        System.err.println("Could not call this Function From Style!");
        return null;
    }
    public String getLeafIcon(){
        System.err.println("Could not call this Function From Style!");
        return null;
    }
}
