package BrickBreaker;

public class SpeedIncreasePowerup extends Powerup {
	private String type="Speed Increase"; // type of power up

	//constructor
	public SpeedIncreasePowerup( ) {
		
		super("src/SpeedUp.png");
	}

	// used for moving the power up.
	@Override
	public void move() {
		moveTo(x,y+3);
	}

	// getting type of the power up.
	@Override
	public String getType() {
		
		return type;
	}

}
