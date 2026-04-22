package BrickBreaker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    //Gameplay game=new Gameplay();
    FileActions FA=new FileActions();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int Xvalue=e.getX();
        int Yvalue=e.getY();

        /*
            public Rectangle Newgame=new Rectangle(270,150,100,50);
    public Rectangle Loadgame=new Rectangle(270,250,100,50);
    public Rectangle Quit =new Rectangle(270,350,100,50);
         */

        //Play Button
        if(Xvalue>=270 && Xvalue<=370){
            if(Yvalue>=150 && Yvalue<=200){
                //PLay Button Pressed
                Gameplay.state=Gameplay.STATE.GAME;
                //game.gameplay();
            }
        }

        // QUit Button
        if(Xvalue>=270 && Xvalue<=370){
            if(Yvalue>=350 && Yvalue<=400){
                //Quit Button Pressed
               System.exit(0);
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
