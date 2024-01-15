import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class abc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class abc extends World
{
    Player fisho;
    World toWorld;
    private boolean a = false;
    Aaa test = new Aaa(); 
    
    public abc()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        fisho = new Player();
        addObject(fisho,0,0);
        check("abc");
    }
    public abc(World inWorld)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        fisho = new Player();
        addObject(fisho,0,0);
        toWorld = inWorld;
        check("abc");
    }
    public void act(){
        if (Greenfoot.mouseClicked(null)){
            test.store("abc",this);
            Greenfoot.setWorld(new def(this,test));
        }
        else if (Greenfoot.isKeyDown("b")){
            if(!test.check("ghf")){
                Greenfoot.setWorld(new ghf(test));
            }
            else{
                Greenfoot.setWorld(test.getHashMap().get("ghf"));
            }
        }
    }
    public void check(String name){
        if(!test.check(name)){
            test.store(name,this);
        }
    }
}
