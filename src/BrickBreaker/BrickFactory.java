package BrickBreaker;


// generates a type of brick according to the inputted parameters.
public class BrickFactory {

    public static String brickpath="src/13-Breakout-Tiles.png"; // brick level 1 image.
    public static String [] brick2path={"src/01-Breakout-Tiles.png","src/02-Breakout-Tiles.png"};// brick level 2 image.
    public static String [] brick3path={"src/17-Breakout-Tiles.png","src/18-Breakout-Tiles.png"};// brick level 3 image.

    // unbreakable bricks image.
    public static String [] Ubrickpath={"src/21-Breakout-Tiles.png","src/22-Breakout-Tiles.png","src/23-Breakout-Tiles.png","src/24-Breakout-Tiles.png"
        , "src/25-Breakout-Tiles.png","src/26-Breakout-Tiles.png","src/27-Breakout-Tiles.png","src/28-Breakout-Tiles.png","src/29-Breakout-Tiles.png"
        ,"src/30-Breakout-Tiles.png"};

    // function to create a brick type.
    public Brick getBrick(int i, int x, int y){

        if(i==0){
            return new Bricks(brickpath,x,y);
        }
        else if(i==1){
            return new BrickL2(brick2path,x,y);
        }
        else if(i==2){
            return new BrickL3(brick3path,x,y);
        }
        else if(i==3){
            return new UBricks(Ubrickpath,x,y);
        }
        else
            return null;

    }

}
