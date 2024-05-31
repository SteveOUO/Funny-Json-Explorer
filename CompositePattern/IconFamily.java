package FunnyJsonExplorer.CompositePattern;

public abstract class IconFamily implements Component{
    public String getType(){
        return "IconFamily";
    }
    public abstract String getLeafIcon();
    public abstract String getMiddleIcon();
}
