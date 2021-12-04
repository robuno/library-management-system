package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemsScreen extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public ItemsScreen() {
        add(mainPanel);
        setSize(800,400);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createTable() {

        ArrayList<ArrayList<String>> books = new ArrayList<>();

        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleBook = data.split(",",0);

                ArrayList<String> aBook = new ArrayList<String>();
                books.add(aBook);

                aBook.add(singleBook[0]);
                aBook.add(singleBook[1]);
                aBook.add(singleBook[2]);
                aBook.add(singleBook[3]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        String[][] booksArray = new String[books.size()][books.get(1).size()];
        for(int i =0; i < booksArray.length; i++) {
            for(int j=0; j < booksArray[3].length; j++) {
                booksArray[i][j] = books.get(i).get(j);
            }
        }



        /*
        Object[][] data = {
                {"1","The Dark Night","aaa","5"},
                {"2","The k Night","abha","2"},
                {"3","The u Nightttt","abvdsa","65"},
                {"5","The i Nightt","aqwera","54"},
                {"1","The Dark Night","aaa","5"},
                {"2","The k Night","abha","2"},
                {"3","The u Nightttt","abvdsa","65"},
                {"5","The i Nightt","aqwera","54"},
                {"1","The Dark Night","aaa","5"},
                {"2","The k Night","abha","2"},
                {"3","The u Nightttt","abvdsa","65"},
                {"5","The i Nightt","aqwera","54"},
                {"1","The Dark Night","aaa","5"},
                {"2","The k Night","abha","2"},
                {"3","The u Nightttt","abvdsa","65"},
                {"5","The i Nightt","aqwera","54"},
                {"1","The Dark Night","aaa","5"},
                {"2","The k Night","abha","2"},
                {"3","The u Nightttt","abvdsa","65"},
                {"5","The i Nightt","aqwera","54"},
        };
        */


        table1.setModel(new DefaultTableModel(
                booksArray, new String[]{"ID","Title","Location","Item Type"}
        ));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
