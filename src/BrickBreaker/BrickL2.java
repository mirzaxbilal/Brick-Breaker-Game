package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class BrickL2 extends Brick{
    protected Image [] image=new Image[2];// stores the array of images that will be iteratively show on the screen
    protected int x; // coordinates of the bricks.
    protected int y;
    protected int w; // width and height of the bricks.
    protected int h;
    protected boolean alive1;
    protected boolean alive;
    protected boolean Isleft;
    protected int dx=1;
    public BrickL2(String [] path, int x, int y) {
        this.x = x;
        this.y = y;
        for(int i=0;i< path.length;i++) {
            ImageIcon imageIcon = new ImageIcon(path[i]); // used for creating img.
            image[i] = imageIcon.getImage();
            w = image[i].getWidth(null);
            h = image[i].getHeight(null);
        }
        this.alive=true;
        this.alive1=true;
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


    public Image[] getImage() {
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

    // used for moving the bricks
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
    // used for painting the image according to its strength.
    @Override
    public void paintCompnent(Graphics2D g) {
        if(alive){
            g.drawImage(image[0], x - image[0].getWidth(null)/2, y - image[0].getHeight(null)/2, null);
        }
        else {
            g.drawImage(image[1], x - image[1].getWidth(null)/2, y - image[1].getHeight(null)/2, null);
        }
    }
}


