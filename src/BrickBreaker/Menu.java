package BrickBreaker;

import javax.swing.*;
import java.awt.*;

public class Menu {
    /*
    used for creating rectangular box as buttons.
     */
    public Rectangle Newgame=new Rectangle(270,150,100,50);
    public Rectangle Loadgame=new Rectangle(270,250,100,50);
    public Rectangle Quit =new Rectangle(270,350,100,50);
    ImageIcon img = new ImageIcon("src/image-asset.jpeg");// used for background of the menu.
   Image BGImage = img.getImage();


    public void draw(Graphics g){
        Graphics2D g2d= (Graphics2D) g;
        g.drawImage(BGImage, 0, 0, null); // used for drawing bg image.
        /*
        used for writing all the text on the menu
         */
       g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString("BRICK BREAK", 170, 60);
        g.setFont(new Font("arial", Font.BOLD, 25));
        g.setColor(Color.WHITE);
        g.drawString("New ", Newgame.x+25, Newgame.y+35);
        g.drawString("Load", Loadgame.x+25, Loadgame.y+35);
        g.drawString("Quit", Quit.x+25, Quit.y+35);
        g.setColor(Color.WHITE);

        // drawing all the rectangles.
        g2d.draw(Newgame);
        g2d.draw(Loadgame);
        g2d.draw(Quit);
    }


    //used to get the types of fronts available.
    public static void main(String []args){

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String font : fonts) {
            System.out.println(font);
        }
    }
}
