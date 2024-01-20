import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{   
    private Hitbox collider;
    private Inventory handSlots;
    private SimpleTimer timer;
    public Player(){
        collider = new Hitbox(getImage().getWidth(),getImage().getHeight());
        handSlots = new Inventory();
        timer = new SimpleTimer();
        setIcon("tempson.png");
    }
    public void setIcon(String imageUrl){
        GreenfootImage icon = new GreenfootImage(imageUrl);
        icon.scale(32, 32);
        Util.scale(icon);
        setImage(icon);
    }
    public void act()
    {
        move();
        clear();
        if(timer.millisElapsed() >= Constants.PICKUP_COOLDOWN){
            if(Greenfoot.isKeyDown("e")){
                if(isTouching(Shelter.class)){
                    dropOff();
                }else{
                    pickUp();
                }
            }
        }
    }
    protected void addedToWorld(World world){
        world.addObject(collider, getX(), getY());
    }
    int speed = 10;
    private void move(){
        int pWidth = getImage().getWidth()/2;
        int pHeight = getImage().getHeight()/2;
    } 
    public void clear(){
         if(Greenfoot.isKeyDown("k")){
            tempWorld world = (tempWorld)getWorld();
            Inventory bunkerInventory = world.getShelter().getInventory();
            System.out.println(bunkerInventory);
            for(Item i: handSlots.getStorage()){
                bunkerInventory.getStorage().add(i);
            }
            handSlots.getStorage().clear();
            handSlots.clearWeight();
         }
    }
    public double getDistance(Actor actor){
        return Math.hypot(Math.abs(actor.getX() - getX()), Math.abs(actor.getY() - getY()));
    }
    public void pickUp(){
            if(handSlots.isEmpty()){
                ArrayList<Item> nearbyObjects = (ArrayList<Item>)getObjectsInRange(100, Item.class);
    
                Item nearestItem = null;
                Item currentItem = null;
                double nearestDistance = 0;
                double currentDistance;
                
                for(int i = 0; i< nearbyObjects.size(); i++){
                    currentItem = nearbyObjects.get(i);
                    currentDistance = getDistance(currentItem);
                    if(currentDistance < nearestDistance){
                        nearestItem = currentItem;
                        nearestDistance = currentDistance;
                    }
                }
                handSlots.getStorage().add(currentItem);
                handSlots.addWeight(currentItem.getWeight());
                
                tempWorld world = (tempWorld)getWorld();
                world.getShelter().getInventory().getStorage().add(currentItem);
                timer.mark();
                getWorld().removeObject(currentItem);
            }
        
    }
    public void dropOff(){
            Shelter bunker = (Shelter)getOneIntersectingObject(Shelter.class);
            Inventory bunkerInventory = bunker.getInventory();
            for(Item i: handSlots.getStorage()){
                bunkerInventory.getStorage().add(i);
            }
            handSlots.getStorage().clear();
            handSlots.clearWeight();
        
    }
    public boolean checkWall(int x, int y){
        return  collider.intersectWall(x,y);
    }   
    public boolean onPressurePlate(){
        return isTouching(PressurePlate.class);
    }
    public boolean touchP(PressurePlate p){
        return intersects(p);
    }
    
}
