import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Tile
{
    private String[] itemList;
    
    public Floor(){
        super();
        symbol = "-"; 
        itemList = new String[]{"Food", "Water"};
        
        setIcon("FLOOR.png");
    }
    protected void addedToWorld(World w){
        if(Greenfoot.getRandomNumber(10)==1){
            randomItem();
        }
    }
    public void randomItem(){
        String itemName = itemList[Greenfoot.getRandomNumber(itemList.length)];
        spawnItem(itemName);
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
