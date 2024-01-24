import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * One Night at PETHS: Final Project
 * 
 * <p>Set in the local high school of PETHS, little Timmy has to survive from the feral phantoms chasing after him.
 * Forced to seek shelter in PETHS, Timmy must utilize his knowledge of the school map to collect resources and bunker
 * down in his secret hideout, the Business Room. Can Timmy last the night and make to 6am or will he fall victim to 
 * the unrestraint monsters? </p>
 * 
 * <p>
 * Key Features:
 * 
 * <li>
 * 2 playable games 
 * </li>
 * <ul>
 *      <li>
 *       One interactive birds eye view collection game
 *      </li>
 *      <li>
 *       One immersive first person survival game
 *      </li>
 * </ul>
 * 
 * <li>
 *  Self generating map using 2d arrays and file loading/generation
 * </li>
 * 
 * <li>
 * Self made images/ sound effects and jumpscares
 * </li>
 * 
 * <li>
 *  Unique enemy algorithms
 * </li>
 * 
 * <li>
 *  Secret skin sprites and easter eggs
 * </li>
 * 
 * <li>
 *  Smooth camera panning and self updating camera images 
 * </li>
 * 
 * <li>
 *  pesudo 3d effect on birds eye view game    
 * </li>
 * 
 * <li>
 *  Unique Art work for enemies, cameras, world, tile map, sfx, gifs, animations,
 * </li>
 * 
 * <li>
 *  Lots of code is handled in a Manager Class to create modliar and reusable code
 * </li>
 * 
 * Miscellaneous Features: 
 * <li>
 *  Run StartWorld and wait 10 seconds to experience a small easter egg
 * </li>
 * 
 * </ul>
 * </p>
 * 
 * <p>
 * Code:----------------------------------------------------------
 * <ul>
 * Button Code
 * Mr.Cohen's button code from Mr Cohen's SuperUseful Library
 * </ul>
 * <ul>
 * SuperSmoothMover Code
 * Mr.Cohen's SuperSmoothMover code from Mr Cohen's SuperUseful Library
 * </ul>
 * </p>
 * 
 * <p>
 * Graphics:----------------------------------------------------------
 * <ul>
 * Win World Screen
 * ShutterStock you win screen By: Kira Myshura
 * https://www.shutterstock.com/video/clip-1101141465-you-win-text-glitch-effects-concept-video
 * </ul>
 * </p>
 * 
 * Sound Effects:-------------------------------------------------------------
 * <ul>
 * Glitch Sound Effect:
 * By: PremswaroopKasukurthi
 * Link: https://pixabay.com/sound-effects/glitch-sound-effect-12796/ 
 * </ul>
 * 
 * <ul>
 * Item Equip:
 * By: Pixabay
 * Link: https://pixabay.com/sound-effects/item-equip-6904/
 * </ul>
 * 
 * <ul>
 * Water splash (:
 * By: Pixabay
 * Link: https://pixabay.com/sound-effects/water-splash-80537/
 * </ul>
 * 
 * <ul>
 * Footsteps on wood floor:
 * By: Pixabay
 * Link: https://pixabay.com/sound-effects/footsteps-on-wood-floor-14735/
 * </ul>
 * 
 * <ul>
 * Can Pickup:
 * By: Crigence
 * Link: https://pixabay.com/sound-effects/can-pickup-167810/ 
 * </ul>
 * 
 * <ul>
 * Wooden Trunk Latch 1:
 * By: floraphonic
 * Link:https://pixabay.com/sound-effects/wooden-trunk-latch-1-183944/
 * </ul>
 * 
 * <ul>
 * Time Passing Sound Effect (fast clock):
 * By: Phoenix_Connection_Brazil
 * Link: https://pixabay.com/sound-effects/time-passing-sound-effect-fast-clock-108403/
 * </ul>
 * 
 * <ul>
 * transition coat:
 * By: aiko_Changing
 * https://pixabay.com/sound-effects/transition-coat-121423/
 * </ul>
 * 
 * <ul>
 * Camera Open, Close and Switch Sound:
 * By: Scott Coffin
 * https://freddy-fazbears-pizza.fandom.com/wiki/Camera_Monitor/Audio  
 * </ul>
 * 
 * <ul>
 * straw slurp:
 * By: Pixabay
 * https://pixabay.com/sound-effects/straw-slurp-7066/ 
 * </ul>
 * 
 * <ul>
 * Eating Juicy Meat:
 * By: Pixabay
 * https://pixabay.com/sound-effects/eating-juicy-meat-7024/
 * </ul>
 * 
 * <ul>
 * door slam angrily:
 * By: Pixabay
 * https://pixabay.com/sound-effects/door-slam-angrily-86963/
 * </ul>
 * 
 * <ul>
 * door-open-close:
 * By: Pixabay
 * https://pixabay.com/sound-effects/door-open-close-45475/ 
 * </ul>
 * 
 * <ul>
 * FNAF 6AM Sound
 * By: Scott Coffin
 * https://www.myinstants.com/en/instant/fnaf-6-am/
 * </ul>
 * 
 *  <p> 
 * Known Bugs:
 * <ul>
 * - Sometimes if the game is played too many times without recompiling, Java will run out of heap space
 * </ul>
 * <p>
 * 
 * Greenfootworld for Floor 1 where first part of the gameplay takes place
 * 
 * @author Dawson 
 * @version January 2024
 */
public class gatherRoom extends SuperWorld
{
    private Viewport vp;
    private MapArray ma;
    private Player p;
    private Timer gameTimer;
    private Shelter bunker;
    private Shadow shadow;
    private Bar timerBar;
    private SoundManager sm;
    private int woodCounter;
    private boolean unlockedEasterEgg;
    
    /**
     * Constructor for Floor 1 World
     */
    public gatherRoom()
    {   
        // Add in Java Viewport to generate the 2d array map of the first floor
        super(Constants.WW, Constants.WH, 1);
        vp = new Viewport(Constants.WW,Constants.WH);
        ma = new MapArray();
        addObject(vp, 0, 0);
        
        p = new Player();
        addObject(p, Constants.WW/2, Constants.WH/2);
        displayHandSlots();
        
        gameTimer = new Timer(2);
        addObject(gameTimer,100,100);
        
        bunker = new Shelter();
        addObject(bunker, 500,300);
        
        shadow = new Shadow();
        addObject(shadow, Constants.WW/2,Constants.WH/2);

        DynamicLighting dynamicLight = new DynamicLighting(Constants.WW,Constants.WH);
        addObject(dynamicLight, Constants.WW/2,Constants.WH/2);
        dynamicLight.refresh(50);
        
        timerBar = new Bar(3600, "clockIcon.png", new Color(55,55,255));
        addObject(timerBar, 200, 70);
        
        addObject(new AnimatedPopup(), Constants.WW/2, Constants.WH/2);
        
        GreenfootImage blackBg = new GreenfootImage(Constants.WW, Constants.WH);
        blackBg.fill();
        setBackground(blackBg);
        
        sm = new SoundManager();
        woodCounter = 0;
        unlockedEasterEgg = false;
        
        // Paint order to z-sort all items on the World
        setPaintOrder(Timer.class, Bar.class, Display.class, Effect.class, SuperSmoothMover.class,Floor.class, Inventory.class);
    }
    
    public void act(){
        super.act();
        inputMove();
        timerBar.refresh(-gameTimer.getAct());

        if(gameTimer.getAct() > 0){
            goToWorld(new loseWorld());
        }
        if(woodCounter >= 5){
            if(!unlockedEasterEgg){
                unlockPurpleTimmy();
            }
        }
    }
    
    /**
     * Method to go to next phase
     */
    public void nextPhase(){
        goToWorld(new GameRoom(p.getItemChest()));
    }
    
    /**
     * Method to unlock secret Timmy skin
     * Change the skin in the player source code
     */
    public void unlockPurpleTimmy(){
        unlockedEasterEgg = !unlockedEasterEgg;
        writeFile("purpleTimSprites","purpletimmysprites.png");
        saveFile("files/data.txt");
    }
    /**
     * Method to create visual display of the player's current inventory slots.
     * It shows how many slots are avaiable and how many slots are used up.
     */
    public void displayHandSlots(){
        ArrayList<Item> handInventory = p.getHandSlots().getStorage();
        for(Display d: getObjects(Display.class)){
            removeObject(d);
        }
        int curIndex = 0;
        for(int i = 0; i < handInventory.size(); i++){
            Item item = handInventory.get(i);
            for(int j = 0; j < item.getWeight(); j++){
                GreenfootImage image = new GreenfootImage("itemSprites/"+item+".png");
                
                //If items take more than one slot, make the additonal images semi-transparent 
                //to indicate the weight belongs to the item
                if(j!=0) image.setTransparency(80); 
                addObject(new Display(image), 200*curIndex + 275, 650);
                curIndex++;
            }
        }
        
        //Create an item icon to display
        for(int i = curIndex; i < 4; i++){
            GreenfootImage image = new GreenfootImage("hand.png");
            addObject(new Display(image),200*i + 275,650);
        }
    }
    
    /**
     * Main Function to move player around the 2d array and update the screen 
     */
    public void inputMove(){
        double moveX = 0;
        double moveY = 0;
        
        // Mlitiple if statemenst allow for diagonal movements
        if(Greenfoot.isKeyDown("a")){
            moveX+=-p.getSpeed();
        }
        if(Greenfoot.isKeyDown("w")){
            moveY+=-p.getSpeed();
        } 
        if(Greenfoot.isKeyDown("s")){
            moveY+=p.getSpeed();
        } 
        if(Greenfoot.isKeyDown("d")){
            moveX+=p.getSpeed();
        }
        
        double ogMoveX = moveX;
        double ogMoveY = moveY;
        
        
        if(Math.abs(moveX)>=p.getSpeed()&&Math.abs(moveY)>=p.getSpeed()){
            moveX = (int)(Math.sqrt(Math.pow(moveX, 2)/2)+1) * Math.signum(moveX);
            moveY = (int)(Math.sqrt(Math.pow(moveX, 2)/2)+1) * Math.signum(moveY);
        }
        
        //Use viewport to generate render in the nearby tiles
        if(!p.checkWall((int)moveX, (int)moveY)) {
            vp.move(moveX, moveY);
        }else if(!p.checkWall((int)ogMoveX, 0)) {
            moveX = ogMoveX;
            vp.move(moveX, 0);
        }else if(!p.checkWall(0, (int)ogMoveY)) {
            moveY = ogMoveY;
            vp.move(0, moveY);
        }
        
        if(moveX > 0 && moveY < 0){
            p.setCurFrame(2, 3);
            p.setIdle(3);
        }else if(moveX < 0 && moveY < 0){
            p.setCurFrame(2, 5);
            p.setIdle(5);
        }else if(moveX > 0){
            p.setCurFrame(2, 1);
            p.setIdle(1);
        }else if(moveX < 0){
            p.setCurFrame(2, 4);
            p.setIdle(4);
        }else if(moveY > 0){
            p.setCurFrame(2, 0);
            p.setIdle(0);
        }else if(moveY < 0){
            p.setCurFrame(2, 2);
            p.setIdle(2);
        }else if(moveX == 0 && moveY == 0){
            p.setCurFrame(1, 0);
        }
    }
    
    /*
     * Helper methods
     */
    public boolean checkPlayer(int x, int y){
        return getObjectsAt(x, y, Player.class).size()>0;
    }
    public void increaseWoodCounter(){
        woodCounter+=1;
        System.out.println(woodCounter);
    }
    public Shelter getShelter(){
        return bunker;
    }
    public MapArray getMap(){
        return ma;
    }
    public Viewport getVP(){
        return vp;
    }
    public Shadow getShadow(){
        return shadow;
    }
    public SoundManager getSM(){
        return sm;
    }
}


