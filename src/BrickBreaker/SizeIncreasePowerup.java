package BrickBreaker;



public class SizeIncreasePowerup extends Powerup {

	private String type="Size Increase"; // type of the power up.

	// constructor

	public SizeIncreasePowerup()
	{
		super("src/SizeIncrease.png");
	}

	//moving power
	@Override
	public void move() {
		moveTo(x,y+3);
	}

	// getter method.
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
}
