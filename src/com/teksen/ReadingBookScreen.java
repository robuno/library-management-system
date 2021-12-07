package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingBookScreen extends JFrame{

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
    private JTextField statusTextField;
    //private ArrayList<ArrayList<String>> allItems = getItemsArrayList();

    public ReadingBookScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //System.out.println(readingBookArray.length);
        ArrayList<ArrayList<String>> allItems = getItemsArrayList();
        ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
        String[][] readingBookArray = getReadingBookArray(books);
        createTable(readingBookArray);

        ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArray(books);

        addNewReadingBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
                String[][] readingBookArray = getReadingBookArray(books);
                ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArray(books);

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
                ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArray(books);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, allItems);

            }
        });
    }

    public void pressAddBookButton(DefaultTableModel modelTable) {
        System.out.println(getLastItemID());
        String newBookText = "\n"+ ( getLastItemID()+1) +","+
                textFieldTitle.getText() +","+textFieldLocation.getText()+","+
                readingBookTextField.getText()+","+statusTextField.getText()+","+
                textFieldAuthor.getText() +","+ textFieldPublisher.getText()+","+
                textFieldLang.getText()+","+ textFieldYear.getText()+","+
                textFieldEdition.getText()+","+textFieldPageNo.getText()+","+
                textFieldISBN.getText()+","+ textFieldGenre.getText();

        writeToTxt(newBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                getLastItemID(),
                textFieldTitle.getText(),
                textFieldLocation.getText(),
                readingBookTextField.getText(),
                statusTextField.getText(),
                textFieldAuthor.getText(),
                textFieldPublisher.getText(),
                textFieldLang.getText(),
                textFieldYear.getText(),
                textFieldEdition.getText(),
                textFieldPageNo.getText(),
                textFieldISBN.getText(),
                textFieldGenre.getText()
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
                        //System.out.print(allItems.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            //System.out.println("rowWillBeDeleted: "+rowWillBeDeleted);
            allItems.remove(rowWillBeDeleted);
            //getReadingBookArray(allItems);

            String newBookText = "";

            /*
            ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
            ArrayList<ReadingBook> readingBooksClassArrayList = createReadingBookClassArray(books);
            for(int i=0; i< readingBooksClassArrayList.size();i++) {
                System.out.println(readingBooksClassArrayList.get(i));
                newBookText += readingBooksClassArrayList.get(i)+"\n";
            }
             */


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



    private ArrayList<ReadingBook> createReadingBookClassArray(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<ReadingBook> readingBookClassArray = new ArrayList<>();

        for(int i=0; i<itemsArrayList.size();i++) {

            ReadingBook aNewReadingBook = new ReadingBook(
                    Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                    itemsArrayList.get(i).get(3), // String itemType
                    itemsArrayList.get(i).get(1), // String title
                    itemsArrayList.get(i).get(2), // String locationInformation
                    itemsArrayList.get(i).get(4), // String status
                    itemsArrayList.get(i).get(5), // String author
                    itemsArrayList.get(i).get(6), // String publisher
                    itemsArrayList.get(i).get(7), // String language
                    Integer.parseInt(itemsArrayList.get(i).get(8)), // int year
                    Integer.parseInt(itemsArrayList.get(i).get(9)), // int edition
                    Integer.parseInt(itemsArrayList.get(i).get(10)), // int page number
                    itemsArrayList.get(i).get(11), // String ISBN
                    itemsArrayList.get(i).get(12) // String genre
                    );

            readingBookClassArray.add(aNewReadingBook);
            System.out.println(aNewReadingBook);

            /*
            System.out.println(
                    "i0: "+Integer.parseInt(itemsArrayList.get(i).get(0))+",  "+
                    "i3: "+itemsArrayList.get(i).get(3)+",  "+
                    "i1: "+itemsArrayList.get(i).get(1)+",  "+
                    "i2: "+itemsArrayList.get(i).get(2)+",  "+
                    "i3: "+itemsArrayList.get(i).get(3)+",  "+
                    "i4: "+itemsArrayList.get(i).get(4)+",  "+
                    "i5: "+itemsArrayList.get(i).get(5)+",  "+
                    "i6: "+itemsArrayList.get(i).get(6)+",  "+
                    "i7: "+itemsArrayList.get(i).get(7)+",  "+
                    "i8: "+Integer.parseInt(itemsArrayList.get(i).get(8))+",  "+
                    "i9: "+Integer.parseInt(itemsArrayList.get(i).get(9))+",  "+
                    "i10: "+Integer.parseInt(itemsArrayList.get(i).get(10))+",  "+
                    "i11: "+itemsArrayList.get(i).get(11)+",  "+
                    "i12: "+itemsArrayList.get(i).get(12));
            */
            //System.out.println();
        }

        /*
        System.out.println("---------------------------------");
        for(int i=0; i< readingBookClassArray.size();i++) {
            System.out.println(readingBookClassArray.get(i));
        }

         */
        return readingBookClassArray;
    }

    private void createTable(String[][] readingBooksArray) {
        table1.setModel(new DefaultTableModel(
                readingBooksArray,
                new String[]{"ID","Title","Location","Item Type","Status",
                        "Author","Publisher","Language","Year","Edition",
                        "Page Number","ISBN","Field"}
        ));

    }
}
