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
    private Tile[][] itemLayer;
    private String[] itemList;
    
    public MapArray()
    {
        itemList = new String[]{"Food", "Water"};
        map = new Tile[50][50];
        itemLayer = new Tile[50][50];
        
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
        
        fillWalls(map);
        setWalls(map);
        
    }
    
     public Tile createTile(String type){
         try{
            return (Tile)Constants.tileHash.get(type).newInstance();
        }catch(InstantiationException e){
            
        }catch(IllegalAccessException e){
            
        }
        return new Floor();
    }
    public Tile[][] getTiles(){
        return map;
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
    public Tile[][] getItems(){
        return itemLayer;
    }
    public void printMap(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+" ");
            }
            //System.out.println();
        }
    }
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
    //public void 
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
}
