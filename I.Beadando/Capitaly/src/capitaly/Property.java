/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

/**
 *
 * @author Greg
 */
public class Property extends BoardPieces {

    private Boolean owned=false;
    private String ownedBy;
    private Boolean housed=false;
    
    public Property() {
        super('P', 1000);
    }

    /**
     * @return the property is owned or not. 
     * true or false
     */
    public Boolean getOwned() {
        return owned;
    }
    /**
    * @param owned, it sets the owned to true if someone buys the property, or false if hi fails in game
    */
    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
    /**
     * sets property parameters
     * @param name, the name of the player who buys something
     */
    public void buy(String name){
        this.owned=true;
        this.ownedBy=name;
    }
    /**
     * @return true if there is a house in the property
     * return false is there is no house
     */
    public Boolean getHoused() {
        return housed;
    }
    /**
     * 
     * @return the name of the player who ownes the property
     */
    public String getOwnedBy() {
        return ownedBy;
    }
    /**
     * 
     * @param ownedBy , the name of the player who owns the property
     */
    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    /**
     * you can set if there is a house in the property or not
     * @param housed 
     */
    public void setHoused(Boolean housed) {
        this.housed = housed;
    }
    
}
