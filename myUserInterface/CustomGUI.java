package myUserInterface;

import myExceptions.SellWithDiscountException;
import myPackage.myClass.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * user interface window for product's input / output to file
 */
public class CustomGUI extends JFrame {
    public Product product2;

    /**
     * creates a window
     * @param productList product with which we're going to work
     */
    public CustomGUI(List<Product> productList){
        setSize(WIDTH, HEIGHT);
        setTitle("input / output");

        Container contentPane = getContentPane();
        JPanel buttonPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(contentPane.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        final JList<Product> list = new JList(productList.toArray());
        contentPane.add(list, BorderLayout.WEST);

        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                    }
                });

        addButton(buttonPanel, "to file",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt){
                        SwingUtilities.invokeLater(
                                new Runnable(){
                                    public void run(){
                                        int[] indices = list.getSelectedIndices();
                                        if(indices.length == 0){
                                            statusLabel.setText("no products selected");
                                        } else {
                                            OutputThread thread = new OutputThread(productList, indices);
                                            thread.start();
                                            statusLabel.setText("copied to file");
                                        }
                                    }
                                });
                    }
                });

        addButton(buttonPanel, "from file",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt){
                        SwingUtilities.invokeLater(
                                new Runnable(){
                                    public void run(){
                                        InputThread thread = new InputThread();
                                        thread.start();
                                        statusLabel.setText("copied from file");
                                    }
                                });
                    }
                });

        addButton(buttonPanel, "Close",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        System.exit(0);
                        SwingUtilities.invokeLater(
                                new Runnable(){
                                    public void run(){
                                        statusLabel.setText("exiting");
                                    }
                                });
                    }
                });

        contentPane.add(buttonPanel, BorderLayout.NORTH);
        statusPanel.add(statusLabel);
    }

    /** method for adding additional buttons*/
    public void addButton (Container c, String title,
                           ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }


    /** const value for window width*/
    public static final int WIDTH = 300;
    /** const value for window height*/
    public static final int HEIGHT = 500;
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

/**
 * thread for output to file
 */
class OutputThread extends Thread
{
    /** temp object for writing*/
    List<Product> productList;
    int[] selected;
    public  OutputThread(List<Product> list, int[] indices){productList = list; selected = indices;}

    public void run()
    {
        try{
            ObjectOutputStream outputFile = null;

            try{
                outputFile = new ObjectOutputStream(new FileOutputStream("file.bin"));
                outputFile.writeObject(selected.length);
                for(int i = 0; i < selected.length; ++i){
                    outputFile.writeObject(productList.get(i));
                    sleep(10);
                }

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

/**
 * thread for input to file
 */
class InputThread extends Thread
{
    List<Product> productList = new ArrayList<Product>();
    int size;
    public void run(){
        try{

            ObjectInputStream inputFile = null;
            try{
//                System.out.println("\n" + "Before file read:");
//                System.out.println(productList);

                inputFile = new ObjectInputStream(new FileInputStream("file.bin"));
                size = (int) inputFile.readObject();
                for (int i = 0; i < size; ++i){
                    productList.add((Product) inputFile.readObject());
                }

                System.out.println("\n" + "test object values:");
                System.out.println(productList);
            }
            catch (FileNotFoundException ex){
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