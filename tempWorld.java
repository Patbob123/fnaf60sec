import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Greenfootworld for Floor 1
 * 
 * @author Dawson 
 * @version January 2024
 */
public class tempWorld extends SuperWorld
{
    private Viewport vp;
    private MapArray ma;
    private Player p;
    private Timer gameTimer;
    private Shelter bunker;
    private Shadow shadow;
    private Bar timerBar;
    private SoundManager sm;
    
    public tempWorld()
    {   
        // Add in Java Viewport to generate the 2d array map of the first floor
        super(Constants.WW, Constants.WH, 1);
        vp = new Viewport(Constants.WW,Constants.WH);
        ma = new MapArray();
        addObject(vp, 0, 0);
        
        p = new Player();
        addObject(p, Constants.WW/2, Constants.WH/2);
        displayHandSlots();
        
        gameTimer = new Timer(60);
        addObject(gameTimer,100,100);
        
        bunker = new Shelter();
        addObject(bunker, 500,300);
        
        shadow = new Shadow();
        addObject(shadow, Constants.WW/2,Constants.WH/2);

        DynamicLighting dynamicLight = new DynamicLighting(Constants.WW,Constants.WH);
        addObject(dynamicLight, Constants.WW/2,Constants.WH/2);
        dynamicLight.refresh(50);
        
        timerBar = new Bar(3600, "clockIcon.png", new Color(55,55,255));
        addObject(timerBar, 200, 70);
        
        GreenfootImage blackBg = new GreenfootImage(Constants.WW, Constants.WH);
        blackBg.fill();
        setBackground(blackBg);
        
        sm = new SoundManager();
        
        // Paint order to z-sort all items on the World
        setPaintOrder(Timer.class, Bar.class, Display.class, Effect.class, SuperSmoothMover.class,Floor.class, Inventory.class);
    }
    
    public void act(){
        if(Greenfoot.getRandomNumber(1000) == 500){
            sm.playSound("whispers");
        }
        inputMove();
        //timerBar.refresh(-gameTimer.getAct());
        if(gameTimer.getAct() > 240){
            sm.playSound("threeSecondsLeft");
        }
        if(gameTimer.getAct() > 0){
            Greenfoot.setWorld(new endWorld());
        }
    }
    
    public void nextPhase(){
        Greenfoot.setWorld(new GameRoom(p.getItemChest()));
    }
    /**
     * Method to create visual display of the player's current inventory slots.
     * It shows how many slots are avaiable and how many slots are used up.
     */
    public void displayHandSlots(){
        ArrayList<Item> handInventory = p.getHandSlots().getStorage();
        for(Display d: getObjects(Display.class)){
            removeObject(d);
        }
        int curIndex = 0;
        for(int i = 0; i < handInventory.size(); i++){
            Item item = handInventory.get(i);
            for(int j = 0; j < item.getWeight(); j++){
                GreenfootImage image = new GreenfootImage("itemSprites/"+item+".png");
                
                //If items take more than one slot, make the additonal images semi-transparent 
                //to indicate the weight belongs to the item
                if(j!=0) image.setTransparency(80); 
                addObject(new Display(image), 200*curIndex + 275, 650);
                curIndex++;
            }
        }
        
        //Create an item icon to display
        for(int i = curIndex; i < 4; i++){
            GreenfootImage image = new GreenfootImage("hand.png");
            addObject(new Display(image),200*i + 275,650);
        }
    }
    
    /**
     * Main Function to move player around the 2d array and update the screen 
     */
    public void inputMove(){
        double moveX = 0;
        double moveY = 0;
        
        // Multiple if statemenst allow for diagonal movements
        if(Greenfoot.isKeyDown("a")){
            moveX+=-p.getSpeed();
        }
        if(Greenfoot.isKeyDown("w")){
            moveY+=-p.getSpeed();
        } 
        if(Greenfoot.isKeyDown("s")){
            moveY+=p.getSpeed();
        } 
        if(Greenfoot.isKeyDown("d")){
            moveX+=p.getSpeed();
        }
        
        double ogMoveX = moveX;
        double ogMoveY = moveY;
        
        
        if(Math.abs(moveX)>=p.getSpeed()&&Math.abs(moveY)>=p.getSpeed()){
            moveX = (int)(Math.sqrt(Math.pow(moveX, 2)/2)+1) * Math.signum(moveX);
            moveY = (int)(Math.sqrt(Math.pow(moveX, 2)/2)+1) * Math.signum(moveY);
        }
        
        //Use viewport to generate render in the nearby tiles
        if(!p.checkWall((int)moveX, (int)moveY)) {
            vp.move(moveX, moveY);
        }else if(!p.checkWall((int)ogMoveX, 0)) {
            moveX = ogMoveX;
            vp.move(moveX, 0);
        }else if(!p.checkWall(0, (int)ogMoveY)) {
            moveY = ogMoveY;
            vp.move(0, moveY);
        }
        
        if(moveX > 0 && moveY < 0){
            p.setCurFrame(2, 3);
            p.setIdle(3);
        }else if(moveX < 0 && moveY < 0){
            p.setCurFrame(2, 5);
            p.setIdle(5);
        }else if(moveX > 0){
            p.setCurFrame(2, 1);
            p.setIdle(1);
        }else if(moveX < 0){
            p.setCurFrame(2, 4);
            p.setIdle(4);
        }else if(moveY > 0){
            p.setCurFrame(2, 0);
            p.setIdle(0);
        }else if(moveY < 0){
            p.setCurFrame(2, 2);
            p.setIdle(2);
        }else if(moveX == 0 && moveY == 0){
            p.setCurFrame(1, 0);
        }
    }
    public boolean checkPlayer(int x, int y){
        return getObjectsAt(x, y, Player.class).size()>0;
    }
    public Shelter getShelter(){
        return bunker;
    }
    public MapArray getMap(){
        return ma;
    }
    public Viewport getVP(){
        return vp;
    }
    public Shadow getShadow(){
        return shadow;
    }
    public SoundManager getSM(){
        return sm;
    }
}


