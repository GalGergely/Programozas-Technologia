/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.util.ArrayList;

/**
 *
 * @author Greg
 */
abstract class BoardPieces {

    char type;
    int price;

    public BoardPieces(char type, int price) {
        this.type = type;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Boardpiece{" + "type=" + type + ", price=" + price + '}';
    }
}
