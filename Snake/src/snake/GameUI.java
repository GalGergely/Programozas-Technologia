/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.sql.SQLException;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.sql.SQLException;

/**
 *
 * @author pbali
 */
public class GameUI {

    private JFrame frame;
    private GameEngine gameArea;
    private JLabel timeLabel;
    private Timer timer;
    private long startTime;

    public GameUI() throws ClassNotFoundException, SQLException {
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameArea = new GameEngine();
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Játék");
        menuBar.add(gameMenu);
        JMenuItem newMenu = new JMenuItem("Új játék");
        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart();
                timer=new Timer(10,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeLabel.setText(elapsedTime()+" ms");
            }
        });
        startTime=System.currentTimeMillis();
        timer.start();
            }
        });
        gameMenu.add(newMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        JMenu scoreMenu = new JMenu("Eredmény");
        JMenuItem scores = new JMenuItem("Top 10");
        scores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HighScoreWindow hm = new HighScoreWindow(gameArea.db.getScores(), frame);
                } catch (SQLException s) {
                }

            }
        });
        scoreMenu.add(scores);
        menuBar.add(scoreMenu);
        timeLabel = new JLabel(" ");
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        timer=new Timer(10,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeLabel.setText(elapsedTime()+" ms");
            }
        });
        startTime=System.currentTimeMillis();
        timer.start();
        frame.getContentPane().add(gameArea);
    frame.getContentPane().add(timeLabel, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

}
