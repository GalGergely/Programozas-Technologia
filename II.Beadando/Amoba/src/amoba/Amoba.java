/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amoba;

import model.AmobaModel;

/**
 *
 * @author Greg
 */
public class Amoba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       AmobaModel model= new AmobaModel();
       AmobaGUI gui= new AmobaGUI(model);
       System.out.println();
    }
    
}
