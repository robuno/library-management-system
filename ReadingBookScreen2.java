
package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingBookScreen2 extends JFrame{

    private JPanel mainPanel;
    private JTable table1;
    private JPanel addBookPanel;
    private JTextField textFieldTitle;
    private JTextField readingBookTextField;
    private JTextField textFieldLocation;
    private JTextField textFieldAuthor;
    private JTextField textFieldPublisher;
    private JTextField textFieldLang;
    private JTextField textFieldYear;
    private JTextField textFieldEdition;
    private JTextField textFieldPageNo;
    private JTextField textFieldISBN;
    private JTextField textFieldGenre;
    private JButton addNewReadingBookButton;
    private JButton deleteButton;
    //private ArrayList<ArrayList<String>> allItems = getItemsArrayList();

    public ReadingBookScreen2() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //System.out.println(readingBookArray.length);
        ArrayList<ArrayList<String>> allItems = getItemsArrayList();
        ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
        String[][] readingBookArray = getReadingBookArray(books);
        createTable(readingBookArray);

        addNewReadingBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
                String[][] readingBookArray = getReadingBookArray(books);
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddBookButton(modelTable);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
                String[][] readingBookArray = getReadingBookArray(books);
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, allItems);

            }
        });
    }

    public void pressAddBookButton(DefaultTableModel modelTable) {
        System.out.println(getLastItemID());
        String newBookText = "\n"+ ( getLastItemID()+1) +","+
                textFieldTitle.getText() +","+textFieldLocation.getText()+","+
                readingBookTextField.getText()+","+ textFieldAuthor.getText() +","+
                textFieldPublisher.getText()+","+textFieldLang.getText()+","+
                textFieldYear.getText()+","+textFieldEdition.getText()+","+
                textFieldPageNo.getText()+","+textFieldISBN.getText()+","+
                textFieldGenre.getText();

        writeToTxt(newBookText,"items.txt",true);

        ArrayList<ArrayList<String>> allItems2 = getItemsArrayList();

        modelTable.addRow(new Object[] {
                getLastItemID(),
                textFieldTitle.getText(),
                textFieldLocation.getText(),
                readingBookTextField.getText(),
                textFieldAuthor.getText(),
                textFieldEdition.getText(),
                textFieldISBN.getText(),
                textFieldGenre.getText(),
                textFieldLang.getText(),
                textFieldPageNo.getText(),
                textFieldPublisher.getText(),
                textFieldYear.getText()
        });
    }

    public void pressDeleteBookButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<allItems.size(); i++) {
                for(int j = 0; j< allItems.get(0).size(); j++) {
                    if( Integer.parseInt(allItems.get(i).get(0)) ==selectedRowID) {
                        System.out.print(allItems.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            System.out.println("rowWillBeDeleted: "+rowWillBeDeleted);
            allItems.remove(rowWillBeDeleted);
            //getReadingBookArray(allItems);

            String newBookText = "";
            for(int i=0; i< allItems.size(); i++) {
                for(int j=0; j<allItems.get(i).size();j++) {
                    newBookText += allItems.get(i).get(j);
                    if(!allItems.get(i).get(j).equals(allItems.get(i).get(allItems.get(i).size() -1))) {
                        newBookText += ",";
                    }
                }
                if((i+1) != allItems.size()) {
                    newBookText += "\n";
                }
            }

            writeToTxt(newBookText,"items.txt",false);

                    /*
                    for(int i=0; i<allItems.size(); i++) {
                        for(int j = 0; j< allItems.get(0).size(); j++) {
                            System.out.print(allItems.get(i).get(j)+",");
                        }
                        System.out.println();
                    }

                     */


                    /*
                    for(int i =0; i < readingBookArray.length; i++) {
                        for(int j=0; j < readingBookArray[0].length; j++) {
                            if (Integer.parseInt(readingBookArray[i][0]) == selectedRowID)
                                System.out.print(readingBookArray[i][j]+" ");
                                books.remove(selectedRowID);
                        }
                    }
                    */

            //System.out.println(selectedRowID);

            modelTable.removeRow(table1.getSelectedRow());
        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to delete.");
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private int getLastItemID() {
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

        int lastID = Integer.parseInt(items.get( items.size() - 1).get(0));
        return lastID;
    }

    private void writeToTxt(String data, String file,boolean appendOrWriteAll) {

        try {
            //System.out.println(newBookText);

            File f1 = new File(file);
            FileWriter fileWritter = new FileWriter(f1.getName(),appendOrWriteAll);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(data);
            bw.close();
        } catch(IOException e2){
            e2.printStackTrace();
        }


    }

    private int numberOfItems() {
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
        return items.size();
    }


    private ArrayList<ArrayList<String>> getItemsArrayList() {
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
    private ArrayList<ArrayList<String>> getReadingBookArrayList(ArrayList<ArrayList<String>> items) {
        ArrayList<ArrayList<String>> books = new ArrayList<>();

        for(int i=0; i < items.size();i++) {
            if (items.get(i).get(3).equals("ReadingBook")) {
                books.add(items.get(i));
            }
        }

        //System.out.println();
        //System.out.println(books);

        return books;
    }


    private String[][] getReadingBookArray(ArrayList<ArrayList<String>> books) {

        //System.out.println(books);

        String[][] readingBooksArray = new String[books.size()][books.get(1).size()];
        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length ; j++) {
                readingBooksArray[i][j] = books.get(i).get(j);
            }
        }

        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length; j++) {
                //System.out.println(readingBooksArray[i][j]);
            }
        }
        return readingBooksArray;
    }

    private void createReadingBookClassArray(String[][] readingBooksArray) {

    }

    private void createTable(String[][] readingBooksArray) {
        table1.setModel(new DefaultTableModel(
                readingBooksArray,
                new String[]{"ID","Title","Location","Item Type",
                        "Author","Publisher","Language","Year","Edition",
                        "Page Number","ISBN","Field"}
        ));

    }
}


