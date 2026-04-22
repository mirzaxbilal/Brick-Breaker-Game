package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class Dball {
    /*
    Duplicate of ball class, used for split ball.
     */
        protected boolean powerup;
        protected Image image;
        protected int ballposX;
        protected int ballposY;
        protected static int ballXdir=-1;
        protected static int ballYdir=-2;
        protected ImageIcon img;
        protected boolean FireBall;

        public Dball(int x, int y){
            img = new ImageIcon("src/ball.png");
            image = img.getImage();
            FireBall=false;
            ballposX=x;
            ballposY=y;
        }



        public void FireBall()
        {
            img = new ImageIcon("src/FireBall.png");
            image = img.getImage();
            FireBall=true;

        }

        public void NormalBall()
        {
            img = new ImageIcon("src/ball.png");
            image = img.getImage();
            FireBall=false;

        }
        public static void changedirectiony(){

            ballYdir=-ballYdir;
        }
        public void changedirectionx(){

            ballXdir=-ballXdir;
        }
        public void changeposition(){

            ballposX +=ballXdir;
            ballposY+=ballYdir;

        }

        public void setBallX(int x)
        {
            ballposX=x;
        }
        public void setBallY(int y)
        {
            ballposX=y;
        }
        public void setBallXdir(int xdir){
            ballXdir=xdir;
        }
        public void setBallYdir(int ballYdir){
            ballYdir=ballYdir;
        }
        //getters
        public int getBallposX() {
            return ballposX;
        }

        public int getBallXdir() {
            return ballXdir;
        }
        public int getBallposY(){
            return ballposY;
        }

        public int getBallYdir() {
            return ballYdir;
        }

        public Image getImageforpowerup()
        {
            return image;
        }


    }


