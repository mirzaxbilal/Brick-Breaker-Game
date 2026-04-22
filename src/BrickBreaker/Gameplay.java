
package BrickBreaker;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/*
Gameplay is using key listener, and mouse listener to take input from keyboard and mouse
 */
public class Gameplay extends JPanel implements KeyListener,ActionListener, MouseListener {

	/*
		All the require variables are initialized and declared.
	 */

	/*
	All the integer variables are declared.
	 */
	private int score; // used for scoring
	private int life=3; // no of lives at the start of the game
	private int random=0; // used for generating random powerups
	private Timer timer; // used for run time action performed
	private int delay;	// used to set timer
	int counterUB=0; // used for checking if only unbreakable bricks are left
	private double counter=0; // used for generating powerups after a certain count
	private double FireBallCounter=0; // used for fireball time limit
	private double BulletCounter=0; // used for bullet powerup time limit
	int BGWidth; // used for background image width
	int BGHeight; //used for background image height
	/*
	to tell broken brick's coordinates.
	 */
	private int BrickX;
	private int BrickY;
	// used for limiting no of powerups.
	int limit;


	private boolean play; // used to start the game
	private boolean dead; // checking if game over
	private boolean level1; // checking if level 1 completed
	private boolean level2; // used for checking if level 2 completed.
	boolean SBballcheck; // used to check split ball power up.
	private boolean winner; // used for ending game if player has completed both levels


	String path; // used for providing image path


	Brick brc; // used for storing bricks created from factory
	private Paddle paddle; // creates paddle
	private Ball ball; // creates ball
	Powerup powerup; // used for storing power ups created from factory


	ArrayList <Brick> brick_array= new ArrayList<>(); // stores all the bricks created.
	private ArrayList<Powerup> PowerupList; // stores all the powerups created
	ArrayList<Brick> Moving_array= new ArrayList<>(); // stores the moving bricks
	private ArrayList<Dball> dballs; // stores the extra balls created with split ball powerup
	private ArrayList<Bullet> BList; // stores the bullet fired.


	PowerupFactory powerupFactory; // used for creating powerus
	private BrickFactory brickFactory = new BrickFactory(); // used for randomly generating bricks

	/*
	used for storing backGround image
	 */
	protected ImageIcon img;
	Image BGImage;



	/*
	used for storing state of the game
	 */
	public static enum STATE{
		MENU,
		GAME
	}

	public static STATE state=STATE.MENU;


	private final Menu menu=new Menu(); // used for creating menu


	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		Not used.
		 */

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
		reads the coordinates of the mouse press
		 */
		int Xvalue=e.getX();
		int Yvalue=e.getY();

		/*
		Used for checking which button is pressed on our menu
		 */
		if(state==STATE.MENU){
			/*
			if play button is pressed a new game is loaded on the screen
			 */

			//Play Button
			if(Xvalue>=270 && Xvalue<=370){
				if(Yvalue>=150 && Yvalue<=200){

					//PLay Button Pressed
					Gameplay.state=STATE.GAME;
					dead=false;
					life=3;
					brick_array.clear();
					Moving_array.clear();
					dballs.clear();
					PowerupList.clear();
					timer.stop();
					gameplay();


				}
			}
			/*
			if load button is pressed the previous game is loaded on the screen
			 */
			//Load Button
			if(Xvalue>=270 && Xvalue<=370){
				if(Yvalue>=250 && Yvalue<=300){
					//load button pressed
					Gameplay.state=STATE.GAME;
				}
			}
			/*
			if Quit button is pressed the game is exited.
			 */
			// QUit Button
			if(Xvalue>=270 && Xvalue<=370) {
				if (Yvalue >= 350 && Yvalue <= 400) {
					//Quit Button Pressed
					System.exit(0);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}



	public Gameplay(){
		// When ever the constructor is loaded, it calls the gameplay function
		gameplay();
	}

	public void gameplay() {
		/*
		all the variables are initialized according to the need to start a new game.
		 */
		path="src/28nlrzzjkggge02.jpg";
		score=0;
		delay=8;
		level1=true;
		level2=false;
		winner=false;
		PowerupList=new ArrayList<Powerup>();
		dballs=new ArrayList<Dball>();
		powerupFactory=new PowerupFactory();
		paddle=Paddle.getInstance();
		paddle.reset();
		ball=new Ball((paddle.getX()-10)+((paddle.getWidth())/2), paddle.getY()-20);
		dead=false;
		SBballcheck=false;
		limit=8;

		// Used to create the background of menu, levels, winner.
		BackGround();

		//used for storing all the bricks for level 1.
		level1();

		/*
		all the important keylisteners are added and timer is started to start the game.
		 */
		addKeyListener(this);
		this.addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}

		// used for painting all the components of the game.
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,708,610);


		if(state==STATE.GAME ) {
			if(!winner){
			g.drawImage(BGImage, 0, 0, null); // BG image is drawn
			g.drawImage(paddle.image, paddle.getX(), paddle.getY(), null); // paddle is drawn
			Graphics2D g2d = (Graphics2D) g;
			paint_list11(g2d, brick_array); // brick arraylist is painted through a function
			paint_list11(g2d, Moving_array); //  moving bricks are painted

			//the ball
			g.drawImage(ball.image, ball.getBallposX(), ball.getBallposY(), null); // ball is drawn.

			//Dballs
				// all the split balls are drawn
			try {
				for (int i = 0; i < dballs.size(); i++) {
					g.drawImage(dballs.get(i).image, dballs.get(i).getBallposX(), dballs.get(i).getBallposY(), null);
				}
			} catch (Exception e) {

			}


			//Scores
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Score: " + score, 590, 30);
			
			//Life
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Life: " + life, 587, 60);

			// writing level 1
			if (level1) {
				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("Level 1", 708 / 2 - 40, 30);

			}
			//writing level 2
			else if (level2) {
				g.setColor(Color.PINK);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("Level 2", 708 / 2 - 40, 30);
			}

			//when you lose
			if (dead == true) {
				play = false;
				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("Game Over, Scores: " + score, 190, 300);

				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("Press (Enter) to Restart", 230, 350);
			}

			//powerups
				// all powerups are drawn
			for (int i = 0; i < PowerupList.size(); i++) {
				g.drawImage(PowerupList.get(i).image, PowerupList.get(i).getX(), PowerupList.get(i).getY(), null);
			}
			//bullets
			for (int i = 0; BList.size() > i; i++) {
				g.drawImage(BList.get(i).image, BList.get(i).getX(), BList.get(i).getY(), null);
			}
		}
			if(winner){
				g.drawImage(BGImage, 0, 0, null);
				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 20));
				g.drawString("Press Esc to return to Menu", 230, 450);
			}

		}
		else if(state==STATE.MENU)
		{
			menu.draw(g); // calls the menu draw function
		}

		g.dispose();


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//checks if split ball ball power up is generated or not
		if (SBballcheck == true) {
			limit = 7;
		}
		movingaarray(); // used for moving all the bricks in the moving array
		moving_brick_check(); // used for checking collisions of the bricks in the moving array.
		timer.start();
		counter++;

		random = (int) (Math.random() * limit); // used for generating a random powerup
		BList = paddle.getList(); // gets the bullet list from paddle class

		if (state == STATE.GAME) {
			if (play) {
		
				//BulletPowerup is turned off after counter reaches 800
				if (paddle.getBulletPowerup() == true) {
					BulletCounter++;
					if (BulletCounter == 800) {
						paddle.setBulletPowerup(false);
						BulletCounter = 0;
					}

				}
				//BulletPowerup is turned off after counter reaches 800
				if (ball.FireBall) {
					FireBallCounter++;
					if (FireBallCounter == 800) {
						ball.NormalBall();
						FireBallCounter = 0;
					}
				}
				
				//Detects collision of powerup with paddle and then implements the powerup
				try {
					for (int i = 0; i < PowerupList.size(); i++) {
						if (PowerupList.get(i).getBounds().intersects(paddle.getBounds())) { //for size increase powerup
							if (PowerupList.get(i).getType().equalsIgnoreCase("Size Increase")) {
								PowerupList.remove(PowerupList.get(i));
								paddle.IncreaseSize();

							}
							if (powerup.getType().equalsIgnoreCase("Size Decrease")) { //for size Decrease powerup
								PowerupList.remove(PowerupList.get(i));
								paddle.DecreaseSize();

							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Speed Increase")) { //for Speed Increase powerup
								paddle.SpeedIncrease();
								PowerupList.remove(PowerupList.get(i));
							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Speed Decrease")) { //for Speed Decrease powerup
								paddle.SpeedDecrease();
								PowerupList.remove(PowerupList.get(i));
							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Fire Ball")) {	//for FireBall powerup
								ball.FireBall();
								PowerupList.remove(PowerupList.get(i));
							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Life Powerup")) {	//for Life powerup
								life += 1;
								PowerupList.remove(PowerupList.get(i));
							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Bullet Powerup")) { //for Bullet powerup
								paddle.setBulletPowerup(true);
								PowerupList.remove(PowerupList.get(i));
							}
							if (PowerupList.get(i).getType().equalsIgnoreCase("Split Ball")) { 	//for Split Ball powerup

								dballs.add(new Dball(ball.getBallposX(), ball.getBallposY()));
								life++;
								SBballcheck = true;
								PowerupList.remove(PowerupList.get(i));
							}


						
						}
					}
				} catch (Exception e1) {

				}
				// checks collision between the ball and the paddle
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(paddle.getBounds())) {
					ball.changedirectiony();
					
				}
				ball.changeposition(); // moving the ball

				/*
				checks collision between the ball and the boundries
				 */
				
				if (ball.getBallposX() < 0) {
					ball.changedirectionx();
				
				}
				if (ball.getBallposY() < 0) {
					ball.changedirectiony();

				}
				if (ball.getBallposX() > 670) {
					ball.changedirectionx();
					
				}
				try {
					if (powerup.getY() > 600) {
						cleanPowerupList();
					}
				} catch (Exception a) {

				}
				// moves all the powerups in the arraylist

				for (int i = 0; PowerupList.size() > i; i++) {

					PowerupList.get(i).move();

				}
				// moves all the bullets in the arraylist

				for (int i = 0; BList.size() > i; i++) {

					BList.get(i).move();

				}

			}	
			//Checks Collission for duplicate balls
			try {
				for (int j = 0; j < dballs.size(); j++) {

					if (new Rectangle(dballs.get(j).getBallposX(), dballs.get(j).getBallposY(), 20, 20).intersects(paddle.getBounds())) {
						dballs.get(j).changedirectiony();
					}
					dballs.get(j).changeposition();

					if (dballs.get(j).getBallposX() < 0) {
						dballs.get(j).changedirectionx();
					}
					if (dballs.get(j).getBallposY() < 0) {
						dballs.get(j).changedirectiony();

					}
					if (dballs.get(j).getBallposX() > 670) {
						dballs.get(j).changedirectionx();
					}
					if (dballs.get(j).getBallposY() > 600) {
						life--;
						dballs.remove(j);

					}

				}
			} catch (Exception a) {

			}
			check_collision(brick_array); // checks ball collision with the bricks stored
			check_collision(Moving_array); // checks the ball collision with the moving bricks

			try {
				Dcheck_collision(brick_array); // checks all the duplicate ball created with the bricks stored
				Dcheck_collision(Moving_array);  // checks all the duplicate ball created with the  moving bricks stored
			} catch (Exception d) {


			}
			try {
				Bullet_collision(brick_array); // checks the bullet collision on the bricks.
				Bullet_collision(Moving_array);
			} catch (Exception f) {

			}

		// used to decrease life if ball is dropped
			if (ball.getBallposY() >= 600) {
				life -= 1;
				play = false;
				ball = new Ball((paddle.getX() - 10) + ((paddle.getWidth()) / 2), paddle.getY() - 20);
			}

			death();// checks for lives to be 0

			if (level1) {
				EndLevel1();
			}

			// checks if game is completed to call the winner function.
			if (!level1 && brick_array.size() == 0 && Moving_array.size() == 0) {
				winner();
			}
		}
		repaint();
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		if game ends, it can be restarted by pressing enter.
		 */
		if(dead){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				brick_array.clear();
				Moving_array.clear();
				dballs.clear();
				PowerupList.clear();
				BList.clear();
				dead=false;
				life=3;
				gameplay();
			}
		}

		// if escape is pressed, returns to main menu.
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			state=STATE.MENU;
			
		}


		if(state==STATE.GAME) {

			// starts the game
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (dead == false) {
					play = true;
				}

			}
			// used for moving the ball with the paddle before the game starts.
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (play == false) {
					if (paddle.getType().equalsIgnoreCase("Normal Paddle")) {
						if (ball.getBallposX() >= 625) {
							ball.setBallX(625);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() + 50);
							} else {
								ball.setBallX(ball.getBallposX() + 20);
							}
						}
					}
					if (paddle.getType().equalsIgnoreCase("Small Paddle")) {
						if (ball.getBallposX() >= 650) {
							ball.setBallX(650);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() + 50);
							} else {
								ball.setBallX(ball.getBallposX() + 20);
							}
						}
					}
					if (paddle.getType().equalsIgnoreCase("Large Paddle")) {
						if (ball.getBallposX() >= 600) {
							ball.setBallX(600);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() + 50);
							} else {
								ball.setBallX(ball.getBallposX() + 20);
							}
						}
					}


				

				}


			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (play == false) {
					if (paddle.getType().equalsIgnoreCase("Normal Paddle")) {
						if (ball.getBallposX() <= 45) {
							ball.setBallX(45);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() - 50);
							} else {
								ball.setBallX(ball.getBallposX() - 20);
							}
						}
					}

					if (paddle.getType().equalsIgnoreCase("Small Paddle")) {
						if (ball.getBallposX() <= 16) {
							ball.setBallX(16);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() - 50);
							} else {
								ball.setBallX(ball.getBallposX() - 20);
							}
						}
					}
					if (paddle.getType().equalsIgnoreCase("Large Paddle")) {
						if (ball.getBallposX() <= 80) {
							ball.setBallX(80);
						} else {
							if (paddle.getDx() == 50) {
								ball.setBallX(ball.getBallposX() - 50);
							} else {
								ball.setBallX(ball.getBallposX() - 20);
							}
						}
					}

					

				}

			}

			paddle.keyPressed(e); // used moving the paddle according to the key pressed
		}

	}
	//used to remove the power ups when left the screen.
	public void cleanPowerupList()

	{
		for(int i=0;i<PowerupList.size();i++)
		{
			PowerupList.remove(PowerupList.get(i));
		}
	}

	//used for painting all the objects of a array list.
	public void paint_list11(Graphics2D a, ArrayList e) {
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i)!=null) {
				Brick vehicle11 = (Brick) e.get(i);
				vehicle11.paintCompnent(a);
			}
		}
	}
	// used for stoping the game, when all lives are ended.
	public void death(){
		if(life<1)
		{
			dead=true;
			timer.stop();
			//System.exit(0);
		}
	}

	// used for checking if the game is completed.
	public void winner(){
			level2=false;
			winner=true;
			play=false;
			path="src/11.jpg";
			BackGround();// used to change the background image.
			

	}



	public void level1() {

		movingBricks(); // used for creating moving bricks.
		int i=25;
		int y=300;
		int n=0;
		/*
		Used for creating a digonal pattern of bricks for level 1.
		 */
		while (n<14){
			int ran = (int) (Math.random() * 3);
			brc = brickFactory.getBrick(ran, i,y);
			brick_array.add(brc);
			i+=brc.getBounds().getWidth();
			y-=brc.getBounds().getHeight();
			n++;
		}
		i=675;
		y=300;
		n=0;
		while (n<14){
			int ran = (int) (Math.random() * 3);
			brc = brickFactory.getBrick(ran, i,y);
			brick_array.add(brc);
			i-=brc.getBounds().getWidth();
			y-=brc.getBounds().getHeight();
			n++;
		}

		// used for storing all unbreakble bricks to the game.
		brc= brickFactory.getBrick(3,708/2,232);
		brick_array.add(brc);
		brc= brickFactory.getBrick(3,708/2,147);
		brick_array.add(brc);
		brc= brickFactory.getBrick(3,225,185);
		brick_array.add(brc);
		brc= brickFactory.getBrick(3,475,185);
		brick_array.add(brc);

	}


	// stores all the moving bricks to the array.
	public void movingBricks(){
		for (int i = 50; i < 700; i += 250) {
			int ran = (int) (Math.random() * 3);
			brc = brickFactory.getBrick(ran, i, 350);
			Moving_array.add(brc);
		}

		for (int i = 50; i < 700; i += 250) {
			int ran = (int) (Math.random() * 3);
			brc = brickFactory.getBrick(ran, i, 50);
			Moving_array.add(brc);
		}
	}

	//calls the moving component of the relevent brick.
	public void movingaarray(){
		for(int i=0;i<Moving_array.size();i++){
			Brick object=Moving_array.get(i);
			if(object instanceof Bricks){
				((Bricks) object).move();
			}
			else if(object instanceof BrickL2){
				((BrickL2) object).move();
			}
			else if(object instanceof BrickL3){
				((BrickL3) object).move();
			}
			else if(object instanceof UBricks){
				((UBricks) object).move();
			}
		}
	}

	//used for checking the collision between the moving bricks.
	public void moving_brick_check() {
		for (int i = 0; i < Moving_array.size(); i++) {
			Brick object = Moving_array.get(i);
			Rectangle r = object.getBounds();
			for (int j = i+1; j < Moving_array.size(); j++){
				Brick obj2= Moving_array.get(j);
				if(obj2.getBounds().intersects(r)){
					// gets the brick and changes its direction according to its moving variant.
					if(object instanceof Bricks){
						((Bricks) object).Isleft=!((Bricks) object).Isleft;
					}
					else if(object instanceof BrickL2){
						((BrickL2) object).Isleft=!((BrickL2) object).Isleft;
					}
					else if(object instanceof BrickL3){
						((BrickL3) object).Isleft=!((BrickL3) object).Isleft;
					}
					else if(obj2 instanceof UBricks){
						((UBricks) object).Isleft=!((UBricks) object).Isleft;
					}
					if(obj2 instanceof Bricks){
						((Bricks) obj2).Isleft=!((Bricks) obj2).Isleft;
					}
					else if(obj2 instanceof BrickL2){
						((BrickL2) obj2).Isleft=!((BrickL2) obj2).Isleft;
					}
					else if(obj2 instanceof BrickL3){
						((BrickL3) obj2).Isleft=!((BrickL3) obj2).Isleft;
					}
					else if(obj2 instanceof UBricks){
						((UBricks) obj2).Isleft=!((UBricks) obj2).Isleft;
					}
				}
			}
		}
	}

	//used for checking the collision between the bricks and ball.
	public void check_collision(ArrayList e){
		for(int i=0;i<e.size();i++){
			Object object=e.get(i);
			Rectangle r = ((Brick)object).getBounds();
			if(ball.FireBall==true) {
				// checks if the ball is fireBall it can destroy any brick.
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					BrickX=r.x;
					BrickY=r.y;
					ball.changedirectiony();
						e.remove(object);
					ball.changedirectiony();

					// generates a powerup on brick destruction.
					if(!(object instanceof UBricks))
					{

						if(counter>200)
						{
							powerup=powerupFactory.GetPowerUp(random);
							powerup.SetXY(BrickX, BrickY);
							PowerupList.add(powerup);
							score +=10;
							counter=0;
						}
					}
				}
			}
			if(object instanceof Bricks) {
				//checks collision between a normal brick and destroys it and generates a pwoerup.
				//changes ball direction on collision.
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					BrickX=r.x;
					BrickY=r.y;
					ball.changedirectiony();
					e.remove(object);
					if(counter>200)
					{
						powerup=powerupFactory.GetPowerUp(random);
						powerup.SetXY(BrickX, BrickY);
						PowerupList.add(powerup);
						score +=10;
						counter=0;
					}
				}
			}
			if(object instanceof  BrickL2){
				// checks collision between a level 2 brick and changes image if first collision and destroys if second.
				//changes ball direction on collision
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					ball.changedirectiony();
					if(((BrickL2) object).isAlive()){
						((BrickL2) object).setAlive(false);
					}
					else{
						BrickX=r.x;
						BrickY=r.y;
						e.remove(object);
						ball.changedirectiony();
						if(counter>200)
						{
							// generates a power if brick is destroyed.
							powerup=powerupFactory.GetPowerUp(random);
							powerup.SetXY(BrickX, BrickY);
							PowerupList.add(powerup);
							score +=10;
							counter=0;
						}
					}
				}
			}

			if(object instanceof  BrickL3)
			{
				/*
				checks collision between a level 3 brick and changes image if 2nd collision and destroys it if third.
				changes ball direction on collision
				 */
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					ball.changedirectiony();
					if(((BrickL3) object).isAlive()){
						((BrickL3) object).setAlive(false);
					}
					else if ((((BrickL3) object).isAlive1()))
					{
						((BrickL3) object).setAlive1(false);
					}
					else {
						BrickX=r.x;
						BrickY=r.y;
						e.remove(object);
						ball.changedirectiony();
						if(counter>200)
						{
							// generates a powerup if brick destoryed.
							powerup=powerupFactory.GetPowerUp(random);
							powerup.SetXY(BrickX, BrickY);
							PowerupList.add(powerup);
							score +=10;
							counter=0;
						}
					}
				}
			}
			if (object instanceof UBricks){
				/*
				checks collision to change direction with unbreakable brick.
				destroys brick only if unbreakable bricks are left.
				 */

				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					ball.changedirectiony();
					
					for(int x=0;x<e.size();x++){
						Object obj=e.get(x);
						if((obj instanceof UBricks))
						{
						counterUB++;
						}
						
					}
					if(counterUB==e.size() && Moving_array.size()==0){
							e.remove(object);
							score+=100;
					}
					counterUB=0;
				}
			}
		}

	}

	/*
	copy of the check collision function for checking collision of all the split balls created.
	 */
	public void Dcheck_collision(ArrayList e){
		for(int a=0;a<dballs.size();a++) {
			for (int i = 0; i < e.size(); i++) {
				Object object = e.get(i);
				Rectangle r = ((Brick) object).getBounds();
				if (dballs.get(a).FireBall == true) {
					if (new Rectangle(dballs.get(a).getBallposX(), dballs.get(a).getBallposY(), 20, 20).intersects(r)) {
						BrickX = r.x;
						BrickY = r.y;
						dballs.get(a).changedirectiony();
						if (!(object instanceof UBricks))
							e.remove(object);

						dballs.get(a).changedirectiony();
						if (!(object instanceof UBricks)) {
							if (counter > 200) {
								powerup = powerupFactory.GetPowerUp(random);
								powerup.SetXY(BrickX, BrickY);
								PowerupList.add(powerup);
								score += 10;
								counter = 0;
							}
						}
					}
				}
				if (object instanceof Bricks) {
					if (new Rectangle(dballs.get(a).getBallposX(), dballs.get(a).getBallposY(), 20, 20).intersects(r)) {
						BrickX = r.x;
						BrickY = r.y;
						dballs.get(a).changedirectiony();
						e.remove(object);
						if (counter > 200) {
							powerup = powerupFactory.GetPowerUp(random);
							powerup.SetXY(BrickX, BrickY);
							PowerupList.add(powerup);
							score += 10;
							counter = 0;
						}
					}
				}
				if (object instanceof BrickL2) {
					if (new Rectangle(dballs.get(a).getBallposX(), dballs.get(a).getBallposY(), 20, 20).intersects(r)) {
						dballs.get(a).changedirectiony();
						if (((BrickL2) object).isAlive()) {
							((BrickL2) object).setAlive(false);
						} else {
							BrickX = r.x;
							BrickY = r.y;
							e.remove(object);
							dballs.get(a).changedirectiony();
							if (counter > 200) {
								powerup = powerupFactory.GetPowerUp(random);
								powerup.SetXY(BrickX, BrickY);
								PowerupList.add(powerup);
								score += 10;
								counter = 0;
							}
						}
					}
				}
				if (object instanceof BrickL3) {
					if (new Rectangle(dballs.get(a).getBallposX(), dballs.get(a).getBallposY(), 20, 20).intersects(r)) {
						dballs.get(a).changedirectiony();
						if (((BrickL3) object).isAlive()) {
							((BrickL3) object).setAlive(false);
						} else if ((((BrickL3) object).isAlive1())) {
							((BrickL3) object).setAlive1(false);
						} else {
							BrickX = r.x;
							BrickY = r.y;
							e.remove(object);
							dballs.get(a).changedirectiony();
							if (counter > 200) {
								powerup = powerupFactory.GetPowerUp(random);
								powerup.SetXY(BrickX, BrickY);
								PowerupList.add(powerup);
								score += 10;
								counter = 0;
							}
						}
					}
				}
				if (object instanceof UBricks) {
					if (new Rectangle(dballs.get(a).getBallposX(),dballs.get(a).getBallposY(), 20, 20).intersects(r)) {
						

						dballs.get(a).changedirectiony();
						for(int x=0;x<e.size();x++){
							Object obj=e.get(x);
							if((obj instanceof UBricks))
							{
								counterUB++;
							}
							
						}
						if(counterUB==e.size() && Moving_array.size()==0){
							e.remove(object);
							score+=100;
						}
						counterUB=0;
					}
				}
			}
		}
	}


	// used for starting level 2.
	public void EndLevel1(){

		if(brick_array.size()==0 && Moving_array.size()==0){
			level1=false;
			play=false;
			level2=true;
			ball=new Ball((paddle.getX()-10)+((paddle.getWidth())/2), paddle.getY()-20);
			path="src/bc4d0966198ee5e2049ebeaae786066bfb257d38.jpg";
			// used for showing the user that level 1 is completed.
			JOptionPane.showMessageDialog(null,"Congrats! you have reached level 2. Press Ok to start");
			BackGround();// changes background.
			Level2();// re loads bricks for level2
		}
	}

	// creates the background image to be displayed accordingly.
	public void BackGround(){
		img = new ImageIcon(path);
		BGImage = img.getImage();
		BGWidth=img.getIconWidth();
		BGHeight=img.getIconHeight();
	}

	public void Level2()
			/*
			used for creating random bricks in a heart shaped pattern.
			 */
	{
		for(int i=100;i<650;i+=50){
			if(i<300 || i>400){
			brc= brickFactory.getBrick((int)(Math.random()*3),i,100);
			brick_array.add(brc);
			}
		}
		for(int i=50;i<700;i+=50){
			if(i!=350){
				brc= brickFactory.getBrick((int)(Math.random()*3),i,150 );
				brick_array.add(brc);
			}
		}
		for(int i=50;i<700;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,200);
				brick_array.add(brc);
			}
		}
		for(int i=100;i<650;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,250);
				brick_array.add(brc);
			}
		}
		for(int i=150;i<600;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,300);
				brick_array.add(brc);
			}
		}
		for(int i=200;i<550;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,350);
				brick_array.add(brc);
			}
		}
		for(int i=250;i<500;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,400);
				brick_array.add(brc);
			}
		}
		for(int i=300;i<450;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,450);
				brick_array.add(brc);
			}
		}
		for(int i=350;i<400;i+=50){
			{
				brc= brickFactory.getBrick((int)(Math.random()*3),i,500);
				brick_array.add(brc);
			}
		}
	}


	/*
	checks the brick collision with all the bullets fired.
	 */
	public void Bullet_collision(ArrayList e){
		/*
		gets the type of brick from the brick array and destroys it when bullet is collided according to its
		level (Strength).
		 */
		for(int a=0;a<BList.size();a++) {
			for (int i = 0; i < e.size(); i++) {
				Object object = e.get(i);
				Rectangle r = ((Brick) object).getBounds();
				
				if (object instanceof Bricks) {
					if (BList.get(a).getBounds().intersects(r)) {
						e.remove(object);
						BList.remove(a);

					}
				}
				if (object instanceof BrickL2) {
					if (BList.get(a).getBounds().intersects(r)) {
			
						if (((BrickL2) object).isAlive()) {
							((BrickL2) object).setAlive(false);
						} else {
							e.remove(object);
							BList.remove(a);
						}
					}
				}
				if (object instanceof BrickL3) {
					if (BList.get(a).getBounds().intersects(r)) {
					
						if (((BrickL3) object).isAlive()) {
							((BrickL3) object).setAlive(false);
						} else if ((((BrickL3) object).isAlive1())) {
							((BrickL3) object).setAlive1(false);
						} else {
							
							e.remove(object);
							BList.remove(a);
						}
					}
				}
				
				if (object instanceof UBricks) {
					if (BList.get(a).getBounds().intersects(r)) {

						BList.remove(a);

						for(int x=0;x<e.size();x++){
							if(e.get(x) instanceof UBricks)
							{
							counterUB++;
							}
						}
						if(counterUB==e.size() && Moving_array.size()==0){
							e.remove(object);
						}
						counterUB=0;
					}
				}
			}
		}
	}

}







