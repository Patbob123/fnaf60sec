import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class introWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends SuperWorld
{
    private GreenfootImage[] introFrames1, introFrames2, introFrames3, allFrames;
    private int act, currIndex, frameWorld, acts;
    private boolean audio;
    private Presser next;
    private GreenfootImage nextButton;
    
    /**
     * Constructor for objects of class introWorld.
     * 
     */
    public IntroWorld()
    {
        super(1152, 768, 1);
        act = 4200; //want 6 acts per frame, pause at begining for fader, pause at end for voiceover
        currIndex = 1;

        audio = false;
        allFrames = new GreenfootImage[42];
        nextButton = new GreenfootImage("nextButton.png");
        next = new Presser(goGatherRoom,nextButton);
        
        for(int i = 0; i < 42; i++){
            allFrames[i]= new GreenfootImage("animation1/anim" + (i+1) + ".png");
        }

    }

    public void act () {

        super.act();
        acts ++;
        act -= 100;
        
        if(act % 18 == 0) {
            currIndex++;
        }
        if(currIndex < 42){
            setBackground(allFrames[currIndex]);
        }
        else{
            GreenfootImage blackBG = new GreenfootImage("transition1.png");
            setBackground(blackBG);
            playAudioOnce();
            addObject(next, 1000, 700);
        }
    }
    public void playAudioOnce(){
        if(!audio){
            audio = !audio;
            sm.playSound("timmyVoice");
        }
    }
    public void playAnimation (int animNum, int index) {
        if(animNum == 1) {
            setBackground(introFrames1[index]);
        }
        if(animNum == 2) {
            setBackground(introFrames2[index]);
        }
        if(animNum == 3) {
            setBackground(introFrames3[index]);
        }
    }
    public void goGatherRoom(){
        goToWorld(new GatherRoom());
    }
    
    public Function goGatherRoom = () -> goGatherRoom();
}