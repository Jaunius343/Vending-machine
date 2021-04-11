package myExceptions;

public class sellWithDiscountException extends sellException {

    public final static int maxDiscount = 100;

    public sellWithDiscountException(){}

    public sellWithDiscountException(String str){
        super(str);
    }
}
