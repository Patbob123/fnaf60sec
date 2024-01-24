import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import greenfoot.GreenfootSound;

/**
 * Sound Manager is reponsible for storing and retreving all the sound files to allow modular and reusable code in other classes
 * 
 * @author Vincent
 * <p>
 * Modified by: Dawson
 * </p>
 * @version January 2024
 */
public class SoundManager extends Actor
{
    
    private HashMap<String, Sound> soundFiles = new HashMap<>();
    private ArrayList<Sound> playingSounds = new ArrayList<>();
    
    private GreenfootSound[] sounds;
    private int soundsIndex;
    
    /**
     * Constructor for Sound Manager
     */
    public SoundManager()
    {
        //Store all sound files into a HashMap for easy retrevial 
        soundFiles.put("builderMusic", new Sound("builderMusic.mp3",50 ));
        soundFiles.put("Jaded", new Sound("Jaded.mp3",50 ));
        soundFiles.put("blast", new Sound("blaster.mp3",20 ));
        soundFiles.put("boom", new Sound("boom.mp3",60 ));
        soundFiles.put("transition", new Sound("transition.mp3",100 ));
        //soundFiles.put("click", new Sound("buttonclick.wav",100 ));
        //soundFiles.put("hover", new Sound("buttonhover.wav",100 ));
        soundFiles.put("blip", new Sound("blip.wav",80));
        soundFiles.put("rain", new Sound("rainambient.mp3",50));
        soundFiles.put("electricshock", new Sound("lightsout.mp3",50));
        soundFiles.put("battlemusic", new Sound("battlemusic.mp3",50));
        soundFiles.put("bossmusic", new Sound("bossmusic.mp3",50));
        soundFiles.put("enemydeath", new Sound("enemydeath.mp3",50));
        soundFiles.put("youdied", new Sound("youdied.mp3",50));
        soundFiles.put("youwin", new Sound("winsound.mp3",50));
        soundFiles.put("lightFootsteps", new Sound("lightFootsteps.wav",50));
        soundFiles.put("consistentHeavyFootsteps", new Sound("consistentHeavyFootsteps.wav",100));
        soundFiles.put("doorOpen", new Sound("doorOpen.wav",90));
        soundFiles.put("doorClose", new Sound("doorClose.wav",90));
        soundFiles.put("eatingsound", new Sound("eatingsound.wav",100));
        soundFiles.put("drinkSound", new Sound("drinksound.wav",100));
        soundFiles.put("cameraOpen", new Sound("cameraOpen.wav",90));
        soundFiles.put("cameraClose", new Sound("cameraClose.wav",100));
        soundFiles.put("cameraSwitch", new Sound("cameraSwitch.wav",100));
        soundFiles.put("phoneGuy", new Sound("phoneguy.mp3",100));          
        
        soundFiles.put("grabFood", new Sound("firstFloor/grabFood.mp3",100));
        soundFiles.put("grabWood", new Sound("firstFloor/grabWood.mp3",100));
        soundFiles.put("grabWater", new Sound("firstFloor/grabWater.mp3",100));
        soundFiles.put("grabBattery", new Sound("firstFloor/grabBattery.mp3",100));
        soundFiles.put("chestOpen", new Sound("firstFloor/chestOpen.mp3",100));
        soundFiles.put("threeSecondsLeft", new Sound("firstFloor/threeSecondsLeft.mp3",100));
        soundFiles.put("whispers", new Sound("firstFloor/whispers.wav",90));
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Method to add sound manager to world
     * 
     * @param w    World it gets added to
     */
    public void addedToWorld(World w){
        for(Map.Entry<String, Sound> set: soundFiles.entrySet()){
            set.getValue().setImage(new GreenfootImage(1,1));
            getWorld().addObject(set.getValue(), getX(), getY());
        }
    }
    
    /**
     * Methods to access Sound Objects to play sounds
     */
    public void playSound(String sound){
        soundFiles.get(sound).playSound();
    }
    public void playSoundLoop(String sound){
        soundFiles.get(sound).playSoundLoop();        
    }
    
    /**
     * Method to pause sounds
     */
    public void pauseSounds(){
        for(Map.Entry<String, Sound> set: soundFiles.entrySet()){
            if(set.getValue().isPlaying()){
                playingSounds.add(set.getValue());
                set.getValue().pauseSoundLoop();
            }
            
        }
        
    }
    
    /**
     * Method to resume sounds
     */
    public void resumeSounds(){
        
        for(int i = 0; i < playingSounds.size(); i++){
            playingSounds.get(i).playSoundLoop();
        }
        playingSounds.clear();
    }
    
    /**
     * Method to stop sounds
     */
    public void stopSounds(){
        for(Map.Entry<String, Sound> set: soundFiles.entrySet()){
            set.getValue().stopSoundLoop();
        }
    }
    
    /**
     * Method to fade in sounds
     */
    public void fadeInSounds(){
        for(Map.Entry<String, Sound> set: soundFiles.entrySet()){
            set.getValue().soundFadeIn();
        }
    }
    
    /**
     * Method to fade out sounds
     */
    public void fadeOutSounds(){
        for(Map.Entry<String, Sound> set: soundFiles.entrySet()){
            set.getValue().soundFadeOut();
        }
    }
    
    /**
     * Method to play fade in sounds
     * 
     * @param sound     Name of the sound
     */
    public void fadeIn(String sound){
        soundFiles.get(sound).soundFadeIn();
    }
    
    /**
     * Method to play fade out sounds
     * 
     * @param sound     Name of the sound
     */
    public void fadeOut(String sound){
        soundFiles.get(sound).soundFadeOut();
    }
    
}