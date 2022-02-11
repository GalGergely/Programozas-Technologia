/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.sql.SQLException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author pbali
 */
public class GameEngine extends JPanel {

    private final int SNAKE_COMPONENT_LENGTH = 30;
    private final int SNAKE_MOVEMENT = 30;

    private boolean paused = false;
    private Image background;
    private Snake snake;
    private Food food;
    private int num = 0;
    private ArrayList<Rock> rocks;
    private Timer newFrameTimer;
    private JFrame f;
    public Database db;

    public GameEngine() throws SQLException, ClassNotFoundException {
        super();
        db = new Database(10);
        background = new ImageIcon("images/desert.png").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (snake.getVelX() != SNAKE_MOVEMENT) {
                    snake.setVelX(-SNAKE_MOVEMENT);
                    snake.setVelY(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (snake.getVelX() != -SNAKE_MOVEMENT) {
                    snake.setVelX(SNAKE_MOVEMENT);
                    snake.setVelY(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (snake.getVelY() != SNAKE_MOVEMENT) {
                    snake.setVelY(-SNAKE_MOVEMENT);
                    snake.setVelX(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (snake.getVelY() != -SNAKE_MOVEMENT) {
                    snake.setVelY(SNAKE_MOVEMENT);
                    snake.setVelX(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / 10, new NewFrameListener());
        newFrameTimer.start();
    }

    public void restart() {
        Image snakeComponent = new ImageIcon("images/snakebody.png").getImage();
        Image snakeHead = new ImageIcon("images/snakehead.png").getImage();
        snake = new Snake(10, 10, SNAKE_COMPONENT_LENGTH, SNAKE_COMPONENT_LENGTH, snakeComponent, snakeHead);
        snake.setVelX(SNAKE_MOVEMENT);
        snake.setVelY(0);
        snake.addFirst();
        Image foodImage = new ImageIcon("images/foodfinal.png").getImage();
        food = new Food(snake.getx(), snake.gety(), 30, 30, foodImage, snake);
        Image rockImage = new ImageIcon("images/rock.png").getImage();
        rocks = new ArrayList<Rock>();
        num = 10;
        for (int i = 0; i < num; i++) {
            rocks.add(0, new Rock(snake.getx(), snake.gety(), 30, 30, rockImage, snake));
        }

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 800, null);
        snake.draw(grphcs);
        food.draw(grphcs);
        for (int i = 0; i < num; i++) {
            rocks.get(i).draw(grphcs);
        }
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                if (snake.velx != 0) {
                    if (snake.moveX()) {
                        if (food.collides(snake)) {
                            snake.addLength();
                        }
                        if (snake.hitSnake()) {
                            f = new JFrame();
                            String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                            try {
                                db.putHighScore(name, snake.getLength());
                            } catch (SQLException ex) {
                            }
                            snake.setVelX(0);
                            snake.setVelY(0);
                        }
                        for (int i = 0; i < num; i++) {
                            if (rocks.get(i).collides(snake)) {
                                f = new JFrame();
                                String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                                try {
                                    db.putHighScore(name, snake.getLength());
                                } catch (SQLException ex) {
                                }
                                snake.setVelX(0);
                                snake.setVelY(0);
                            }

                        }
                        for (int i = 0; i < num; i++) {
                            food.collides(rocks.get(i));

                        }

                    } else {
                        f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                        try {
                            db.putHighScore(name, snake.getLength());
                        } catch (SQLException ex) {
                        }
                        snake.setVelX(0);
                        snake.setVelY(0);
                    }
                }
                if (snake.vely != 0) {
                    if (snake.moveY()) {
                        if (food.collides(snake)) {
                            snake.addLength();
                        }
                        if (snake.hitSnake()) {
                            f = new JFrame();
                            String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                            try {
                                db.putHighScore(name, snake.getLength());
                            } catch (SQLException ex) {
                            }
                            snake.setVelX(0);
                            snake.setVelY(0);
                        }
                        for (int i = 0; i < num; i++) {
                            if (rocks.get(i).collides(snake)) {
                                f = new JFrame();
                                String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                                try {
                                    db.putHighScore(name, snake.getLength());
                                } catch (SQLException ex) {
                                }
                                snake.setVelX(0);
                                snake.setVelY(0);
                            }
                        }
                        for (int i = 0; i < num; i++) {
                            food.collides(rocks.get(i));

                        }
                    } else {
                        f = new JFrame();
                        String name = JOptionPane.showInputDialog(f, "Írd be a neved, hogy elmenthessem az eredményt!");
                        try {
                            db.putHighScore(name, snake.getLength());
                        } catch (SQLException ex) {
                        }
                        snake.setVelX(0);
                        snake.setVelY(0);
                    }
                }
            }
            repaint();
        }
    }

}
