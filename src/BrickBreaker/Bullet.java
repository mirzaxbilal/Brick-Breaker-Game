package BrickBreaker;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet {
	protected Image image; // creating bullet image.
	/*
	coordinates and width and height of image.
	 */

	protected int x;
	protected int y;
	protected int w;
    protected int h;
	
	//bullet constructor.
	public Bullet(Paddle paddle)
	{
		this.x=(paddle.getX())+(paddle.getWidth()/2);
		this.y=paddle.getY();
		ImageIcon imageIcon=new ImageIcon("src/Bullet.png");
		image=imageIcon.getImage();
		w=image.getWidth(null);
		h=image.getHeight(null);
	}

	// used for moving the bullet.
	public void move()
	{
		y-=8;
	}

	//used to return boundaries of the bullet for checking collision
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, w, h);
	}
	
	//All getter methods.
	public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }
    
    public int getWidth() {
        
        return w;
    }
    
    public int getHeight() {
        
        return h;
    }    
    public void paint(Graphics g)
    {
    	g.drawImage(image, x, y, null);
    }
  
	
}