import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class theWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class theWorld extends World
{
    private Button camButton;
    private Button cam1;
    private Button cam2;
    private Button cam3;
    private Button cam4;
    private Button cam5;
    private Button cam6;
    private Button cam7;
    Actor camMap= new CameraMap();

    private String cameraName;

    private int numClicks = 2;
    private int camClicks = 0;

    private int CMXOffset = 1015;
    private int CMYOffset = 156;

    private Player p;
    private SimpleTimer timer;

    private double hM = 10.0;
    private boolean stage1;
    private boolean stage2;
    private boolean stage3;
    private boolean stage4;
    private boolean openedCamMap = false;
    private boolean expanded = false;

    /**
     * Constructor for objects of class theWorld.
     * 
     */
    public theWorld()
    {    
        super(Constants.WW, Constants.WH, 1);

        camButton = new Button("+", 40);
        addObject(camButton, 1045, 30);
        setPaintOrder(Button.class, CameraMap.class);

        p = new Player();
        addObject(p, Constants.WW/2, Constants.WH/2);

        timer = new SimpleTimer();

    }

    public void act(){ 
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
                    removeObject(cam1);
                    removeObject(cam2);
                    removeObject(cam3);
                    removeObject(cam4);
                    removeObject(cam5);
                    removeObject(cam6);
                    removeObject(cam7);
                    removeObject(camMap);
                    //System.out.println("collapsed" + numClicks);
                    openedCamMap = false;
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

    private void cameras(){
        updateCamera(cam1);
        updateCamera(cam2);
        updateCamera(cam3);
        updateCamera(cam4);
        updateCamera(cam5);
        updateCamera(cam6);
        updateCamera(cam7);
    }

    private void generateCamMap(){
        cam1 = new Button("CAM1", 20);
        cam2 = new Button("CAM2", 20);
        cam3 = new Button("CAM3", 20); 
        cam4 = new Button("CAM4", 20);
        cam5 = new Button("CAM5", 20);
        cam6 = new Button("CAM6", 20);
        cam7 = new Button("CAM7", 20);

        addObject(camMap, CMXOffset, CMYOffset);

        addObject(cam1, CMXOffset - 63, CMYOffset - 10);
        addObject(cam2, CMXOffset - 41, CMYOffset + 24);
        addObject(cam3, CMXOffset + 62, CMYOffset - 11);

        addObject(cam4, CMXOffset + 23, CMYOffset + 20);
        addObject(cam5, CMXOffset - 95, CMYOffset + 54);
        addObject(cam6, CMXOffset + 5, CMYOffset + 66);

        addObject(cam7, CMXOffset + 94, CMYOffset + 40);

    }
}
