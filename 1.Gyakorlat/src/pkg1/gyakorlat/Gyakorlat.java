/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.gyakorlat;

import java.util.ArrayList;

/**
 *
 * @author Greg
 */
public class Gyakorlat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ArrayList<Hallgato> hallgatok = new ArrayList<Hallgato>();
       Hallgato x = new Hallgato("Bence", 3.3);
       hallgatok.add(x);
       Hallgato y = new Hallgato("Gergo", 4.1);
       hallgatok.add(y);
       Hallgato z = new Hallgato("Mark", 3.8);
       hallgatok.add(z);   
       
       Hallgato max = new Hallgato("none", 0);
       Hallgato min = new Hallgato("none", 5);
       for(int i=0; i<hallgatok.size(); i++)
       {
           if(max.getAtlag() < hallgatok.get(i).getAtlag())
           {
               max =  hallgatok.get(i);
           }
           if(min.getAtlag() > hallgatok.get(i).getAtlag())
           {
               min =  hallgatok.get(i);
           }      
       }
        System.out.println("Legjobb hallgato:" + max.toString());
        System.out.println("Legroszabb hallgato:" + min.toString());
        System.out.println("osztondijas hallgatok:");
        int cntr=0;
        for(int i=0; i<hallgatok.size(); i++)
        {
            if(hallgatok.get(i).getAtlag() > 4 )
            {
                System.out.println(hallgatok.get(i).getNev());
                cntr++;
            }
        }
        if(cntr==0)
        {
            System.out.println("Nincs osztondijas hallgato");
        }
        
    }
    
}
