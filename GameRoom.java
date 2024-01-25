import greenfoot.*;
import java.util.List;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 */
public class GameRoom extends SuperWorld {
    private boolean isAlive, leftDoorClosed, rightDoorClosed;

    private int currCam;

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
    

    private int camsOffset = 0;
    
    private int[] camX = {CMXOffset - 95 + camsOffset,CMXOffset - 41 + camsOffset, CMXOffset - 63 + camsOffset, CMXOffset + 94 + camsOffset, CMXOffset + 23 + camsOffset, CMXOffset + 62 + camsOffset, CMXOffset + 5 + camsOffset};
    private int[] camY = {CMYOffset + 54, CMYOffset + 24, CMYOffset - 10, CMYOffset + 40, CMYOffset + 20, CMYOffset - 11, CMYOffset + 66};

    Button[] cams = new Button[7];

    private boolean inCameras;

    private Button camButton;

    private int numClicks = 2;

    private double maxWater, maxBattery, maxWood, maxFood;
    
    private double hunger = 0.0;
    private double water = 0.0;
    private double wood;
    private double battery;

    private int waterCount;
    private int foodCount;
    
    private boolean changedWorld = false;
    private boolean openedCamMap = false;
    private boolean expanded = false;

    private SimpleTimer timer;

    private CameraMap camMap;
    private Tile tiles[][];
    private Bar batteryBar, soundBar, foodBar, waterBar;
    private EnemyManager em;
    private Presser leftButton, rightButton, foodButton, waterButton;
    private SoundManager sm;
    
    private int visionTime;

    private int ratio; 
    private final int ALPHA_CHANNEL = 255;


    private GreenfootImage[] openLDoor;
    private GreenfootImage[] openRDoor;

    //private VisionBlock fading;
    
    private DynamicLighting fading;
    private ArrayList<Item> itemChest;
    
    //private Enemy daniel, tyrone;
    
    public GameRoom(){
        this(new ArrayList<Item>());
        ArrayList<Item> itemTest = new ArrayList<>();
        itemTest.add(new Battery());
        
    }
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
        bgFrames = new GreenfootImage[24]; 
        openLDoor = new GreenfootImage[4];
        openRDoor = new GreenfootImage[12];
        leftDoorFrames = new GreenfootImage[6];
        rightDoorFrames = new GreenfootImage[10];
        danielFrames = new GreenfootImage[3];
        tyroneFrames = new GreenfootImage[4];
        camWithEnemy = new Camera[7];
        camWithNoEnemy = new Camera[7]; 
        
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

        currentFrameIndex = 12;  //the middle
        setBackground(bgFrames[currentFrameIndex]);

        camMap = new CameraMap("translucentCamMapV2.PNG");

        GreenfootImage doorLeft = new GreenfootImage("buttons/doorButton1.png");
        GreenfootImage doorRight = new GreenfootImage("buttons/doorButton2.png");
        GreenfootImage foodBut = new GreenfootImage("buttons/foodButton.png");
        GreenfootImage waterBut = new GreenfootImage("buttons/waterButton.png");
        
        leftButton = new Presser(leftDoor, doorLeft);
        rightButton = new Presser(rightDoor, doorRight);
        foodButton = new Presser(feed, foodBut);
        waterButton = new Presser(drink, waterBut);
        addObject(foodButton, 461, 665);
        addObject(waterButton, 731, 665);
        
        addInventory();
        maxBattery = 1000;
        battery = maxBattery;
        
        maxFood = 100;
        hunger = maxFood;
        maxWater = 100;
        water = maxWater;
        
        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(191, 205, 50));
        addObject(batteryBar, 150, 100);
        foodBar = new Bar(maxFood, "meatIcon.png", new Color(255, 0, 0));
        addObject(foodBar, 150, 200);
        waterBar = new Bar(maxWater, "waterIcon.png", new Color(0, 255, 255));
        addObject(waterBar, 150, 300);
        
        soundBar = new Bar(10, "soundIcon.png", new Color(0, 255, 0));
        addObject(soundBar, 150, 707);

        camButton = new Button("AAAAAAAAAAAAAAAAAAAAA", 20, true);
        addObject(camButton, 1014, 741);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);
        
        //daniel = em.getDaniel();
        //tyrone = em.getTyrone();

        timer = new SimpleTimer();

        fading = new DynamicLighting (Constants.WW, Constants.WH);
        addObject(fading, Constants.WW/2, Constants.WH/2);
        
        sm = new SoundManager();
        isAlive = true;

        setPaintOrder(Button.class, CameraMap.class, Bar.class, Presser.class, DynamicLighting.class);

    }

    public void act() {
        super.act();
        time--;
        if(time == 21500) {
            sm.playSound("phoneGuy");
        }

        if(time == 18900) { //spawn them after 30 seconds
            em.getTyrone().setStage(1);
            em.getDaniel().setStage(1);
            
        } else if(time < 21000) {
            em.moveEnemies();
        }
        
        hunger = -1*Math.pow((1/1.002), -1*(21600/60))+11;
        water = -1*(1/2)*((21600/60));
        //bB = -1*(1/3)*(timer.millisElapsed()/1000);
        
        //System.out.println("time elapsed: " + timer.millisElapsed()/1000);
        //System.out.println("hunger meter: " + hM);
        if(time > 0 || isAlive){
            if(time%120 == 0){
                maxFood -=1;
                maxWater -=1;
            }
            if (Greenfoot.mousePressed(camButton)){
                if(!inCameras){
                    generateCamMap();
                    camButton.updateMe("VVVVVVVVVVVVVVVVVVVVVVV");
                    inCameras = true;
                    sm.playSound("cameraOpen");
                }else{
                    camButton.updateMe("AAAAAAAAAAAAAAAAAAAAA");
                    removeButtons();
                    clearCams();
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    inCameras = false;
                    sm.playSound("cameraClose");
                }
            }

            if(inCameras){
                if(time%60 == 0){
                    battery -=1;
                }
                for(int i = 0; i < cams.length; i++){
                    if(Greenfoot.mousePressed(cams[i])){
                        System.out.println("h");
                        
                        clearCams();
                        currCam = i+1;
                        checkCam(currCam, em.getTyroneLocation(), em.getDanielLocation());
                        sm.playSound("cameraSwitch");
                    }
                }

            }
        } 
        
        if(!inCameras) {
            checkMouseMovement();
        }
        
        if(!isAlive && !changedWorld) {
            changedWorld = !changedWorld;
            goToWorld(new endWorld());
        }
        
        if(time < 0 && isAlive) {
            //goToWorld(new winWorld());
        }
        if(time%6 == 0){
            batteryBar.refresh(battery);
            foodBar.refresh(foodCount);
            waterBar.refresh(waterCount);
            soundBar.refresh(Greenfoot.getMicLevel());
        }
        addDoorButtons();
        
        
        
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

    public void checkCam(int currCam, int tyroneLocation, int danielLocation){
        System.out.println(currCam);
        System.out.println(tyroneLocation);
        System.out.println(danielLocation);
        if(currCam == tyroneLocation || currCam == danielLocation) {
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
            cams.switchExpansion(243, 239, 51, 50); 
        }
    }
    
    public void addInventory(){
        for(Item i : itemChest){
            switch(i.toString()){
                case "Battery":
                    maxBattery += 10.0;
                    break;
                case "Wood":
                    maxWood += 1.0;
                    break;
                case "Water":
                    waterCount++;
                    break;
                case "Food":
                    foodCount++;
                    break;
            }
            
        }
    }
    
    /**
     * Set method for battery
     */
    public void setBattery(double battery) {
        this.battery = battery;
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
            if(leftDoorClosed){
                sm.playSound("doorClose");
            }
            else{
                sm.playSound("doorOpen");
            }
            if(time%60 == 0){
                battery -=2;
            }
            for(int i = 0; i < 5; i++ ){
                GreenfootImage temp = bgFrames[i];
                bgFrames[i] = leftDoorFrames[i];
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
            if(rightDoorClosed){
                sm.playSound("doorClose");
            }
            else{
                sm.playSound("doorOpen");
            }
            if(time%60 == 0){
                battery -=2;
            }
            for(int i = 0; i < 10; i++ ){
                GreenfootImage temp = bgFrames[bgFrames.length - i-1];
                bgFrames[bgFrames.length - i-1] = rightDoorFrames[i];
                rightDoorFrames[i] = temp;
            }
            setBackground(bgFrames[bgFrames.length - 1]);
        }
    };

    public Function feed = () -> {
        sm.playSound("eatingsound");
    };

    public Function drink = () -> {
        sm.playSound("drinkSound");
        hunger = hunger+10 > maxFood ? maxFood: hunger+10;
        foodCount--;
    };

}