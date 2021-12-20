package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class DigitalScreen extends JFrame implements Sticker{
    private JPanel mainPanel;
    private JTable table1;
    private JTextField titleTextField;
    private JTextField locationTextField;
    private JTextField digitalTextField;
    private JTextField directionTextField;
    private JTextField companyTextField;
    private JTextField topicTextField;
    private JTextField languageTextField;
    private JTextField physicalPropTextField;
    private JTextField yearTextField;
    private JTextField timeTextField;
    private JTextField isbnTextField;
    private JTextField statusTextField;
    private JButton deleteSelectedDigitalItemButton;
    private JButton addNewDigitalItemButton;
    private JButton createStickerForSelectedButton;
    private JTextField searchTitleTextField;
    private JTextField searchDirectorTextField;
    private JButton buttonSearchTitle;
    private JButton buttonSearchDirector;
    private JPanel searchPanel;
    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Director","Company","Topic","Language","Physical Property",
            "ISBN","Time","Year"};

    public DigitalScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Digital Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public DigitalScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Digital Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<Digital> digitalArrayList = createDigitalClassArrayList(allItems);
        String[][] digitalArray = getDigitalArray(digitalArrayList);
        mainPage.createTable(table1,digitalArray,labelOfTable);


        addNewDigitalItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForDigital = new mainPage();
                int lastID = itScreenForDigital.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddDigitalButton(modelTable, lastID);


            }
        });

        deleteSelectedDigitalItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForDigital = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteDigitalButton(modelTable, allItems);

            }
        });

        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForDigital = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();
                ArrayList<Digital> digitalArrayList = createDigitalClassArrayList(allItems);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);
            }
        });

        String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
                "Director","Company","Topic","Language","Physical Property",
                "ISBN","Time","Year"};

        buttonSearchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,digitalArray,labelOfTable,1,13);
                searchScreenDigital.setVisible(true);
            }
        });

        buttonSearchDirector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedDirector = searchDirectorTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedDirector,digitalArray,labelOfTable, 5,13);
                searchScreenDigital.setVisible(true);
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
                                "%-16s%-35s\n%-23s%-35s\n%-22s%-35s"
                        ,
                        "ID:",allItems.get(i).get(0),
                        "Title:",allItems.get(i).get(1),
                        "Location:",allItems.get(i).get(2),
                        "Type:",allItems.get(i).get(3),
                        "Status:",allItems.get(i).get(4),

                        "Director:",allItems.get(i).get(5),
                        "Company:",allItems.get(i).get(6),
                        "Topic:",allItems.get(i).get(7),
                        "Language:",allItems.get(i).get(8),
                        "Physical Property:",allItems.get(i).get(9),

                        "ISBN:",allItems.get(i).get(10),
                        "Time:",allItems.get(i).get(11),
                        "Year:",allItems.get(i).get(12)
                );
            }
        }
        //System.out.println(stickerText);
        return stickerText;

    }

    public void pressDeleteDigitalButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        System.out.println(itemsArrayList);
        System.out.println("********");

        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(0).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) {
                        System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }

            System.out.println("rowwillbedeleted"+rowWillBeDeleted);
            itemsArrayList.remove(rowWillBeDeleted);

            String newBookText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newBookText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newBookText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newBookText += "\n";
                }
            }

            System.out.println("newbooktext: "+newBookText);


            mainPage.writeToTxt(newBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }


    public void pressAddDigitalButton(DefaultTableModel modelTable,  int lastID){
        if( !(mainPage.isNumeric(timeTextField.getText(),mainPanel)) || !(mainPage.isNumeric(yearTextField.getText(),mainPanel)) ) {
            return;
        }

        String newBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                digitalTextField.getText()+","+statusTextField.getText()+","+
                directionTextField.getText() +","+ companyTextField.getText()+","+
                topicTextField.getText()+","+ languageTextField.getText()+","+
                physicalPropTextField.getText()+","+isbnTextField.getText()+","+
                timeTextField.getText()+","+ yearTextField.getText();

        mainPage.writeToTxt(newBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                 lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                digitalTextField.getText(),
                statusTextField.getText(),
                directionTextField.getText(),
                companyTextField.getText(),
                topicTextField.getText(),
                languageTextField.getText(),
                physicalPropTextField.getText(),
                isbnTextField.getText(),
                timeTextField.getText(),
                yearTextField.getText()
        });
    }


    public ArrayList<Digital> createDigitalClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {


        ArrayList<Digital> digitalClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("Digital")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                Digital aNewDigital = new Digital(
                        Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                        itemsArrayList.get(i).get(3), // String itemType
                        itemsArrayList.get(i).get(1), // String title
                        itemsArrayList.get(i).get(2), // String locationInformation
                        itemsArrayList.get(i).get(4), // String status
                        itemsArrayList.get(i).get(5), // String director
                        itemsArrayList.get(i).get(6), // String company
                        itemsArrayList.get(i).get(7), // String topic
                        itemsArrayList.get(i).get(8), // String language
                        itemsArrayList.get(i).get(9), // String physical property
                        itemsArrayList.get(i).get(10), // String ISBN
                        Integer.parseInt(itemsArrayList.get(i).get(11)), // int time
                        Integer.parseInt(itemsArrayList.get(i).get(12)) // int year
                );
                digitalClassArray.add(aNewDigital);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        /*
        for(int i=0; i < digitalClassArray.size(); i++) {
            System.out.println(digitalClassArray.get(i).getId()+","+
                    digitalClassArray.get(i).getTitle()+","+
                    digitalClassArray.get(i).getLocationInformation()+","+
                    digitalClassArray.get(i).getItemType()+","+
                    digitalClassArray.get(i).getStatus()+","+
                    digitalClassArray.get(i).getDirector()+","+
                    digitalClassArray.get(i).getCompany()+","+
                    digitalClassArray.get(i).getTopic()+","+
                    digitalClassArray.get(i).getLanguage()+","+
                    digitalClassArray.get(i).getPhysicalProperty()+","+
                    digitalClassArray.get(i).getISBN()+","+
                    digitalClassArray.get(i).getTime()+","+
                    digitalClassArray.get(i).getYear());
        }

         */
        return digitalClassArray;
    }

    public String[][] getDigitalArray(ArrayList<Digital> digitalArrayList) {
        String[][] digitalArray = new String[digitalArrayList.size()][13];
        for(int i=0; i < digitalArrayList.size();i++) {
            digitalArray[i][0] = String.valueOf(digitalArrayList.get(i).getId());
            digitalArray[i][1] = digitalArrayList.get(i).getTitle();
            digitalArray[i][2] = digitalArrayList.get(i).getLocationInformation();
            digitalArray[i][3] = digitalArrayList.get(i).getItemType();
            digitalArray[i][4] = digitalArrayList.get(i).getStatus();
            digitalArray[i][5] = digitalArrayList.get(i).getDirector();
            digitalArray[i][6] = digitalArrayList.get(i).getCompany();
            digitalArray[i][7] = digitalArrayList.get(i).getTopic();
            digitalArray[i][8] = digitalArrayList.get(i).getLanguage();
            digitalArray[i][9] = digitalArrayList.get(i).getPhysicalProperty();
            digitalArray[i][10] = digitalArrayList.get(i).getISBN();
            digitalArray[i][11] = String.valueOf(digitalArrayList.get(i).getTime());
            digitalArray[i][12] = String.valueOf(digitalArrayList.get(i).getYear());
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */


        return digitalArray;
    }

}
