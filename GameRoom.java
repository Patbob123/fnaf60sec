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
    private int time; 
    
    private GreenfootImage[] bgFrames;  //images for background
    private int currentIndex;

    //For cameras
    private int CMXOffset = 910;
    private int CMYOffset = 603;
    
    private int[] camX = {CMXOffset - 63,CMXOffset - 41, CMXOffset + 62, CMXOffset + 23, CMXOffset - 95, CMXOffset + 5, CMXOffset + 94};
    private int[] camY = {CMYOffset - 10, CMYOffset + 24, CMYOffset - 11, CMYOffset + 20, CMYOffset + 54, CMYOffset + 66, CMYOffset + 40};

    Button[] cams = new Button[7];
    private boolean inCameras;
    Camera[] camWithEnemy;
    Camera[] camWithNoEnemy;

    
    private Button camButton;

    private int numClicks = 2;

    private double hM = 10.0;
    private boolean stage1;
    private boolean stage2;
    private boolean stage3;
    private boolean stage4;
    private boolean openedCamMap = false;
    private boolean expanded = false;

    private SimpleTimer timer;

    private CameraMap camMap;
    private Camera c1, c1Empty, c2, c2Empty, c3, c3Empty, c4, c4Empty, c5, c5Empty, c6, c6Empty, c7, c7Empty;
    private Tile tiles[][];
    private Bar batteryBar;
    private EnemyManager em;
    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(Constants.WW, Constants.WH, 1);
        actCounter = 0;
        time = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;

        bgFrames = new GreenfootImage[7]; //7 bc idk how many images we have
        for (int i = 0; i < 7; i++) {
            bgFrames[i] = new GreenfootImage("bgFrames/frame" + i + ".png");  
        }
        
        camWithEnemy = new Camera[7];
        for (int i = 0; i < 7; i++) {
            camWithEnemy[i] = new Camera(1, true, "Cameras/camera" + (i+1) + ".png");  
        }
        
        camWithNoEnemy = new Camera[7]; 
        for (int i = 0; i < 7; i++) {
            camWithNoEnemy[i] = new Camera(1, false, "Cameras/camera" + (i+1) + "Empty" + ".png"); 
        }
        
        currentIndex = 3;  //the middle
        setBackground(bgFrames[currentIndex]);
        
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        camMap = new CameraMap("translucentCamMap.PNG");

        /**
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
        */
        

        //Presser leftButton = new Presser();
        //Presser rightButton = new Presser();
        
        wall1 = new Wall();
        wall2 = new Wall();

        Door1 = new Door();
        Door2 = new Door();

        maxBattery = 100;
        battery = 50;

        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 255));
        addObject(batteryBar, 1101, 27);

        camButton = new Button("+", 40);
        addObject(camButton, 915, 734);

        em = new EnemyManager();
        addObject(em, getWidth() /2, getHeight()/2);

        timer = new SimpleTimer();

        setPaintOrder(Button.class, Bar.class);
    }

    public void act() {
        time--;

        if(time == 21300) { //spawn them after 30 seconds
            em.setDStageOne(true);
            em.setBgStageOne(true);
        }

        hM = -1*Math.pow((1/1.002), -1*(timer.millisElapsed()/1000))+11;
        //System.out.println("time elapsed: " + timer.millisElapsed()/1000);
        //System.out.println("hunger meter: " + hM);
        if(hM != 0.0){
            if (Greenfoot.mousePressed(camButton)){
                //System.out.println(numClicks);
                if(numClicks == 2){
                    camButton.updateMe("-");
                    generateCamMap();
                    //System.out.println("expanded" + numClicks);
                    numClicks--;
                    inCameras = true;
                }else{
                    camButton.updateMe("+");
                    numClicks++;
                    removeCamera();
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    inCameras = false;
                }
            }
            if(inCameras){
                updateCam();
            }
            if(hM < 8 && hM > 6){
                //System.out.println("Stage 1");
            }
            if(hM < 6 && hM > 4){
                //System.out.println("Stage 2");
            }
            if(hM < 4 && hM > 2){
                //System.out.println("Stage 3");
            }
            if(hM < 2 && hM > 0){
                System.out.println("Stage 4");
            }
        } else Greenfoot.setWorld(new endWorld()); //add parameter later on if needed

        //black guy cameras

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

        
    }

    public void clearCams() {
        List objects = getObjects(Camera.class);
        if (objects != null) { removeObjects(objects); }
    }

    /**
     * Method to check if the mouse is moving 
     */
    public void checkMouseMovement() {
        if (Greenfoot.mouseMoved(null)) {
            int mouseX = Greenfoot.getMouseInfo().getX();

            int newIndex = (mouseX * bgFrames.length) / getWidth(); //calc for new index

            //set new background and index
            if (newIndex != currentIndex) {
                setBackground(bgFrames[newIndex]);
                currentIndex = newIndex;
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

    public void updateCamera(Button camera, int stage, Camera cam, Camera empty){
        if (Greenfoot.mousePressed(camera)){
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
                if(!em.getDStage(stage)) {
                    addObject(empty, getWidth()/2, getHeight()/2);
                }
            }
        }
    }

    private void removeCamera(){
        for (int i = 0; i < cams.length; i++){
            removeObject(cams[i]);
        }
    }

    private void updateCam(){
        for (int i = 0; i < cams.length; i++){
            updateCamera(cams[i], i+1, camWithEnemy[i], camWithNoEnemy[i]);
        }
    }

}