import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Write a description of class SchoolWorlds here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class SchoolWorld extends World
{
    protected WorldManager wManager;
    protected HashMap<String, World> worldStorage;
    protected Player fisho;
    protected PressurePlate left, right, up, down;
    
    private Button map;
    /**
     * Constructor for objects of class SchoolWorlds.
     * 
     */
    public SchoolWorld(WorldManager wManager)
    {
        super(1152, 768, 1);
        this.wManager = wManager;
        worldStorage = wManager.getHashMap();
        map();
    }
    public boolean isCreated(String worldName){
        if(wManager.checkIfCreated(worldName) == false){
            wManager.storeWorld(worldName, this);
            return false;
        }
        return true;
    }
    public void map(){
        map = new Button("map", 40);
        addObject(map, 1091, 45);
    }
}
