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
                if(Greenfoot.getRandomNumber(10)==0&&i!=0&&j!=0){
                    type = "W";
                }
                map[i][j] = createTile(type);
            }
        }
        
        fillWalls(map);
        printMap();
        
        setWalls(map);
        
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
    public void printMap(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
