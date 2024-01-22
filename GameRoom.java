import greenfoot.*;
import java.util.List;
/**
 * Notes:
 * Make each night 1 minute, from 12am-6am (6mins per night)
 * Have battery percentage drain 1 every 5 seconds
 */
public class GameRoom extends World {
    private int backgroundX;
    private int backgroundSpeed;
    private Wall wall1;
    private Wall wall2;
    private Door Door1;
    private Door Door2;
    private boolean isAlive;
    private boolean leftDoorClosed;
    private boolean rightDoorClosed;
    private int battery;
    private int maxBattery;
    
    private int actCounter;
    private int timer; 
    
    //For cameras
    private int CMXOffset = 1015;
    private int CMYOffset = 156;
    
    private int[] camX = {CMXOffset - 63,CMXOffset - 41, CMXOffset + 62, CMXOffset + 23, CMXOffset - 95, CMXOffset + 5, CMXOffset + 94};
    private int[] camY = {CMYOffset - 10, CMYOffset + 24, CMYOffset - 11, CMYOffset + 20, CMYOffset + 54, CMYOffset + 66, CMYOffset + 40};
    
    Button[] cams = new Button[7];
    private boolean inCameras;
    private boolean expanded = false;
    
    private Button map;
    private Button cam1;
    private Button cam2;
    private Button cam3;
    private Button cam4;
    private Button cam5;
    private Button cam6;
    private Button cam7;
    private int numClicks = 2;
    private CameraMap camMap;
    private Camera c1, c1Empty, c2, c2Empty, c3, c3Empty, c4, c4Empty, c5, c5Empty, c6, c6Empty, c7, c7Empty;
    private Tile tiles[][];
    private BatteryBar batteryBar;
    private EnemyManager em;
    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(1152, 768, 1);
        actCounter = 0;
        timer = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        camMap = new CameraMap("translucentCamMap.PNG");
        c1 = new Camera(1, true, "Cameras/camera1.png");
        c1Empty = new Camera(1, false, "Cameras/camera1Empty.png");
        c2 = new Camera(1, true, "Cameras/camera2.png");
        c2Empty = new Camera(1, false, "Cameras/camera2Empty.png");
        c3 = new Camera(1, true, "baby2.png");
        c3Empty = new Camera(1, false, "baby2.png");
        c4 = new Camera(1, true, "baby2.png");
        c4Empty = new Camera(1, false, "baby2.png");
        c5 = new Camera(1, true, "baby2.png");
        c5Empty = new Camera(1, false, "baby2.png");
        c6 = new Camera(1, true, "baby2.png");
        c6Empty = new Camera(1, false, "baby2.png");
        c7 = new Camera(1, true, "baby2.png");
        c7Empty = new Camera(1, false, "baby2.png");
        
        backgroundX = 100; //to set at middle
        setBackground(backgroundImage);
        updateBackgroundPosition();

        wall1 = new Wall();
        wall2 = new Wall();
        
        Door1 = new Door();
        Door2 = new Door();
        
        maxBattery = 100;
        battery = 50;
        
        batteryBar = new BatteryBar(battery);
        addObject(batteryBar, 1101, 27);
    
        map = new Button("+", 40);
        addObject(map, 915, 734);
        
        backgroundSpeed = 96;
        backgroundX = (backgroundImage.getWidth() - getWidth()) / 2;
        
        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);
        setPaintOrder(Button.class, BatteryBar.class);
    }
    
    
    public void act() {
        timer--;
        
        if(timer == 21300) { //spawn them after 30 seconds
            em.setDStageOne(true);
            em.setBgStageOne(true);
        }
        
        //to open and reopen the cameras
        if (Greenfoot.mousePressed(map)){
            if(numClicks == 2){
                map.updateMe("-");
                generateCamMap();
                numClicks--;
                inCameras = true;
            }else{
                map.updateMe("+");
                numClicks++;
                removeObject(cam1);
                removeObject(cam2);
                removeObject(cam3);
                removeObject(cam4);
                removeObject(cam5);
                removeObject(cam6);
                removeObject(cam7);
                removeObject(camMap);
                List objects = getObjects(Camera.class);
                if (objects != null) { removeObjects(objects); }
                inCameras = false;
            }
        }
        
        //black guy cameras
        if(Greenfoot.mousePressed(cam1)) {
            clearCams();
            if(em.getBgStage(1)){
                addObject(c1, getWidth()/2, getHeight()/2);
            }
            if(!em.getBgStage(1)) {
                addObject(c1Empty, getWidth()/2, getHeight()/2);
            }
        }
        if(Greenfoot.mousePressed(cam2)) {
            clearCams();
            if(em.getBgStage(2)){
                addObject(c2, getWidth()/2, getHeight()/2);
            }
            if(!em.getBgStage(2)){
                addObject(c2Empty, getWidth()/2, getHeight()/2);
            }
        }
        if(Greenfoot.mousePressed(cam3)) {
            clearCams();
            if(em.getBgStage(3)){
                addObject(c3, getWidth()/2, getHeight()/2);
            }
            if(!em.getBgStage(3)){
                addObject(c3Empty, getWidth()/2, getHeight()/2);
            }
        }

        //for daniel cameras
        if(Greenfoot.mousePressed(cam4)) {
            clearCams();
            if(em.getDStage(4)){
                addObject(c4, getWidth()/2, getHeight()/2);
            }
            if(!em.getDStage(4)){
                addObject(c4Empty, getWidth()/2, getHeight()/2);
            }
        }
        if(Greenfoot.mousePressed(cam5)) {
            clearCams();
            if(em.getDStage(5)){
                addObject(c5, getWidth()/2, getHeight()/2);
            }
            if(!em.getDStage(5)){
                addObject(c5Empty, getWidth()/2, getHeight()/2);
            }
        }
        if(Greenfoot.mousePressed(cam6)) {
            clearCams();
            if(em.getDStage(6)){
                addObject(c3, getWidth()/2, getHeight()/2);
            }
            if(!em.getDStage(6)){
                addObject(c6Empty, getWidth()/2, getHeight()/2);
            }
        }
        
        //for daniel??
        if(Greenfoot.mousePressed(cam7)) {
            clearCams();
            if(em.getDStage(7)){
                addObject(c7, getWidth()/2, getHeight()/2);
            }
            if(!em.getDStage(7)){
                addObject(c7Empty, getWidth()/2, getHeight()/2);
            }           
        }
        
        if(timer % 300 == 0) {
            battery-=10; //temporary. is supposed to be 1
            batteryBar.updateBar(battery);
        }
        if(!inCameras) {
            checkMouseMovement();
        }
        if(timer % 3600 == 0) {
            //switch to next hour on clock
        }
        
        if((battery == 0 && timer > 0) || !isAlive) {
            //play game over
        }
        if(battery > 0 && timer > 0 && isAlive) {
            //play game win
        }
        
        
        
    }
    
    public void clearCams() {
        List objects = getObjects(Camera.class);
        if (objects != null) { removeObjects(objects); }
    }
    
    /**
     * Method to check if the mouse is moving 
     */
    private void checkMouseMovement() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            int mouseXPos = mouse.getX();
            int deltaX = mouseXPos - getWidth() / 2; 

            //move speed
            backgroundX += deltaX * backgroundSpeed / getWidth(); 
            
            //so that movement doesn't go past image size
            int maxOffset = getBackground().getWidth() - getWidth();
            backgroundX = Math.max(0, Math.min(maxOffset, backgroundX));
            
            updateBackgroundPosition();
            if (mouseXPos > getWidth() / 2) {
                if(backgroundX > 30) {
                    if(backgroundX > 101){
                    addObject(wall2, getWidth() - 100 , getHeight() / 2);
                    removeObject(wall1);
                }
                    else wall1.setLocation(wall1.getX() - 5, wall1.getY());
                }
                
            } else if (mouseXPos < getWidth() / 2) {
                if(backgroundX < 169) {
                    if(backgroundX < 98){
                        addObject(wall1, getWidth() - 1052, getHeight() / 2);
                        removeObject(wall2);
                    }
                    else {
                        wall2.setLocation(wall2.getX() + 5, wall2.getY());
                    }
                }
                
            }
        }
        
    }
    /**
     * Method to generate layout of cameras
     */
    public void generateCamMap(){
        addObject(camMap, CMXOffset, CMYOffset);
        for (int i = 0; i < cams.length; i++){
            cams[i] = new Button ("CAM" + (i+1), 20);
            addObject(cams[i], camX[i], camY[i]);
        }
    }
    
    /**
     * Method to update the background 
     */
    private void updateBackgroundPosition() {
        GreenfootImage background = getBackground();
        background.clear(); 
    
        GreenfootImage newBackground = new GreenfootImage("businessroom.png");
        background.drawImage(newBackground, -backgroundX, 0); 
        
        setBackground(background);
    }
    
    
    /**
     * Set method for battery
     */
    public void setBattery(int battery) {
        this.battery = Math.max(0, Math.min(maxBattery, battery));
    }
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    
    public boolean getRightDoor() {
        return rightDoorClosed;
    }
    public boolean getLeftDoor() {
        return leftDoorClosed;
    }
    /**
     * Gets if player is in cameras
     */
    public boolean getIsInCameras() {
        return inCameras;
    }
    
    private void updateCamera(Button camera){
        if (Greenfoot.mousePressed(camera)){
            camera.switchExpansion(241, 245, 39, 150);
            if (camera.isExpanded()){
                System.out.println("cam" + camera + " is expanded");
            }else{
                System.out.println("cam" + camera + " is collapsed");
            }
        }
    }
    
    private void removeCamera(){
        for (int i = 0; i < cams.length; i++){
            removeObject(cams[i]);
        }
    }
    
    private void cameras(){
        for (int i = 0; i < cams.length; i++){
            updateCamera(cams[i]);
        }
    }

    private void generateCamMap(){
        addObject(camMap, CMXOffset, CMYOffset);
        for (int i = 0; i < cams.length; i++){
            cams[i] = new Button ("CAM" + (i+1), 20);
            addObject(cams[i], camX[i], camY[i]);
        }
    }
}