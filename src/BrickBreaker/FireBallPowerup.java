package BrickBreaker;

public class FireBallPowerup extends Powerup{

	private String type="Fire Ball"; // type of power

	//constructor
	public FireBallPowerup() {
		super("src/FireBallPowerup.png");
	
	}

	//used for moving
	@Override
	public void move() {
		moveTo(x,y+3);
		
	}

	//getter.
	@Override
	public String getType() {
		return type;
	}

}
