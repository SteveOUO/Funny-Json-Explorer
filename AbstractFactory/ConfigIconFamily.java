package FunnyJsonExplorer.AbstractFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigIconFamily extends IconFamily {
    private static String leafIcon;
    private static String middleIcon;

    // Static block to read from YAML file once when the class is loaded
    static {
        Map<String, String> config = new HashMap<>();
        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get("config.yaml")), StandardCharsets.UTF_8);
            String[] lines = content.split("\\n");

            leafIcon = lines[3].replaceAll("\\s", "")+" ";
            middleIcon = lines[1].replaceAll("\\s", "")+" ";
            System.out.println("LeafIcon:"+leafIcon);
            System.out.println("MiddleIcon:"+middleIcon);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load icons from config.yaml", e);
        }
    }

    @Override
    public String getLeafIcon() {
        return leafIcon;
    }

    @Override
    public String getMiddleIcon() {
        return middleIcon;
    }
}
