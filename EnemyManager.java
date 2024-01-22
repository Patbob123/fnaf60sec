import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyManager extends Actor
{
    private Enemy blackGuy, daniel;
    private int bgTick, dTick;
    
    private boolean dStageOne;
    private boolean dStageTwo;
    private boolean dStageThree;
    private boolean bgStageOne;
    private boolean bgStageTwo;
    private boolean bgStageThree;  
    private boolean specialStage;
    private int dLocantCounter;
    private int bgLocantCounter;
    
    private int specialStageTimer;
    private int specialRunningTimer;
    private int dLocantThreeTimer;
    private int bgLocantThreeTimer;
    
    public EnemyManager() {
        //Enemies have different movement times 
        bgTick = 360;
        dTick = 600;
        dLocantThreeTimer = 240;
        bgLocantThreeTimer = 240;
        specialStageTimer = 1800;
        specialRunningTimer = 300;
        bgLocantCounter = 1;
        dLocantCounter = 1;
        dStageOne = false;
        dStageTwo = false;
        dStageThree = false;
        bgStageOne = false;
        bgStageTwo = false;
        bgStageThree = false; 
        specialStage= false;
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Get Daniel's location counter
     */
    public int getDLocant() {
        return dLocantCounter;
    }
    /**
     * Get Black Guy's location counter
     */
    public int getBgLocant() {
        return bgLocantCounter;
    }
    /**
     * Set Daniel's stage number
     * @param stage     Set to this number
     */
    public void setDStageOne(boolean stage) {
        this.dStageOne = stage;
    }
    /**
     * Set Black Guy's stage number
     * @param stage     Set to this number
     */
    public void setBgStageOne(boolean stage) {
        this.bgStageOne = stage;
    }
    /**
     * Set Special stage number
     * @param stage     Set to this number
     */
    public void setSpecialStage(boolean stage) {
        this.specialStage = stage;
    }
    
    public boolean getBgStage(int stageNum){
        if(stageNum == 1) {
            return bgStageOne;
        }
        else if(stageNum == 2) {
            return bgStageTwo;
        }
        else {
            return bgStageThree;
        }
    }
    public boolean getDStage(int stageNum){
        if(stageNum == 4) {
            return dStageOne;
        }
        else if(stageNum == 5) {
            return dStageTwo;
        }
        else {
            return dStageThree;
        }
    }
    /**
     * Act - do whatever the EnemyManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bgTick--;
        dTick--;
        
        //If any of the enemies are on stage 3
        if(dLocantCounter == 3) {
            dLocantThreeTimer--;
            if(dLocantThreeTimer == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                int specialSpawn = Greenfoot.getRandomNumber(10);
                if(specialSpawn == 3) {
                    specialStage = true;
                    specialRunningTimer = 300;
                    specialStageTimer = 1800;
                }
                else {
                    dLocantCounter = 1;
                    dStageThree = false;
                    dStageOne = true; 
                    specialStage = false;
                }
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }    
        if(bgLocantCounter == 3) {
            bgLocantThreeTimer--;
            if(bgLocantThreeTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
                bgLocantCounter = 1;
                bgStageThree = false;
                bgStageOne = true;
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }  
        
        if(specialStage){
            if(specialStageTimer == 0) {
                specialRunningTimer--;
                //play running sound in LEFT EAR
            }
            else{
                specialStageTimer--; 
            }
            
        }
        if(specialRunningTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
            ((GameRoom)getWorld()).setAlive(false);
            //play daniel jumpscare
        }
        if(bgTick == 0 && bgLocantCounter != 3) {
            int chance = Greenfoot.getRandomNumber(2);
            if(chance == 1){
                bgLocantCounter++;
                if(bgLocantCounter == 2) {
                    bgStageOne = false;
                    bgStageTwo = true;
                }
                else if(bgLocantCounter == 3) {
                    bgStageTwo = false;
                    bgStageThree = true;
                }
            }
            bgTick = 360;
        }
        if(dTick == 0 && dLocantCounter != 3) {
            dLocantCounter++;
            if(dLocantCounter == 2) {
                bgStageOne = false;
                bgStageTwo = true;
            }
            else if(dLocantCounter == 3) {
                bgStageTwo = false;
                bgStageThree = true;
            }
            dTick = 600;
        }
        
        
    }
    
}
