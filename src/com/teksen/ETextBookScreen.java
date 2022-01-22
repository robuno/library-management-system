package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> ETextBookScreen Class </h1>
 * The ETextBookScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for ETextBookScreen class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class ETextBookScreen extends JFrame implements Sticker{
    private JTextField searchTitleTextField;// textfield instance variable
    private JTextField searchAuthorTextField;// textfield instance variable
    private JTextField searchFieldTextField;// textfield instance variable
    private JButton titleSearchButton;
    private JButton AuthorSearchButton;
    private JButton FieldSearchButton;
    private JTextField titleTextField;// textfield instance variable
    private JTextField languageTextField;// textfield instance variable
    private JTextField editionTextField;// textfield instance variable
    private JTextField locationTextField;// textfield instance variable
    private JTextField onlineTextField;// textfield instance variable
    private JTextField yearTextField;// textfield instance variable
    private JTextField pageNumberTextField;// textfield instance variable
    private JTable table1;// table instance variable
    private JTextField publisherTextField;// textfield instance variable
    private JTextField AuthorTextField;// textfield instance variable
    private JTextField ISBNTextField;// textfield instance variable
    private JTextField URLTextField;// textfield instance variable
    private JTextField fieldTextField;// textfield instance variable
    private JButton addNewETextButton; // button instance variable
    private JButton deleteSelectedETextButton; // button instance variable
    private JButton createStickerForEButton; // button instance variable
    private JPanel mainPanel;// panel instance variable
    private JTextField ETextBookTextField;// textfield instance variable

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Publisher","Language",
            "Year","Edition","Page Number",
            "ISBN", "URL","Field"}; // labels for table


    /**
     * ETextBookScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public ETextBookScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("E-Text Book Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        this.allItems = allItems; // assign allItems

        ArrayList<ETextBook> ETextBookArrayList = createETextBookClassArrayList(allItems); // create ArrayList
        String[][] ETextBookArray = getETextBookArray(ETextBookArrayList); // create Array
        MainPage.createTable(table1,ETextBookArray,labelOfTable); // create Table

        ImageIcon searchAuthorIcon = new ImageIcon("search.png"); // upload search icon
        Image searchAuthorIconScale = searchAuthorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchAuthorIconScale= new ImageIcon(searchAuthorIconScale); // create icon
        AuthorSearchButton.setIcon(scaledSearchAuthorIconScale); // display search icon
        titleSearchButton.setIcon(scaledSearchAuthorIconScale); // display search icon
        FieldSearchButton.setIcon(scaledSearchAuthorIconScale); // display search icon

        addNewETextButton.addActionListener(new ActionListener() {
            /**
             * Add New ETextBook Button
             * When the add new ETextBook button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddETextBookButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForETextBook = new MainPage();
                int lastID = itScreenForETextBook.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddETextBookButton(modelTable, lastID);
            }
        });

        deleteSelectedETextButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected ETextBook Button
             * When the delete selected ETextBook button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteETextBookButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForETextBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForETextBook.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteETextBookButton(modelTable, allItems);
            }
        });

        createStickerForEButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected ETextBook Button
             * When the create sticker for selected ETextBook button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForETextBook = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForETextBook.getItemsArrayList();

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
             * Search Title in ETextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenETextBook= new SearchScreen(searchedTitle,ETextBookArray,labelOfTable,1,14);
                searchScreenETextBook.setVisible(true);

            }
        });
        AuthorSearchButton.addActionListener(new ActionListener() {
            /**
             * Search Author in ETextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenETextBook = new SearchScreen(searchedAuthor,ETextBookArray,labelOfTable,5,14);
                searchScreenETextBook.setVisible(true);
            }
        });
        FieldSearchButton.addActionListener(new ActionListener() {
            /**
             * Search Field in ETextBook
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedField = searchFieldTextField.getText();
                SearchScreen searchScreenETextBook = new SearchScreen(searchedField,ETextBookArray,labelOfTable,13,14);
                searchScreenETextBook.setVisible(true);
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
     * Delete ETextBook Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteETextBookButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
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
            String newETextBookText = "";

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

            MainPage.writeToTxt(newETextBookText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }

    /**
     * Add ETextBook Function
     * This function add new ETextBook by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddETextBookButton(DefaultTableModel modelTable,  int lastID){

        if( !(MainPage.isNumeric(editionTextField.getText(),mainPanel))
                || !(MainPage.isNumeric(yearTextField.getText(),mainPanel))
                || !(MainPage.isNumeric(pageNumberTextField.getText(),mainPanel)) ) {
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

        MainPage.writeToTxt(newETextBookText,"items.txt",true);

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

    /**
     * Create ETextBook Array List Function
     * This function creates ETextBook Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return ETextBook ArrayList.
     */
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

    /**
     * Get ETextBook Array Function
     * This function crates ETextBook array to display items in table
     * @param ETextBookArrayList is the ArrayList contains only ETextBook
     * @return Digital nested array.
     */
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
        return ETextBookArray;
    }
}
