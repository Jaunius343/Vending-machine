package myPackage.myClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Snack extends Product
implements  Cloneable {
    private float weight;     //in kilos
    private int calories;     //for 100 grams

    public Snack(){
        this("Snack", new BigDecimal(0), new Date(), 0, 0);
    }

    public Snack(String n, BigDecimal p, Date d, float w, int cal){
        super(n, p, d);
        weight = w;
        calories = cal;
    }

    public float getSize(){return weight;}
    public void setSize(float x) {weight = x;}

    public int getCal(){return calories;}
    public void setCal(int x) {calories = x;}

    private String type = "Snack";
    public String getType(){return type;}

    public String toString(){
        return (super.toString() + "\n" + "Product type: " + type + "\n" + "Calories per 100g: " + calories);
    }

    public BigDecimal pricePerSize (){
        return price.divide(new BigDecimal(weight), 2, RoundingMode.HALF_UP);
        //price / weight;
    }

    public float totalCalWorth(){
        return calories * weight * 10;
    }

    public boolean isHealthy(){
        if (totalCalWorth() >= (weight * 1000)){        // if ratio is 1:1 with weight or there are more calories, then unhealthy
            return false;
        } else {
            return true;
        }
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

