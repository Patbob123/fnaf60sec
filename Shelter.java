import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shelter class to store items for the second floor world
 * 
 * @author Edmond
 * @version January 2024
 */
public class Shelter extends Tile
{
    private Inventory bunker;
    /**
     * Constructor for Shelter
     */
    public Shelter(){
        super();
        symbol = "#"; 
        bunker = new Inventory();
        url = "chest";
    }
    /**
     * Getter Method for the inventory
     */
    public Inventory getInventory(){
        return bunker;
    }
}
