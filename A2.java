import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class A2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A2 extends SchoolWorld
{

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
        left = new PressurePlate(50,50);
        addObject(left,0,384);
        right = new PressurePlate(50,50);
        addObject(right,1152,384);
    }
    
    public void act(){
        if(!justTeleported && fisho.touchP(left)){
            fisho.setLocation(fisho.getX() +100, fisho.getY());
            if(wManager.checkIfCreated("A1")){
                World nextWorld = worldStorage.get("A1");
                Greenfoot.setWorld(nextWorld);
            }
            else{
                Greenfoot.setWorld(new A1(wManager));
            }
        }
        if(!justTeleported && fisho.touchP(right)){
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
