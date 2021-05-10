package myUserInterface;

import myExceptions.SellWithDiscountException;
import myPackage.myClass.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.*;

public class CustomGUI extends JFrame {
    public Product product2;
    public CustomGUI(Product obj){
        setSize(WIDTH, HEIGHT);
        setTitle("input / output");

        Container contentPane = getContentPane();
        JPanel buttonPanel = new JPanel();

        addButton(buttonPanel, "to file",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt){
                        InputThread thread = new InputThread(obj);
                        thread.start();
                    }
                });
        addButton(buttonPanel, "from file",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt){
                        OutputThread thread = new OutputThread();
                        thread.start();
                    }
                });
        addButton(buttonPanel, "Close",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        System.exit(0);
                    }
                });
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addButton (Container c, String title,
                           ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }


    public static final int WIDTH = 400;
    public static final int HEIGHT = 150;
}
//class IOthread
//{
//    public static void main(String[] args)
//    {
//        JFrame frame = new CustomGUI();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.show();
//    }
//}

class InputThread extends Thread
{
    Product product;
    public InputThread(Product obj){product = obj;}

    public void run()
    {
        try{
            ObjectOutputStream outputFile = null;

            try{
                outputFile = new ObjectOutputStream(new FileOutputStream("file.txt"));
                outputFile.writeObject(product.getType());
                outputFile.writeObject(product.getPrice());
//                sleep(1000);
                outputFile.writeObject(product.getExpiration());
//                sleep(1000);
                outputFile.writeObject(product.getString());
//                sleep(1000);
                outputFile.writeObject(product.getDiscount());
//                sleep(1000);
                outputFile.writeObject(product.getSize());
                outputFile.writeObject(product.getCal());
                sleep(10);
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            finally {
                try {
                    outputFile.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        catch(InterruptedException ex){
            ;
        }
    }
}

class OutputThread extends Thread
{
    Product product;

    public void run(){
        try{

            ObjectInputStream inputFile = null;
            try{
                String temp;
                inputFile = new ObjectInputStream(new FileInputStream("file.txt"));
                temp = (String) inputFile.readObject();
//                System.out.println("this is a test: " + temp);

                ProductFactory productFactory = new ProductFactory();
                product = productFactory.createProduct(temp);

                System.out.println("\n" + "Before file read:");
                System.out.println(product);

                product.setPrice((BigDecimal) inputFile.readObject());
                product.setDate((Date) inputFile.readObject());
                product.setString((String) inputFile.readObject());
                product.setDiscount((int) inputFile.readObject());
                product.setSize((float) inputFile.readObject());
                product.setCal((int) inputFile.readObject());

                System.out.println("\n" + "test object values:");
                System.out.println(product);
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
            catch(SellWithDiscountException ex) {
                ex.printStackTrace();
            }
            catch(ClassNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            finally {
                try {
                    inputFile.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            sleep(10);
        }
        catch (InterruptedException ex){
            ;
        }
    }
}