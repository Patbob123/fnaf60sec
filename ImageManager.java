import java.util.HashMap;
import greenfoot.GreenfootImage;
import greenfoot.Actor;

/**
 * Write a description of class ImageManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageManager extends Actor
{
    // instance variables - replace the example below with your own
    private HashMap<String, GreenfootImage[]> imageFiles = new HashMap<>();

    
    /**
     * Constructor for objects of class ImageManager
     */
    public ImageManager()
    {
        // Init images
        for (int i = 0; i < 24; i++) {
            bgFrames[i] = new GreenfootImage("bgFrames/frame" + i + ".jpg");  
            if(i < 4) openLDoor[i] = new GreenfootImage("bgFrames/frame" + i + ".jpg");  
            if(i < 12) openRDoor[i] =  new GreenfootImage("bgFrames/frame" + (bgFrames.length-i-1) + ".jpg");  
            if(i < 6) leftDoorFrames[i] = new GreenfootImage("leftDoorFrames/frame" + i + ".jpg");  
            if(i < 10) rightDoorFrames[i] = new GreenfootImage("rightDoorFrames/frame" + i + ".jpg");  
            if(i < 3) danielFrames[i] = new GreenfootImage("leftEnemy/frame" + i + ".jpg");  
            if(i < 4) tyroneFrames[i] = new GreenfootImage("rightEnemy/frame" + i + ".jpg");  
            if(i < 7) camWithEnemy[i] = new Camera(1, true, "Cameras/camera" + (i+1) + ".png"); 
            if(i < 7) camWithNoEnemy[i] = new Camera(1, false, "Cameras/camera" + (i+1) + "Empty" + ".png"); 
        }
        
        //Store all image files into a HashMap for easy retrevial 
        imageFiles.put("bgFrames", new Sound("builderMusic.mp3",50 ));
        imageFiles.put("Jaded", new Sound("Jaded.mp3",50 ));
        imageFiles.put("blast", new Sound("blaster.mp3",20 ));
        imageFiles.put("boom", new Sound("boom.mp3",60 ));
        imageFiles.put("transition", new Sound("transition.mp3",100 ));
        //soundFiles.put("click", new Sound("buttonclick.wav",100 ));
        //soundFiles.put("hover", new Sound("buttonhover.wav",100 ));
        imageFiles.put("blip", new Sound("blip.wav",80));
        imageFiles.put("rain", new Sound("rainambient.mp3",50));
 
        
        setImage(new GreenfootImage(1,1));
    }


}
