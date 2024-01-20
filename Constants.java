import java.util.HashMap;
/**
 * Write a description of class Constants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Constants  
{
    public static final int SCALE = 3;
    public static final int PICKUP_COOLDOWN = 1000; 
    public static final int TILE_LEN = 96;
    public static final int WH = 768;
    public static final int WW = 1152;
    
    public static final HashMap<String, Class> itemHash = new HashMap<>(){{
        try {
            put("Food", Class.forName("Food"));
            put("Water", Class.forName("Water"));
        }catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }};
    public static final HashMap<String, Class> tileHash = new HashMap<>(){{
        try {
            put("#", Class.forName("Shelter"));
            put("W", Class.forName("Wall"));
            put("-", Class.forName("Floor"));
            
        }catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }};
    public Constants()
    {
    }
}
