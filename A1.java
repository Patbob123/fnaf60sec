import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class A1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A1 extends SchoolWorld
{
    /**
     * Constructor for objects of class A1.
     * 
     */
    public A1(WorldManager wManager)
    {
        super(wManager);
        isCreated("A1");
        
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right,1152,384);
    }
    
    public void act(){
        if(fisho.touchP(right)){
            fisho.setLocation(fisho.getX() -100, fisho.getY());
            if(wManager.checkIfCreated("A2")){
                World nextWorld = worldStorage.get("A2");
                Greenfoot.setWorld(nextWorld);
            }
            else{
                Greenfoot.setWorld(new A2(wManager));
            }
        }
    }
}
