package main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FocusTest extends JFrame{
    public FocusTest(){
        setLayout(new BorderLayout());
        JButton btn = new JButton("Click me");
        add(btn, BorderLayout.NORTH);
        btn.setFocusable(false);
        btn.addActionListener(e -> System.out.println("Click") );
        
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Pressed: " + e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) { new FocusTest(); }
}
