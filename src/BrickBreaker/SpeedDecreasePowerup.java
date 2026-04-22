package BrickBreaker;

public class SpeedDecreasePowerup extends Powerup {

	private String type="Speed Decrease"; // type of the power up

	// constructor.

	public SpeedDecreasePowerup() {
		super("src/SpeedDecrease.png");
	}

	// moving the power up
	@Override
	public void move() {
		moveTo(x,y+3);
		
	}

	// getter method for type.
	@Override
	public String getType() {
		return type;
	}

}
