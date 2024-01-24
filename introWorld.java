import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class introWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class introWorld extends SuperWorld
{
    private GreenfootImage[] introFrames1, introFrames2, introFrames3;
    private int act, currIndex, frameWorld, acts;
    /**
     * Constructor for objects of class introWorld.
     * 
     */
    public introWorld()
    {
        super(1152, 768, 1);
        act = 4200; //want 6 acts per frame, pause at begining for fader, pause at end for voiceover
        currIndex = 1;
        frameWorld = 1;
        introFrames1 = new GreenfootImage[28];
        introFrames2 = new GreenfootImage[9];
        introFrames3 = new GreenfootImage[5];
        
        for(int i = 1; i <= 28; i++ ){
            introFrames1[i] = new GreenfootImage("anim1/firstanim" + i + ".png");
            if(i <= 9) introFrames2[i] = new GreenfootImage("anim2/secondanim" + i + ".png");
            if(i <= 5) introFrames3[i] = new GreenfootImage("anim3/thirdanim" + i + ".png");
        }
        
    }
    
    public void act () {
        
        super.act();
        acts ++;
        act -= 100;
        
        if(act % 6 == 0) {
            currIndex++;
        }
        switch(frameWorld){
            case 1:
                playAnimation(1, currIndex);
                break;
            case 2:
                playAnimation(2, currIndex);
                break;
            case 3:
                playAnimation(3, currIndex);
                break;
        }
        
        if(act == 168) {
            currIndex = 0;
            frameWorld++;
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
    
    public void goToTempWorld(){
        goToWorld(new tempWorld());
    }
}
