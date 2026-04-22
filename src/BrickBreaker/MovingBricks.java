package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class MovingBricks extends Brick {
    protected Image image; // stores the array of images that will be iteratively show on the screen
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    private int dx=2;
    protected boolean alive;
    private boolean Isleft;
    /*
    stores the coordinate, dimensions and status of the vehicle
     */
    protected long count = 1; //used for running the array of images

    public MovingBricks(String path, int x, int y)
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

   /* public void paintComponent(Graphics2D g)
    {
        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
        //g.drawImage(image[num], x - image[num].getWidth(null)/2, y - image[num].getHeight(null)/2, null);
        g.setColor(new Color(255,0,0));
        //g.drawRect(x-w/2, y-w/2, w, h); //Only to show image bounds, can be removed
        count++;
    }*/

    public void moveTo(int x, int y)
    {	/*
	    sets the object to a specific location on our screen.
		*/
        this.x = x;
        this.y = y;
    }

    // public abstract void move();
    //public abstract void Fire();


    public int getX() {
        // returns the x coordinate of our object
        return x;
    }

    public int getY() {
        // returns the y coordinate of our object
        return y;
    }

    public int getWidth() {
        // returns the width of our object
        return w;
    }

    public int getHeight() {
        // returns the height of our object
        return h;
    }

    public Image getImage() {
        // returns the image formed on the screen of our object
        return image;
    }

    public boolean isAlive() {
        // returns the status of our object
        return alive;
    }

    public void setAlive(boolean alive) {
        // sets the status of our object
        this.alive = alive;
    }

    @Override
    public void paintCompnent(Graphics2D g) {
        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
        //g.drawImage(image[num], x - image[num].getWidth(null)/2, y - image[num].getHeight(null)/2, null);
        g.setColor(new Color(255,0,0));
        //g.drawRect(x-w/2, y-w/2, w, h); //Only to show image bounds, can be removed
        count++;
    }
    public void move(){
        if(this.x==28){
            Isleft=true;
        }
        else if(this.x==666){
            Isleft=false;
        }
        if(Isleft){
            x+=1;
        }
        if(!Isleft){
            x-=1;
        }
    }
}
