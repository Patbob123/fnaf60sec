import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.PriorityQueue;

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
        
        handSlots = new Inventory();
        timer = new SimpleTimer();
        setIcon("tempson.png");
        collider = new Hitbox(getImage().getWidth(),getImage().getHeight()/2);
    }
    public void setIcon(String imageUrl){
        GreenfootImage icon = new GreenfootImage(imageUrl);
        icon.scale(32, 32);
        Util.scale(icon);
        setImage(icon);
    }
    public void act()
    {
        z_sortAround();
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
    private void z_sortAround(){
        World w = getWorld();
        int curX = getX();
        int curY = getY();
        PriorityQueue <Actor> pq = new PriorityQueue <>((a,b)->b.getY()+b.getImage().getHeight()/2-a.getY()+a.getImage().getHeight()/2);
        for(Actor a: getIntersectingObjects(SuperSmoothMover.class)){
            if(a==(null)) continue;
            if(a.toString().equals("W"))pq.add(a);
        }
        System.out.println(pq);
        for(Actor a: pq){
            int x = a.getX();
            int y = a.getY();
            System.out.println((curY+getImage().getHeight()/2)+" "+y);
            System.out.println(y<(curY+getImage().getHeight()/2));
            if(y>(curY+getImage().getHeight()/2)){
                System.out.println("AS"+" "+(curY+getImage().getHeight()/2));
                w.removeObject(this);
                //w.addObject(this, curX, curY);
            }
            w.removeObject(a);
            w.addObject(a, x, y);
        }
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
        if(!collider.intersectWall(x,y)){
            return false;
        }
        return collider.intersectWall(10,0)||collider.intersectWall(0,10)||collider.intersectWall(-10,0)||collider.intersectWall(0,-10);
    }   
    public boolean onPressurePlate(){
        return isTouching(PressurePlate.class);
    }
    public boolean touchP(PressurePlate p){
        return intersects(p);
    }
    
}
