import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class introWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class introWorld extends SuperWorld
{
    private GreenfootImage[] introFrames1, introFrames2, introFrames3, allFrames;
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
        
        allFrames = new GreenfootImage[42];
        for(int i = 1; i < 28; i++ ){
            introFrames1[i] = new GreenfootImage("anim1/firstanim" + i + ".png");
            if(i < 9) introFrames2[i] = new GreenfootImage("anim2/secondanim" + i + ".png");
            if(i < 5) introFrames3[i] = new GreenfootImage("anim3/thirdanim" + i + ".png");
        }
        
        
        for(int i = 0; i < 43; i++){
            if(i < 28) allFrames[i]= new GreenfootImage("anim1/firstanim" + (i+1) + ".png");
            if(i < 9) allFrames[i+28] = new GreenfootImage("anim2/secondanim" + (i+1) + ".png");
            if(i < 5) allFrames[i+37] = new GreenfootImage("anim3/thirdanim" + (i+1) + ".png");
        }
        
    }
    
    public void act () {
        
        super.act();
        acts ++;
        act -= 100;
        
        if(act % 6 == 0) {
            currIndex++;
        }
        /*
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
        */
        
        
        if(act == 168) {
            currIndex = 0;
            frameWorld++;
        }
        if(currIndex < 42){
            setBackground(allFrames[currIndex]);
        }
        else{
            GreenfootImage blackBG = new GreenfootImage(Constants.WW, Constants.WH);
            blackBG.fill();
            setBackground(blackBG);
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
    
    public void goToTempWorld(){
        goToWorld(new tempWorld());
    }
}