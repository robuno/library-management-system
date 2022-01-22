package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> EReadingBookScreen Class </h1>
 * The EReadingBookScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for EReadingBookScreen class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class EReadingBookScreen extends JFrame implements Sticker{
    private JTextField titleTextField; // textfield instance variable
    private JTextField eReadingBookTextField; // textfield instance variable
    private JTextField authorTextField; // textfield instance variable
    private JTextField locationTextField; // textfield instance variable
    private JTextField onlineTextField; // textfield instance variable
    private JTextField publisherTextField; // textfield instance variable
    private JTextField languageTextField; // textfield instance variable
    private JTextField yearTextField; // textfield instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JTextField searchAuthorTextField; // textfield instance variable
    private JButton titleSearchButton; // button instance variable
    private JButton authorSearchButton; // button instance variable
    private JButton addNewEReadingButton; // button instance variable
    private JButton deleteSelectedEReadingButton; // button instance variable
    private JButton createStickerForEButton; // button instance variable
    private JTable table1; // table instance variable
    private JTextField searchGenreTextField; // textfield instance variable
    private JButton genreSearchButton; // button instance variable
    private JTextField editionTextField; // textfield instance variable
    private JTextField pageNumberTextField; // textfield instance variable
    private JTextField ISBNTextField; // textfield instance variable
    private JTextField URLTextField; // textfield instance variable
    private JTextField genreTextField; // textfield instance variable
    private JPanel mainPanel; // panel instance variable

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language","Year","Edition","Page Number","ISBN", "URL","Genre"};


    /**
     * EReadingBookScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public EReadingBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("EReadingBook Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        this.allItems = allItems;// assign allItems

        ArrayList<EReadingBook> EReadingBookArrayList = createEReadingBookClassArrayList(allItems); // create ArrayList
        String[][] EReadingBookArray = getEReadingBookArray(EReadingBookArrayList); // create Array
        MainPage.createTable(table1,EReadingBookArray,labelOfTable); // create Table

        ImageIcon searchAuthorIcon = new ImageIcon("search.png"); // upload icon
        Image searchAuthorIconScale = searchAuthorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchAuthorIconScale= new ImageIcon(searchAuthorIconScale); // set icon
        authorSearchButton.setIcon(scaledSearchAuthorIconScale); // show icon
        titleSearchButton.setIcon(scaledSearchAuthorIconScale); // show icon
        genreSearchButton.setIcon(scaledSearchAuthorIconScale); // show icon

        addNewEReadingButton.addActionListener(new ActionListener() {
            /**
             * Add New EReadingBook Button
             * When the add new EReadingBook button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddEReadingBookButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEReadingBook = new MainPage();
                int lastID = itScreenForEReadingBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddEReadingBookButton(modelTable, lastID);
            }
        });
        deleteSelectedEReadingButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected EReadingBook Button
             * When the delete selected EReadingBook button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteEReadingBookButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEReadingBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEReadingBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteEReadingBookButton(modelTable, allItems);
            }
        });
        createStickerForEButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected EReadingBook Button
             * When the create sticker for selected EReadingBook button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEReadingBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEReadingBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                if(!stickerText.equals("")) {
                    StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                    stckScreen.setVisible(true);
                }
            }
        });
        titleSearchButton.addActionListener(new ActionListener() {
            /**
             * Search Title in EReadingBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenEReadingBook= new SearchScreen(searchedTitle,EReadingBookArray,labelOfTable,1,14);
                searchScreenEReadingBook.setVisible(true);
            }
        });
        authorSearchButton.addActionListener(new ActionListener() {
            /**
             * Search Author in EReadingBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenEReadingBook = new SearchScreen(searchedAuthor,EReadingBookArray,labelOfTable,5,14);
                searchScreenEReadingBook.setVisible(true);

            }
        });
        genreSearchButton.addActionListener(new ActionListener() {
            /**
             * Search Genre in EReadingBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedGenre = searchGenreTextField.getText();
                SearchScreen searchScreenEReadingBook = new SearchScreen(searchedGenre,EReadingBookArray,labelOfTable,13,14);
                searchScreenEReadingBook.setVisible(true);
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
     * Delete EReadingBook Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteEReadingBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(i).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) {
                        //System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }

            itemsArrayList.remove(rowWillBeDeleted);
            String newEReadingBookText = "";
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

            MainPage.writeToTxt(newEReadingBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }
    /**
     * Add EReadingBook Function
     * This function add new digital by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddEReadingBookButton(DefaultTableModel modelTable,  int lastID){
        if( !(MainPage.isNumeric(editionTextField.getText(),mainPanel))
                || !(MainPage.isNumeric(yearTextField.getText(),mainPanel))
                || !(MainPage.isNumeric(pageNumberTextField.getText(),mainPanel)) ) {
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

        MainPage.writeToTxt(newEReadingBookText,"items.txt",true);

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

    /**
     * Create EReadingBook Array List Function
     * This function creates EReadingBook Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return Article ArrayList.
     */
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

    /**
     * Get EReadingBook Array Function
     * This function crates EReadingBook array to display items in table
     * @param EReadingBookArrayList is the ArrayList contains only Articles
     * @return Digital nested array.
     */
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
        return EReadingBookArray;
    }

}
