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
        itemList = new String[]{"Food", "Water","Battery","Wood"};
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
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "s1", "s2", "w",  "w",  "w",  "w",  "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "s3", "s4", "w",  "w",  "w",  "w",  "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "l1", "l1", "w",  "w"},  
            {"w",  "w",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "w",  "w",  "w",  "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "l2", "l2", "w",  "w"},  
            {"w",  "g",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "d",  "-",  "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "l1", "l1", "w",  "w"},  
            {"w",  "w",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "-",  "w",  "w",  "w",  "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "l2", "l2", "w",  "w"},
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "d",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w", "-2",  "-2", "-2", "-2", "-2", "-2", "-2", "-2", "w",  "w",  "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "w",  "w",  "-",  "-",  "-",  "w",  "w",  "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "w",  "w"},  
            {"w",  "w", "-2",  "-2", "-2", "-2", "-2", "-2", "-2", "-2", "w",  "w",  "-3", "t1", "t2", "t3", "-3", "t1", "t2", "t3", "-3", "w",  "w",  "-",  "-",  "-",  "w",  "w",  "-4", "t1", "t2", "t3", "t1", "t2", "t3", "t1", "t2", "t3", "-4", "w",  "w"},  
            {"w",  "w", "-2",  "-2", "-2", "-2", "-2", "-2", "-2", "-2", "w",  "w",  "-3", "t4", "t5", "t6", "-3", "t4", "t5", "t6", "-3", "d",  "-",  "-",  "-",  "-",  "w",  "w",  "-4", "t4", "t5", "t6", "t4", "t5", "t6", "t4", "t5", "t6", "-4", "w",  "w"},  
            {"w",  "w",  "w",  "-2", "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "w",  "w",  "-",  "-",  "-",  "d",  "-",  "-4", "t1", "t2", "t3", "t1", "t2", "t3", "t1", "t2", "t3", "-4", "w",  "w"}, 
            {"w",  "w",  "w",  "d",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "w",  "w",  "-",  "-",  "-",  "w",  "w",  "-4", "t4", "t5", "t6", "t4", "t5", "t6", "t4", "t5", "t6", "-4", "w",  "w"},  
            {"w",  "w",  "w",  "-2", "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-3", "t1", "t2", "t3", "-3", "t1", "t2", "t3", "-3", "w",  "w",  "-",  "-",  "-",  "w",  "w",  "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "w",  "w"},  
            {"w",  "w", "-2",  "-2", "-2", "-2", "-2", "-2", "-2", "-2", "w",  "w",  "-3", "t4", "t5", "t6", "-3", "t4", "t5", "t6", "-3", "d",  "-",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w", "-2",  "-2", "-2", "-2", "-2", "-2", "-2", "-2", "w",  "w",  "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "-3", "w",  "w",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "-",  "-",  "-",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"},  
            {"w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "g",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w",  "w"}  
                        
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
                if(type.equals("d")) type = "-";
                if(type.length()>1){
                    map[i][j] = createTile(type.substring(0,1));
                    map[i][j].assignProp(type.substring(1,2));
                    System.out.println("A:::"+type.substring(0,1)+"B:: "+ type.substring(1,2));
                    itemLayer[i][j] = generateItem(type.substring(0,1), type.substring(1,2));
                }else{
                    map[i][j] = createTile(type);
                    itemLayer[i][j] = generateItem(type, String.valueOf(Greenfoot.getRandomNumber(30)));
                }
                
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
                itemLayer[i][j] = generateItem(type, "1");
            }
        }
        return map;
    }
    /**
     * Helper method to convert String into a Tile using Constant's hashmap
     */
     public Tile createTile(String type){
        //System.out.println(type);
         try{
            return (Tile)Constants.tileHash.get(type).newInstance();
        }catch(InstantiationException e){
            
        }catch(IllegalAccessException e){
            
        }
        return new Floor();
    }
    
    public Tile generateItem(String type, String room){
        //if(Greenfoot.getRandomNumber(3)==1){
            System.out.println("123:"+type+" "+room);
            if(type.equals("-")&&Greenfoot.getRandomNumber(5)==1){
                String itemName = "";
                System.out.println("A");
                try{
                    switch(room){
                        case "1":
                            itemName = itemList[3];
                            return (Tile)Constants.itemHash.get(itemName).newInstance();
                        case "2":
                            itemName = itemList[1];
                            return (Tile)Constants.itemHash.get(itemName).newInstance();
                        case "3":
                            itemName = itemList[0];
                            return (Tile)Constants.itemHash.get(itemName).newInstance();
                        case "4":
                            itemName = itemList[2];
                            return (Tile)Constants.itemHash.get(itemName).newInstance();
                    }
                    
                }catch(InstantiationException e){
                    
                }catch(IllegalAccessException e){
                    
                }
            }
        //}
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
            //System.out.println(i+" "+j);
            if(map[i][j].toString().equals("W")){
                
                try {
                    if(!map[i-1][j].toString().equals("W")){
                    // System.out.println(!map[i-1][j]+"]");
                        modifier += "T";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    if(!map[i+2][j].toString().equals("W")&&!map[i+2][j].toString().equals("D")){
                        modifier += "B";
                    }
                    
    
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    //System.out.println(map[i][j-1].toString());
                    if((!map[i+1][j+1].toString().equals("W")||!map[i][j+1].toString().equals("W"))){
                        modifier += "R";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                try {
                    if((!map[i+1][j-1].toString().equals("W")||!map[i][j-1].toString().equals("W"))&&!map[i+1][j-1].toString().equals("D")){
                        modifier += "L";
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
                if(modifier.equals("")){
                    try {
                        if(!map[i+2][j+1].toString().equals("W")&&map[i+2][j].toString().equals("W")){
                            modifier += "CBR";
                        }
                    } catch (ArrayIndexOutOfBoundsException e){}
                    try {
                        if(!map[i+2][j-1].toString().equals("W")&&map[i+2][j].toString().equals("W")){
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
                }else{
                    try {
                        if(!map[i+1][j].toString().equals("W")){
                        modifier = "B2";
                        }
                    } catch (ArrayIndexOutOfBoundsException e){}
                    try {
                        if(map[i+1][j].toString().equals("D")){
                        modifier = "B";
                        }
                    } catch (ArrayIndexOutOfBoundsException e){}
                    
                }
                modifier+=map[i][j].getUrl();
            }else{
                String prop = map[i][j].getProp();
                if(map[i][j].getProp()==null) prop = "";
                modifier = map[i][j].getUrl()+prop;
            }
            //System.out.println(map[i][j].toString());
            map[i][j].setIcon("WallIcon/"+modifier+".png");
    
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
