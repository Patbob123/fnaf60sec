import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Display End Game Screen 
 * 
 * @author (your name) 
 * @version January 2024
 */
public class endWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("start.png");
    
    public endWorld()
    {   
        super(Constants.WW, Constants.WH, 1);
        
        
        GreenfootImage startBut = new GreenfootImage("startButton.png");
        nextButton = new Presser(goStartWorld, startBut);
        addObject(nextButton, 200, 600);
        
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
    }
    
    public void goToStartWorld(){
        goToWorld(new startWorld());
    }
    public Function goStartWorld = () -> goToStartWorld();
}
