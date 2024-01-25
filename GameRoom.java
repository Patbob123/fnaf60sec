import greenfoot.*;
import java.util.List;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Main world for floor 2 game play (FNAF style)
 */
public class GameRoom extends SuperWorld {
    private boolean isAlive, leftDoorclosed, rightDoorclosed;

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
    private int[] camY = {CMYOffset + 54, CMYOffset + 24, CMYOffset - 10, CMYOffset + 38, CMYOffset + 20, CMYOffset - 11, CMYOffset + 65};

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
    private ArrayList<Item> itemChest;

    private boolean changedWorld = false;
    private boolean notPlayedSound = false;
    private boolean openedCamMap = false;
    private boolean expanded = false;

    private SimpleTimer timer;

    private CameraMap camMap;
    private Tile tiles[][];
    private Bar batteryBar, soundBar, foodBar, waterBar;
    private EnemyManager em;
    private Presser leftButton, rightButton, foodButton, waterButton;

    private int visionTime;

    private int ratio; 
    private final int ALPHA_CHANNEL = 255;

    private GreenfootImage[] openLDoor;
    private GreenfootImage[] openRDoor;

    //private VisionBlock fading;

    private DynamicLighting fading;
    private Static cameraStatic;

    private boolean soundPlayed; 

    /**
     * Constructor for GameRoom without items
     */
    public GameRoom(){
        this(new ArrayList<Item>());
        ArrayList<Item> itemTest = new ArrayList<>();
        itemTest.add(new Battery());

    }

    /**
     * Constructor for GameRoom with items
     */
    public GameRoom(ArrayList<Item> itemChest) {
        super(Constants.WW, Constants.WH, 1);
        this.itemChest = itemChest;
        time = 21600; // 6 minutes
        inCameras = false;
        leftDoorclosed = false;
        rightDoorclosed = false;
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
        GreenfootImage foodBut = new GreenfootImage("itemSprites/Food.png");
        GreenfootImage waterBut = new GreenfootImage("itemSprites/Water.png");

        Util.scale(foodBut);
        Util.scale(waterBut);

        leftButton = new Presser(leftDoor, doorLeft);
        rightButton = new Presser(rightDoor, doorRight);
        foodButton = new Presser(feed, foodBut);
        waterButton = new Presser(drink, waterBut);
        addObject(foodButton, 461, 665);
        addObject(waterButton, 731, 665);
        
        maxBattery = 100;
        addInventory();
        
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

        camButton = new Button("Open Cameras (-1 J/s)", 20, true);
        addObject(camButton, 1014, 741);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);

        //daniel = em.getDaniel();
        //tyrone = em.getTyrone();

        timer = new SimpleTimer();

        fading = new DynamicLighting (Constants.WW, Constants.WH);
        addObject(fading, Constants.WW/2, Constants.WH/2);

        cameraStatic = new Static();

        isAlive = true;

        soundPlayed = false;

        setPaintOrder(Button.class, CameraMap.class, Bar.class, Presser.class, DynamicLighting.class, Static.class);

    }

    public void act() {
        super.act();
        time--;
        if(!soundPlayed){
            soundPlayed = true;
            getSM().playSoundLoop("phase2ambiance");
        }
        if(time == 21500) {
            getSM().playSound("phoneGuy");
        }

        //Spawn enemies and move them
        if(time == 18900) { // Spawn after 30 seconds
            em.getTyrone().setStage(1);
            em.getDaniel().setStage(1);

        } else if(time < 18900) {
            em.moveEnemies();
        }

        //Check Mic Level
        if(Greenfoot.getMicLevel() >= 10){
            if(Greenfoot.getRandomNumber(2) == 0){
                sm.playSound("ihearyou");
            }
            else{
                sm.playSound("heyboss");
            }
        }

        // Main loop to check for camera activity
        if(time > 0 || isAlive){
            if(time%120 == 0){
                //hunger = -1*Math.pow((1/1.002), -1*(21600/60))+11;
                //water = -1*(1/2)*((21600/60));
                hunger -= 1;
                water -= 1;
            }
            if(ratio != 255){
                ratio = (int)((water/maxWater)*100.0);
                System.out.println("water: " + water);
                System.out.println("maxWater: " + maxWater);
                System.out.println("ratio: " + ratio);
            }else{
                ratio = ALPHA_CHANNEL;                
            }
            fading.refresh(ratio);
            if (Greenfoot.mousePressed(camButton)){
                if(!inCameras){
                    generateCamMap();
                    camButton.updateMe("Close Cameras");
                    inCameras = true;
                    sm.playSound("cameraOpen");
                }else{
                    camButton.updateMe("Open Cameras (-1 J/s)");
                    removeButtons();
                    clearCams();
                    removeObject(cameraStatic);
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    inCameras = false;
                    sm.playSound("cameraClose");
                }
            }

            // Update camera images and decrease power
            if(inCameras){
                if(time%60 == 0){
                    battery -=1;
                }
                for(int i = 0; i < cams.length; i++){
                    if(Greenfoot.mousePressed(cams[i])){

                        cams[i].switchExpansion(243, 239, 51, 65); 

                        System.out.println("h");

                        clearCams();
                        currCam = i+1;
                        checkCam(currCam, em.getTyroneLocation(), em.getDanielLocation());
                        sm.playSound("cameraSwitch");
                        addObject(cameraStatic,0,0);
                    }
                }

            }
        } 

        // If not in camera, move the screen around
        if(!inCameras) {
            checkMouseMovement();
        }

        // End game states
        if(!isAlive && !changedWorld) {
            changedWorld = !changedWorld;
            if(!notPlayedSound){
                notPlayedSound = !notPlayedSound;
                sm.playSound("youdied");
            }
            String killer = em.getKiller();
            goToWorld(new LoseWorld(killer));
        }

        if(time <= 0 && isAlive && !changedWorld) {
            changedWorld = !changedWorld;
            if(!notPlayedSound){
                notPlayedSound = !notPlayedSound;
                sm.playSound("winsound");
            }
            goToWorld(new WinWorld());
        }
        if(time%6 == 0){
            batteryBar.refresh(battery);
            foodBar.refresh(hunger);
            waterBar.refresh(water);
            soundBar.refresh(Greenfoot.getMicLevel());
        }
        addDoorButtons();
    }

    /**
     * Method to add the door buttons only when screen is on 
     * first or last index
     */
    public void addDoorButtons() {
        if(currentFrameIndex < 4) {
            addObject(leftButton, 46, 387);
        } else {
            if(leftButton != null) {
                removeObject(leftButton);
            }
        }

        if(currentFrameIndex > bgFrames.length-4) {
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

    /**
     * Method to check whether or not the camera should display an enemy
     * 
     * @param currCam           Current camera number
     * @param tyroneLocation    Tyrone's location number
     * @param danielLocation    Daniel's location number
     */
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

    /**
     * Method to display camera
     * 
     * @param camNum    display image of certain camera
     * @param isThere   decide whethere to use image with enemy or not
     */
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

            
            if(newIndex == 0 && battery <= 0 && leftDoorclosed) {
                for(int i = 0; i < 4; i++ ){
                    bgFrames[i] = openLDoor[i];
                }
                leftDoorclosed = false;
            }
            if(newIndex == bgFrames.length-1 && battery <= 0 && rightDoorclosed) {
                for(int i = 0; i < 12; i++ ){
                    bgFrames[bgFrames.length - (i+1)] = openRDoor[i];
                }
                rightDoorclosed = false;
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
     * Transfer all items from first floor (tempWorld) to the second floor
     */

    public void addInventory(){
        for(Item i : itemChest){
            switch(i.toString()){
                case "Battery":
                    maxBattery += 30.0;
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

    //Methods to play jumpscare 
    public void danielJumpScare(){
        addObject(new Fader(2,false,"danieljump2.jpg"),Constants.WW/2, Constants.WH/2);
        sm.playSound("Scream2");
        System.out.println("here");
    }

    public void tyroneJumpScare(){
        addObject(new Fader(2,false,"danieljump1.jpg"),Constants.WW/2, Constants.WH/2);
        sm.playSound("Scream1");
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
        return rightDoorclosed;
    }

    /**
     * Get method for left door
     */
    public boolean getLeftDoor() {
        return leftDoorclosed;
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
            
            leftDoorclosed = !leftDoorclosed;
            if(leftDoorclosed){
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
            rightDoorclosed = !rightDoorclosed;
            if(rightDoorclosed){
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
        hunger = hunger+10 > maxFood ? maxFood: hunger+10;
        foodCount--;
    };

    public Function drink = () -> {
        sm.playSound("drinksound");
        water = water+10 > maxWater ? maxWater: water+10;
        waterCount--;
    };

}