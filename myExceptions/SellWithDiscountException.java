package myExceptions;

import java.math.BigDecimal;

public class SellWithDiscountException extends SellException {
    private int BadDiscount;

    public SellWithDiscountException(){}
    //konstruktorius, konstravimo metu priskirti nuolaidą, su kuria buvo klaida.
    public SellWithDiscountException(int discount){
        BadDiscount = discount;
    }

    public int getBadDiscount() {
        return BadDiscount;
    }
}
