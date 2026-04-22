package BrickBreaker;

public class SplitPowerup extends Powerup {

    private String type="Split Ball";  // type of the power up

    public SplitPowerup() {
        super("src/45-Breakout-Tiles.png");
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

