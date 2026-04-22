package BrickBreaker;

public class PowerupFactory {

	//generates a powerup according to the parameter inputted.

	public Powerup GetPowerUp(int i)
	{
		if(i == 0) 
		{
			return new SizeIncreasePowerup();
		}else if(i == 1) 
		{
			return new SizeDecreasePowerup();
		}else if(i == 2) 
		{
			return new SpeedIncreasePowerup();
		}else if(i == 3) 
		{
			return new SpeedDecreasePowerup();
		}else if(i == 4) 
		{
			return new FireBallPowerup();
		}else if(i == 5) 
		{
			return new LifePowerup();
		}else if(i == 6) 
		{
			return new BulletPowerUp();

		}
		else if(i==7) {
			return new SplitPowerup();
		}
		else  {
			return null;
		}
	}
	
	
}
