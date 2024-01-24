import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.image.BufferedImage;

/**
 * Man Class to Control the Actions of the Player in the First Floor World
 * 
 * @author Dawson
 * <p> Edited and updated by Vincent </p>
 * @version January 2024
 */
public class Player extends Entity
{   
    private Hitbox collider;
    private Inventory handSlots;
    private ArrayList<Item> itemChest;
    private int speed;
    
    //Arrays to hold Animation
    private GreenfootImage[][] idleFrames;
    private GreenfootImage[][] walkingFrames;
    private GreenfootImage[] curFrames;
    private int curFrame;
    private int curIdle;
    private SimpleTimer animTimer; 
    
    // Sprite assets for the player interation
    private GreenfootImage plusImage = new GreenfootImage("plus.png");
    private GreenfootImage minusImage = new GreenfootImage("minus.png");
    private GreenfootImage noImage = new GreenfootImage("no.png");
    private Popup ePopup = new Popup(new GreenfootImage("E.png"));
    private PriorityQueue <Actor> zSortQueue;
    private boolean init = true;;
    
    private boolean showPopup;
    
    private String spriteSheetUrl = "timmysprites.png";
    
    /**
     * Constructor for the Player actor
     */
    public Player(){
        speed = 10;
        handSlots = new Inventory();
        itemChest = new ArrayList<>();
        animTimer = new SimpleTimer();
        
        idleFrames = new GreenfootImage[6][4];
        walkingFrames = new GreenfootImage[6][6];
        //setIcon("tempson.png");
        zSortQueue = new PriorityQueue <>((a,b) -> a.getY()-b.getY());
        showPopup = false;
    }
    
    public void act()
    {
        z_sortAround();
        animate();
        interact();
    }
    
    /**
     * Helper method to scale Icons
     * 
     * @param icon      The GreenfootImage of the icon
     */
    public void scaleIcon(GreenfootImage icon){
        icon.scale(32, 52);
        Util.scale(icon);
    }
    
    /**
     * Method to construct and load the animation sprites of the player
     * 
     * @param frames    The 2d array of GreenfootImage animation frames
     * @param startY    Y-offset variable
     */
    public void loadSprites(GreenfootImage[][] frames, int startY){
        for(int i = 0; i < frames.length-2; i++){
            for(int j = 0; j < frames[0].length; j++){
                GreenfootImage image = new GreenfootImage(spriteSheetUrl);
                GreenfootImage cropimage = new GreenfootImage(16, 26);
                cropimage.drawImage(image, -j*16, -i*32-startY);
                scaleIcon(cropimage);
                frames[i][j] = cropimage;
            }
        }
        for(int i = frames.length-2; i < frames.length; i++){
            for(int j = 0; j < frames[0].length; j++){
                GreenfootImage image = new GreenfootImage(spriteSheetUrl);
                GreenfootImage cropimage = new GreenfootImage(16, 26);
                cropimage.drawImage(image, -j*16, -(32+(i-(frames.length-2))*32*2)-startY);
                cropimage.mirrorHorizontally();
                scaleIcon(cropimage);
                frames[i][j] = cropimage;
            }
        }
    }
    
    /**
     * Display animations and iterate through the 2d array of frames
     */
    public void animate(){
        if(animTimer.millisElapsed() >= Constants.ANIM_SPEED){
            curFrame++;
            if(curFrames.length <= curFrame){
                curFrame = 0;
            }
            setImage(curFrames[curFrame]);
            animTimer.mark();
        }
    }
    
    /**
     * Method to set the current frame of the animation
     * 
     * @param frameType     Walking/ Idle animation
     * @param frameDir      Direction the player is facing
     */
    public void setCurFrame(int frameType, int frameDir){
        switch(frameType){
            case 1:
                curFrames = idleFrames[curIdle];
            break;
            case 2:
                curFrames = walkingFrames[frameDir];
            break;
        }
    }
    
    /**
     * Method to set the animation to idle
     * 
     * @param frameDir      Direction the player is facing
     */
    public void setIdle(int frameDir){
        curIdle = frameDir;
    }
    
    /**
     * Add the player to the world along with the hitbox
     * 
     * @param world     The World
     */
    protected void addedToWorld(World world){
        if(init){
            init = false;
            spriteSheetUrl = getW().getFile("TimSprite");
            loadSprites(idleFrames, 0);
            loadSprites(walkingFrames, 128);
            
            setImage(idleFrames[0][0]);
            
            collider = new Hitbox(getImage().getWidth()-speed,1);
            world.addObject(collider, getX(), getY());
        }
    }
    
    /**
     * Method to sort all objects in the world to create 3d effect of the player
     * Player stands behind the wall in certain angles and in front of the wall in other angles
     */
    private void z_sortAround(){
        World w = getWorld();
        int curX = getX();
        int curY = getY();
        int colY = getCollider().getY();
        boolean k = false;
        int i = 0; 
        if(getIntersectingObjects(Tile.class).size()==0) return;
        if(zSortQueue!=null) zSortQueue.clear();
        for(Actor a: getIntersectingObjects(Tile.class)){
            i++;
             System.out.println(i);
            if(a==(null)) continue;
            if(a.toString().equals("W"))zSortQueue.add(a);
        }
        
        while(!zSortQueue.isEmpty()){
             i++;
              System.out.println(i++);
            Actor a = zSortQueue.poll();
            
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
    
    /**
     * Getter method for current distance from the player to the actor
     * 
     * @param actor     The desired actor
     */
    public double getDistance(Actor actor){
        return Math.hypot(Math.abs(actor.getX() - getX()), Math.abs(actor.getY() - getY()));
    }
    
    /**
     * Main method to pick up the items on the ground
     *  Checks if:
     *  - item is in range 
     *  - If multiple items are in range, get the closest item
     *  - Checks if the current item fits in the inventory, if it can add it to the inventory
     */
    public void pickUp(){    
        ArrayList<Item> nearbyObjects = (ArrayList<Item>)getObjectsInRange(100, Item.class);
        // Grab all avaliable items in the radius, determine the closest item to the player
        if(nearbyObjects.size() > 0){

            Item nearestItem = null;
            Item currentItem = null;
            double nearestDistance = 100;
            double currentDistance;
            
            for(int i = 0; i< nearbyObjects.size(); i++){
                currentItem = nearbyObjects.get(i);
                currentDistance = getDistance(currentItem);
                if(currentDistance < nearestDistance){
                    nearestItem = currentItem;
                    nearestDistance = currentDistance;
                }
            }
            
            // Check if the player has the inventory space to pick up the item
            if(handSlots.canPickup(currentItem.getWeight())){
                handSlots.addWeight(currentItem.getWeight());
                handSlots.getStorage().add(currentItem);
                                
                getW().getVP().removeItem(currentItem);
                
                updateHandDisplay();
                
                // Display a visual to indicate that the player has picked up an item
                getW().addObject(new PopupFader(currentItem.getImage(),80,false), getX(),getY()-60);
                getW().addObject(new PopupFader(plusImage,100,false), getX()-50, getY()-60);
            }else{
                // Visual to indicate that the player cannot pick up the item
                getW().addObject(new PopupFader(noImage,100,false), getX(), getY()-60);
            }
        }
            
    }
    
    /**
     * Method to drop off the items to the storage area
     */
    public void dropOff(){
            for(int i = 0; i < handSlots.getStorage().size(); i++){
                Item item = handSlots.getStorage().get(i);
                itemChest.add(item);
                getW().addObject(new PopupFader(item.getImage(),80,false), getX(),getY()-60*(i+1));
                getW().addObject(new PopupFader(minusImage,100,false), getX()-50, getY()-60*(i+1));
            }
            
            // After all the items are copied, clear the current player inventory
            handSlots.getStorage().clear();
            handSlots.clearWeight();
            updateHandDisplay();
    }
    
    /**
     * Method to display a visual that lets the user know whether or not they can interact with an object
     */
    public void interact(){
        String key = Greenfoot.getKey();
        if(isTouching(Stair.class)){
            showPopup = true;
            if(key != null){
                if(key.toLowerCase().equals("e")){
                    getW().nextPhase();
                }
            }
        }else if(isTouching(Shelter.class)){
            showPopup = true;
            if(key != null){
                if(key.toLowerCase().equals("e")){
                    dropOff();
                }
            }
        }else if(isTouching(Item.class)){
            showPopup = true;
            if(key != null){
                if(key.toLowerCase().equals("e")){
                    pickUp();
                }
            }
        }else{
            showPopup = false;
        }
        if(showPopup){
            getW().addObject(ePopup, getX(),getY()-120);
        }else if(getW().getObjects(Popup.class).contains(ePopup)){
            getW().removeObject(ePopup);
        }
    }
    /**
     * Helper method to refresh display of current inventory
     */
    public void updateHandDisplay(){
        getW().displayHandSlots();
    }
    /**
     * Method to check if the player hits the wall
     * 
     * @param x     x-coordinate
     * @param y     y-coordinate
     */
    public boolean checkWall(int x, int y){
        return collider.intersectWall(x,y);
    }   
    /**
     * Helper method to check if the player on top of a pressure plate
     */
    public boolean onPressurePlate(){
        return isTouching(PressurePlate.class);
    }
    /**
     * Helper method to check if the player has touched a pressure plate
     */
    public boolean touchP(PressurePlate p){
        return intersects(p);
    }
    /**
     * Getter method for the hitbox
     */
    public Hitbox getCollider(){
        return collider;
    }
    /**
     * Getter method for the speed of the player
     */
    public int getSpeed(){
        return speed;
    }
    /**
     * Getter method for the item chest
     */
    public ArrayList<Item> getItemChest(){
        return itemChest;
    }
    /**
     * Getter method for the current inventory
     */
    public Inventory getHandSlots(){
        return handSlots;
    }
    /**
     * Getter method for the First Floor World
     */
    public tempWorld getW(){
        return (tempWorld)getWorld();
    }
}
