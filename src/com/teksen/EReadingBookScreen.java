package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EReadingBookScreen extends JFrame{
    private JTextField titleTextField;
    private JTextField eReadingBookTextField;
    private JTextField authorTextField;
    private JTextField locationTextField;
    private JTextField onlineTextField;
    private JTextField publisherTextField;
    private JTextField languageTextField;
    private JTextField yearTextField;
    private JTextField searchTitleTextField;
    private JTextField searchAuthorTextField;
    private JButton titleSearchButton;
    private JButton authorSearchButton;
    private JButton addNewEReadingButton;
    private JButton deleteSelectedEReadingButton;
    private JButton createStickerForEButton;
    private JTable table1;
    private JTextField searchGenreTextField;
    private JButton genreSearchButton;
    private JTextField editionTextField;
    private JTextField pageNumberTextField;
    private JTextField ISBNTextField;
    private JTextField URLTextField;
    private JTextField genreTextField;
    private JPanel mainPanel;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition","Page Number","ISBN", "URL","Genre"};

    public EReadingBookScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E Reading Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public EReadingBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E-Reading Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<EReadingBook> EReadingBookArrayList = createEReadingBookClassArrayList(allItems);
        String[][] EReadingBookArray = getEReadingBookArray(EReadingBookArrayList);
        mainPage.createTable(table1,EReadingBookArray,labelOfTable);

        addNewEReadingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEReadingBook = new mainPage();
                int lastID = itScreenForEReadingBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddEReadingBookButton(modelTable, lastID);
            }
        });
        deleteSelectedEReadingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEReadingBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEReadingBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteEReadingBookButton(modelTable, allItems);
            }
        });
        createStickerForEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEReadingBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEReadingBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);
            }
        });
        titleSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenEReadingBook= new SearchScreen(searchedTitle,EReadingBookArray,labelOfTable,1,14);
                searchScreenEReadingBook.setVisible(true);
            }
        });
        authorSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenEReadingBook = new SearchScreen(searchedAuthor,EReadingBookArray,labelOfTable,5,14);
                searchScreenEReadingBook.setVisible(true);

            }
        });
        genreSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedGenre = searchGenreTextField.getText();
                SearchScreen searchScreenEReadingBook = new SearchScreen(searchedGenre,EReadingBookArray,labelOfTable,13,14);
                searchScreenEReadingBook.setVisible(true);

            }
        });

    }
    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        int selectedRowNo = table1.getSelectedRow();
        //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
        int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

        String stickerText ="";
        for(int i=0; i <allItems.size(); i++) {
            if ( Integer.parseInt(allItems.get(i).get(0)) == selectedRowID) {
                System.out.println(allItems.get(i).get(1)+"-"+selectedRowID);
                stickerText = String.format(
                        "%-26s%-35s\n%-25s%-35s\n%-21s%-35s\n%-23s%-35s\n%-23s%-35s\n"+
                                "%-23s%-35s\n%-21s%-35s\n%-19s%-35s\n%-24s%-35s\n%-23s%-35s\n"+
                                "%-16s%-35s\n%-23s%-35s\n%-23s%-35s\n%-23s%-35s"
                        ,
                        "ID:",allItems.get(i).get(0),
                        "Title:",allItems.get(i).get(1),
                        "Location:",allItems.get(i).get(2),
                        "Type:",allItems.get(i).get(3),
                        "Status:",allItems.get(i).get(4),

                        "Author:",allItems.get(i).get(5),
                        "Publisher:",allItems.get(i).get(6),
                        "Language:",allItems.get(i).get(7),
                        "Year:",allItems.get(i).get(8),
                        "Edition:",allItems.get(i).get(9),
                        "Page Number:",allItems.get(i).get(10),
                        "ISBN:",allItems.get(i).get(11),
                        "URL",allItems.get(i).get(12),
                        "Genre",allItems.get(i).get(13)
                        );
            }
        }
        //System.out.println(stickerText);
        return stickerText;

    }
    public void pressDeleteEReadingBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        System.out.println(itemsArrayList);
        System.out.println("********");

        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(i).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) {
                        System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }

            System.out.println("rowwillbedeleted"+rowWillBeDeleted);
            itemsArrayList.remove(rowWillBeDeleted);

            String newEReadingBookText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newEReadingBookText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newEReadingBookText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newEReadingBookText += "\n";
                }
            }

            System.out.println("newbooktext: "+newEReadingBookText);

            mainPage.writeToTxt(newEReadingBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }
    public void pressAddEReadingBookButton(DefaultTableModel modelTable,  int lastID){

        if( !(mainPage.isNumeric(editionTextField.getText(),mainPanel))
                || !(mainPage.isNumeric(yearTextField.getText(),mainPanel))
                || !(mainPage.isNumeric(pageNumberTextField.getText(),mainPanel)) ) {
            return;
        }

        String newEReadingBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                eReadingBookTextField.getText()+","+onlineTextField.getText()+","+
                authorTextField.getText() +","+ publisherTextField.getText()+","+
                languageTextField.getText()+","+ yearTextField.getText()+","+
                editionTextField.getText()+","+pageNumberTextField.getText()+","+
                ISBNTextField.getText()+","+URLTextField.getText()+","+
                genreTextField.getText();

        mainPage.writeToTxt(newEReadingBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                eReadingBookTextField.getText(),
                onlineTextField.getText(),
                authorTextField.getText(),
                publisherTextField.getText(),
                languageTextField.getText(),
                yearTextField.getText(),
                editionTextField.getText(),
                pageNumberTextField.getText(),
                ISBNTextField.getText(),
                URLTextField.getText(),
                genreTextField.getText()
        });

    }
    public ArrayList<EReadingBook> createEReadingBookClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<EReadingBook> EReadingBookClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("EReadingBook")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                EReadingBook aNewEReadingBook = new EReadingBook(
                        Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                        itemsArrayList.get(i).get(3), // String itemType
                        itemsArrayList.get(i).get(1), // String title
                        itemsArrayList.get(i).get(2), // String locationInformation
                        itemsArrayList.get(i).get(4), // String status
                        itemsArrayList.get(i).get(5),
                        itemsArrayList.get(i).get(6),
                        itemsArrayList.get(i).get(7),
                        Integer.parseInt(itemsArrayList.get(i).get(8)),
                        Integer.parseInt(itemsArrayList.get(i).get(9)),
                        Integer.parseInt(itemsArrayList.get(i).get(10)),
                        itemsArrayList.get(i).get(11),
                        itemsArrayList.get(i).get(12),
                        itemsArrayList.get(i).get(13)

                );
                EReadingBookClassArray.add(aNewEReadingBook);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return EReadingBookClassArray;
    }
    public String[][] getEReadingBookArray(ArrayList<EReadingBook> EReadingBookArrayList) {
        String[][] EReadingBookArray = new String[EReadingBookArrayList.size()][14];
        for(int i=0; i < EReadingBookArrayList.size();i++) {
            EReadingBookArray[i][0] = String.valueOf(EReadingBookArrayList.get(i).getId());
            EReadingBookArray[i][1] = EReadingBookArrayList.get(i).getTitle();
            EReadingBookArray[i][2] = EReadingBookArrayList.get(i).getLocationInformation();
            EReadingBookArray[i][3] = EReadingBookArrayList.get(i).getItemType();
            EReadingBookArray[i][4] = EReadingBookArrayList.get(i).getStatus();
            EReadingBookArray[i][5] = EReadingBookArrayList.get(i).getAuthor();
            EReadingBookArray[i][6] = EReadingBookArrayList.get(i).getPublisher();
            EReadingBookArray[i][7] = EReadingBookArrayList.get(i).getLanguage();
            EReadingBookArray[i][8] = String.valueOf(EReadingBookArrayList.get(i).getYear());
            EReadingBookArray[i][9] = String.valueOf(EReadingBookArrayList.get(i).getEdition());
            EReadingBookArray[i][10] = String.valueOf(EReadingBookArrayList.get(i).getPageNumber());
            EReadingBookArray[i][11] = EReadingBookArrayList.get(i).getISBN();
            EReadingBookArray[i][12] = EReadingBookArrayList.get(i).getUrl();
            EReadingBookArray[i][13] = EReadingBookArrayList.get(i).getGenre();
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */


        return EReadingBookArray;
    }

}
