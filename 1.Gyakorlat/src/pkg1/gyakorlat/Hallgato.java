/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1.gyakorlat;

/**
 *
 * @author Greg
 */
public class Hallgato {
    private String nev;
    private double atlag;

    public Hallgato(String nev, double atlag) {
        this.nev = nev;
        this.atlag = atlag;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public double getAtlag() {
        return atlag;
    }
    
    public void setAtlag(double atlag) {
        this.atlag = atlag;
    }
    @Override
    public String toString() {
        return "Hallgato{" + "nev=" + nev + ", atlag=" + atlag + '}';
    }
}
