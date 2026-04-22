package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class Ball {

    protected boolean powerup; // used for checking if powerup gained.
    protected Image image; // used for getting ball's image.
 
    protected int ballposX; // used for ball's position.
    protected int ballposY; // used for ball's position.
    protected static int ballXdir=-1; // used for moving the ball in X direction.
    protected static int ballYdir=-2; // used for moving the ball in Y direction.
    protected ImageIcon img;
    protected boolean FireBall; // used for checking if ball is turned into a fireball.

    // ball constructor
    public Ball(int x, int y){
        img = new ImageIcon("src/ball.png");
        image = img.getImage();
        FireBall=false;
        ballposX=x;
        ballposY=y;
    }

   
    // fireball constructor
    public void FireBall()
    {
    	img = new ImageIcon("src/FireBall.png");
        image = img.getImage();
        FireBall=true;
    	
    }

    // used for calling normal ball when fire ball power up ends.
    public void NormalBall()
    {
    	img = new ImageIcon("src/ball.png");
        image = img.getImage();
        FireBall=false;
    	
    }
    // used for changing ball's y direction.
    public  void changedirectiony(){

        ballYdir=-ballYdir;
    }
    // used for chaning ball's x direction.
    public void changedirectionx(){

        ballXdir=-ballXdir;
    }

    // used for moving the ball.
    public void changeposition(){

        ballposX +=ballXdir;
        ballposY+=ballYdir;

    }

    // All setter methods.
    public void setBallX(int x)
    {
    	ballposX=x;
    }
    public void setBallY(int y)
    {
    	ballposX=y;
    }
    public void setBallXdir(int xdir){
        ballXdir=xdir;
    }

    
    //All getter methods.
    public int getBallposX() {
        return ballposX;
    }

    public int getBallXdir() {
        return ballXdir;
    }
    public int getBallposY(){
       return ballposY;
    }

    public int getBallYdir() {
        return ballYdir;
    }

    public Image getImageforpowerup()
    {
        return image;
    }


}
