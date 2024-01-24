import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Display Lose Screen 
 * 
 * @author Vincent  
 * @version January 2024
 */
public class loseWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("endWorld.png");
    private Fader fade;
    
    public loseWorld(String enemy)
    {   
        super(Constants.WW, Constants.WH, 1,enemy);
        
        
        GreenfootImage restartBut = new GreenfootImage("restartButton.png");
        restartBut.scale(200,50);
        nextButton = new Presser(goStartWorld, restartBut);
        addObject(nextButton, 600, 450);
            
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
    }
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
