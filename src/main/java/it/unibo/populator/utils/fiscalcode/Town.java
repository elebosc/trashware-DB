/*
 * SOURCE: https://github.com/martapanc/ItalianFiscalCodeCalculator
 */

package it.unibo.populator.utils.fiscalcode;

public class Town {

    private String townName, townCode;

    public Town(String townName, String townCode) {
        this.townName = townName;
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public String getTownCode() {
        return townCode;
    }
    
    public String toString() {
        return getTownName() + " " + getTownCode();
    }
}
