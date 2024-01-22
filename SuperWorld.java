import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

/**
 * Superworld is a wrapper class of all the worlds, does not need to be abstract
 * 
 * @author Dawson Li
 * @version November, 24, 2023
 */
public class SuperWorld extends World
{

    protected SoundManager sm;
    protected Fader fade, fadeOut;
    protected int currActs;
    protected boolean goingToWorld;
    private World world;
    
    private HashMap<String, String> fileInfo;
    
    /**
     * Constructor for SuperWorld
     * 
     * 
     */
    public SuperWorld(int width, int height, int pixel)
    {    
        super(width, height, pixel); 
        
        //init arraylist for file info
        fileInfo = new HashMap<String, String>();
        readFile("files/data.txt");
        writeFile("TimSprite","purpletimmysprites.png");
        saveFile("files/data.txt");
        //setting initial values for variables
        currActs = 0;
        goingToWorld = false;
        
        sm = new SoundManager();
        addObject(sm, 0, 0);
        
        setPaintOrder(  
            Presser.class,
            Effect.class,
            SuperSmoothMover.class,
            Enemy.class
        );
        
        //faders
        fade = new Fader ((60*3), false); //60 acts = 1 second, so 3 seconds for fader
        fadeOut = new Fader ((60*2), true);
        
        //add fader object to fade in on creation
        addObject(fade, Constants.WW/2, Constants.WH/2);
    }
    
    /**
     * Loads from Greenfoot Database
     * 
     * @param index
     */
    public String getGreenfootUser(int index){
        if (UserInfo.isStorageAvailable()) {
             UserInfo info = UserInfo.getMyInfo();
             return info.getString(index);
            
         }
         return"";
    }
    
    /**
     * Saves to Greenfoot Database
     * 
     * @param index
     * @param s
     */
    public void writeGreenfootUser(int index, String s){
        if (UserInfo.isStorageAvailable()) {
             UserInfo info = UserInfo.getMyInfo();
             info.setString(index, s);
             info.store();
         }
    }
    
    /**
     * Reads from file
     * 
     * @param index
     */
    public void readFile(String fileName){
        try{
            Scanner fileInp = new Scanner(new File(fileName));
            while(fileInp.hasNextLine()){
                String[] line = fileInp.nextLine().split("=");
                fileInfo.put(line[0], line[1]);
            }
            fileInp.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    /**
     * Gets from file
     * 
     * @param index
     */
    public String getFile(String key){
        return fileInfo.get(key);
    }
    
    /**
     * Writes to file
     * 
     * @param index
     * @param s
     */
    public void writeFile(String key, String s){
        fileInfo.put(key, s);
    }
    /**
     * Saves to file
     * 
     * @param index
     * @param s
     */
    public void saveFile(String fileName){
        try{
            FileWriter writer = new FileWriter(new File(fileName));
            for (Map.Entry<String, String> set : fileInfo.entrySet()) {

                 writer.write(set.getKey() + "=" + set.getValue()+"\n");
                // Printing all elements of a Map
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Play and stop music
     */
    public void started(){
        sm.resumeSounds();
    }
    public void stopped(){
        sm.pauseSounds();
    }
    /**
     * Gets Sound Manager
     * 
     * @return Sound Manager 
     */
    public SoundManager getSM(){
        return sm;
    }
    
    /**
     * Track mouse info for animations
     */
    public void act(){
        if(goingToWorld){
            currActs++;
            //when fader is done, stop sounds and switch worlds
            if(currActs >= fadeOut.getMaxDuration()){
                sm.stopSounds();
                Greenfoot.setWorld(world);
            }
        }
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null) return;
        if(Greenfoot.mouseClicked(null)){
            sm.playSound("blip");//click sound
            Cursor cursorAnim = new Cursor();
            addObject(cursorAnim, mouse.getX(), mouse.getY());
        }
        
    }
    
    /**
     * Adds a transition (fade out of world)
     */
    public void goToWorld(World w){
        goingToWorld = true;
        sm.playSound("transition");
        //add fade out object
        addObject(fadeOut, Constants.WW/2, Constants.WH/2);
        world = w;
    }
    
}