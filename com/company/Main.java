package com.company;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import myPackage.myClass.*;
import myExceptions.*;
import myUserInterface.*;
import javax.swing.*;

/**
 * main for testing the features
 */
public class Main{
    public static void main(String[] args) throws CloneNotSupportedException{
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
        Snack p5;
//        System.out.print("\n" + p5.pricePerSize() + "\n");
        Snack p6 = new Snack("product6", new BigDecimal(7.5), new Date(120, 5, 10), 0.3F, 75);
        p5 = p6;
//        System.out.println(p6.isHealthy());
//        System.out.println(p5.isHealthy());
        System.out.println("\n" + p6);
        System.out.println(p6.pricePerSize());
//        System.out.println(p6.totalCalWorth());

        try {
            System.out.println(p6.sell(new BigDecimal(10)));
        }
        catch (SellException exOb){
            exOb.printStackTrace();
//            System.out.println(exOb.getMessage());
        }

        try {
            p6.setDiscount(50);
        }
        catch(SellWithDiscountException exOb){
            exOb.printStackTrace();
            System.out.print("Bad discount: " + exOb.getBadDiscount() + "\n");
        }

        try {
            System.out.println(p6.sellWithDiscount(new BigDecimal(5)));
        }
        catch(SellWithDiscountException exOb){
            exOb.printStackTrace();
        }
        catch (SellException exOb){
            exOb.printStackTrace();
        }

        try {

            p6.testObject.R = 1;
            p6.testObject.G = 2;
            p6.testObject.B = 3;

            p5 = (Snack) p6.clone();
            ((Snack)p5).setCal(50);
            p5.setString("this is a test");
            p5.setPrice(new BigDecimal(20));
            p5.setDate(new Date(140, 7, 15));

            System.out.println("\n" + "deep clone test" + "\n");

            p5.testObject.R = 10;
            p5.testObject.G = 20;
            p5.testObject.B = 30;

            System.out.println(p6.testObject.R);
            System.out.println(p6.testObject.G);
            System.out.println(p6.testObject.B);

            System.out.println(p5.testObject.R);
            System.out.println(p5.testObject.G);
            System.out.println(p5.testObject.B);

            System.out.println(p6);
            System.out.println("\n" + p5);
        }
        catch (CloneNotSupportedException exc)
        {
            System.out.println("Cloning exception:" + exc);
        }

        System.out.println(p5.pricePerSize());
//        System.out.println(p6.isHealthy());
//        System.out.println(p5.isHealthy());
//        p5.println();

        Drink p7 = new Drink();
        System.out.println(p7.isLegal(17));

        System.out.println(p7.getNumber());

        ProductFactory productFactory = new ProductFactory();
        Product p8 = productFactory.createProduct("Snack");
        System.out.println(p8);

        Product p9 = productFactory.createProduct("Drink");
        System.out.println(p9);
        System.out.println("size: " + p9.getSize());

        Product p10 = productFactory.createProduct("Snack", "product10", new BigDecimal(125), new Date(140, 7, 11), 0.5F, 180);
        System.out.println("\n" + p10);
//        System.out.println( ((Snack) p10).isHealthy());

//        System.out.println(p6.getPrice().toString());
        


        List<Product> productList = new ArrayList<Product>();
        Set<Product> productSet = new HashSet<Product>();
        productList.add(p6);
        productList.add(p10);
        productList.add(p8);
        productList.add(p9);
        System.out.println(productList.get(0));
//        for (int i = 0; i < 10; ++i){
//            productList.add(p6);
//            productSet.add(p6);
//        }
//        System.out.println(productList);
//        System.out.println(productList.contains(p6));
//        System.out.println("productList size: " + productList.size());
//        System.out.println("productSet size: " + productSet.size());

        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        JFrame frame = new CustomGUI(productList);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.show();
                    }
                });


//            ObjectOutputStream outputFile = null;
//
//            try{
//                outputFile = new ObjectOutputStream(new FileOutputStream("file3.txt"));
//                outputFile.writeObject(p6);
//            }
//            catch (FileNotFoundException ex){
//                ex.printStackTrace();
//            }
//            catch (IOException ex){
//                ex.printStackTrace();
//            }
//            finally {
//                try {
//                    outputFile.close();
//                }
//                catch(IOException ex){
//                    ex.printStackTrace();
//                }
//            }



//        System.out.println("Test");
//        System.out.println(((CustomGUI) frame).product2);
//        System.out.println("Test");
//        try{
//            ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream("file2.txt"));
//            outputFile.writeObject(70);
//            outputFile.close();
//
//            outputFile.
//            outputFile.writeObject("a");
//            outputFile.close();
//        }
//        catch (FileNotFoundException ex){
//            ex.printStackTrace();
//        }
//        catch (IOException ex){
//            ex.printStackTrace();
//        }

//        BigDecimal a = new BigDecimal(7);
//        BigDecimal b = new BigDecimal(6);
//        System.out.println(a.compareTo(b));
//        BigDecimal money = new BigDecimal(1.33);
//        System.out.println(money);

//        ArrayList<Float> temp_array = Drink.availableSizes();
//        System.out.println(temp_array);
//        System.out.println(Drink.getSizeCount());

    }
}
