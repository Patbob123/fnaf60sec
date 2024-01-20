import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        
        
        setPaintOrder(SuperSmoothMover.class, Floor.class, Timer.class, Inventory.class);
        gameTimer = new Timer(60);
        addObject(gameTimer,100,100);
        
        bunker = new Shelter();
        addObject(bunker, 500,300);
    }
    public void act(){
        
        if(Greenfoot.isKeyDown("a")){
            if(!p.checkWall(-10, 0)) vp.move(-10, 0);
        }
        if(Greenfoot.isKeyDown("w")){
            if(!p.checkWall(0, -10)) vp.move(0, -10);
        } 
        if(Greenfoot.isKeyDown("s")){
            if(!p.checkWall(0, 10)) vp.move(0, 10);
        } 
        if(Greenfoot.isKeyDown("d")){
            if(!p.checkWall(10, 0)) vp.move(10, 0);
        }
        
        if(gameTimer.getTime() > 0){
            Greenfoot.setWorld(new Crossroads());
        }
    }
    public MapArray getMap(){
        return ma;
    }
    public boolean checkPlayer(int x, int y){
        return getObjectsAt(x, y, Player.class).size()>0;
    }
    public Shelter getShelter(){
        return bunker;
    }
}


