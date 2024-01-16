import java.util.HashMap;
import greenfoot.*;

public class Aaa  
{
    private HashMap <String, World> worlds = new HashMap<String, World>();
    public Aaa()
    {
        
    }
    public HashMap <String, World> getHashMap(){
        return worlds; 
    }
    public void store(String name, World world){
        worlds.put(name, world);
    }
    public boolean check(String name){
        for(String i : worlds.keySet()){
            if(i.equals(name)){
                return true;
            }
        }
        return false;
    }
}
