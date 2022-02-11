package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
     final int GAME_WIDTH = 600;
     final int GAME_HEIGHT = 600;
     final int UNIT_SIZE = 25;
     final int GAME_UNITS = (GAME_WIDTH * GAME_HEIGHT) / UNIT_SIZE;
     final int DELAY = 100;
     Player Player1;
     Player Player2;
     boolean running = false;
     Timer timer;
     Random random;
     String won;
     boolean readytoclose=false;
     ArrayList<Wall> walls;
     public Database db;
     
    
    Game(String p1, myColor c1, String p2, myColor c2) throws SQLException, ClassNotFoundException {
        db = new Database(10);
        walls=new ArrayList();
        random = new Random();
        Player1=new Player(p1,c1,0,0,'R');
        Player2=new Player(p2,c2,23*UNIT_SIZE,23*UNIT_SIZE,'L');
        
        random = new Random();
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new Game.MyKeyAdapter());
        this.setSize(600, 600);
        startGame();

    }
    /**
     * ez generalja a falakat aminek neki lehet menni
     */
    public void generateWalls() {
        Random r = new Random();
        int d = (int) ((r.nextInt(5) + 2) / 1);
        for (int i = 0; i < d; i++) 
        {
        Random r1 = new Random();
        int x = (int) ((r1.nextInt(21) + 2) / 1);
        int y = (int) ((r1.nextInt(21) + 2) / 1);
        x=x*UNIT_SIZE;
        y=y*UNIT_SIZE;
        walls.add(new Wall(x,y));
        System.out.println("x: "+walls.get(i).x + " y: "+walls.get(i).y);
        }
    }
    /**
     * ez inditja el a jatekot
     */
    public void startGame() {
        generateWalls();
        running = true;
       timer = new Timer(DELAY, this);
        timer.start();
    }
    /**
     * ez rajzol ki egy komponenst
     * @param g 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    /**
     * ez rajzol ki a grafikára
     * @param g 
     */
    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.gray);
            g.setPaintMode();
            for (int i = 0; i < GAME_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, GAME_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, GAME_WIDTH, i * UNIT_SIZE);
            }
            for (int i = 0; i < Player2.bodyParts; i++) {
                g.setColor(new Color(Player2.color.r, Player2.color.g, Player2.color.b));
                g.fillRect(Player2.x[i], Player2.y[i], UNIT_SIZE, UNIT_SIZE);
            }
            for (int i = 0; i < Player1.bodyParts; i++) {
                g.setColor(new Color(Player1.color.r, Player1.color.g, Player1.color.b));
                g.fillRect(Player1.x[i], Player1.y[i], UNIT_SIZE, UNIT_SIZE);
            }
           /* for (int i = 0; i < Player2.bodyParts; i++) {
                if (i == 0) {
                    g.setColor(new Color(250, 0, 0));
                    g.fillRect(Player2.x[i], Player2.y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    if ((i * 10) < 255) {
                        g.setColor(new Color(255, (i * 10), (i * 10)));
                    }
                    g.fillRect(Player2.x[i], Player2.y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }*/
            for (int i = 0; i < walls.size(); i++) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(walls.get(i).x, walls.get(i).y, UNIT_SIZE, UNIT_SIZE);
            }
        } else {
            gameOver(g);
        }
    }
    /**
     * ezzel mozognak a motorok
     */
    public void move() {
        for (int i = Player1.bodyParts; i > 0; i--) {
            Player1.x[i] = Player1.x[i - 1];
            Player1.y[i] = Player1.y[i - 1];
        }
        switch (Player1.direction) {
            case 'U':
                Player1.y[0] = Player1.y[0] - UNIT_SIZE;
                break;
            case 'D':
                Player1.y[0] = Player1.y[0] + UNIT_SIZE;
                break;
            case 'L':
                Player1.x[0] = Player1.x[0] - UNIT_SIZE;
                break;
            case 'R':
                Player1.x[0] = Player1.x[0] + UNIT_SIZE;
                break;
        }
        for (int i = Player2.bodyParts; i > 0; i--) {
            Player2.x[i] = Player2.x[i - 1];
            Player2.y[i] = Player2.y[i - 1];
        }
        switch (Player2.direction) {
            case 'U':
                Player2.y[0] = Player2.y[0] - UNIT_SIZE;
                break;
            case 'D':
                Player2.y[0] = Player2.y[0] + UNIT_SIZE;
                break;
            case 'L':
                Player2.x[0] = Player2.x[0] - UNIT_SIZE;
                break;
            case 'R':
                Player2.x[0] = Player2.x[0] + UNIT_SIZE;
                break;
        }
    }
/**
 * megnézi összementek e a csövesbánatok
 * @throws SQLException 
 */
    public void checkCollisions() throws SQLException {
        //player1 with himself
        for (int i = Player1.bodyParts; i > 0; i--) {
            if ((Player1.x[0] == Player1.x[i]) && (Player1.y[0] == Player1.y[i])) {
                running = false;
                won=Player2.name;
                db.plusHighScore(Player2.name, 1);
            }
        }
        //player1 with walls
        if (Player1.x[0] < 0) {
            running = false;
            won=Player2.name;
            db.plusHighScore(Player2.name, 1);
        }
        if (Player1.x[0] >= GAME_WIDTH) {
            running = false;
            won=Player2.name;
            db.plusHighScore(Player2.name, 1);
        }
        if (Player1.y[0] < 0) {
            running = false;
            won=Player2.name;
            db.plusHighScore(Player2.name, 1);
        }
        if (Player1.y[0] >= GAME_HEIGHT) {
            running = false;
            won=Player2.name;
            db.plusHighScore(Player2.name, 1);
        }
         //player2 with himself
        for (int i = Player2.bodyParts; i > 0; i--) {
            if ((Player2.x[0] == Player2.x[i]) && (Player2.y[0] == Player2.y[i])) {
                running = false;
                won=Player1.name;
                db.plusHighScore(Player1.name, 1);
            }
        }
        //player2 with walls
        if (Player2.x[0] < 0) {
            running = false;
            won=Player1.name;
            db.plusHighScore(Player1.name, 1);
        }
        if (Player2.x[0] >= GAME_WIDTH) {
            running = false;
            won=Player1.name;
            db.plusHighScore(Player1.name, 1);
        }
        if (Player2.y[0] < 0) {
            running = false;
            won=Player1.name;
            db.plusHighScore(Player1.name, 1);
        }
        if (Player2.y[0] >= GAME_HEIGHT) {
            running = false;
            won=Player1.name;
            db.plusHighScore(Player1.name, 1);
        }
        //Player1 colides into Player2
        for (int i = 0; i < Player2.bodyParts; i++) {
            if (Player1.x[0]==Player2.x[i] && Player1.y[0]==Player2.y[i]) {
                running = false;
                won =Player2.name;
                db.plusHighScore(Player2.name, 1);
            }
        }
        //Player2 colides into Player1
        for (int i = 0; i < Player1.bodyParts; i++) {
            if (Player2.x[0]==Player1.x[i] && Player2.y[0]==Player1.y[i]) {
                running = false;
                won = Player1.name;
                db.plusHighScore(Player1.name, 1);
            }
        }
        //Player 1 collides into generated wall
        for (int i = 0; i < walls.size(); i++) {
            if (Player1.x[0]==walls.get(i).x && Player1.y[0]==walls.get(i).y) {
                running = false;
                won = Player2.name;
                db.plusHighScore(Player2.name, 1);
            }
        }
        //Player 2 collides into generated wall
        for (int i = 0; i < walls.size(); i++) {
            if (Player2.x[0]==walls.get(i).x && Player2.y[0]==walls.get(i).y) {
                running = false;
                won = Player1.name;
                db.plusHighScore(Player1.name, 1);
            }
        }
    }
    /**
     * átálítja a grafikát ha vége a játéknak
     * @param g 
     */
    public void gameOver(Graphics g) {

        g.setColor(Color.red);;
        g.drawString("Game Over", 100, 100);
        g.drawString(won + " won! GG", 200, 100);
        g.drawString("Press esc to go back to menu", 100, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            try {
                checkCollisions();
            } catch (SQLException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (Player1.direction != 'R') {
                        Player1.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (Player1.direction != 'L') {
                        Player1.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if (Player1.direction != 'D') {
                        Player1.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (Player1.direction != 'U') {
                        Player1.direction = 'D';
                    }
                    break;
                case KeyEvent.VK_J:
                    if (Player2.direction != 'R') {
                        Player2.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_L:
                    if (Player2.direction != 'L') {
                        Player2.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_I:
                    if (Player2.direction != 'D') {
                        Player2.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_K:
                    if (Player2.direction != 'U') {
                        Player2.direction = 'D';
                    }
                    break;
                    case KeyEvent.VK_LEFT:
                    if (Player2.direction != 'R') {
                        Player2.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (Player2.direction != 'L') {
                        Player2.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (Player2.direction != 'D') {
                        Player2.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (Player2.direction != 'U') {
                        Player2.direction = 'D';
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    ((Window) getRootPane().getParent()).dispose();
                    readytoclose=true;
                    System.out.println("true");
                    break;
                    case KeyEvent.VK_SPACE:
                    timer.stop();
                    break;
            }
        }
    }
}
