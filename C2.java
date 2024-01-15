import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C2 extends Grid
{
    private Map map = ram.getMap();
    World toWorld;
    public C2(Memory ram)
    {
        super(ram);
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
    }
    public C2(Grid world,Memory ram)
    {
        super(world,ram);
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
        
        toWorld = world;
    }
    public void act(){
        if(fisho.touchP(right)){
            map.storeWorld("C2",this);
            
            map.loadWorld("C3",ram);
        }
        else if(fisho.touchP(left)){
            //map.storeWorld("C2",this);
            //map.loadWorld("C1", ram);
            
            Greenfoot.setWorld(new C1(this,ram));
        }
    }
    public void setCoord(){
        map.setPointer("C2");
        map.setX(1);
        map.setY(2);
    }
}
