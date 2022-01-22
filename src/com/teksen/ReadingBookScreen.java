package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1> ReadingBookScreen Class </h1>
 * The ReadingBookScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for ReadingBookScreen class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class ReadingBookScreen extends JFrame implements Sticker {
    private JPanel mainPanel;// panel instance variable
    private JTable table1;// table instance variable
    private JPanel addBookPanel;// panel instance variable
    private JTextField textFieldTitle;// textfield instance variable
    private JTextField readingBookTextField;// textfield instance variable
    private JTextField textFieldLocation;// textfield instance variable
    private JTextField textFieldAuthor;// textfield instance variable
    private JTextField textFieldPublisher;// textfield instance variable
    private JTextField textFieldLang;// textfield instance variable
    private JTextField textFieldYear;// textfield instance variable
    private JTextField textFieldEdition;// textfield instance variable
    private JTextField textFieldPageNo;// textfield instance variable
    private JTextField textFieldISBN;// textfield instance variable
    private JTextField textFieldGenre;// textfield instance variable
    private JButton addNewReadingBookButton;// button instance variable
    private JButton deleteButton;// button instance variable
    private JTextField statusTextField;// textfield instance variable
    private JButton createStickerForSelectedButton;// button instance variable
    private JTextField searchTitleTextField;// textfield instance variable
    private JButton buttonSearchTitle;// button instance variable
    private JTextField searchAuthorTextField;// textfield instance variable
    private JButton buttonSearchAuthor;// button instance variable
    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition",
            "Page Number","ISBN","Field"};// labels for table


    /**
     * ReadingBookScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public ReadingBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Reading Book Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        this.allItems = allItems; // assign allItems

        ArrayList<ReadingBook> readingBooksArrayList = createReadingBookClassArrayList(allItems); // create ArrayList
        String[][] readingBookArray = getReadingBookArray(readingBooksArrayList); // create Array
        MainPage.createTable(table1,readingBookArray,labelOfTable); // create Table

        ImageIcon searchAuthorIcon = new ImageIcon("search.png");// upload search icon
        Image searchAuthorIconScale = searchAuthorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchAuthorIconScale= new ImageIcon(searchAuthorIconScale);// create icon
        buttonSearchAuthor.setIcon(scaledSearchAuthorIconScale);// display search icon
        buttonSearchTitle.setIcon(scaledSearchAuthorIconScale);// display search icon


        addNewReadingBookButton.addActionListener(new ActionListener() {
            /**
             * Add New ReadingBook Button
             * When the add new ReadingBook button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddBookButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                int lastID = itScreenForDigital.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddBookButton(modelTable,lastID);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected ReadingBook Button
             * When the delete selected digital button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteBookButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, allItems);
            }
        });

        createStickerForSelectedButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected ReadingBook Button
             * When the create sticker for selected ReadingBook button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                if(!stickerText.equals("")) {
                    StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                    stckScreen.setVisible(true);
                }
            }
        });


        buttonSearchTitle.addActionListener(new ActionListener() {
            /**
             * Search Title in ReadingBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,readingBookArray,labelOfTable,1,13);
                searchScreenDigital.setVisible(true);
            }
        });

        buttonSearchAuthor.addActionListener(new ActionListener() {
            /**
             * Search Title in ReadingBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedAuthor,readingBookArray,labelOfTable,5,13);
                searchScreenDigital.setVisible(true);
            }
        });
    }

    /**
     * CreateSticker Function
     * This function creates a sticker text to display the selected item's values in
     * sticker screen. To determine the item whose sticker will be displayed, user needs to select
     * a single row in the table and press the create sticker button.
     * @param modelTable is the table model for articles
     * @param allItems is the all items in database
     * @return sticker text for selected item.
     */
    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        String stickerText ="";
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));
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
        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to create sticker.");

            }
            return"";
        }
    }

    /**
     * Add ReadingBook Function
     * This function add new digital by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddBookButton(DefaultTableModel modelTable, int lastID) {
        if( !(MainPage.isNumeric(textFieldPageNo.getText(),mainPanel))
                || !(MainPage.isNumeric(textFieldEdition.getText(),mainPanel))
                || !(MainPage.isNumeric(textFieldYear.getText(),mainPanel)) ) {
            return;
        }


        String newBookText = "\n"+ ( lastID+1) +","+
                textFieldTitle.getText() +","+textFieldLocation.getText()+","+
                readingBookTextField.getText()+","+statusTextField.getText()+","+
                textFieldAuthor.getText() +","+ textFieldPublisher.getText()+","+
                textFieldLang.getText()+","+ textFieldYear.getText()+","+
                textFieldEdition.getText()+","+textFieldPageNo.getText()+","+
                textFieldISBN.getText()+","+ textFieldGenre.getText();

        MainPage.writeToTxt(newBookText,"items.txt",true);

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

    /**
     * Delete ReadingBook Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param allItems is the all items in database
     * @return nothing.
     */
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
            MainPage.writeToTxt(newBookText,"items.txt",false);
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

    /**
     * Create ReadingBook Array List Function
     * This function creates ReadingBook Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return ReadingBook ArrayList.
     */
    public ArrayList<ReadingBook> createReadingBookClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<ReadingBook> readingBookClassArrayList = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("ReadingBook")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
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

    /**
     * Get ReadingBook Array Function
     * This function crates ReadingBook array to display items in table
     * @param readingBookClassArrayList is the ArrayList contains only ReadingBook
     * @return Digital nested array.
     */
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

        return readingBookArray;
    }

}
