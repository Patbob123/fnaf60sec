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
    private ArrayList<Item> itemChest;
    private SimpleTimer timer;
    private int speed;
    public Player(){
        speed = 10;
        handSlots = new Inventory();
        itemChest = new ArrayList<>();
        timer = new SimpleTimer();
        setIcon("tempson.png");
        collider = new Hitbox(getImage().getWidth()-speed,1);
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

        if(timer.millisElapsed() >= Constants.PICKUP_COOLDOWN){
            if(Greenfoot.isKeyDown("e")){
                if(isTouching(Shelter.class)){
                    dropOff();
                }else{
                    pickUp();
                }
            }
            if(Greenfoot.isKeyDown("i")){
                //System.out.println(itemChest.toString());
            }
        }
    }
    protected void addedToWorld(World world){
        world.addObject(collider, getX(), getY());
    }
    private void z_sortAround(){
        World w = getWorld();
        int curX = getX();
        int curY = getY();
        int colY = getCollider().getY();
        boolean k = false;
        PriorityQueue <Actor> pq = new PriorityQueue <>((a,b) -> a.getY()-b.getY());
        for(Actor a: getIntersectingObjects(SuperSmoothMover.class)){
            if(a==(null)) continue;
            if(a.toString().equals("W"))pq.add(a);
        }
        while(!pq.isEmpty()){
              
            Actor a = pq.poll();
            
            int x = a.getX();
            int y = a.getY();

            if(y>colY&&!k){
                k = true;
                w.removeObject(this);
                w.addObject(this, curX, curY);
            }
            
            w.removeObject(a);
            w.addObject(a, x, y);
        }
        if(!k){
            w.removeObject(this);
            w.addObject(this, curX, curY);
        }
    }
    public double getDistance(Actor actor){
        return Math.hypot(Math.abs(actor.getX() - getX()), Math.abs(actor.getY() - getY()));
    }
    public void pickUp(){
            
                ArrayList<Item> nearbyObjects = (ArrayList<Item>)getObjectsInRange(100, Item.class);
                if(nearbyObjects.size() > 0){
    
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
                    
                    if(handSlots.canPickup(currentItem.getWeight())){
                        handSlots.addWeight(currentItem.getWeight());
                        handSlots.getStorage().add(currentItem);
                                        
                        timer.mark();
                        getW().getVP().removeItem(currentItem);
                        
                        updateHandDisplay();
                        
                        getWorld().addObject(new PopupFader(currentItem.getImage(),100,false), getX(),getY()-60);
                        
                        GreenfootImage textImage = new GreenfootImage(" + ", 40, Color.RED, new Color(0, 0, 0, 0));
                        getWorld().addObject(new PopupFader(textImage,100,false), getX()-20, getY()-60);
                    }
                }
            
    }
    
    public void dropOff(){
            for(Item item: handSlots.getStorage()){
                itemChest.add(item);
            }
            handSlots.getStorage().clear();
            handSlots.clearWeight();
            updateHandDisplay();
    }
    public void updateHandDisplay(){
        getW().displayHandSlots();
    }
    public boolean checkWall(int x, int y){
        return collider.intersectWall(x,y);
    }   
    

    public boolean onPressurePlate(){
        return isTouching(PressurePlate.class);
    }
    public boolean touchP(PressurePlate p){
        return intersects(p);
    }
    public Hitbox getCollider(){
        return collider;
    }
    public int getSpeed(){
        return speed;
    }
    public Inventory getHandSlots(){
        return handSlots;
    }
    public tempWorld getW(){
        return (tempWorld)getWorld();
    }
}
