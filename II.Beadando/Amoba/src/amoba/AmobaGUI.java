/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amoba;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GraphicAttribute;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.AmobaModel;

/**
 *
 * @author Greg
 */
public class AmobaGUI {

    private JFrame menu;
    private JPanel menuPanel;
    private JFrame game;
    private JPanel gamePanel;
    private JPanel buttonPanel;
    private AmobaModel model;
    private JFrame endofgame;
    private JLabel turn;
    private JLabel endOfGame;
    private ArrayList<ArrayList<MyJButton>> buttons;

    public AmobaGUI(AmobaModel model) {
        buttons=new ArrayList<>();
        this.model = model;
        menu = new JFrame("Amoba");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.getContentPane().setLayout(new BoxLayout(menu.getContentPane(), BoxLayout.Y_AXIS));

        menuPanel = new JPanel();
        JTextField textField = new JTextField("5");
        textField.setColumns(10);
        menuPanel.add(textField);
        JButton subbmit = new JButton("subbmit");
        subbmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setBoradSize(Integer.parseInt(textField.getText()));
                System.out.println(model.getBoradSize());
                menu.setVisible(false);
                createGame();
                game.setVisible(true);
            }
        });
        menuPanel.add(subbmit);

        JLabel empty = new JLabel(" ");
        menu.add(empty);

        JLabel label = new JLabel("set the board size:");
        menu.add(label);
        menu.add(menuPanel);
        menu.setSize(300, 150);
        menu.setVisible(true);
        endofgame = new JFrame("Amoba");
        endofgame.getContentPane().setLayout(new BoxLayout(endofgame.getContentPane(), BoxLayout.Y_AXIS));
        endofgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endOfGame=new JLabel("End Of Game.");
        endofgame.add(endOfGame);
        JButton anya = new JButton("Play Again");
        anya.addActionListener(new restartGame());
        endofgame.add(anya);
        endofgame.setSize(300, 150);
        
    }
    private class restartGame implements ActionListener{

        public restartGame() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.dispose();
            endofgame.setVisible(false);
            menu.setVisible(true);
            buttons=new ArrayList<>();
         model.reset();
        }
        
    }

    private void createGame() {
        String[][] temperarymemory = new String[model.getBoradSize()][model.getBoradSize()];
        game = new JFrame("Amoba");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setLayout(new BoxLayout(game.getContentPane(), BoxLayout.Y_AXIS));

        buttonPanel = new JPanel(new GridLayout(model.getBoradSize(), model.getBoradSize()));
        buttons=new ArrayList<>();
        for (int i = 0; i < model.getBoradSize(); i++) {
            buttons.add(new ArrayList<>());
            for (int j = 0; j < model.getBoradSize(); j++) {
                temperarymemory[i][j] = "I";
                MyJButton b = new MyJButton(i, j);
                buttons.get(i).add(b);
                System.out.println("b:"+b+" "+buttons.get(i).get(j) + ",i: "+i);
                b.addActionListener(new GameButtonListener(b));
                buttonPanel.add(b);
            }
        }
        model.setGameBoardMemory(temperarymemory);
        buttonPanel.setSize(500, 500);
        turn = new JLabel("Player1 Turn");
        game.add(turn);
        game.add(buttonPanel);
        game.setSize(600, 600);
    }
    private void deleteButtonFromGui() {
        if(model.needsDelete){
            for (int i = 0; i < model.getBoradSize(); i++) {
                for (int j = 0; j < model.getBoradSize(); j++) {
                    if(buttons.get(i).get(j).x==model.needDeletex){
                        if(buttons.get(i).get(j).y==model.needDeletey){
                            buttons.get(model.needDeletex).get(model.needDeletey).setText("");
                            buttons.get(model.needDeletex).get(model.needDeletey).setEnabled(true);
                            System.out.println("aha");
                            model.needsDelete=false;
                        }
                    }
                }
            }  
        }
        if(model.needsDelete2){
            for (int i = 0; i < model.getBoradSize(); i++) {
                for (int j = 0; j < model.getBoradSize(); j++) {
                    if(buttons.get(i).get(j).x==model.needDeletex2){
                        if(buttons.get(i).get(j).y==model.needDeletey2){
                            buttons.get(model.needDeletex2).get(model.needDeletey2).setText("");
                            buttons.get(model.needDeletex2).get(model.needDeletey2).setEnabled(true);
                            System.out.println("aha2");
                            model.needsDelete2=false;
                        }
                    }
                }
            }  
        }
    }

    private class GameButtonListener implements ActionListener {

        MyJButton b;

        private GameButtonListener(MyJButton b) {
            this.b = b;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean asd = model.turn;
            if (asd) {
                b.setText("X");
                model.setGameBoardMemory2(b.x, b.y, "X");
                model.checkStreakOnPlayerMove(b.x, b.y, "X");
                turn.setText("Player1 Turn");
            } else {
                b.setText("O");
                model.setGameBoardMemory2(b.x, b.y, "O");
                model.checkStreakOnPlayerMove(b.x, b.y, "O");
                turn.setText("Player2 Turn");
            }
            model.dontetlen();
            b.setEnabled(false);
            if(model.thegameended)
            {
                game.setVisible(false);
                endOfGame.setText(endOfGame.getText()+" "+model.winner+" Congrats");
                endofgame.setVisible(true);
            }
            deleteButtonFromGui();
            model.setTurn(!model.getTurn());
        }
    }

    private class MyJButton extends JButton {

        int x;
        int y;

        public MyJButton(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "MyJButton{" + "x=" + x + ", y=" + y + '}';
        }
    }

}
