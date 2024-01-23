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
    
    private int dLocation;
    private int bgLocation;
    private int specialStageTimer;
    private int specialRunningTimer;
    private int dLocantThreeTimer;
    private int bgLocantThreeTimer;
    
    public EnemyManager() {
        //Enemies have different movement times 
        bgTick = 360;
        dTick = 600;
        dLocation = 0;
        bgLocation = 0;
        dLocantThreeTimer = 240;
        bgLocantThreeTimer = 240;
        specialStageTimer = 1800;
        specialRunningTimer = 300;
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
     * Set Daniel's stage number
     * @param stage     Set to this number
     */
    public void setDStageOne(boolean stage) {
        this.dStageOne = stage;
        dLocation = 1;
    }
    /**
     * Set Daniel's stage number
     * @param stage     Set to this number
     */
    public void setDStageTwo(boolean stage) {
        this.dStageTwo = stage;
        dLocation = 2;
    }
    /**
     * Set Daniel's stage number
     * @param stage     Set to this number
     */
    public void setDStageThree(boolean stage) {
        this.dStageThree = stage;
        dLocation = 3;
    }
    /**
     * Set Black's stage number
     * @param stage     Set to this number
     */
    public void setBgStageFour(boolean stage) {
        this.dStageOne = stage;
        dLocation = 4;
    }
    /**
     * Set Black's stage number
     * @param stage     Set to this number
     */
    public void setBgStageFive(boolean stage) {
        this.dStageTwo = stage;
        dLocation = 5;
    }
    /**
     * Set Black's stage number
     * @param stage     Set to this number
     */
    public void setBgStageSix(boolean stage) {
        this.dStageThree = stage;
        dLocation = 6;
    }
    /**
     * Set Daniel's stage number
     * @param stage     Set to this number
     */
    public void setDStageSeven(boolean stage) {
        this.dStageThree = stage;
        dLocation = 7;
    }
    /**
     * Set Black Guy's stage number
     * @param stage     Set to this number
     */
    public void setBgStageOne(boolean stage) {
        this.bgStageOne = stage;
        bgLocation = 4;
    }
    /**
     * Set Special stage number
     * @param stage     Set to this number
     */
    public void setSpecialStage(boolean stage) {
        this.specialStage = stage;
    }
    public int getDLocation() {
        return dLocation;
    }
    public int getBgLocation() {
        return bgLocation;
    }
    public boolean getBgStage(int stage){
        if(stage == 1) {
            return bgStageOne;
        }
        else if(stage == 2) {
            return bgStageTwo;
        }
        else {
            return bgStageThree;
        }
    }
    public boolean getDStage(int stage){
        if(stage == 4) {
            return dStageOne;
        }
        else if(stage == 5) {
            return dStageTwo;
        }
        else if(stage == 6){
            return dStageThree;
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
        if(dStageThree == true) {
            dLocantThreeTimer--;
            if(dLocantThreeTimer == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                int specialSpawn = Greenfoot.getRandomNumber(10);
                if(specialSpawn == 3) {
                    dLocation = 7;
                    specialStage = true;
                    specialRunningTimer = 300;
                    specialStageTimer = 1800;
                }
                else {
                    dLocation = 1;
                    dStageThree = false;
                    dStageOne = true; 
                    specialStage = false;
                }
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }    
        if(bgStageThree == true) {
            bgLocantThreeTimer--;
            if(bgLocantThreeTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
                bgLocation = 4;
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
        
        
        if(bgTick == 0 && bgStageThree == false) {
            int chance = Greenfoot.getRandomNumber(2);
            if(chance == 1){
                
                if(bgStageOne == true) {
                    bgLocation = 5;
                    bgStageOne = false;
                    bgStageTwo = true;
                }
                
                else if(bgStageTwo == true) {
                    bgLocation = 6;
                    bgStageTwo = false;
                    bgStageThree = true;
                }
            }
            bgTick = 360;
        }
        
        if(dTick == 0 && dStageThree == false) {
            if(dStageOne == true) {
                dLocation = 2;
                dStageOne = false;
                dStageTwo = true;
            }
            
            else if(dStageTwo == true) {
                dLocation = 3;
                dStageTwo = false;
                dStageThree = true;
            }
            dTick = 600;
        }
        
        
    }
    
}
