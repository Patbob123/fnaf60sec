import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int height = 3;
    int width = 3;
    public String [][] test = {
        {"f", "f", "f"},
        {"f", "w","f"},
        {"w", "f", "w"}
    };
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1152, 768, 1,false); 
        MapBuilder map = new MapBuilder();
        //addObject(map,0,0);
        Character fisho = new Character();
        addObject(fisho,0,0);
        drawWorld();
        
    }
    public void drawWorld(){
        for(int i = 0; i< height;i++){
            for(int j = 0; j< width; j++){
                if(test[j][i].equals("w")){
                    addObject(new Wall(25,25), (100*i), 100*j);
                }
            }
        }
    }
    public void saveWorld(){
        
    }
    public void loadWorld(){
        
    }
}
