import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Display Start Game Screen
 * 
 * @author Dawson
 * @version January 2024
 */
public class startWorld extends SuperWorld
{   
    private GreenfootImage startBg = new GreenfootImage("start.png");
    private GreenfootImage flipEyesImage = new GreenfootImage("start.png");
    private SimpleTimer timer;
    private Presser nextButton;
    
    private boolean soundPlayed;

    public startWorld()
    {    
        super(1152, 768, 1);
        timer = new SimpleTimer();
        timer.mark();
        
        soundPlayed = false;
        
        GreenfootImage startBut = new GreenfootImage("startButton.png");
        nextButton = new Presser(goIntroWorld, startBut);
        addObject(nextButton, 200, 600);
        
        flipEyesImage.drawImage(new GreenfootImage("flipeyes.png"), 0, 0);
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
        
    }
    public void act(){
        super.act();
        if(!soundPlayed){
            soundPlayed = true;
            getSM().playSoundLoop("startMusic");
        }
        if(timer.millisElapsed()>=Constants.EYE_SWITCH){
            setBackground(flipEyesImage);
            timer.mark();
        }
        
        
    }
    public void goToIntroWorld(){
        goToWorld(new gatherRoom());
    }
    public Function goIntroWorld = () -> goToIntroWorld();
}
