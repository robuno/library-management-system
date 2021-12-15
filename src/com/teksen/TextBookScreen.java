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

public class TextBookScreen extends JFrame{
    private JButton searchTitleButton;
    private JTextField searchTitleTextField;
    private JTextField searchAuthorTextField;
    private JTextField searchFieldTextField;
    private JButton searchAuthorButton;
    private JButton searchFieldButton;
    private JTextField titleTextField;
    private JTextField textBookTextField;
    private JTextField authorTextField;
    private JTextField languageTextField;
    private JTextField locationTextField;
    private JTextField availableTextField;
    private JTextField publisherTextField;
    private JTextField yearTextField;
    private JTextField pageNumberTextField;
    private JTextField editionTextField;
    private JTextField ISBNTextField;
    private JTextField fieldTextField;
    private JButton addNewTextBookButton;
    private JButton deleteSelectedTextBookButton;
    private JButton createStickerForSelectedButton;
    private JTable table1;
    private JPanel mainPanel;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition","Page Number","ISBN","Field"};

    public TextBookScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Text Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public TextBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Text Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<TextBook> TextBookArrayList = createTextBookClassArrayList(allItems);
        String[][] TextBookArray = getTextBookArray(TextBookArrayList);
        mainPage.createTable(table1,TextBookArray,labelOfTable);

        addNewTextBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForTextBook = new mainPage();
                int lastID = itScreenForTextBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddTextBookButton(modelTable, lastID);

            }
        });
        deleteSelectedTextBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForTextBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForTextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteTextBookButton(modelTable, allItems);

            }
        });
        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForTextBook = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForTextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);

            }
        });
        searchTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenTextBook= new SearchScreen(searchedTitle,TextBookArray,labelOfTable,1,13);
                searchScreenTextBook.setVisible(true);

            }
        });
        searchAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenTextBook = new SearchScreen(searchedAuthor,TextBookArray,labelOfTable,5,13);
                searchScreenTextBook.setVisible(true);
            }
        });
        searchFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedField = searchFieldTextField.getText();
                SearchScreen searchScreenTextBook = new SearchScreen(searchedField,TextBookArray,labelOfTable,12,13);
                searchScreenTextBook.setVisible(true);

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
                                "%-16s%-35s\n%-23s%-35s\n%-24s%-35s"
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
                        "Field",allItems.get(i).get(12)
                );
            }
        }
        //System.out.println(stickerText);
        return stickerText;

    }
    public void pressDeleteTextBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
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

            String newTextBookText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newTextBookText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newTextBookText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newTextBookText += "\n";
                }
            }

            System.out.println("newbooktext: "+newTextBookText);

            mainPage.writeToTxt(newTextBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }
    public void pressAddTextBookButton(DefaultTableModel modelTable,  int lastID){
        String newTextBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                textBookTextField.getText()+","+availableTextField.getText()+","+
                authorTextField.getText() +","+ publisherTextField.getText()+","+
                languageTextField.getText()+","+ yearTextField.getText()+","+
                editionTextField.getText()+","+pageNumberTextField.getText()+","+
                ISBNTextField.getText()+","+ fieldTextField.getText();

        mainPage.writeToTxt(newTextBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                textBookTextField.getText(),
                availableTextField.getText(),
                authorTextField.getText(),
                publisherTextField.getText(),
                languageTextField.getText(),
                yearTextField.getText(),
                editionTextField.getText(),
                pageNumberTextField.getText(),
                ISBNTextField.getText(),
                fieldTextField.getText()
        });

    }
    public ArrayList<TextBook> createTextBookClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<TextBook> TextBookClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("TextBook")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                TextBook aNewTextBook = new TextBook(
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
                        itemsArrayList.get(i).get(12)

                );
                TextBookClassArray.add(aNewTextBook);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return TextBookClassArray;
    }
    public String[][] getTextBookArray(ArrayList<TextBook> TextBookArrayList) {
        String[][] TextBookArray = new String[TextBookArrayList.size()][14];
        for(int i=0; i < TextBookArrayList.size();i++) {
            TextBookArray[i][0] = String.valueOf(TextBookArrayList.get(i).getId());
            TextBookArray[i][1] = TextBookArrayList.get(i).getTitle();
            TextBookArray[i][2] = TextBookArrayList.get(i).getLocationInformation();
            TextBookArray[i][3] = TextBookArrayList.get(i).getItemType();
            TextBookArray[i][4] = TextBookArrayList.get(i).getStatus();
            TextBookArray[i][5] = TextBookArrayList.get(i).getAuthor();
            TextBookArray[i][6] = TextBookArrayList.get(i).getPublisher();
            TextBookArray[i][7] = TextBookArrayList.get(i).getLanguage();
            TextBookArray[i][8] = String.valueOf(TextBookArrayList.get(i).getYear());
            TextBookArray[i][9] = String.valueOf(TextBookArrayList.get(i).getEdition());
            TextBookArray[i][10] = String.valueOf(TextBookArrayList.get(i).getPageNumber());
            TextBookArray[i][11] = TextBookArrayList.get(i).getISBN();
            TextBookArray[i][12] = TextBookArrayList.get(i).getField();
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */


        return TextBookArray;
    }


}
