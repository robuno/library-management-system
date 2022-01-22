package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> TextBookScreen Class </h1>
 * The TextBookScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for TextBookScreen class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class TextBookScreen extends JFrame implements Sticker{
    private JButton searchTitleButton; // button instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JTextField searchAuthorTextField; // textfield instance variable
    private JTextField searchFieldTextField; // textfield instance variable
    private JButton searchAuthorButton;// button instance variable
    private JButton searchFieldButton;// button instance variable
    private JTextField titleTextField; // textfield instance variable
    private JTextField textBookTextField; // textfield instance variable
    private JTextField authorTextField; // textfield instance variable
    private JTextField languageTextField; // textfield instance variable
    private JTextField locationTextField; // textfield instance variable
    private JTextField availableTextField; // textfield instance variable
    private JTextField publisherTextField; // textfield instance variable
    private JTextField yearTextField; // textfield instance variable
    private JTextField pageNumberTextField; // textfield instance variable
    private JTextField editionTextField; // textfield instance variable
    private JTextField ISBNTextField; // textfield instance variable
    private JTextField fieldTextField; // textfield instance variable
    private JButton addNewTextBookButton; // button instance variable
    private JButton deleteSelectedTextBookButton; // button instance variable
    private JButton createStickerForSelectedButton; // button instance variable
    private JTable table1; // table instance variable
    private JPanel mainPanel; // panel instance variable

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition","Page Number","ISBN","Field"};

    /**
     * TextBookScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public TextBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Text Book Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        this.allItems = allItems; // assign allItems

        ArrayList<TextBook> TextBookArrayList = createTextBookClassArrayList(allItems); // create ArrayList
        String[][] TextBookArray = getTextBookArray(TextBookArrayList); // create Array
        MainPage.createTable(table1,TextBookArray,labelOfTable); // create Table

        ImageIcon searchAuthorIcon = new ImageIcon("search.png"); // upload search icon
        Image searchAuthorIconScale = searchAuthorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchAuthorIconScale= new ImageIcon(searchAuthorIconScale); // create icon
        searchAuthorButton.setIcon(scaledSearchAuthorIconScale); // display search icon
        searchTitleButton.setIcon(scaledSearchAuthorIconScale); // display search icon
        searchFieldButton.setIcon(scaledSearchAuthorIconScale);// display search icon

        addNewTextBookButton.addActionListener(new ActionListener() {
            /**
             * Add New TextBook Button
             * When the add new TextBook button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddTextBookButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForTextBook = new MainPage();
                int lastID = itScreenForTextBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddTextBookButton(modelTable, lastID);
            }
        });

        /**
         * Delete Selected TextBook Button
         * When the delete selected TextBook button is pressed, get the all items in the array
         * to determine which object is going to be deleted in database and create table model
         * to update list.
         *
         * Call pressDeleteTextBookButton function to perform removing operation
         * @param e is the pressing event.
         */
        deleteSelectedTextBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForTextBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForTextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteTextBookButton(modelTable, allItems);
            }
        });

        /**
         * Create Sticker Selected TextBook Button
         * When the create sticker for selected TextBook button is pressed, get the all items in the array
         * to determine the item whose sticker will be created in database and create table model
         * to get the selected item. Create sticker text to display sticker for selected item.
         *
         * @param e is the pressing event.
         */
        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForTextBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForTextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                if(!stickerText.equals("")) {
                    StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                    stckScreen.setVisible(true);
                }

            }
        });
        searchTitleButton.addActionListener(new ActionListener() {
            /**
             * Search Title in TextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenTextBook= new SearchScreen(searchedTitle,TextBookArray,labelOfTable,1,13);
                searchScreenTextBook.setVisible(true);
            }
        });
        searchAuthorButton.addActionListener(new ActionListener() {
            /**
             * Search Author in TextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenTextBook = new SearchScreen(searchedAuthor,TextBookArray,labelOfTable,5,13);
                searchScreenTextBook.setVisible(true);
            }
        });
        searchFieldButton.addActionListener(new ActionListener() {
            /**
             * Search Field in TextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedField = searchFieldTextField.getText();
                SearchScreen searchScreenTextBook = new SearchScreen(searchedField,TextBookArray,labelOfTable,12,13);
                searchScreenTextBook.setVisible(true);
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
                    //System.out.println(allItems.get(i).get(1)+"-"+selectedRowID);
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
     * Delete TextBook Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteTextBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
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

            itemsArrayList.remove(rowWillBeDeleted);
            String newTextBookText = "";

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

            MainPage.writeToTxt(newTextBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }

    /**
     * Add TextBook Function
     * This function add new digital by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddTextBookButton(DefaultTableModel modelTable,  int lastID){
        String newTextBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                textBookTextField.getText()+","+availableTextField.getText()+","+
                authorTextField.getText() +","+ publisherTextField.getText()+","+
                languageTextField.getText()+","+ yearTextField.getText()+","+
                editionTextField.getText()+","+pageNumberTextField.getText()+","+
                ISBNTextField.getText()+","+ fieldTextField.getText();

        MainPage.writeToTxt(newTextBookText,"items.txt",true);

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

    /**
     * Create TextBook Array List Function
     * This function creates TextBook Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return TextBook ArrayList.
     */
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

    /**
     * Get TextBook Array Function
     * This function crates Article array to display items in table
     * @param TextBookArrayList is the ArrayList contains only TextBooks
     * @return Digital nested array.
     */
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
        return TextBookArray;
    }
}
