package myPackage.myClass;

import java.util.Date;
import java.util.ArrayList;

public class Drink extends Product {
    private float volume;
    private float fizziness;

    public Drink(){
        this("Drink", 0, new Date(), 0, 0, 0);
    }

    public Drink(String n, float p, Date d, float v, int f, int age){
        super(n, p, d);
        volume = v;
        fizziness = f;
        ageRestriction = age;
//        addSizes(v);
    }

    public float getSize(){return volume;}
    public void setSize(float x) {volume = x;}

    private String type = "Drink";
    public String getType(){return type;}

    public String toString(){
        return (super.toString() + "\n" + "Product type: " + type + "\n" + "fizziness: " + fizziness + "%");
    }

    public float pricePerSize (){
        return price / volume;
    }

    private int ageRestriction;
    public boolean isLegal(int age){
        if (ageRestriction == 0 || age >= ageRestriction){
            return true;
        } else {
            return false;
        }
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

    public boolean isHealthy(){
        if(fizziness < 50){
            return true;
        } else {
            return false;
        }
    }
}
