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
    public HashMap<String, Grid> getWorlds(){
        return worlds; 
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
    public void loadWorld(String pointer, Memory ram){
        
        switch(pointer){
            case "C1":
                if(!searchHashMap(pointer)){
                    create("C1",ram);
                }
                Greenfoot.setWorld(c1);
                break;
            case "C2":
                if(!searchHashMap(pointer)){
                    create("C2",ram);
                }
                Greenfoot.setWorld(c2);
                break;
            case "C3":
                if(!searchHashMap(pointer)){
                    create("C3",ram);
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
    public void create(String location, Memory ram){
        switch(location){
            case "C1":
                c1 = new C1(worlds.get(location),ram);
                worlds.put("C1",c1);
                break;
            case "C2":
                c2 = new C2(worlds.get(location),ram);
                worlds.put("C2",c2);
                break;
            case "C3":
                c3 = new C3(worlds.get(location),ram);
                worlds.put("C3",c3);
                break;
        }
        System.out.println(worlds.keySet());
    }
    public void storeWorld(String Location,Grid world){
        worlds.put(Location,world);
    }
}
