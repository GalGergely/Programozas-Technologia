/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Image;

/**
 *
 * @author pbali
 */
public class Rock extends Sprite{
    public Rock(int x,int y,int width,int height,Image img,Snake s) {
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
    
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    
}
