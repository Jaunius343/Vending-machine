package myExceptions;

import java.math.BigDecimal;

/**
 * exception for selling with discount
 */
public class SellWithDiscountException extends SellException {
    /**
     * field for saving bad discount
     */
    private int badDiscount;

    /**
     * no arguments constructor
     */
    public SellWithDiscountException(){}
    //konstruktorius, konstravimo metu priskirti nuolaidą, su kuria buvo klaida.

    /**
     * creating exception and saving the value which threw an exception
     * @param discount bad discount
     */
    public SellWithDiscountException(int discount){
        badDiscount = discount;
    }

    /**
     * returns bad discount value
     * @return int BadDiscount
     */
    public int getBadDiscount() {
        return badDiscount;
    }
}
