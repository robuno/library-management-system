package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class SearchScreen extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JTextArea searchInfoTextArea;


    public SearchScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Search Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public SearchScreen(String searchedTitle,String[][] digitalArray, String[] labelOfTable,int indexOfSearched, int lengthOfArray) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Search Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[][] searchedDigitalItemsArray = getSearchedDigitalItems(searchedTitle, digitalArray,indexOfSearched, lengthOfArray);

        mainPage.createTable(table1,searchedDigitalItemsArray,labelOfTable);
    }

    public String[][] getSearchedDigitalItems(String searchedTitle,String[][] digitalArray, int indexOfSearched, int lengthOfArray) {
        String[][] searchedDigitalItemsArray = new String[digitalArray.length][lengthOfArray];

        int arrayIndex = 0;
        for(int i=0; i< digitalArray.length;i++) {
            if(digitalArray[i][indexOfSearched].toLowerCase().equals(searchedTitle.toLowerCase())) {
                for(int j=0; j<lengthOfArray;j++) {
                    searchedDigitalItemsArray[arrayIndex][j] = digitalArray[i][j];
                }
                arrayIndex+=1;
            }
        }

        /*
        for(int i=0; i< searchedDigitalItemsArray.length;i++) {
            System.out.print(i+": ");
            for(int j=0; j<13;j++) {
                System.out.print(searchedDigitalItemsArray[i][j]+",");
            }
            System.out.println();
        }

         */

        String[][] newSearchedDigitalItemsArray = new String[arrayIndex][lengthOfArray];

        for(int i=0; i< newSearchedDigitalItemsArray.length;i++) {
            for(int j=0; j<lengthOfArray;j++) {
                newSearchedDigitalItemsArray[i][j] = searchedDigitalItemsArray[i][j];
            }
            System.out.println();
        }

        /*
        for(int i=0; i< newSearchedDigitalItemsArray.length;i++) {
            System.out.print(i+": ");
            for(int j=0; j<13;j++) {
                System.out.print(newSearchedDigitalItemsArray[i][j]+",");
            }
            System.out.println();
        }
         */

        if( newSearchedDigitalItemsArray.length !=0) {
            searchInfoTextArea.setText("Searched items are found!"+"\n"+
                    "Number of items found: "+newSearchedDigitalItemsArray.length);
        } else {
            searchInfoTextArea.setText("Searched item is not found in the library!");
        }

        return newSearchedDigitalItemsArray;
    }



}
