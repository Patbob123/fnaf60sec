import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ghf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ghf extends World
{
    Aaa test;
    Player fisho;
    public ghf(Aaa test)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        fisho = new Player();
        addObject(fisho,0,0);
        this.test = test;
        check();
    }
    public void check(){
        if(!test.check("ghf")){
            test.store("ghf",this);
        }
    }
    public void act(){
        if (Greenfoot.mouseClicked(null)){
            World world = test.getHashMap().get("abc");
            System.out.println(world.toString());
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(fisho)){
            if(!test.check("ghf")){
                Greenfoot.setWorld(new def(this,test));
            }
        }
    }
}
