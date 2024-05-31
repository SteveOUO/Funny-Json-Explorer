package FunnyJsonExplorer.CompositePattern;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class JsonExplorer {
    private Container container;

    public JsonExplorer(Component style, Component icon) {
        this.container = new Container();
        container.add(style);
        container.add(icon);
    }

    public void show(Map<String, Object> json){
        this.container.draw(json);
    }

    private Map<String, Object> load(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(new File(filePath).toPath()));
        content = content.trim();
        if (content.startsWith("{") && content.endsWith("}")) {
            content = content.substring(1, content.length() - 1).trim();
            return parseJsonObject(content);
        }
        return new HashMap<>();
    }

    private Map<String, Object> parseJsonObject(String content) {
        Map<String, Object> map = new HashMap<>();
        int braceCount = 0;
        int start = 0;
        boolean parsingKey = true;
        String currentKey = null;

        for (int i = 0; i <= content.length(); i++) {
            char c = i < content.length() ? content.charAt(i) : ',';
            if (c == '{') braceCount++;
            if (c == '}') braceCount--;
            if (braceCount == 0 && (c == ':' || c == ',' || i == content.length())) {
                if (parsingKey) {
                    currentKey = content.substring(start, i).trim().replaceAll("\"", "");
                    parsingKey = false;
                    start = i + 1;
                } else {
                    String value = content.substring(start, i).trim().replaceAll("\"", "");
                    if (value.equals("null")) {
                        map.put(currentKey, null);
                    } else if (value.startsWith("{") && value.endsWith("}")) {
                        map.put(currentKey, parseJsonObject(value.substring(1, value.length() - 1)));
                    } else {
                        map.put(currentKey, value);
                    }
                    parsingKey = true;
                    start = i + 1;
                }
            }
        }
        return map;
    }    

    public static void main(String[] args) throws IOException {
        String filePath = null;
        Component styleComponent = null;
        Component iconComponent = null;
        Map<String, Supplier<Component>> componentsMap = new HashMap<>();
        componentsMap.put("tree", TreeStyle::new);
        componentsMap.put("rectangle", RectangleStyle::new);
        componentsMap.put("default", DefaultIconFamily::new);
        componentsMap.put("poker", PokerIconFamily::new);
    
        // Parse command line arguments
        String styleArg = "";
        String iconFamilyArg = "";
        for (int i = 0; i < args.length; i++) {
            if ("-f".equals(args[i]) && i < args.length - 1) {
                filePath = args[i + 1];
                i++; // Skip the next argument since it's the file path
            } else if ("-s".equals(args[i]) && i < args.length - 1) {
                styleArg = args[i + 1];
                i++; // Skip the next argument since it's the style parameter
            } else if ("-i".equals(args[i]) && i < args.length - 1) {
                iconFamilyArg = args[i + 1];
                i++; // Skip the next argument since it's the icon family parameter
            }
        }
        if(componentsMap.get(styleArg) == null){
            // Handle unknown style parameter
            System.err.println("Unknown style: " + styleArg);
            return;
        }else if(componentsMap.get(iconFamilyArg) == null){
            System.err.println("Unknown icon: " + iconFamilyArg);
            return;
        }else{
            styleComponent = componentsMap.get(styleArg).get();
            iconComponent = componentsMap.get(iconFamilyArg).get();
        }
    
        // Check if necessary parameters are provided
        if (filePath == null || styleComponent == null || iconComponent == null) {
            System.err.println("Usage: fje -f <json file> -s <style> -i <icon family>");
            return;
        }
    
        // Create JsonExplorer object and render
        JsonExplorer explorer = new JsonExplorer(styleComponent, iconComponent);
        Map<String, Object> json = explorer.load(filePath);
        explorer.show(json);
    }    
}
