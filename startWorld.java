import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startWorld extends SuperWorld
{   
    private GreenfootImage startBg = new GreenfootImage("start.png");
    
    public startWorld()
    {    
        super(1152, 768, 1);
        
        setBackground(startBg);
        //$addObject(fisho,0,0);
        //$middle = new PressurePlate(50,50);
        //$addObject(middle, 576, 384);
    }
    public void act(){
        //$if(fisho.touchP(middle)){
        //    A1 a1 = new A1(wManager);
        //    Greenfoot.setWorld(a1);
        //}
    }
}
