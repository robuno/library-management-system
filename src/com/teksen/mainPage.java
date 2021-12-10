package com.teksen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mainPage extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton manageDigital;
    private JButton manageReadingBooksButton;
    private JButton checkItemLlistButton;
    private JButton issueAnItemButton;
    private JButton manageStudentsButton;
    private JButton returnAnItemButton;
    private JPanel manageAllPanel;
    private JButton button1;
    private JPanel libraryUsersPanel;

    private ArrayList<ArrayList<String>> allItems;


    public mainPage() {
        add(mainPanel);
        setSize(800,400);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = getItemsArrayList();
        // String[][] readingBookArray = getItemArray(allItems);

        //createTable(readingBookArray);
        manageDigital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                DigitalScreen digiScreen = new DigitalScreen(allItems);
                digiScreen.setVisible(true);
            }
        });

        manageReadingBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReadingBookScreen rBScreen = new ReadingBookScreen(allItems);
                rBScreen.setVisible(true);

            }
        });
        checkItemLlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReadingBookScreen rdScreen2 = new ReadingBookScreen(allItems);
                DigitalScreen digiScreen2 = new DigitalScreen(allItems);

                ArrayList<Digital> digitalArrayList = digiScreen2.createDigitalClassArrayList(allItems);
                ArrayList<ReadingBook> readingBookArrayList = rdScreen2.createReadingBookClassArrayList(allItems);

                ItemsScreen itemsScreen = new ItemsScreen( digitalArrayList, readingBookArrayList);
                itemsScreen.setVisible(true);
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonScreen prsScreen = new PersonScreen();
                ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();
                StudentScreen stuScreen = new StudentScreen(personArrayList);
                stuScreen.setVisible(true);
            }
        });


        issueAnItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                PersonScreen prsScreen = new PersonScreen();
                ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();

                IssueAnItemScreen isuScreen = new IssueAnItemScreen(personArrayList,allItems);
                isuScreen.setVisible(true);


            }
        });
        returnAnItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReturnItemScreen retScreen = new ReturnItemScreen(allItems);
                retScreen.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public ArrayList<ArrayList<String>> getItemsArrayList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);
                ArrayList<String> aBook = new ArrayList<String>();

                for(int i=0; i < singleItem.length;i++) {
                    aBook.add(singleItem[i]);
                }
                items.add(aBook);
                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return items;
    }


    public int getLastItemID(String fileName) {
        int lastID = 0;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);


                lastID = Integer.parseInt(singleItem[0]);

                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lastID;
    }



}
