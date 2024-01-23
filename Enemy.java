import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enenmy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends EnemyManager
{
    private int stage;
    private String name;
    private int maxTick;
    private int tick;
    
    public Enemy(String name, int maxTick){
        this.name = name;
        this.maxTick = maxTick;

        tick = maxTick;
    }
    public void setStage(int stage){
        this.stage = stage;
    }
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

    }
    public void move(){
        tick--;
        if(Greenfoot.getRandomNumber(2) == 1){
            if(stage+1 > 3) {
                if(stage + 1 == 3) tick = 240;
                tick = maxTick;
                setStage(stage+1);
            }
        }
    }
    public String getName(){
        return this.name;
    }
}
