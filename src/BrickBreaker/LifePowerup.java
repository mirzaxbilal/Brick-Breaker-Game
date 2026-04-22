package BrickBreaker;

public class LifePowerup extends Powerup{

	private String type="Life Powerup"; //type of powerup

	//constructor
	public LifePowerup() {
		super("src/Life.png");
		
	}

	// used for moving the power up

	@Override
	public void move() {
		moveTo(x,y+3);
	}

	// getter method.

	@Override
	public String getType() {
		return type;
	}

}
