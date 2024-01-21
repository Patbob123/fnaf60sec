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
        ArrayList<String> handInventory = p.getHandSlots().getStorage();
        if(handInventory.size() == 0){
            addObject(new Display(new GreenfootImage("hand.png")),100,500);
            addObject(new Display(new GreenfootImage("hand.png")),300,500);  
            addObject(new Display(new GreenfootImage("hand.png")),500,500); 
            addObject(new Display(new GreenfootImage("hand.png")),700,500); 
        }
        String [] hands = arrayListToArray(handInventory);
        
        for(int i = 0; i< hands.length; i++){
            String item = hands[i];
            GreenfootImage image = new GreenfootImage(item+".png");
            addObject(new Display(image), 200*i + 100, 500);
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


