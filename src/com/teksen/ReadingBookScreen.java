package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ReadingBookScreen extends JFrame {

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
    private JButton createStickerForSelectedButton;
    private JTextField searchTitleTextField;
    private JButton buttonSearchTitle;
    private JTextField searchAuthorTextField;
    private JButton buttonSearchAuthor;
    private ArrayList<ArrayList<String>> allItems;

    public ReadingBookScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Reading Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //System.out.println(readingBookArray.length);



    }
    public ReadingBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Reading Book Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArrayList(allItems);
        String[][] readingBookArray = getReadingBookArray(readingBooksArrayList);
        createTable(readingBookArray);


        addNewReadingBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainPage itScreenForDigital = new mainPage();
                int lastID = itScreenForDigital.getLastItemID("items.txt");

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddBookButton(modelTable,lastID);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                //ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
                //String[][] readingBookArray = getReadingBookArray(books);
                //ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArray(books);

                mainPage itScreenForDigital = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, allItems);

            }
        });

        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        mainPage itScreenForDigital = new mainPage();
                        ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();
                        ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArrayList(allItems);

                        DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                        String stickerText = createSticker(modelTable,allItems);
                        StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                        stckScreen.setVisible(true);

                        //stckScreen.createSticker( modelTable, readingBooksArrayList);



                    }
                });
            }
        });

        String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
                "Author","Publisher","Language","Year","Edition",
                "Page Number","ISBN","Field"};

        buttonSearchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,readingBookArray,labelOfTable,1,13);
                searchScreenDigital.setVisible(true);
            }
        });

        buttonSearchAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedAuthor,readingBookArray,labelOfTable,5,13);
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

                        "Author:",allItems.get(i).get(5),
                        "Publisher:",allItems.get(i).get(6),
                        "Language:",allItems.get(i).get(7),
                        "Year:",allItems.get(i).get(8),
                        "Edition:",allItems.get(i).get(9),

                        "Page Number:",allItems.get(i).get(10),
                        "ISBN:",allItems.get(i).get(11),
                        "Genre:",allItems.get(i).get(12)
                );
            }
        }

        //System.out.println(stickerText);

        return stickerText;

    }



    public void pressAddBookButton(DefaultTableModel modelTable, int lastID) {
        System.out.println(lastID);
        String newBookText = "\n"+ ( lastID+1) +","+
                textFieldTitle.getText() +","+textFieldLocation.getText()+","+
                readingBookTextField.getText()+","+statusTextField.getText()+","+
                textFieldAuthor.getText() +","+ textFieldPublisher.getText()+","+
                textFieldLang.getText()+","+ textFieldYear.getText()+","+
                textFieldEdition.getText()+","+textFieldPageNo.getText()+","+
                textFieldISBN.getText()+","+ textFieldGenre.getText();

        writeToTxt(newBookText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
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
            modelTable.removeRow(table1.getSelectedRow());

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

    public void writeToTxt(String data, String file,boolean appendOrWriteAll) {
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

    public ArrayList<ArrayList<String>> getReadingBookArrayList(ArrayList<ArrayList<String>> items) {
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

    public ArrayList<ReadingBook> createReadingBookClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<ReadingBook> readingBookClassArrayList = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("ReadingBook")) {
                System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                ReadingBook aReadingBook = new ReadingBook(
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
                        Integer.parseInt(itemsArrayList.get(i).get(10)), // int pageNumber
                        itemsArrayList.get(i).get(11), // String ISBN
                        itemsArrayList.get(i).get(12) // String genre
                );
                readingBookClassArrayList.add(aReadingBook);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return readingBookClassArrayList;
    }


    public String[][] getReadingBookArray(ArrayList<ReadingBook> readingBookClassArrayList) {
        String[][] readingBookArray = new String[readingBookClassArrayList.size()][13];
        for(int i=0; i < readingBookClassArrayList.size();i++) {
            readingBookArray[i][0] = String.valueOf(readingBookClassArrayList.get(i).getId());
            readingBookArray[i][1] = readingBookClassArrayList.get(i).getTitle();
            readingBookArray[i][2] = readingBookClassArrayList.get(i).getLocationInformation();
            readingBookArray[i][3] = readingBookClassArrayList.get(i).getItemType();
            readingBookArray[i][4] = readingBookClassArrayList.get(i).getStatus();
            readingBookArray[i][5] = readingBookClassArrayList.get(i).getAuthor();
            readingBookArray[i][6] = readingBookClassArrayList.get(i).getPublisher();
            readingBookArray[i][7] = readingBookClassArrayList.get(i).getLanguage();
            readingBookArray[i][8] = String.valueOf(readingBookClassArrayList.get(i).getYear());
            readingBookArray[i][9] = String.valueOf(readingBookClassArrayList.get(i).getEdition());
            readingBookArray[i][10] = String.valueOf(readingBookClassArrayList.get(i).getPageNumber());
            readingBookArray[i][11] = readingBookClassArrayList.get(i).getISBN();
            readingBookArray[i][12] = readingBookClassArrayList.get(i).getGenre();
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */

        return readingBookArray;
    }

    public void createTable(String[][] readingBooksArray) {
        table1.setModel(new DefaultTableModel(
                readingBooksArray,
                new String[]{"ID","Title","Location","Item Type","Status",
                        "Author","Publisher","Language","Year","Edition",
                        "Page Number","ISBN","Field"}
        ));
    }



}
