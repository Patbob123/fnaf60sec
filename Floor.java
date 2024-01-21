import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Tile
{
    
    
    public Floor(){
        super();
        symbol = "-"; 
        
        
        setIcon("FLOOR.png");
    }
    protected void addedToWorld(World w){

    }

    public void spawnItem(String itemName){
        try{
            Item i = (Item)Constants.itemHash.get(itemName).newInstance();
            getWorld().addObject(i, getX(), getY());
        }catch(InstantiationException e){
            
        }catch(IllegalAccessException e){
            
        }
    }
}
