import java.util.HashMap;
/**
 * Class to hold all static/ constant variables
 * 
 * @author Dawson
 * @version January 2024
 */
public class Constants  
{
    public static final int SCALE = 4;
    public static final int EYE_SWITCH = 5000;
    public static final int PICKUP_COOLDOWN = 100; 
    public static final int ANIM_SPEED = 100; 
    public static final int TILE_LEN = 32*SCALE;
    public static final int WH = 768;
    public static final int WW = 1152;
    
    public static final HashMap<String, Class> itemHash = new HashMap<>(){{
        try {
            put("Food", Class.forName("Food"));
            put("Water", Class.forName("Water"));
            put("Battery", Class.forName("Battery"));
            put("Wood", Class.forName("Wood"));
        }catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }};
    public static final HashMap<String, Class> tileHash = new HashMap<>(){{
        try {
            put("#", Class.forName("Shelter"));
            put("w", Class.forName("Wall"));
            put("l", Class.forName("Log"));
            put("t", Class.forName("Table"));
            put("d", Class.forName("Entry"));
            put("c", Class.forName("Counter"));
            put("s", Class.forName("Stair"));
            put("g", Class.forName("Gray"));
            put("-", Class.forName("Floor"));
            
        }catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }};
}
