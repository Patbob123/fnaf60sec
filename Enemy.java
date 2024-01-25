import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy Class
 * 
 * @author Dawson
 * @version Jan 2024
 */
public class Enemy extends Entity
{
    private int stage;
    private String name;
    private int maxTick;
    private int tick;
    private int timeTillReset; 
    
    public Enemy(String name, int maxTick, int resetTime,int stage){
        this.stage = stage;
        this.name = name;
        this.maxTick = maxTick;
        timeTillReset = resetTime; 
        
        tick = maxTick;
        
    }
    
    /**
     * Method to convert Enemy stage to their camera location
     */
    public int getLocation(int stage){
        switch(getName()){
            case "tyrone":
                switch(stage){
                case 1:
                    return 4;
                case 2:
                    return 5;
                case 3:
                    return 6;
                
            }
            break;
            case "daniel":
                switch(stage){
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                
            }
        }
        return -1;
    }
    /**
     * Method to move Enemies
     */
    public void moveLocation(){
        tick--;
        if(tick <= 0) {
            if(Greenfoot.getRandomNumber(2) == 1){
                if(stage+1 <= 3) {
                    if(stage + 1 == 3) tick = 360;
                    tick = maxTick;
                    setStage(stage+1);
                }
            }
        }
    }
    
    /*
     * Helper Method
     */
    public void decreaseTimer(int num){
        timeTillReset -= num;
    }
    public String getName(){
        return this.name;
    }
    public int getStage(){
        return stage;
    }
    public void setResetTimer(int num){
        timeTillReset = num; 
    }
    public void setStage(int stage){
        this.stage = stage;
    }
    public int getResetTimer(){
        return timeTillReset; 
    }
}
