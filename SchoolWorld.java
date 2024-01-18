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
    /**
     * Constructor for objects of class SchoolWorlds.
     * 
     */
    public SchoolWorld(WorldManager wManager)
    {
        super(1152, 768, 1);
        this.wManager = wManager;
        worldStorage = wManager.getHashMap();
        spawnItems();
    }
    public boolean isCreated(String worldName){
        if(wManager.checkIfCreated(worldName) == false){
            wManager.storeWorld(worldName, this);
            return false;
        }
        return true;
    }
    public void spawnItems(){
        for(int i = 0; i< 10; i++){
            int x = Greenfoot.getRandomNumber(1152);
            int y = Greenfoot.getRandomNumber(768);
            addObject(new Item(),x,y);
        }
    }
}
