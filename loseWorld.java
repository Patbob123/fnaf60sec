import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Display End Game Screen 
 * 
 * @author (your name) 
 * @version January 2024
 */
public class loseWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("endWorld.png");
    
    public loseWorld()
    {   
        super(Constants.WW, Constants.WH, 1);
        
        
        GreenfootImage restartBut = new GreenfootImage("restartButton.png");
        restartBut.scale(200,50);
        nextButton = new Presser(goStartWorld, restartBut);
        addObject(nextButton, 600, 450);
        
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
    }
    
    public void goToStartWorld(){
        goToWorld(new startWorld());
    }
    public Function goStartWorld = () -> goToStartWorld();
}
