package myPackage.myClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;

/**
 * Fully functional base class of {@link myPackage.myClass.Product}
 */
public class Drink extends Product
implements Cloneable{
    /** product's volume*/
    private float volume;
    /** product's fizziness*/
    private int fizziness;
    /** product's age restriction*/
    private int ageRestriction = 0;

    /**
     * No arguments constructor
     */
    public Drink(){
        this("Drink", new BigDecimal(0), new Date(), 0, 0);
    }

    /**
     * constructor with values
     * @param n product name
     * @param p product price
     * @param d product expiration date
     * @param v product volume
     * @param f product fiziness
     */
    public Drink(String n, BigDecimal p, Date d, float v, int f){
        super(n, p, d);
        volume = v;
        fizziness = f;
//        addSizes(v);
    }

    /** returns product's age restriction*/
    public int getAgeRestriction(){return ageRestriction;}
    /** sets product's age restriction*/
    public void setAgeRestriction(int age){ageRestriction = age;}

    /** returns product's volume*/
    public float getSize(){return volume;}
    /** sets product's volume*/
    public void setSize(float x) {volume = x;}

    /** returns product's fizziness*/
    public int getCal(){return fizziness;}
    /** sets product's fizziness*/
    public void setCal(int x) {fizziness = x;}

    /** unchangable product type*/
    private String type = "Drink";
    /** return product type*/
    public String getType(){return type;}

    /** prints object to screen*/
    public String toString(){
        return (super.toString() + "\n" + "Product type: " + type + "\n" + "fizziness: " + fizziness + "%");
    }

    /**
     * returns product's cost for 1 litre
     * @return BigDecimal cost
     */
    public BigDecimal pricePerSize (){
        return price.divide(new BigDecimal(volume), 2, RoundingMode.HALF_UP);
        // price / volume;
    }

    /**
     * checks if it's legal to sell the product to the person based on their age
     * @param age age to be checked
     * @return true or false
     */
    public boolean isLegal(int age){
        if (ageRestriction == 0 || age >= ageRestriction){
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if products fizziness is lower than 50%
     * @return true or false
     */
    public boolean isHealthy(){
        if(fizziness < 50){
            return true;
        } else {
            return false;
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

//    public float howBig(){return 1 / volume;}       //neprasminga

//    private static ArrayList<Float>sizes = new ArrayList<>();
//    private static int count = 0;                                            //unnecessary to have and additional list withing class
//    public static int getSizeCount(){return count;}                          //way easier to just iterate object list and check their volumes
//
//    private void addSizes(float v){
//        sizes.add(volume);
//        ++count;
//    }
//
//    public static ArrayList<Float> availableSizes(){
//        return sizes;
//    }

}
