/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.lang.Math;
import java.awt.Image;
import java.awt.Graphics;

/**
 *
 * @author pbali
 */
public class Food extends Sprite {

    public Food(int x,int y,int width,int height,Image img,Snake s) {
        super(x,y,width,height,img);
        int currentx = (int) (Math.random() * 700);
        int currenty = (int) (Math.random() * 700);
        if (s.getx() == currentx && s.gety() == currenty) {
            while (s.getx() == currentx && s.gety() == currenty) {
                currentx = (int) (Math.random() * 700);
                currenty = (int) (Math.random() * 700);
            }
            
        }
        this.x=currentx;
        this.y=currenty;
        this.width=width;
        this.height=height;
        this.image=img;
    }
    
    public boolean collides(Snake snake)
    {
        if(snake.collides(this))
        {
            int currentx = (int) (Math.random() * 700);
        int currenty = (int) (Math.random() * 700);
        if (snake.getx() == currentx && snake.gety() == currenty) {
            while (snake.getx() == currentx && snake.gety() == currenty) {
                currentx = (int) (Math.random() * 700);
                currenty = (int) (Math.random() * 700);
            }
            
        }
        x=currentx;
        y=currenty;
        return true;
        }
        return false;
    }
    public boolean collides(Rock rock)
    {
        if(rock.collides(this))
        {
            int currentx = (int) (Math.random() * 700);
        int currenty = (int) (Math.random() * 700);
        if (rock.getx() == currentx && rock.gety() == currenty) {
            while (rock.getx() == currentx && rock.gety() == currenty) {
                currentx = (int) (Math.random() * 700);
                currenty = (int) (Math.random() * 700);
            }
            
        }
        x=currentx;
        y=currenty;
        return true;
        }
        return false;
    }
    
}
