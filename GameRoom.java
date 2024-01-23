import greenfoot.*;
import java.util.List;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.Arrays;
/**
 * Notes:
 * Make each night 1 minute, from 12am-6am (6mins per night)
 * Have battery percentage drain 1 every 5 seconds
 */
public class GameRoom extends SuperWorld {
    private boolean isAlive;
    private boolean leftDoorClosed;
    private boolean rightDoorClosed;

    private int battery;
    private int maxBattery;

    private GreenfootImage[] bgFrames;  //images for background
    private GreenfootImage[] leftDoorFrames;
    private GreenfootImage[] rightDoorFrames;
    private int currentFrameIndex;
    
    Camera[] camWithEnemy;
    Camera[] camWithNoEnemy;
    
    private int time; 

    //For cameras
    private int CMXOffset = 1015;
    private int CMYOffset = 622;

    private int[] camX = {CMXOffset - 95,CMXOffset - 41, CMXOffset - 63, CMXOffset + 94, CMXOffset + 23, CMXOffset + 62, CMXOffset + 5};
    private int[] camY = {CMYOffset + 54, CMYOffset + 24, CMYOffset - 10, CMYOffset + 40, CMYOffset + 20, CMYOffset - 11, CMYOffset + 66};

    Button[] cams = new Button[7];
    private boolean inCameras;

    private Button camButton;

    private int numClicks = 2;

    private double maxHB = 100.0;
    private double maxWB = 100.0;
    private double hB = 10.0;
    private double wB = 100.0;
    //private double wood = 

    private boolean stage1;
    private boolean stage2;
    private boolean stage3;
    private boolean stage4;
    private boolean openedCamMap = false;
    private boolean expanded = false;

    private SimpleTimer timer;

    private CameraMap camMap;
    private Tile tiles[][];
    private Bar batteryBar;
    private Bar soundBar;
    private EnemyManager em;
    private Presser leftButton;
    private Presser rightButton;
    private Presser foodButton;
    private Presser waterButton;

    private DynamicLightning dynamicLightning;

    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(Constants.WW, Constants.WH, 1);
        time = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        bgFrames = new GreenfootImage[16]; 
        for (int i = 0; i < 16; i++) {
            bgFrames[i] = new GreenfootImage("bgFrames/frame" + i + ".jpg");  
        }
        
        leftDoorFrames = new GreenfootImage[5];
        for (int i = 0; i < 5; i++) {
            leftDoorFrames[i] = new GreenfootImage("leftDoorFrames/frame" + i + ".jpg");  
        }
        
        rightDoorFrames = new GreenfootImage[5];
        for (int i = 0; i < 5; i++) {
            rightDoorFrames[i] = new GreenfootImage("rightDoorFrames/frame" + i + ".jpg");  
        }
        
        camWithEnemy = new Camera[7];
        for (int i = 0; i < 7; i++) {
            camWithEnemy[i] = new Camera(1, true, "Cameras/camera" + (i+1) + ".png");  
        }
        
        camWithNoEnemy = new Camera[7]; 
        for (int i = 0; i < 7; i++) {
            camWithNoEnemy[i] = new Camera(1, false, "Cameras/camera" + (i+1) + "Empty" + ".png"); 
        }
        
        currentFrameIndex = 8;  //the middle
        setBackground(bgFrames[currentFrameIndex]);
        
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        camMap = new CameraMap("translucentCamMapV2.PNG");

        GreenfootImage startBut = new GreenfootImage("startButton.png");
        leftButton = new Presser(leftDoor, startBut);
        rightButton = new Presser(rightDoor, startBut);
        foodButton = new Presser(feed, startBut);
        waterButton = new Presser(drink, startBut);
        addObject(foodButton, 461, 665);
        addObject(waterButton, 731, 665);
        maxBattery = 100;
        battery = 50;
        
        dynamicLightning = new DynamicLightning (Constants.WW, Constants.WH);
        addObject(dynamicLightning, Constants.WW/2, Constants.WH/2);
        
        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 255));
        addObject(batteryBar, 150, 100);

        soundBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 0));
        addObject(soundBar, 150, 200);
        
        camButton = new Button("AAAAAAAAAAAAAAAAAAAAA", 20, true);
        addObject(camButton, 1129, 741);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);

        timer = new SimpleTimer();
        

        setPaintOrder(Button.class, Bar.class, CameraMap.class, DynamicLightning.class);
    }
    

    public void act() {
        time--;

        if(time == 21300) { //spawn them after 30 seconds
            em.setDStageOne(true);
            em.setBgStageOne(true);
        }

        hB = -1*Math.pow((1/1.002), -1*(timer.millisElapsed()/1000))+11;
        wB = -1*(1.0/60)*(timer.millisElapsed()/1000) + 10;
        //System.out.println("water bar: " + wB);
        //bB = -1*(1/3)*(timer.millisElapsed()/1000);

        //System.out.println("time elapsed: " + timer.millisElapsed()/1000);
        //System.out.println("hunger meter: " + hM);
        if(hB != 0.0 && time > 0 || isAlive){
            if (Greenfoot.mousePressed(camButton)){
                if(numClicks == 2){
                    generateCamMap();
                    battery -=1;
                    camButton.updateMe("VVVVVVVVVVVVVVVVVVVVVVV");
                    numClicks--;
                    inCameras = true;
                }else{
                    camButton.updateMe("AAAAAAAAAAAAAAAAAAAAA");
                    numClicks++;
                    removeCamera();
                    clearCams();
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    inCameras = false;
                }
            }
            if(inCameras){
                updateCam();
            }
            if(hB < 8 && hB > 6){
                //System.out.println("Stage 1");
                //add the image of the enemy that is here
            }
            if(hB < 6 && hB > 4){
                //System.out.println("Stage 2");
            }
            if(hB < 4 && hB > 2){
                //System.out.println("Stage 3");
            }
            if(hB < 2 && hB > 0){
                System.out.println("Stage 4");
            }
        } else Greenfoot.setWorld(new endWorld()); //add parameter later on if needed

        //black guy cameras

        if (wB != 0){
            
            dynamicLightning.refresh(100-(int)(((double)wB/maxWB)*100));


        }
        
        addDoorButtons();
        
        if(time % 300 == 0) {
            battery-=10; //temporary. is supposed to be 1
            batteryBar.refresh(battery);
        }
        if(!inCameras) {
            checkMouseMovement();
        }
        if(time % 3600 == 0) {
            //switch to next hour on clock
        }

        if((battery == 0 && time > 0) || !isAlive) {
            //play game over
        }
        if(battery > 0 && time > 0 && isAlive) {
            //play game win
        }
        soundBar.refresh(Greenfoot.getMicLevel());
        
    }
    
    /**
     * Method to add the door buttons only when screen is on 
     * first/second index or last/second last index
     */
    public void addDoorButtons() {
        if(currentFrameIndex == 0 || currentFrameIndex == 1) {
            addObject(leftButton, 119, 387);
        } else {
            if(leftButton != null) {
                removeObject(leftButton);
            }
        }
        
        if(currentFrameIndex == bgFrames.length-1 || currentFrameIndex == bgFrames.length-2) {
            addObject(rightButton, 1048, 421);
        } else {
            if(rightButton != null) {
                removeObject(rightButton);
            }
        }
    }
    
    /**
     * Method to clear the camera images
     */
    public void clearCams() {
        List objects = getObjects(Camera.class);
        if (objects != null) { removeObjects(objects); }
    }
    
    /**
     * Method to remove camera buttons
     */
    public void removeCamera(){
        for (int i = 0; i < cams.length; i++){
            removeObject(cams[i]);
        }
    }
    
    /**
     * Method to update the cameras
     */
    public void updateCam(){
        for (int i = 0; i < cams.length; i++){
            updateCamera(cams[i], i+1, camWithEnemy[i], camWithNoEnemy[i]);
        }
    }
    
    /**
     * Method to check if the mouse is moving 
     */
    private void checkMouseMovement() {
        if (Greenfoot.mouseMoved(null)) {
            int mouseX = Greenfoot.getMouseInfo().getX();
            int newIndex = (mouseX * bgFrames.length) / getWidth(); //calc for new index
            
            //set new background and index
            if (newIndex != currentFrameIndex) {
                setBackground(bgFrames[newIndex]);
                currentFrameIndex = newIndex;
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
     * Set method for battery
     */
    public void setBattery(int battery) {
        this.battery = Math.max(0, Math.min(maxBattery, battery));
    }
    /**
     * Set method for characters life status
     */
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    /**
     * Get method for right door
    */
    public boolean getRightDoor() {
        return rightDoorClosed;
    }
    /**
     * Get method for left door
    */
    public boolean getLeftDoor() {
        return leftDoorClosed;
    }

    /**
     * Gets if player is in cameras
     */
    public boolean getIsInCameras() {
        return inCameras;
    }
    
    /**
     * Method that switches what a specific camera sees
     * 
     * @param camera                    The camera
     * @param stage                     The which stage number enemy is on
     * @param cam                       The image with the enemy present
     * @param empty                     The image with no enemy
     */
    public void updateCamera(Button camera, int stage, Camera cam, Camera empty){
        if (Greenfoot.mousePressed(camera)){
            battery -=1;
            camera.switchExpansion(241, 245, 39, 150);
            clearCams();
            if (camera.isExpanded()){
                if(em.getBgStage(stage)){
                    addObject(cam, getWidth()/2, getHeight()/2);
                }
                if(!em.getBgStage(stage)) {
                    addObject(empty, getWidth()/2, getHeight()/2);
                }
                if(em.getDStage(stage)){
                    addObject(cam, getWidth()/2, getHeight()/2);
                }
                if(!em.getDStage(stage)){
                    addObject(empty, getWidth()/2, getHeight()/2);
                }
            }
        }
    }

    /**
     * Action for left door being pressed
     */
    public Function leftDoor = () -> {
        leftDoorClosed = !leftDoorClosed;
        battery -=1;
        for(int i = 0; i < 5; i++ ){
            GreenfootImage temp = bgFrames[i];
            bgFrames[0 + i] = leftDoorFrames[i];
            leftDoorFrames[i] = temp;
        }
    };
    
    /**
     * Action for right door being pressed
     */
    public Function rightDoor = () -> {
        rightDoorClosed = !rightDoorClosed;
        battery -=1;
        for(int i = 0; i < 5; i++ ){
            GreenfootImage temp = bgFrames[15];
            bgFrames[11 + i] = rightDoorFrames[i];
            rightDoorFrames[i] = temp;
        }
       
    };
    
    public Function feed = () -> {
        
    };
    
    public Function drink = () -> {
        
    };
}