package BrickBreaker;

public class SizeDecreasePowerup extends Powerup {
	
	private String type="Size Decrease"; // type of power up

	// constructor
	public SizeDecreasePowerup() {

			super("src/SizeDecrease.png");
		}

		// used for moving the power up
		@Override
		public void move() {
			moveTo(x,y+3);
		}

		// used for getting type of the power up.
		@Override
		public String getType() {
			
			return type;
		}
		
		
}
