import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class tempWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tempWorld extends World
{
    private Viewport vp;
    private MapArray ma;
    private Player p;
    private Timer gameTimer;
    private Shelter bunker;
    public tempWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.WW, Constants.WH, 1);
        vp = new Viewport(Constants.WW,Constants.WH);
        ma = new MapArray();
        addObject(vp, 0, 0);
        
        p = new Player();
        addObject(p, Constants.WW/2, Constants.WH/2);
        
        
        setPaintOrder(Timer.class, Display.class, SuperSmoothMover.class,Floor.class, Inventory.class);
        gameTimer = new Timer(60);
        addObject(gameTimer,100,100);
        
        bunker = new Shelter();
        addObject(bunker, 500,300);
        
        vp.move(0,0);
    }
    public void act(){
        
        if(Greenfoot.isKeyDown("a")){
            if(!p.checkWall(-p.getSpeed(), 0)) vp.move(-10, 0);
        }
        if(Greenfoot.isKeyDown("w")){
            if(!p.checkWall(0, -p.getSpeed())) vp.move(0, -10);
        } 
        if(Greenfoot.isKeyDown("s")){
            if(!p.checkWall(0, p.getSpeed())) vp.move(0, 10);
        } 
        if(Greenfoot.isKeyDown("d")){
            if(!p.checkWall(p.getSpeed(), 0)) vp.move(10, 0);
        }
        
        if(gameTimer.getTime() > 0){
            Greenfoot.setWorld(new Crossroads());
        }
    }
    public MapArray getMap(){
        return ma;
    }
    public Viewport getVP(){
        return vp;
    }
    public boolean checkPlayer(int x, int y){
        return getObjectsAt(x, y, Player.class).size()>0;
    }
    public Shelter getShelter(){
        return bunker;
    }
    
    public void displayHandSlots(){
        ArrayList<Item> handInventory = p.getHandSlots().getStorage();
        for(Display d: getObjects(Display.class)){
            removeObject(d);
        }
        int curIndex = 0;
        for(int i = 0; i < handInventory.size(); i++){
            Item item = handInventory.get(i);
            for(int j = 0; j < item.getWeight(); j++){
                GreenfootImage image = new GreenfootImage(item+".png");
                if(j!=0) image.setTransparency(80);
                addObject(new Display(image), 200*curIndex + 100, 500);
                curIndex++;
            }

        }
        for(int i = curIndex; i < 4; i++){
            GreenfootImage image = new GreenfootImage("hand.png");
            addObject(new Display(image),200*i + 100,500);
        }
    }
    public String [] arrayListToArray(ArrayList<String> array){
        Object[] tempArray = array.toArray();
        String [] newArray = new String [tempArray.length];
        for(int i = 0; i < tempArray.length; i++){
            newArray[i] = (String)tempArray[i];
        }
        return newArray;
    }
}


