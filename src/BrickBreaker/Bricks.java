package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class Bricks extends Brick {
    protected Image image; // stores the array of images that will be iteratively show on the screen
    protected int x; // coordinates of the bricks.
    protected int y;
    protected int w; // width and height of the bricks.
    protected int h;
    protected int dx=1;
    protected boolean Isleft;
    protected boolean alive;
    /*
    stores the coordinate, dimensions and status of the vehicle
     */

    public Bricks(String path, int x, int y)
    {
		/*
		creates an object of vehicle through the image path and coordinates provided as
		parameters.
		*/
        this.x = x;
        this.y = y;
            ImageIcon imageIcon = new ImageIcon(path); // used for creating img.
            image = imageIcon.getImage();
            w = image.getWidth(null);
            h = image.getHeight(null);
        this.alive=true; //status of the object is set
    }


    public Rectangle getBounds() {
        return new Rectangle(x-w/2, y-w/2, w, h); // returns the space covered by our vehicle on the screen
    }


    public void moveTo(int x, int y)
    {	/*
		sets the object to a specific location on our screen.
		*/
        this.x = x;
        this.y = y;
    }



    public int getX() {
        // returns the x coordinate of our object
        return x;
    }

    public int getY() {
        // returns the y coordinate of our object
        return y;
    }


    public Image getImage() {
        // returns the image formed on the screen of our object
        return image;
    }

    // used for moving the brick.
    public void move(){
        if(this.x==28){
            Isleft=true;
        }
        else if(this.x==666){
            Isleft=false;
        }
        if(Isleft){
            x+=dx;
        }
        if(!Isleft){
            x-=dx;
        }
    }

    // used for painting the image of the brick.
    @Override
    public void paintCompnent(Graphics2D g) {
        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
        g.setColor(new Color(255,0,0));
    }
}
