package com.dario.pong;

import com.dario.pong.ball.Ball;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * @author Dario Stano
 */
public class Main extends JFrame {
    
    //Double Buffering
    Image dbImage;
    Graphics dbg;
   
    //Ball
    static Ball b = new Ball(192, 142);
    
    public Main(){
        this.setTitle("Pong Game");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
    }
    
    public static void main(String[] args){
        Main main = new Main();
        // Start
        Thread ball = new Thread(b);
        ball.start();
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        p1.start();
        p2.start();
    }
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void draw(Graphics g){
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);
        
        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 15, 50);
        g.drawString(""+b.p2Score, 370, 50);
        
        repaint();
    }

    private class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }
}
