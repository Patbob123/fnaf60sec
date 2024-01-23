import greenfoot.*;
import java.util.List;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Notes:
 * Make each night 1 minute, from 12am-6am (6mins per night)
 * Have battery percentage drain 1 every 5 seconds
 */
public class GameRoom extends SuperWorld {
    private boolean isAlive;
    private boolean leftDoorClosed;
    private boolean rightDoorClosed;

    private int currCam;
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

    private double maxWater;
    private double maxHunger;
    private double maxBattery;
    private double maxWood;
    
    private double hunger = 10.0;
    private double water = 10.0;
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
    private int visionTime;
    
    private VisionBlock fading;
    private ArrayList<Item> itemChest;
    
    /**
     * Constructor for GameRoom
     */
    public GameRoom(ArrayList<Item> itemChest) {
        super(Constants.WW, Constants.WH, 1);
        this.itemChest = itemChest;
        time = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        currCam = 0;
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

        GreenfootImage doorLeft = new GreenfootImage("buttons/doorButton1.png");
        GreenfootImage doorRight = new GreenfootImage("buttons/doorButton2.png");
        GreenfootImage food = new GreenfootImage("buttons/foodButton.png");
        GreenfootImage water = new GreenfootImage("buttons/waterButton.png");
        
        leftButton = new Presser(leftDoor, doorLeft);
        rightButton = new Presser(rightDoor, doorRight);
        foodButton = new Presser(feed, food);
        waterButton = new Presser(drink, water);
        addObject(foodButton, 461, 665);
        addObject(waterButton, 731, 665);
        maxBattery = 0;
        battery = 50;

        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 255));
        addObject(batteryBar, 150, 100);

        soundBar = new Bar(10, "energyIcon.png", new Color(0, 255, 0));
        addObject(soundBar, 150, 200);
        
        camButton = new Button("AAAAAAAAAAAAAAAAAAAAA", 20, true);
        addObject(camButton, 1129, 741);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);

        timer = new SimpleTimer();
        
        setPaintOrder(Button.class, CameraMap.class, Bar.class, VisionBlock.class);

    }
    

    public void act() {
        time--;
        if(Greenfoot.isKeyDown("f")) {
            em.setDStageOne(true);
            em.setBgStageOne(true);
        }
        if(Greenfoot.isKeyDown("g")) {
            em.setDStageOne(false);
            em.setDStageTwo(true);
        }
        if(Greenfoot.isKeyDown("h")) {
            em.setDStageTwo(false);
            em.setDStageThree(true);
        }

        if(time == 21000) { //spawn them after 30 seconds
            em.setDStageOne(true);
            em.setBgStageOne(true);
        }
        
        if(leftDoorClosed) {
            battery-=1; //temporary. is supposed to be 1
            batteryBar.refresh(battery);
        }
        if(rightDoorClosed) {
            battery-=1; //temporary. is supposed to be 1
            batteryBar.refresh(battery);
        }
        
        hunger = -1*Math.pow((1/1.002), -1*(21600/60))+11;
        water = -1*(1/2)*((21600/60));
        //bB = -1*(1/3)*(timer.millisElapsed()/1000);

        //System.out.println("time elapsed: " + timer.millisElapsed()/1000);
        //System.out.println("hunger meter: " + hM);
        if(hB != 0.0 && time > 0 || isAlive){
            if (Greenfoot.mousePressed(camButton)){
                if(numClicks == 2){
                    generateCamMap();
                    camButton.updateMe("VVVVVVVVVVVVVVVVVVVVVVV");
                    numClicks--;
                    inCameras = true;
                }else{
                    camButton.updateMe("AAAAAAAAAAAAAAAAAAAAA");
                    numClicks++;
                    removeButtons();
                    clearCams();
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    inCameras = false;
                }
            }
            
            if(inCameras){
                if(time%60 == 0){
                battery -=1;
                }
                batteryBar.refresh(battery);
                if(Greenfoot.mousePressed(cams[0])) {
                    clearCams();
                    currCam = 1;
                    checkCam(currCam, em.getDLocation());
                }
                if(Greenfoot.mousePressed(cams[1])) {
                    clearCams();
                    currCam = 2;
                    checkCam(currCam, em.getDLocation());
                }if(Greenfoot.mousePressed(cams[2])) {
                    clearCams();
                    currCam = 3;
                    checkCam(currCam, em.getDLocation());
                }
                if(Greenfoot.mousePressed(cams[3])) {
                    clearCams();
                    currCam = 4;
                    checkCam(currCam, em.getBgLocation());
                }
                if(Greenfoot.mousePressed(cams[4])) {
                    clearCams();
                    currCam = 5;
                    checkCam(currCam, em.getBgLocation());
                }if(Greenfoot.mousePressed(cams[5])) {
                    clearCams();
                    currCam = 6;
                    checkCam(currCam, em.getBgLocation());
                }
                if(Greenfoot.mousePressed(cams[6])) {
                    clearCams();
                    currCam = 7;
                    checkCam(currCam, em.getDLocation());
                }
                
            }
            
            
        } else goToWorld(new endWorld()); //add parameter later on if needed

        //black guy cameras

        if (water != 0){
            
            fading = new VisionBlock (500, 500, 100);
            addObject(fading, 0, 500);
            
            
            
            //The entire screen darkens gradually as time elaspses
        }
        
        
        
        if(!inCameras) {
            checkMouseMovement();
        }

        if((battery == 0 && time > 0) || !isAlive) {
            //play game over
        }
        if(battery > 0 && time > 0 && isAlive) {
            //play game win
        }
        
        addDoorButtons();
        soundBar.refresh(Greenfoot.getMicLevel());
        
    }
    
    /**
     * Method to add the door buttons only when screen is on 
     * first or last index
     */
    public void addDoorButtons() {
        if(currentFrameIndex == 0) {
            addObject(leftButton, 69, 387);
        } else {
            if(leftButton != null) {
                removeObject(leftButton);
            }
        }
        
        if(currentFrameIndex == bgFrames.length-1) {
            addObject(rightButton, 1092, 421);
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
    public void removeButtons(){
        for (int i = 0; i < cams.length; i++){
            removeObject(cams[i]);
        }
    }
    
    public void checkCam(int currCam, int enemyLocation) {
        if(currCam == enemyLocation) {
            displayCam(currCam, true);
        } else {
            displayCam(currCam, false);
        }
    }
    
    public void displayCam(int camNum, boolean isThere) {
        if(isThere) {
            addObject(camWithEnemy[camNum - 1], getWidth()/2, getHeight()/2);
        } else {
            addObject(camWithNoEnemy[camNum - 1], getWidth()/2, getHeight()/2);
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
            cams[i] = new Button ("CAM" + (i+1), 20, true, i + 1);
            addObject(cams[i], camX[i], camY[i]);
        }
    }
    public void addInventory(){
        for(Item i : itemChest){
            switch(i.toString()){
                case "Battery":
                    maxBattery += 10;
                    break;
                case "Wood":
                    maxWood += 1;
                    break;
                case "Water":
                    maxWater += 10;
                    break;
                case "Food":
                    maxFood += 10;
                    break;
            }
            
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
     * Action for left door being pressed
     */
    public Function leftDoor = () -> {
        if(battery > 0) {
            leftDoorClosed = !leftDoorClosed;
            
            if(time%60 == 0){
                battery -=2;
            }
            for(int i = 0; i < 5; i++ ){
                GreenfootImage temp = bgFrames[i];
                bgFrames[0 + i] = leftDoorFrames[i];
                leftDoorFrames[i] = temp;
            }
            setBackground(bgFrames[0]);
        }
    };
    
    /**
     * Action for right door being pressed
     */
    public Function rightDoor = () -> {
        if(battery > 0) {
            rightDoorClosed = !rightDoorClosed;
            if(time%60 == 0){
                battery -=2;
            }
            for(int i = 0; i < 5; i++ ){
                GreenfootImage temp = bgFrames[15];
                bgFrames[11 + i] = rightDoorFrames[i];
                rightDoorFrames[i] = temp;
            }
            setBackground(bgFrames[bgFrames.length - 1]);
        }
    };
    
    public Function feed = () -> {
        
    };
    
    public Function drink = () -> {
        
    };
}