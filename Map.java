import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
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
    private HashMap <String, Grid> worlds = new HashMap<String, Grid>();
    
    private int length = 3;
    private int width = 3;
    private B1 b1;
    private B2 b2;
    private B3 b3;
    private C1 c1;
    private C2 c2;
    private C3 c3;
    public Map(){
        
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
    public void loadWorld(String pointer){
        
        switch(pointer){
            case "B1":
                if(!searchHashMap(pointer)){
                    
                    create("B1");
                }
                Greenfoot.setWorld(b1);
                break;
            case "B2":
                if(!searchHashMap(pointer)){
                    create("B2");
                }
                Greenfoot.setWorld(b2);
                break;
            case "B3":
                if(!searchHashMap(pointer)){
                    create("B3");
                }
                Greenfoot.setWorld(b3);
                break;
            case "C1":
                if(!searchHashMap(pointer)){
                    create("C1");
                }
                Greenfoot.setWorld(c1);
                break;
            case "C2":
                if(!searchHashMap(pointer)){
                    create("C2");
                }
                Greenfoot.setWorld(c2);
                break;
            case "C3":
                if(!searchHashMap(pointer)){
                    create("C3");
                }
                Greenfoot.setWorld(c3);
                break;
        }
    }
    public boolean searchHashMap(String key){
        for(String i : worlds.keySet()){
            if(key.equals(i)){
                return true;
            }
        }
        return false;
    }
    public void create(String location){
        switch(location){
            case "B1":
                b1 = new B1();
                worlds.put("B1",b1);
                break;
            case "B2":
                b2 = new B2();
                worlds.put("B2",b2);
                
                break;
            case "B3":
                b3 = new B3();
                worlds.put("B3",b3);
                break;
            case "C1":
                c1 = new C1();
                worlds.put("C1",c1);
                break;
            case "C2":
                c2 = new C2();
                worlds.put("C2",c2);
                break;
            case "C3":
                c3 = new C3();
                worlds.put("C3",c3);
                break;
        }
        System.out.println(worlds.keySet());
    }
}
