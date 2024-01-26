import greenfoot.*;
/**
 * Converts GreenfootSounds into ArrayLists to ensure sounds can be repeated multiple times without cooldown
 * 
 * @author Vincent
 * @version January 2024
 */
public class Sound extends Actor
{
    private int soundIndex;
    private GreenfootSound[] sounds;
    private int defaultVolume;
    private int volume;
    private int fadeIn; //0 = none, 1 = fade in, 2 = fade out
    /**
     * Constructor for objects of class Sound
     * 
     * @param soundFile      Directory of the sound
     * @param defaultVolume  The volume of the sound
     */
    public Sound(String soundFile, int defaultVolume)
    {
        this.defaultVolume = defaultVolume;
        volume = defaultVolume;
        soundIndex = 0;
        
        sounds = new GreenfootSound[20];
        // Generate Arraylist for each sound created
        for(int i = 0; i < sounds.length; i++){
            sounds[i] = new GreenfootSound(soundFile);
            sounds[i].setVolume(defaultVolume);
        }
    }
    
    /**
     * Method to play the sound
     */
    public void playSound(){
        // Loop through the arraylist, keep track of the current index
        sounds[soundIndex].play();
        if(soundIndex >= sounds.length-1){
            soundIndex = 0;
        } else {
            soundIndex++;    
        }
    }
    
    /**
     * Method to loop sound
     */
    public void playSoundLoop(){
         sounds[soundIndex].playLoop();
    }
    
    /**
     * Fade in sound
     */
    public void soundFadeIn(){
        this.fadeIn = 1;
        volume = 0;
        sounds[soundIndex].setVolume(volume);
    }
    
    /**
     * Fade out sound
     */
    public void soundFadeOut(){
        this.fadeIn = 2;
        volume = defaultVolume;
        sounds[soundIndex].setVolume(volume);
    }
    
    /**
     * Pause looped sound
     */
    public void pauseSoundLoop(){
        for(GreenfootSound s: sounds){
            s.pause();
        }
    }
    
    /**
     * Stop looped sound
     */
    public void stopSoundLoop(){
        sounds[soundIndex].stop();
    }
    
    /**
     * Gets the index of the sound 
     *
     * @return index number
     */
    public int getSoundIndex(){
        return soundIndex;
    }
    
    /**
     * Checks if sound is playing
     * 
     * @return If playing or not
     */
    public boolean isPlaying(){
        for(int i = 0; i < sounds.length; i++){
            if(sounds[i].isPlaying()) return true;
        }
        return false;
    }
    
    /**
     * Act method
     */
    public void act(){
        if(fadeIn == 1){
            if(volume < defaultVolume){
                volume++;
            }
        }else if(fadeIn == 2){
            if(volume > 0){
                volume--;
            }else{
                stopSoundLoop();
            }
        }
        sounds[soundIndex].setVolume(volume);

    }

}
