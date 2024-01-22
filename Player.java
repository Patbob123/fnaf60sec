import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.image.BufferedImage;


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
    private int speed;
    private GreenfootImage[][] idleFrames;
    private GreenfootImage[][] walkingFrames;
    private GreenfootImage[] curFrames;
    private int curFrame;
    private int curIdle;
    private SimpleTimer animTimer; 
    
    private GreenfootImage plusImage = new GreenfootImage("plus.png");
    private GreenfootImage minusImage = new GreenfootImage("minus.png");
    private GreenfootImage noImage = new GreenfootImage("no.png");
    private Popup ePopup = new Popup(new GreenfootImage("E.png"));
    private boolean showPopup;
    private String spriteSheetUrl = "timmysprites.png";
    
    public Player(){
        speed = 10;
        handSlots = new Inventory();
        itemChest = new ArrayList<>();
        animTimer = new SimpleTimer();
        
        idleFrames = new GreenfootImage[6][4];
        walkingFrames = new GreenfootImage[6][6];
        //setIcon("tempson.png");
        
        
       
        
        showPopup = false;
    }
    public void scaleIcon(GreenfootImage icon){
        //GreenfootImage icon = new GreenfootImage(imageUrl);
        icon.scale(32, 52);
        Util.scale(icon);

    }
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
    public void setIdle(int frameDir){
        curIdle = frameDir;
    }
    
    public void act()
    {
        z_sortAround();
        animate();
        interact();
    }
    protected void addedToWorld(World world){
        spriteSheetUrl = getW().getFile("TimSprite");
        loadSprites(idleFrames, 0);
        loadSprites(walkingFrames, 128);
        
        collider = new Hitbox(getImage().getWidth()-speed,1);
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
            
            if(handSlots.canPickup(currentItem.getWeight())){
                handSlots.addWeight(currentItem.getWeight());
                handSlots.getStorage().add(currentItem);
                                
                getW().getVP().removeItem(currentItem);
                
                updateHandDisplay();
                
                getW().addObject(new PopupFader(currentItem.getImage(),80,false), getX(),getY()-60);
                getW().addObject(new PopupFader(plusImage,100,false), getX()-50, getY()-60);
            }else{
                getW().addObject(new PopupFader(noImage,100,false), getX(), getY()-60);
            }
        }
            
    }
    
    public void dropOff(){
            for(int i = 0; i < handSlots.getStorage().size(); i++){
                Item item = handSlots.getStorage().get(i);
                itemChest.add(item);
                getW().addObject(new PopupFader(item.getImage(),80,false), getX(),getY()-60*(i+1));
                getW().addObject(new PopupFader(minusImage,100,false), getX()-50, getY()-60*(i+1));
            }
            handSlots.getStorage().clear();
            handSlots.clearWeight();
            updateHandDisplay();
            

    }
    public void interact(){

        String key = Greenfoot.getKey();
        if(isTouching(Shelter.class)){
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
        
        if(Greenfoot.isKeyDown("i")){
            //System.out.println(itemChest.toString());
        }
        
        if(showPopup){
            getW().addObject(ePopup, getX(),getY()-120);
        }else if(getW().getObjects(Popup.class).contains(ePopup)){
            getW().removeObject(ePopup);
        }
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
