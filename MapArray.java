import greenfoot.*;
/**
 * Write a description of class MapArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapArray  
{
    private int x;
    private Tile[][] map;
    
    public MapArray()
    {
        map = new Tile[50][50];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                String type = "-";
                if(Greenfoot.getRandomNumber(10)==0){
                    type = "W";
                }
                map[i][j] = createTile(type);
            }
        }
    }

     public Tile createTile(String type){
        switch(type){
            case "W":
                return new Wall();
            default:
                return new Floor();
                
        }
    }
    public Tile[][] getTiles(){
        return map;
    }
}
