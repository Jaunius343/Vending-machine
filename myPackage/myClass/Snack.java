package myPackage.myClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Fully functional base class of {@link myPackage.myClass.Product}
 */
public class Snack extends Product
implements  Cloneable {
    /** products weight*/
    private float weight;     //in kilos
    /** products calories in 100 grams*/
    private int calories;     //for 100 grams

    /**
     * No arguments constructor
     */
    public Snack(){
        this("Snack", new BigDecimal(0), new Date(), 0, 0);
    }

    /**
     * constructor with values
     * @param n product name
     * @param p product price
     * @param d product expiration date
     * @param w product weight
     * @param cal product calories in 100 grams
     */
    public Snack(String n, BigDecimal p, Date d, float w, int cal){
        super(n, p, d);
        weight = w;
        calories = cal;
    }

    /**
     * returns products weight
     * @return float weight
     */
    public float getSize(){return weight;}
    /**
     * sets products weight
     * @param x weight to be set
     */
    public void setSize(float x) {weight = x;}

    /**
     * returns product's calories for 100 grams
     * @return int calories
     */
    public int getCal(){return calories;}
    /**
     * sets product's calories
     * @param x calories to be set
     */
    public void setCal(int x) {calories = x;}

    /** unchangable product type*/
    private String type = "Snack";
    /** return product type*/
    public String getType(){return type;}

    /** prints object to screen*/
    public String toString(){
        return (super.toString() + "\n" + "Product type: " + type + "\n" + "Calories per 100g: " + calories);
    }

    /**
     * returns product's cost for 1 kilogram
     * @return BigDecimal cost
     */
    public BigDecimal pricePerSize (){
        return price.divide(new BigDecimal(weight), 2, RoundingMode.HALF_UP);
        //price / weight;
    }

    /**
     * calculates total calorie worth of the product
     * @return float total calories
     */
    public float totalCalWorth(){
        return calories * weight * 10;
    }

    /**
     * checks whether the product has more calories than it's own weight
     * @return true or false
     */
    public boolean isHealthy(){
        if (totalCalWorth() >= (weight * 1000)){        // if ratio is 1:1 with weight or there are more calories, then unhealthy
            return false;
        } else {
            return true;
        }
    }

    /** test object for deep cloning*/
    public color testObject = new color();

    /**
     * creates a deep clone of the object
     * @return deep clone
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException{
        Snack temp = (Snack)super.clone();

        temp.testObject = new color();
        temp.testObject.R = testObject.R;
        temp.testObject.G = testObject.G;
        temp.testObject.B = testObject.B;

        return temp;
    }
}

