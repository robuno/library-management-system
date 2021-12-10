package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ItemsScreen extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JTextField searchTitleTextField;
    private JButton buttonSearchTitle;

    private ArrayList<Digital> digitalArrayList;
    private ArrayList<ReadingBook> readingBookArrayList;
    private ArrayList<Item> itemArrayList;

    public ItemsScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public ItemsScreen(ArrayList<Digital> digitalArrayList,ArrayList<ReadingBook> readingBookArrayList) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.digitalArrayList = digitalArrayList;
        this.readingBookArrayList = readingBookArrayList;

        itemArrayList = createItemArrayList(digitalArrayList,readingBookArrayList);
        String[][] itemArray = getItemArray(itemArrayList);
        createTable(itemArray);

        String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status"};

        buttonSearchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,itemArray,labelOfTable,1,5);
                searchScreenDigital.setVisible(true);
            }
        });
    }

    private ArrayList<Item> createItemArrayList(ArrayList<Digital> digitalArrayList,
                                                ArrayList<ReadingBook> readingBookArrayList) {
        ArrayList<Item> itemArrayList = new ArrayList<>();

        itemArrayList.addAll(readingBookArrayList);
        itemArrayList.addAll(digitalArrayList);

        System.out.println("***");
        for(int i=0; i<itemArrayList.size();i++) {
            System.out.println(itemArrayList.get(i));
        }

        return itemArrayList;
    }

    public String[][] getItemArray(ArrayList<Item> itemArrayList) {
        String[][] itemArray = new String[itemArrayList.size()][5];
        for(int i=0; i < itemArrayList.size();i++) {
            itemArray[i][0] = String.valueOf(itemArrayList.get(i).getId());
            itemArray[i][1] = itemArrayList.get(i).getTitle();
            itemArray[i][2] = itemArrayList.get(i).getLocationInformation();
            itemArray[i][3] = itemArrayList.get(i).getItemType();
            itemArray[i][4] = itemArrayList.get(i).getStatus();
        }

        return itemArray;
    }

    public void createTable(String[][] itemArray) {
        table1.setModel(new DefaultTableModel(
                itemArray,
                new String[]{"ID","Title","Location","Item Type","Status"}
        ));
    }
}
