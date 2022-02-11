/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author pbali
 */
class SnakePart {

    public int x;
    public int y;
    public Image img;

    SnakePart(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
}

public class Snake extends Sprite {

    private int length=0;
    public int velx;
    public int vely;
    private Image snakeFront;
    private ArrayList<SnakePart> parts;
    private boolean isAlive = true;
    Graphics g;

    /**
     * @param args the command line arguments
     */
    public Snake(int x, int y, int width, int height, Image image, Image snakeFront) {
        super(x, y, width, height, image);
        this.snakeFront = snakeFront;
        this.length = 0;
        velx = 0;
        vely = 0;
        parts = new ArrayList<SnakePart>();
        parts.add(0,new SnakePart(x,y,snakeFront));
        isAlive = true;
    }

    public boolean moveX() {
        for (int i = 0; i < parts.size()-1; i++) {
            parts.get(i).x = parts.get(i + 1).x;
            parts.get(i).y = parts.get(i + 1).y;
        }
         x+=velx;
         parts.get(parts.size()-1).x=x;
        if (x + width > 800 || x < 0) {
            isAlive = false;
            return isAlive;
        }
        return isAlive;
    }

    public boolean moveY() {
        for (int i = 0; i < parts.size()-1; i++) {
            parts.get(i).x = parts.get(i + 1).x;
            parts.get(i).y = parts.get(i + 1).y;
        }
        y += vely;
        parts.get(parts.size()-1).y=y;
        if (y + width > 800 || y < 0) {
            isAlive = false;
            return isAlive;
        }
        return isAlive;
    }

    public int getLength() {
        return length;
    }

    public void addFirst()
    {
        parts.add(0,new SnakePart(x-30,y,image));
    }
    
    public void addLength() {
        if (velx > 0) {
            parts.add(0,new SnakePart(parts.get(0).x-30,parts.get(0).y,image));
            length++;
        }
        if (velx < 0) {
            parts.add(0,new SnakePart(parts.get(0).x+30,parts.get(0).y,image));
            length++;
        }
        if (vely > 0) {
            parts.add(0,new SnakePart(parts.get(0).x,parts.get(0).y-30,image));
            length++;
        }
        if (vely < 0) {
            parts.add(0,new SnakePart(parts.get(0).x,parts.get(0).y+30,image));
            length++;

        }

    }

    public void setVelX(int i) {
        this.velx = i;
    }

    public void setVelY(int i) {
        this.vely = i;
    }
    
    public int getVelX() {
       return velx;
    }

    public int getVelY() {
        return vely;
    }
    
    public boolean hitSnake()
    {
        SnakePart snakehead=parts.get(parts.size()-1);
        for(int i=0;i<parts.size()-1;i++)
        {
            if(parts.get(i).x==snakehead.x && parts.get(i).y==snakehead.y)
            {
                return true;
            }
        }
        return false;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }
    

    @Override
    public void draw(Graphics g) {
        if(length==0)
        {
            g.drawImage(parts.get(0).img, parts.get(0).x, parts.get(0).y, width, height, null);
            g.drawImage(parts.get(1).img, parts.get(1).x, parts.get(1).y, width, height, null);
        }
        else
        {
        for (int i = 0; i < parts.size(); i++) {
            g.drawImage(parts.get(i).img, parts.get(i).x, parts.get(i).y, width, height, null);
        }
        }
    }

}
