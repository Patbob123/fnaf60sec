import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Display Lose Screen 
 * 
 * @author Vincent  
 * @version January 2024
 */
public class LoseWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("endWorld.png");
    private Fader fade;
    private int actCounter;
    private boolean jumpScare;
    
    
    public LoseWorld(String enemy)
    {   
        super(Constants.WW, Constants.WH, 1,enemy);
        
        
        GreenfootImage restartBut = new GreenfootImage("restartButton.png");
        restartBut.scale(200,50);
        nextButton = new Presser(goStartWorld, restartBut);
        addObject(nextButton, 600, 450);
            
        setBackground(startBg);
        jumpScare = true;
        
        actCounter = 200;
        
        setPaintOrder(Effect.class, Presser.class);
    }
    public LoseWorld()
    {   
        super(Constants.WW, Constants.WH, 1);
        
        jumpScare = false;
        
        GreenfootImage restartBut = new GreenfootImage("restartButton.png");
        restartBut.scale(200,50);
        nextButton = new Presser(goStartWorld, restartBut);
        addObject(nextButton, 600, 450);     
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
    }
    public void act(){
        actCounter++;
        if(jumpScare){
            if(actCounter >= 150){
                jumpScare = !jumpScare;
                sm.playSound("Scream2");
            }
        }
    }
    public void goToStartWorld(){
        goToWorld(new StartWorld());
    }
    public Function goStartWorld = () -> goToStartWorld();
}
