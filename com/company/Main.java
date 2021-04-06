package com.company;
import java.util.ArrayList;
import java.util.Date;
import myPackage.myClass.*;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        //(new Product()).println();
//        Product p1 = new Product();
////        p1.getLastNumber();
////        Product.getLastNumber();
//        p1.println();
//        System.out.println(p1);
//        p1.setPrice(10);
//        p1.setString("product name");
//        p1.println();
//        System.out.println(p1.getNumber());
//        Product p2 = new Product("product2", 5, new Date(125, 10, 15));
//        System.out.println(p2.getNumber());
//        System.out.println(p2.getExpiration());
//        if(p2.isValidDate()){
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
//        Product p3 = new Product("product3", 6, new Date(120, 5, 10));
//        System.out.println(p3.getExpiration());
//        if(p3.isValidDate(new Date(115))){
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
//        System.out.println(p3.getNumber());
//        System.out.println(p1.getPrice() * Product.convertRatio + "$");
//
        Product p4 = new Snack();
        System.out.println(p4);
//        p4.println();
//
        Product p5;
//        System.out.print("\n" + p5.pricePerSize() + "\n");
        Snack p6 = new Snack("product6", 7.5F, new Date(120, 5, 10), 0.3F, 75);
        System.out.println("\n" + p6);
        System.out.println(p6.pricePerSize());
//        System.out.println(p6.totalCalWorth());

        p6.setDiscount(50);
        System.out.println(p6.sell(10));
        System.out.println(p6.sellWithDiscount(10));

        p5 = p6;
        System.out.println(p5.pricePerSize());
        System.out.println(p6.isHealthy());
        System.out.println(p5.isHealthy());
        p5.println();

        Drink p7 = new Drink();
        System.out.println(p7.isLegal(17));

        System.out.println(p7.getNumber());

//        ArrayList<Float> temp_array = Drink.availableSizes();
//        System.out.println(temp_array);
//        System.out.println(Drink.getSizeCount());
    }
}