package BrickBreaker;


import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Powerup {
	/*
	coordinates and height and width of the power up image is stored
	 */
	protected Image image;
	protected int x;
	protected int y;
	protected int w;
    protected int h;
	
	// power up constructor.
	public Powerup(String path)
	{
		this.x=(int)(Math.random()*590);
		this.y=0;
		ImageIcon imageIcon=new ImageIcon(path);
		image=imageIcon.getImage();
		w=image.getWidth(null);
		h=image.getHeight(null);
	}

	public abstract void move(); // move function for all the classes that  extend powerup

	//All getter and setter functions.
	public abstract String getType();
	
	public Rectangle getBounds()
	{
	    return new Rectangle(x, y, w, h);
	}
	
	
	public void moveTo(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void SetXY(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
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
