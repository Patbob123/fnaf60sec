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

    private int currCam;
    private int battery;
    private int maxBattery;

    private GreenfootImage[] bgFrames;  //images for background
    private GreenfootImage[] leftDoorFrames;
    private GreenfootImage[] rightDoorFrames;
    private GreenfootImage[] danielFrames;
    private GreenfootImage[] tyroneFrames;
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

    private double hB = 10.0;
    private double wB = 10.0;
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


    private DynamicLighting fading;


    private GreenfootImage[] openLDoor;
    private GreenfootImage[] openRDoor;

    //private VisionBlock fading;
    
    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(Constants.WW, Constants.WH, 1);
        time = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        currCam = 0;
        bgFrames = new GreenfootImage[24]; 
        for (int i = 0; i < 24; i++) {
            bgFrames[i] = new GreenfootImage("bgFrames/frame" + i + ".JPG");  
        }

        openLDoor = new GreenfootImage[4];
        for (int i = 0; i < 4; i++) {
            openLDoor[i] = bgFrames[i];  
        }
        
        openRDoor = new GreenfootImage[12];
        for (int i = 0; i < 12; i++) {
            openRDoor[i] = bgFrames[bgFrames.length-1];  
        }
        
        leftDoorFrames = new GreenfootImage[6];
        for (int i = 0; i < 6; i++) {
            leftDoorFrames[i] = new GreenfootImage("leftDoorFrames/frame" + i + ".JPG");  
        }

        rightDoorFrames = new GreenfootImage[10];
        for (int i = 0; i < 10; i++) {
            rightDoorFrames[i] = new GreenfootImage("rightDoorFrames/frame" + i + ".JPG");  
        }

        danielFrames = new GreenfootImage[3];
        for (int i = 0; i < 3; i++) {
            danielFrames[i] = new GreenfootImage("leftEnemy/frame" + i + ".JPG");  
        }
        
        tyroneFrames = new GreenfootImage[4];
        for (int i = 0; i < 4; i++) {
            tyroneFrames[i] = new GreenfootImage("rightEnemy/frame" + i + ".JPG");  
        }
        
        camWithEnemy = new Camera[7];
        for (int i = 0; i < 7; i++) {
            camWithEnemy[i] = new Camera(1, true, "Cameras/camera" + (i+1) + ".png");  
        }

        camWithNoEnemy = new Camera[7]; 
        for (int i = 0; i < 7; i++) {
            camWithNoEnemy[i] = new Camera(1, false, "Cameras/camera" + (i+1) + "Empty" + ".png"); 
        }

        currentFrameIndex = 12;  //the middle
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
        maxBattery = 100;
        battery = 1000;

        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 255));
        addObject(batteryBar, 150, 100);

        soundBar = new Bar(10, "energyIcon.png", new Color(0, 255, 0));
        addObject(soundBar, 150, 200);

        camButton = new Button("AAAAAAAAAAAAAAAAAAAAA", 20, true);
        addObject(camButton, 1129, 741);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);

        timer = new SimpleTimer();

        setPaintOrder(Button.class, CameraMap.class, Bar.class, DynamicLighting.class);

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

        hB = -1*Math.pow((1/1.002), -1*(timer.millisElapsed()/1000))+11;
        wB = -1*(1/2)*(timer.millisElapsed()/1000);
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
                battery-=1; 
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
            if(hB < 2){
                //em.setBgStageSix(true);
            }

        } else goToWorld(new endWorld()); //add parameter later on if needed
        //black guy cameras
        if (wB != 0){
            //visionTime = timer.millisElapsed()/10;
            //fading = new VisionBlock (Constants.WW, Constants.WH, visionTime);

            fading = new DynamicLighting (500, 500);
            addObject(fading, 0, 500);

            
            //The entire screen darkens gradually as time elaspses

            //add if statements when the player drinks water => visionTime += 100;
            // if (wB < 8 && wB >6){
            // //
            // }
            // if (wB < 6 && wB >4){

            // }
            // if (wB < 4 && wB >2){

            // }
            // if (wB < 2 && wB >0){

            // }
        }

        addDoorButtons();

        if(!inCameras) {
            checkMouseMovement();
        }
        if(time % 3600 == 0) {
            //switch to next hour on clock
        }
        
        if((battery == 0 && time > 0) || !isAlive) {
            goToWorld(new endWorld());
        }
        if(battery >= 0 && time > 0 && isAlive) {
            //goToWorld(new winWorld());
        }
        soundBar.refresh(Greenfoot.getMicLevel());

    }

    /**
     * Method to add the door buttons only when screen is on 
     * first or last index
     */
    public void addDoorButtons() {
        if(currentFrameIndex == 0) {
            addObject(leftButton, 46, 387);
        } else {
            if(leftButton != null) {
                removeObject(leftButton);
            }
        }

        if(currentFrameIndex == bgFrames.length-1) {
            addObject(rightButton, 1116, 421);
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

            
            if(newIndex == 0 && battery <= 0 && leftDoorClosed == true) {
                for(int i = 0; i < 4; i++ ){
                    bgFrames[i] = openLDoor[i];
                }
                leftDoorClosed = false;
            }
            if(newIndex == bgFrames.length-1 && battery <= 0 && rightDoorClosed == true) {
                for(int i = 0; i < 12; i++ ){
                    bgFrames[bgFrames.length - (i+1)] = openRDoor[i];
                }
                rightDoorClosed = false;
            }

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
                battery -=1;
                for(int i = 0; i < 4; i++ ){
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
            battery -=1;
            for(int i = 0; i < 10; i++ ){
                GreenfootImage temp = bgFrames[bgFrames.length-1];
                bgFrames[bgFrames.length - (i+1)] = rightDoorFrames[i];
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