import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends Actor
{
    private String pointer = "C1";
    private int x = 0;
    private int y = 2;
    public static String [][] school = {
            {"A1","A2","A3"},
            {"B1","B2","B3"},
            {"C1","C2","C3"}
    };
    
    private int length = 3;
    private int width = 3;
    public Map(){
        
    }
    public void updatePointer (String direction){
        switch(direction){
            case "left":
                x--;
                pointer = findCoordinate(x,y);
                System.out.println(getX());
                loadWorld();
                break;
            case "right":
                
                x++;
                pointer = findCoordinate(x,y);
                loadWorld();
                break;
        }
    }
    public void setPointer(String coord){
        pointer = coord; 
    }
    public String getPointer(){
        return pointer;
    }
    public int getX(){
        findIndex(pointer);
        return x;
    }
    public int getY(){
        findIndex(pointer);
        return y;
    }
    public void setX(int num){
        x = num;
    }
    public void setY(int num){
        y = num;
    }
    private String findCoordinate(int x, int y){
        return school[y][x];
    }
    private void findIndex(String pointer){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                if(school[i][j] == pointer){
                    int x = j;
                    int y= i;
                }
            }
        }
    }
    public void loadWorld(){
        switch(pointer){
            case "C1":
                C1 c1 = new C1();
                Greenfoot.setWorld(c1);
            case "C2":
                C2 c2 = new C2();
                Greenfoot.setWorld(c2);
            case "C3":
                C3 c3 = new C3();
                Greenfoot.setWorld(c3);
        }
    }
}
