package BrickBreaker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UBricks extends Brick {
    protected ArrayList<Image> image_list = new ArrayList<>();
    // stores the array of images that will be iteratively show on the screen
    protected Image image;
    protected int x; // coordinates of the bricks.
    protected int y;
    protected int w; // width and height of the bricks.
    protected int h;
    protected boolean Isleft;
    protected int dx=1;
    protected String [] path;
    public UBricks(String [] path, int x, int y) {
        this.path=path;
        this.x = x;
        this.y = y;
        for(int i=0;i< path.length;i++) {
            ImageIcon imageIcon = new ImageIcon(path[i]); // used for creating img.
            image = imageIcon.getImage();
            image_list.add(image);
            w = image.getWidth(null);
            h = image.getHeight(null);
        }
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

    // draws the brick.
    @Override
    public void paintCompnent(Graphics2D g) {
        {
            int i= (int) (Math.random() * path.length);
            g.drawImage(image_list.get(i), x - image_list.get(i).getWidth(null)/2, y - image_list.get(i).getHeight(null)/2, null);

        }

    }
}
