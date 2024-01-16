import java.util.HashMap;
import greenfoot.*;
/**
 * Write a description of class WorldManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldManager  
{
    private HashMap <String, World> schoolWorlds = new HashMap<String, World>();
    public WorldManager()
    {
        
    }
    public HashMap<String, World> getHashMap(){
        return schoolWorlds;
    }
    public void storeWorld(String name, World world){
        schoolWorlds.put(name, world);
    }
    public boolean checkIfCreated(String key){
        for(String i: schoolWorlds.keySet()){
            if(i.equals(key)){
                return true;
            }
        }
        return false;
    }
    public void setCoordinate(String worldName){
        
    }
}
