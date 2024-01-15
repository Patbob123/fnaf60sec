import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Grid extends World
{
    Player fisho;
    PressurePlate left;
    PressurePlate right;
    Memory ram; 
    public Grid(Memory ram)
    {    
        super(1152, 768, 1);
        this.ram = ram;
    }
    public Grid(Grid world,Memory ram)
    {    
        super(1152, 768, 1);
        this.ram = ram;
    }
}
