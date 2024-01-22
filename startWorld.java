import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startWorld extends SuperWorld
{   
    private GreenfootImage startBg = new GreenfootImage("start.png");
    private GreenfootImage flipEyesImage = new GreenfootImage("start.png");
    private SimpleTimer timer;
    private Presser nextButton;

    public startWorld()
    {    
        super(1152, 768, 1);
        timer = new SimpleTimer();
        timer.mark();
        
        GreenfootImage startBut = new GreenfootImage("startButton.png");
        nextButton = new Presser(goIntroWorld, startBut);
        addObject(nextButton, 200, 600);
        
        flipEyesImage.drawImage(new GreenfootImage("flipeyes.png"), 0, 0);
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
        
    }
    public void act(){
        super.act();
        if(timer.millisElapsed()>=Constants.EYE_SWITCH){
            setBackground(flipEyesImage);
            timer.mark();
        }
        
        
    }
    public void goToIntroWorld(){
        goToWorld(new tempWorld());
    }
    public Function goIntroWorld = () -> goToIntroWorld();
}
