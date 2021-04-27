package myPackage.myClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;

public class Drink extends Product
implements Cloneable{
    private float volume;
    private float fizziness;
    private int ageRestriction = 0;

    public Drink(){
        this("Drink", new BigDecimal(0), new Date(), 0, 0);
    }

    public Drink(String n, BigDecimal p, Date d, float v, int f){
        super(n, p, d);
        volume = v;
        fizziness = f;
//        addSizes(v);
    }

    public int getAgeRestriction(){return ageRestriction;}
    public void setAgeRestriction(int age){ageRestriction = age;}

    public float getSize(){return volume;}
    public void setSize(float x) {volume = x;}

    private String type = "Drink";
    public String getType(){return type;}

    public String toString(){
        return (super.toString() + "\n" + "Product type: " + type + "\n" + "fizziness: " + fizziness + "%");
    }

    public BigDecimal pricePerSize (){
        return price.divide(new BigDecimal(volume), 2, RoundingMode.HALF_UP);
        // price / volume;
    }

    public boolean isLegal(int age){
        if (ageRestriction == 0 || age >= ageRestriction){
            return true;
        } else {
            return false;
        }
    }

    public boolean isHealthy(){
        if(fizziness < 50){
            return true;
        } else {
            return false;
        }
    }

    public color testObject = new color();

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
