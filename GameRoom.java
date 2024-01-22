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

    //For cameras
    private int CMXOffset = 1015;
    private int CMYOffset = 156;

    private int[] camX = {CMXOffset - 63,CMXOffset - 41, CMXOffset + 62, CMXOffset + 23, CMXOffset - 95, CMXOffset + 5, CMXOffset + 94};
    private int[] camY = {CMYOffset - 10, CMYOffset + 24, CMYOffset - 11, CMYOffset + 20, CMYOffset + 54, CMYOffset + 66, CMYOffset + 40};

    Button[] cams = new Button[7];
    private boolean inCameras;

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

        batteryBar = new Bar(maxBattery, "energyIcon.png", new Color(0, 255, 255));
        addObject(batteryBar, 1101, 27);

        camButton = new Button("+", 40);
        addObject(camButton, 915, 734);

        backgroundSpeed = 96;
        backgroundX = (backgroundImage.getWidth() - getWidth()) / 2;

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
                    openedCamMap = true;
                }else{
                    camButton.updateMe("+");
                    numClicks++;
                    removeCamera();
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    openedCamMap = false;
                    inCameras = false;
                }
            }
            if(openedCamMap){
                cameras();
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
                if(em.getBgStage(1)){
                    addObject(c1, getWidth()/2, getHeight()/2);
                }
                if(!em.getBgStage(1)) {
                    addObject(c1Empty, getWidth()/2, getHeight()/2);
                }
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

}