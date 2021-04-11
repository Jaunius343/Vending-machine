package myPackage.myClass;

import java.util.Date;
import myExceptions.*;

public abstract class Product
    implements sellableWithDiscount
{
    protected String name = "name";
    protected float price;
    protected Date expiration;
    protected int discount;

    public float getPrice() {return price;}
    public void setPrice(float x) {price = x;}

    public String getString() {return name;}
    public void setString(String temp) {name = temp;}

    public Date getExpiration() {return expiration;}
    public void setDate(Date date) {expiration = date;}

    public void println(){System.out.println(name + " " + price + "€ " + expiration);}

    public Product(){
        this("product", 0, new Date());
    }

    public Product(String n, float p, Date d){
        name = n;
        price = p;
        expiration = d;
        this.number = nextNumber();
    }

    //su datos galiojimu. be argumentu - tikrina su dabar; jei duota data - su ta data;

    public final boolean isValidDate(){return expiration.after(new Date());}
    public final boolean isValidDate(Date date){return expiration.after(date);}

//    int priceCheck (int a) {if(price == a) return 0;
//                            else return 1;}
//    int priceCheck (float b) {return 1;}


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

    public abstract float pricePerSize ();//{return 0;}

    public abstract boolean isHealthy();//{return false;}

    public float sell(float money)
            throws sellException {
        if(money >= price) {
            return money - price;
        } else {
            throw new sellException("Not enough money");
            //return money - price;
        }
    }

    public void setDiscount(int discount) throws sellWithDiscountException{
        if (discount <= sellWithDiscountException.maxDiscount){
            this.discount = discount;
        } else {
            throw new sellWithDiscountException("Discount is too big");
        }
    };
    public float sellWithDiscount(float money) {
        return money - price * discount / 100;
    }

    public static void main(String[] args) {
        // write your code here
    }

}

interface sellable
{
    float sell(float x) throws sellException;
}

interface sellableWithDiscount extends sellable
{
    void setDiscount(int x) throws sellWithDiscountException;
    float sellWithDiscount(float x);
}

//sell exception (not enough money)
//sell with discount exception (discount is bigger than 100%)

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
