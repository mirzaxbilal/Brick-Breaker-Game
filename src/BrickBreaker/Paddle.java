package BrickBreaker;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Paddle {
	// used for paddle's height and width and its coordinates.
	private int paddleX;
	private int paddleY;
	private int paddleWidth;
	private int paddleHeight;
	protected Image image;

	private static Paddle instance= new Paddle(); // singleton paddle created.
	public String path; // used for storing the path of the image of paddle.
	private ImageIcon img;
	private int dx;
	private String Type; // used for storing the type of the paddle.
	private boolean BulletPowerUp;
	private Bullet bullet;
	private ArrayList<Bullet> BulletList; // used for storing the bullets.


	// All getter and setter methods.
	public void setBulletPowerup(boolean b)
	{
		BulletPowerUp=b;
	}
	public boolean getBulletPowerup()
	{
		return BulletPowerUp;
	}
	public void setType(String type)
	{
		Type=type;
	}
	public static Paddle getInstance()
	{
		return instance;
	}

	// resetting to normal paddle.
	public void reset()
	{
		path="src/paddle.png";
		Type="Normal Paddle";
		img = new ImageIcon(path);
		image = img.getImage();
		paddleY=540;
		paddleX=250;
		dx=20;
		paddleWidth=img.getIconWidth();
		paddleHeight=img.getIconHeight();
		BulletList=new ArrayList<Bullet>();
		BulletPowerUp=false;
	}

//normal paddle constructor
   private Paddle() {
	   path="src/paddle.png";
	   Type="Normal Paddle";
	   	img = new ImageIcon(path);
	   	image = img.getImage();
	   	paddleY=540;
       	paddleX=250;
        dx=20;
	   BulletPowerUp=false;
        paddleWidth=img.getIconWidth();
        paddleHeight=img.getIconHeight();
        BulletList=new ArrayList<Bullet>();
    }

    // checks ig the increased sized power up is gained, and increasing the size according to the previous size.
   public void IncreaseSize()
   {
	   if(Type.equals("Large Paddle"))
	   { 
		   path="src/LargePaddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Large Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
	   else if(Type.equals("Normal Paddle"))
	   {
		   path="src/LargePaddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Large Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
	   else if(Type.equals("Small Paddle"))
	   {
		   path="src/paddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Normal Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
   }

	// checks ig the decrease sized power up is gained, and decreasing the size according to the previous size.
   public void DecreaseSize()
   {
	   if(Type.equals("Large Paddle"))
	   { 
		   path="src/paddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Normal Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
	   else if(Type.equals("Normal Paddle"))
	   {
		   path="src/SmallPaddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Small Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
	   else if(Type.equals("Small Paddle"))
	   {
		   path="src/SmallPaddle.png";
		   img = new ImageIcon(path);
		   image = img.getImage();
		   Type="Small Paddle";
		   paddleWidth=img.getIconWidth();
	       paddleHeight=img.getIconHeight();
	   }
   }

   // ALL  getter functions.
   public String getType()
   {
	   return Type;
   }
   public int getDx()
   {
	 return dx;   
   }
   public Rectangle getBounds()
   {
	   return new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
   }
   public int getHeight()
	{
		return paddleHeight;
	}
   public int getWidth()
	{
		return paddleWidth;
	}
    	public int getX()
    	{
    		return paddleX;
    	}
    	public void setX(int x)
    	{
    		paddleX=x;
    	}
    	public int getY()

    	{
    		return paddleY;
    	}
    	public Image getImage()
    	{
    		return image;
    	}
    	public ArrayList<Bullet> getList()
    	{
			return BulletList;
    		
    	}

    	// used for changing speed of the paddle.
	public void SpeedIncrease()
	{
		dx=50;

	}
	public void SpeedDecrease()
	{
		dx=20;

	}
		// moves the paddle according to the key press.
    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    		{
    			// checks if there is space on the right side, and moves accordingly.
    			if(Type.equals("Large Paddle"))
    			{
    				if(paddleX>=535)
        			{
        				paddleX=535;
        				
        			}
        			else
        			{
        				moveRight();
        			}
    			}
    			else if(Type.equals("Small Paddle"))
    			{
    				if(paddleX>=640)
        			{
        				paddleX=640;
        				
        			}
        			else
        			{
        				moveRight();
        			}
    			}
    			else
    			{	
    			if(paddleX>=580)
    			{
    				paddleX=580;
    				
    			}
    			else
    			{
    				moveRight();
    			}
    			}
    		}
    		if(e.getKeyCode() == KeyEvent.VK_LEFT)
    		{
    			if(paddleX<= 6)
    			{
    				paddleX=6;
    			}
    			else
    			{
    				moveLeft();
    			}
    		}
    		if(e.getKeyCode() == KeyEvent.VK_SPACE)
    		// if bullet powerup is gained, bullets are created and fired.
    		{	if(BulletPowerUp==true)
    			{	
    			 bullet=new Bullet(this);
    			 BulletList.add(bullet);
    			}
    		}
    		
    	}

    	// used for moving the paddle.
    	
    	public void moveRight()
    	{
    	
    		paddleX+=dx;
    		
    	}

    	public void moveLeft()
    	{
    	
    		paddleX-=dx;
    	}

}
