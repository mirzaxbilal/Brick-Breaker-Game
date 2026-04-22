package BrickBreaker;

import java.awt.Rectangle;
import java.util.ArrayList;

public class LevelGenerator {

	public Brick brc;
	public ArrayList <Brick> brick_array= new ArrayList<>();
	public BrickFactory BF= new BrickFactory();
	public ArrayList<Brick> Moving_array= new ArrayList<>();
	Ball ball;
	public Powerup powerup;
	int brickX;
	int brickY;
	PowerupFactory powerupFactory=new PowerupFactory();
	public double counter=0;
	public int score=0;
	public int random=0;
	ArrayList<Powerup> PowerupList=new ArrayList<Powerup>();
	public LevelGenerator(Ball ball,Powerup power)
	{
		this.ball=ball;
		this.powerup=power;
	}
	public void level1() {
		
		int i=25;
		int y=300;
		int n=0;
		
		while (n<14){
			int ran = (int) (Math.random() * 3);
			brc = BF.getBrick(ran, i,y);
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
			brc = BF.getBrick(ran, i,y);
			brick_array.add(brc);
			i-=brc.getBounds().getWidth();
			y-=brc.getBounds().getHeight();
			n++;
		}
		brc=BF.getBrick(3,708/2,232);
		brick_array.add(brc);
		brc=BF.getBrick(3,708/2,147);
		brick_array.add(brc);
		brc=BF.getBrick(3,225,185);
		brick_array.add(brc);
		brc=BF.getBrick(3,475,185);
		brick_array.add(brc);

	}
	
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
	public void checking() {
		for (int i = 0; i < Moving_array.size(); i++) {
			Brick object = Moving_array.get(i);
			Rectangle r=object.getBounds();
			for (int j = i+1; j < Moving_array.size(); j++){
				Brick obj2= Moving_array.get(j);
				if(obj2.getBounds().intersects(r)){
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

	
	public void check_collision(ArrayList e){
		for(int i=0;i<e.size();i++){
			Object object=e.get(i);
			Rectangle r = ((Brick)object).getBounds();
			if(ball.FireBall==true) {
				if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(r)) {
					brickX=r.x;
					brickY=r.y;
				;
					ball.changedirectiony();
					if(!(object instanceof UBricks))
						e.remove(object);
				
					ball.changedirectiony();
					if(!(object instanceof UBricks))
					{
						if(counter>200)
						{
							powerup=powerupFactory.GetPowerUp(random);
							powerup.SetXY(brickX, brickY);
							PowerupList.add(powerup);
							score +=10;
							counter=0;
						}
					}
				}
			}
}
	}
}
