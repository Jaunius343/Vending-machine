package myPackage.myClass;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import myExceptions.*;

/**
 * allows selling product
 */
interface sellable
{
    BigDecimal sell(BigDecimal money) throws SellException;
}

/**
 * allows selling product with discount
 */
interface sellableWithDiscount extends sellable
{
    void setDiscount(int discount) throws SellWithDiscountException;
    BigDecimal sellWithDiscount(BigDecimal money) throws SellWithDiscountException, SellException;
}

/**
 * Abstract class for extending new products
 */
public abstract class Product
    implements sellableWithDiscount, Serializable
{
    /** product name*/
    protected String name = "name";
    /** product price*/
    protected BigDecimal price;
    /** product expiration date*/
    protected Date expiration;
    /** product discount*/
    protected int discount;

    /**
     *  returns product price
     * @return BigDecimal price
     */
    public BigDecimal getPrice() {return price;}
    /**
     * sets the product price
     * @param x price to be set
     */
    public void setPrice(BigDecimal x) {price = x;}

    /**
     * returns product name
     * @return String name
     */
    public String getString() {return name;}
    /**
     * sets the product name
     * @param temp string to be set
     */
    public void setString(String temp) {name = temp;}

    /**
     * returns product expiration date
     * @return Date date
     */
    public Date getExpiration() {return expiration;}
    /**
     * set product expiration date
     * @param date date to be set
     */
    public void setDate(Date date) {expiration = date;}

    /**
     * returns discount percent
     * @return int discount
     */
    public int getDiscount(){return discount;}

    /**
     * prints out product fields to screen
     */
    public void println(){System.out.println(name + " " + price + "€ " + expiration);}

    /**
     * no argument constructor used in extended classes to set base class fields
     */
    public Product(){
        this("product", new BigDecimal(0), new Date());
    }


    /**
     * constructor used in extended classes to set base class fields
     * @param n product name
     * @param p product price
     * @param d product expiration date
     */
    public Product(String n, BigDecimal p, Date d){
        name = n;
        price = p;
        expiration = d;
        this.number = nextNumber();
    }

    /**
     * strictly checks if product expiration > today
     * @return true or false
     */
    public final boolean isValidDate(){return expiration.after(new Date());}
    /**
     * strictly checks if product expiration > given date
     * @param date date which we're checking
     * @return true or false
     */
    public final boolean isValidDate(Date date){return expiration.after(date);}

    /** previous product number*/
    private static int lastNumber = 0;
    /**
     * returns last product's number
     * @return int lastNumber
     */
    public static int getLastNumber() {return lastNumber;}
    /**
     * increases product number
     * @return increased lastNumber
     */
    private static int nextNumber() {return ++lastNumber;}

    /** product number*/
    private int number;
    /** returns current product number*/
    public int getNumber(){return number;}

    /** constant value of euro to dollar ratio*/
    public static final float convertRatio = 1.21F;

    /** prints object to screen*/
    public String toString(){
        return ("product name: " + name + "\n" + "product price: " + price + "€\n" + "expiration date: " + expiration);
    }

    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract float getSize();
    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract void setSize(float x);

    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract int getCal();
    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract void setCal(int x);

    // private String type = "unknown";
    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract String getType();//{return type;}

    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract BigDecimal pricePerSize ();//{return 0;}

    /**{@link myPackage.myClass.Drink} {@link myPackage.myClass.Snack}*/
    public abstract boolean isHealthy();//{return false;}

    /**
     * sells the product
     * @param money amount of money
     * @return change left from money
     * @throws SellException if not enough money
     */
    public BigDecimal sell(BigDecimal money) throws SellException {
        if(//money >= price
        money.compareTo(price) >= 0) {
            return money.subtract(price);
        } else {
            throw new SellException();
        }
    }

    /**
     * sets product discount
     * @param discount discount percentage
     * @throws SellWithDiscountException if discount is bigger than 100
     */
    public void setDiscount(int discount) throws SellWithDiscountException{
        if (discount <= 100){
            this.discount = discount;
        } else {
            throw new SellWithDiscountException(discount);
        }
    }

    /**
     * sells product with discount
     * @param money amount of money
     * @return change left from money
     * @throws SellWithDiscountException if discount wasn't change or set 0({@link Product#sell} should be used)
     * @throws SellException if not enough money
     */
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
