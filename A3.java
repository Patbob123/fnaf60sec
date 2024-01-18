import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class A3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class A3 extends SchoolWorld
{

    /**
     * Constructor for objects of class A3.
     * 
     */
    public A3(WorldManager wManager)
    {
        super(wManager);
        isCreated("A3");
        
        fisho = new Player();
        addObject(fisho,0,0);
        //$left = new PressurePlate(50,50);
        addObject(left,0,384);
    }
    
    public void act(){
        if(fisho.touchP(left)){
            fisho.setLocation(fisho.getX() +100, fisho.getY());
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
