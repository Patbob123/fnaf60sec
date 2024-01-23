import greenfoot.*;
/**
 * Write a description of class MapArray here.
 * 
 * @author Dawson
 * <p> Modified and edited by Jaiden and Edmond </p>
 * @version January 2024
 */
public class MapArray  
{
    private int x;
    private Tile[][] map;
    private Tile[][] itemLayer;
    private String[] itemList;
    
    /**
     * Constructor for the MapArray
     */
    public MapArray()
    {
        itemList = new String[]{"Food", "Water"};
        map = new Tile[50][50];
        itemLayer = new Tile[50][50];
        
        
        map = convertMap(createMap(), map);
        //fillWalls(map);
        setWalls(map);
        
    }
    /** 
     * Method to generate String map
     */
    public String[][] createMap(){
        String[][] mapString = new String[][]{
            {"-", "-", "-", "-" }, 
            {"-", "W", "W", "-" }, 
            {"-", "W", "W", "-" }, 
            {"-", "-", "-", "-" }, 
            {"-", "-", "-", "-" }, 
            
        };
        return mapString;
    }
    /**
     * Method to convert String map into a Tile map
     * 
     * @mapString       String map
     * @map             Tile map
     */
    public Tile[][] convertMap(String[][] mapString, Tile[][] map){
        map = new Tile[mapString.length][mapString[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                String type = mapString[i][j];
                map[i][j] = createTile(type);
                itemLayer[i][j] = generateItem(type);
            }
        }
        return map;
    }
    /**
     * Functionality tester, creates a randomly generated map
     * 
     * @param map             Tile map
     */
    public Tile[][] randomMap(Tile[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                String type = "-";
                if(Greenfoot.getRandomNumber(10)==0&&i!=0&&j!=0){
                    type = "W";
                }
                if(Greenfoot.getRandomNumber(10)==0){
                    type = "#";
                }
                map[i][j] = createTile(type);
                itemLayer[i][j] = generateItem(type);
            }
        }
        return map;
    }
    /**
     * Helper method to convert String into a Tile using Constant's hashmap
     */
     public Tile createTile(String type){
         try{
            return (Tile)Constants.tileHash.get(type).newInstance();
        }catch(InstantiationException e){
            
        }catch(IllegalAccessException e){
            
        }
        return new Floor();
    }
    
    public Tile generateItem(String type){
        if(type.equals("-")){
            try{
                String itemName = itemList[Greenfoot.getRandomNumber(itemList.length)];
                return (Tile)Constants.itemHash.get(itemName).newInstance();
            }catch(InstantiationException e){
                
            }catch(IllegalAccessException e){
                
            }
        }
        return null;
    }
        
    /** 
     * Method to fill all the walls
     * 
     * @map             Tile map
     */
    public void fillWalls(Tile[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
            String modifier = "";
            if(map[i][j].toString().equals("W")){
                try {
                    if(!map[i-1][j].toString().equals("W")){
                    // System.out.println(!map[i-1][j]+"]");
                        map[i-1][j] = new Wall();
                    }
                    } catch (ArrayIndexOutOfBoundsException e){}
                try {
                        if(!map[i][j-1].toString().equals("W")){
                            map[i][j-1] = new Wall();
                        }
                } catch (ArrayIndexOutOfBoundsException e){}
                if(modifier.equals("")){
                    try {
                        if(!map[i-1][j-1].toString().equals("W")){
                            map[i-1][j-1] = new Wall();
                        }
                    } catch (ArrayIndexOutOfBoundsException e){}
                }
        
                }
            }
        }
    }
    
    /** 
     * Wall generator method to determine which type of wall to set
     * Checks nearby walls to determine if the current wall is a corner, edge or filler wall
     */
    public void setWalls(Tile[][] map){

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
            String modifier = "";
            if(!map[i][j].toString().equals("W")) continue;
            try {
                if(!map[i-1][j].toString().equals("W")){
                // System.out.println(!map[i-1][j]+"]");
                    modifier += "T";
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            try {
                if(!map[i+1][j].toString().equals("W")){
                    modifier += "B";
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            try {
                if(!map[i][j+1].toString().equals("W")){
                    modifier += "R";
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            try {
                if(!map[i][j-1].toString().equals("W")){
                    modifier += "L";
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            if(modifier.equals("")){
                try {
                    if(!map[i+1][j+1].toString().equals("W")){
                        modifier += "CBR";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    if(!map[i+1][j-1].toString().equals("W")){
                        modifier += "CBL";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    if(!map[i-1][j+1].toString().equals("W")){
                        modifier += "CTR";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    if(!map[i-1][j-1].toString().equals("W")){
                        modifier += "CTL";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
            }
            //$System.out.println(i+" "+modifier.length());
            if(modifier.length() > 3){
                modifier = "";
            }
            //$System.out.println(i+" "+modifier.length());
            map[i][j].setIcon("WallIcon/"+modifier+"Wall.png");
    
            }
        }
    }
    
    /** 
     * Getter method for tile map
     */
    public Tile[][] getTiles(){
        return map;
    }
    
    /**
     * Getter method for items
     */
    public Tile[][] getItems(){
        return itemLayer;
    }
    
    /**
     * Method to print out the map
     */
    public void printMap(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+" ");
            }
            //System.out.println();
        }
    }
}
