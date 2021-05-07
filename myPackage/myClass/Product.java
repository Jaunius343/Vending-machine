package myPackage.myClass;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import myExceptions.*;

interface sellable
{
    BigDecimal sell(BigDecimal money) throws SellException;
}

interface sellableWithDiscount extends sellable
{
    void setDiscount(int discount) throws SellWithDiscountException;
    BigDecimal sellWithDiscount(BigDecimal money) throws SellWithDiscountException, SellException;
}

public abstract class Product
    implements sellableWithDiscount, Serializable
{
    protected String name = "name";
    protected BigDecimal price;
    protected Date expiration;
    protected int discount;

    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal x) {price = x;}

    public String getString() {return name;}
    public void setString(String temp) {name = temp;}

    public Date getExpiration() {return expiration;}
    public void setDate(Date date) {expiration = date;}

    public int getDiscount(){return discount;}

    public void println(){System.out.println(name + " " + price + "€ " + expiration);}

    public Product(){
        this("product", new BigDecimal(0), new Date());
    }

    public Product(String n, BigDecimal p, Date d){
        name = n;
        price = p;
        expiration = d;
        this.number = nextNumber();
    }

    public final boolean isValidDate(){return expiration.after(new Date());}
    public final boolean isValidDate(Date date){return expiration.after(date);}

    private static int lastNumber = 0;
    public static int getLastNumber() {return lastNumber;}
    private static int nextNumber() {return ++lastNumber;}

    private int number;
    public int getNumber(){return number;}

    public static final float convertRatio = 1.21F;


    public String toString(){
        return ("product name: " + name + "\n" + "product price: " + price + "€\n" + "expiration date: " + expiration);
    }

    public abstract float getSize();
    public abstract void setSize(float x);

    // private String type = "unknown";
    public abstract String getType();//{return type;}

    public abstract BigDecimal pricePerSize ();//{return 0;}

    public abstract boolean isHealthy();//{return false;}

    public BigDecimal sell(BigDecimal money) throws SellException {
        if(//money >= price
        money.compareTo(price) >= 0) {
            return money.subtract(price);
        } else {
            throw new SellException();
        }
    }

    public void setDiscount(int discount) throws SellWithDiscountException{
        if (discount <= 100){
            this.discount = discount;
        } else {
            throw new SellWithDiscountException(discount);
        }
    }

    public BigDecimal sellWithDiscount(BigDecimal money)
            throws SellWithDiscountException, SellException{

        if(discount == 0){
            throw new SellWithDiscountException(discount);
        }

        BigDecimal priceWithDiscount;
        priceWithDiscount = price.multiply(new BigDecimal(discount));
        priceWithDiscount = priceWithDiscount.divide(new BigDecimal(100));

        if (//money >= price
                money.compareTo(priceWithDiscount) >= 0) {
            return money.subtract(priceWithDiscount);
        } else {
            throw new SellException();
        }
    }

    public static void main(String[] args) {
        // write your code here
    }

}




//interface Type extends Healthy                      //example sellable
//{                                                   //extended sells with discount
//    String getType();                               //interface has to reflect an action
//    float getSize();
//    void setSize(float x);
//    float convertRatio = 1.21F;
//}

//interface Healthy
//{
//    boolean isHealthy();
//}
