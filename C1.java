import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C1 extends Grid
{
    private Map map = ram.getMap();
    World toWorld = null;
    public C1(Grid world,Memory ram)
    {
        super(world,ram);
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
        
        toWorld = world; 
    }
    public void act(){
        if(fisho.touchP(right)){
            //ram.getMap().loadWorld("C2",ram);
            
            //map.storeWorld("C1",this);
            
            Greenfoot.setWorld(toWorld);
        }
    }
    public void setCoord(){
        map.setPointer("C1");
        map.setX(0);
        map.setY(2);
    }
}

