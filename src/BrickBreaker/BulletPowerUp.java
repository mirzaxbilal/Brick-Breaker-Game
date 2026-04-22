package BrickBreaker;

public class BulletPowerUp extends Powerup {

	private String type="Bullet Powerup"; // type of powerup.

	//bullet constructor
	public BulletPowerUp() {
		super("src/BulletPowerup.png");
	
	}

	// used for moving the powerup.
	@Override
	public void move() {
		moveTo(x,y+3);
		
	}

	//getter method.
	@Override
	public String getType() {
		return type;
	}

	
}
