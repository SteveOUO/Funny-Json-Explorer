package FunnyJsonExplorer.src;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class JsonExplorer {
    private Container container;

    public JsonExplorer(ContainerFactory factory) {
        Director director = new Director(factory);
        this.container = director.getProduct();
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
        ContainerFactory factory = null;
        Map<String, Supplier<ContainerFactory>> containerFactorysMap = new HashMap<>();
        containerFactorysMap.put("treedefault", TreeDefaultContainerFactory::new);
        containerFactorysMap.put("treepoker", TreePokerContainerFactory::new);
        containerFactorysMap.put("rectangledefault", RectangleDefaultContainerFactory::new);
        containerFactorysMap.put("rectanglepoker", RectanglePokerContainerFactory::new);
        containerFactorysMap.put("treeconfig", TreeConfigContainerFactory::new);

    
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
        if(containerFactorysMap.get(styleArg+iconFamilyArg) == null){
            if (!"tree".equals(styleArg)&&!"rectangle".equals(styleArg)) {
                // Handle unknown style parameter
                System.err.println("Unknown style: " + styleArg+iconFamilyArg);
                return;
            }
        }else{
            factory = containerFactorysMap.get(styleArg+iconFamilyArg).get();
        }
    
        // Check if necessary parameters are provided
        if (filePath == null || factory == null) {
            System.err.println("Usage: fje -f <json file> -s <style> -i <icon family>");
            return;
        }
    
        // Create JsonExplorer object and render
        JsonExplorer explorer = new JsonExplorer(factory);
        Map<String, Object> json = explorer.load(filePath);
        explorer.show(json);
    }    
}
