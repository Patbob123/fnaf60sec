import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startWorld extends World
{   
    Player fisho;
    PressurePlate middle;
    Map map = new Map();
    Memory ram = new Memory(map);
    public startWorld()
    {    
        super(1152, 768, 1);
        fisho = new Player();
        addObject(fisho,0,0);
        middle = new PressurePlate(50,50);
        addObject(middle, 576, 384);
    }
    public void act(){
        if(fisho.touchP(middle)){
            C2 c2 = new C2(ram);
            Greenfoot.setWorld(c2);
        }
    }
}
