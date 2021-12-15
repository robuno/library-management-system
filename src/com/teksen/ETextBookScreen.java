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

public class ETextBookScreen extends JFrame{
    private JTextField searchTitleTextField;
    private JTextField searchAuthorTextField;
    private JTextField searchFieldTextField;
    private JButton titleSearchButton;
    private JButton AuthorSearchButton;
    private JButton FieldSearchButton;
    private JTextField titleTextField;
    private JTextField languageTextField;
    private JTextField editionTextField;
    private JTextField locationTextField;
    private JTextField onlineTextField;
    private JTextField yearTextField;
    private JTextField pageNumberTextField;
    private JTable table1;
    private JTextField publisherTextField;
    private JTextField AuthorTextField;
    private JTextField ISBNTextField;
    private JTextField URLTextField;
    private JTextField fieldTextField;
    private JButton addNewETextButton;
    private JButton deleteSelectedETextButton;
    private JButton createStickerForEButton;
    private JPanel mainPanel;
    private JTextField ETextBookTextField;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition","Page Number","ISBN", "URL","Field"};

    public ETextBookScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E Text Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public ETextBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E-Text Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<ETextBook> ETextBookArrayList = createETextBookClassArrayList(allItems);
        String[][] ETextBookArray = getETextBookArray(ETextBookArrayList);
        mainPage.createTable(table1,ETextBookArray,labelOfTable);

        addNewETextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForETextBook = new mainPage();
                int lastID = itScreenForETextBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddETextBookButton(modelTable, lastID);

            }
        });
        deleteSelectedETextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForETextBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForETextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteETextBookButton(modelTable, allItems);

            }
        });
        createStickerForEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForETextBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForETextBook.getItemsArrayList();

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
                SearchScreen searchScreenETextBook= new SearchScreen(searchedTitle,ETextBookArray,labelOfTable,1,14);
                searchScreenETextBook.setVisible(true);

            }
        });
        AuthorSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenETextBook = new SearchScreen(searchedAuthor,ETextBookArray,labelOfTable,5,14);
                searchScreenETextBook.setVisible(true);
            }
        });
        FieldSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedField = searchFieldTextField.getText();
                SearchScreen searchScreenETextBook = new SearchScreen(searchedField,ETextBookArray,labelOfTable,13,14);
                searchScreenETextBook.setVisible(true);

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
                        "%-25s%-35s\n%-25s%-35s\n%-21s%-35s\n%-23s%-35s\n%-23s%-35s\n"+
                                "%-23s%-35s\n%-21s%-35s\n%-19s%-35s\n%-24s%-35s\n%-23s%-35s\n"+
                                "%-16s%-35s\n%-23s%-35s\n%-23s%-35s\n%-24s%-35s"
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
                        "Field",allItems.get(i).get(13)
                );
            }
        }
        //System.out.println(stickerText);
        return stickerText;

    }
    public void pressDeleteETextBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
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

            String newETextBookText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newETextBookText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newETextBookText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newETextBookText += "\n";
                }
            }

            System.out.println("newbooktext: "+newETextBookText);

            mainPage.writeToTxt(newETextBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }
    public void pressAddETextBookButton(DefaultTableModel modelTable,  int lastID){

        if( !(mainPage.isNumeric(editionTextField.getText(),mainPanel))
                || !(mainPage.isNumeric(yearTextField.getText(),mainPanel))
                || !(mainPage.isNumeric(pageNumberTextField.getText(),mainPanel)) ) {
            return;
        }

        String newETextBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                ETextBookTextField.getText()+","+onlineTextField.getText()+","+
                AuthorTextField.getText() +","+ publisherTextField.getText()+","+
                languageTextField.getText()+","+ yearTextField.getText()+","+
                editionTextField.getText()+","+pageNumberTextField.getText()+","+
                ISBNTextField.getText()+","+URLTextField.getText()+","+
                fieldTextField.getText();

        mainPage.writeToTxt(newETextBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                ETextBookTextField.getText(),
                onlineTextField.getText(),
                AuthorTextField.getText(),
                publisherTextField.getText(),
                languageTextField.getText(),
                yearTextField.getText(),
                editionTextField.getText(),
                pageNumberTextField.getText(),
                ISBNTextField.getText(),
                URLTextField.getText(),
                fieldTextField.getText()
        });

    }
    public ArrayList<ETextBook> createETextBookClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<ETextBook> ETextBookClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("ETextBook")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                ETextBook aNewETextBook = new ETextBook(
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
                ETextBookClassArray.add(aNewETextBook);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return ETextBookClassArray;
    }
    public String[][] getETextBookArray(ArrayList<ETextBook> ETextBookArrayList) {
        String[][] ETextBookArray = new String[ETextBookArrayList.size()][14];
        for(int i=0; i < ETextBookArrayList.size();i++) {
            ETextBookArray[i][0] = String.valueOf(ETextBookArrayList.get(i).getId());
            ETextBookArray[i][1] = ETextBookArrayList.get(i).getTitle();
            ETextBookArray[i][2] = ETextBookArrayList.get(i).getLocationInformation();
            ETextBookArray[i][3] = ETextBookArrayList.get(i).getItemType();
            ETextBookArray[i][4] = ETextBookArrayList.get(i).getStatus();
            ETextBookArray[i][5] = ETextBookArrayList.get(i).getAuthor();
            ETextBookArray[i][6] = ETextBookArrayList.get(i).getPublisher();
            ETextBookArray[i][7] = ETextBookArrayList.get(i).getLanguage();
            ETextBookArray[i][8] = String.valueOf(ETextBookArrayList.get(i).getYear());
            ETextBookArray[i][9] = String.valueOf(ETextBookArrayList.get(i).getEdition());
            ETextBookArray[i][10] = String.valueOf(ETextBookArrayList.get(i).getPageNumber());
            ETextBookArray[i][11] = ETextBookArrayList.get(i).getISBN();
            ETextBookArray[i][12] = ETextBookArrayList.get(i).getUrl();
            ETextBookArray[i][13] = ETextBookArrayList.get(i).getField();
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */


        return ETextBookArray;
    }



}
