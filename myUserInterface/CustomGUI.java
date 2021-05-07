package myUserInterface;

import myExceptions.SellWithDiscountException;
import myPackage.myClass.Snack;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.*;

public class CustomGUI extends JFrame {
    public CustomGUI(){
        setSize(WIDTH, HEIGHT);
        setTitle("input / output");

        Container contentPane = getContentPane();
        JPanel buttonPanel = new JPanel();

        addButton(buttonPanel, "to file",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt){
                        InputThread thread = new InputThread(new Snack("product6", new BigDecimal(7.5), new Date(120, 5, 10), 0.3F, 75));
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
    Snack snack;
    public InputThread(Snack obj){snack = obj;}
    public void run()
    {
        try{
            ObjectOutputStream outputFile = null;

            try{
                outputFile = new ObjectOutputStream(new FileOutputStream("file.txt"));
                outputFile.writeObject(snack.getPrice());
//                sleep(1000);
                outputFile.writeObject(snack.getExpiration());
//                sleep(1000);
                outputFile.writeObject(snack.getString());
//                sleep(1000);
                outputFile.writeObject(snack.getDiscount());
//                sleep(1000);
                outputFile.writeObject(snack.getSize());
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
    Snack snack = new Snack();

    public void run(){
        try{
            System.out.println("\n" + "Before file read:");
            System.out.println(snack);

            ObjectInputStream inputFile = null;
            try{
                inputFile = new ObjectInputStream(new FileInputStream("file.txt"));
                snack.setPrice((BigDecimal) inputFile.readObject());
                snack.setDate((Date) inputFile.readObject());
                snack.setString((String) inputFile.readObject());
                snack.setDiscount((int) inputFile.readObject());
                snack.setSize((float) inputFile.readObject());


                System.out.println("\n" + "test object values:");
                System.out.println(snack);
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