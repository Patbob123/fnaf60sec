import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class theWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class secondFloor extends World
{
    private Button camButton;
    private Actor camMap= new CameraMap("translucentCamMap.PNG");

    private String cameraName;

    private int numClicks = 2;
    private int camClicks = 0;

    private int CMXOffset = 1015;
    private int CMYOffset = 156;
    
    private int[] camX = {CMXOffset - 63,CMXOffset - 41, CMXOffset + 62, CMXOffset + 23, CMXOffset - 95, CMXOffset + 5, CMXOffset + 94};
    private int[] camY = {CMYOffset - 10, CMYOffset + 24, CMYOffset - 11, CMYOffset + 20, CMYOffset + 54, CMYOffset + 66, CMYOffset + 40};

    Button[] cams = new Button[7];
    
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
    public secondFloor()
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
                    removeCamera();
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
    
    
}
