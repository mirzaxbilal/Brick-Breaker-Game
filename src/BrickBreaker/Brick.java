package BrickBreaker;

import java.awt.*;

// a brick class from which all types of bricks will be extending.
public abstract class Brick {
    public abstract void paintCompnent(Graphics2D g);
    public abstract Rectangle getBounds();
}
