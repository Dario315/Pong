/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dario.pong.ball;

import com.dario.pong.ball.paddle.Paddle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Dario Stano
 */
public final class Ball implements Runnable{
    
    int x, y, xDirection, yDirection;
    
    public int p1Score, p2Score;
    
    public Paddle p1 = new Paddle(15, 140, 1);
    public Paddle p2 = new Paddle(370, 140, 2);
    
    Rectangle ball;
    
    public Ball(int x, int y){
        p1Score = p2Score = 0;
        this.x = x;
        this.y = y;
        //Ball bewegung X Richtung
        Random r = new Random();
        int rDir = r.nextInt(1);
        if(rDir == 0)
            rDir--;
        setXDirection(rDir);
        //Ball bewegung Y Richtung
        int yrDir = r.nextInt(1);
        if(rDir == 0)
            yrDir--;
        setYDirection(yrDir);
        //Rechteck erstellen
        ball = new Rectangle(this.x, this.y, 10, 10);
    }
    
    public void setXDirection(int xdir){
        xDirection = xdir;
    }
    public void setYDirection(int ydir){
        yDirection = ydir;
    }
    
     public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);
    }
     
     public void collision(){
         if(ball.intersects(p1.paddle))
             setXDirection(+1);
         if(ball.intersects(p2.paddle))
             setXDirection(-1);
     }
    
    public void move(){
        collision();
        ball.x += xDirection;
        ball.y += yDirection;
        
        //Abpraller wenn Wand in der nähe
        if(ball.x <= 0){
            setXDirection(+1);
            //Punkte zum Score
            p2Score++;
        }
        if(ball.x >= 385){
            setXDirection(-1);
            //Punkte zum Score
            p1Score++;
        }
        
        if(ball.y <= 15)
            setYDirection(-2);
        
        if(ball.y >= 285)
            setYDirection(+1);
    }
    
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(7);
            }
        }catch(Exception e){System.err.println(e.getMessage());}
    }
}
