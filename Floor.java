import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Floor Tile
 * 
 * @author Edmond
 * @version January 2024
 */
public class Floor extends Tile
{
    /**
     * Constructor for the Floor Tile
     */
    public Floor(){
        super();
        symbol = "-"; 
        setIcon("FLOOR.png");
    }
    protected void addedToWorld(World w){

    }
    /**
     * Method to spawn items
     * 
     * @itemName    String name of the item
     */
    public void spawnItem(String itemName){
        try{
            Item i = (Item)Constants.itemHash.get(itemName).newInstance();
            getWorld().addObject(i, getX(), getY());
        }catch(InstantiationException e){
            
        }catch(IllegalAccessException e){
            
        }
    }
}
