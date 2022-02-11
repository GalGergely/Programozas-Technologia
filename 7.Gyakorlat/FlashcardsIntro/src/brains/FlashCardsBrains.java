/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brains;

import flashcards.Card;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Greg
 */
public class FlashCardsBrains {

    private ArrayList<Card> cards;
    private int cardNumber;
    private boolean showQuestion;
    private int score;

    public void reset() {
        score = 0;
        cardNumber = 0;
        showQuestion = true;
    }

    public void read(BufferedReader br) throws IOException {
        String line;
        cards = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] qa = line.split("~");
            if (qa.length == 2) {
                cards.add(new Card(qa[0], qa[1]));
            }
        }
    }

    public void changeShowQuestion() {
        showQuestion = !showQuestion;
    }

    public void goodAnswer() {
        if (!showQuestion) {
            showQuestion = !showQuestion;
        }
        score++;
        cardNumber++;

    }

    public void badAnswer() {
        if (!showQuestion) {
            showQuestion = !showQuestion;
        }
        cardNumber++;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean getShowQuestion() {
        return showQuestion;
    }

    public void setShowQuestion(boolean showQuestion) {
        this.showQuestion = showQuestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
