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

    public Boolean getOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
    public void buy(String name){
        this.owned=true;
        this.ownedBy=name;
    }
    
    public Boolean getHoused() {
        return housed;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public void setHoused(Boolean housed) {
        this.housed = housed;
    }
    
}
