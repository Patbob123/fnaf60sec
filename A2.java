import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class A2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A2 extends SchoolWorld
{
    private ResourceScramble bunker;
    /**
     * Constructor for objects of class A2.
     * 
     */
    public A2(WorldManager wManager)
    {
        super(wManager);
        isCreated("A2");
        
        fisho = new Player();
        addObject(fisho,0,0);
        //$left = new PressurePlate(50,50);
        addObject(left,0,384);
        //$right = new PressurePlate(50,50);
        addObject(right,1152,384);
        //$bunker = new ResourceScramble(100,50);
        addObject(bunker,556,192);
    }
    
    public void act(){
        if(fisho.touchP(left)){
            fisho.setLocation(fisho.getX() +100, fisho.getY());
            if(wManager.checkIfCreated("A1")){
                World nextWorld = worldStorage.get("A1");
                Greenfoot.setWorld(nextWorld);
            }
            else{
                Greenfoot.setWorld(new A1(wManager));
            }
        }
        if(fisho.touchP(right)){
            fisho.setLocation(fisho.getX() -100, fisho.getY());
            if(wManager.checkIfCreated("A3")){
                World nextWorld = worldStorage.get("A3");
                Greenfoot.setWorld(nextWorld);
            }
            else{
                Greenfoot.setWorld(new A3(wManager));
            }
        }
    }
}
