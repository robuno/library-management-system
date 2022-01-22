package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> DigitalScreen Class </h1>
 * The DigitalScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for Digital class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class DigitalScreen extends JFrame implements Sticker{
    private JPanel mainPanel; // panel instance variable
    private JTable table1; // table instance variable
    private JTextField titleTextField; // textfield instance variable
    private JTextField locationTextField; // textfield instance variable
    private JTextField digitalTextField; // textfield instance variable
    private JTextField directionTextField; // textfield instance variable
    private JTextField companyTextField; // textfield instance variable
    private JTextField topicTextField; // textfield instance variable
    private JTextField languageTextField; // textfield instance variable
    private JTextField physicalPropTextField; // textfield instance variable
    private JTextField yearTextField; // textfield instance variable
    private JTextField timeTextField; // textfield instance variable
    private JTextField isbnTextField; // textfield instance variable
    private JTextField statusTextField; // textfield instance variable
    private JButton deleteSelectedDigitalItemButton; // button instance variable
    private JButton addNewDigitalItemButton; // button instance variable
    private JButton createStickerForSelectedButton; // button instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JTextField searchDirectorTextField; // textfield instance variable
    private JButton buttonSearchTitle; // button instance variable
    private JButton buttonSearchDirector; // button instance variable
    private JPanel searchPanel; // panel instance variable
    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Director","Company","Topic","Language","Physical Property",
            "ISBN","Time","Year"};// labels for table


    /**
     * DigitalScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public DigitalScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Digital Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        this.allItems = allItems; // assign allItems

        ArrayList<Digital> digitalArrayList = createDigitalClassArrayList(allItems); // create Digital ArrayList
        String[][] digitalArray = getDigitalArray(digitalArrayList); // create Digital Array
        MainPage.createTable(table1,digitalArray,labelOfTable); // create table

        ImageIcon searchDirectorIcon = new ImageIcon("search.png"); // upload search icon
        Image searchDirectorIconScale = searchDirectorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchDirectorIconScale= new ImageIcon(searchDirectorIconScale);// create icon
        buttonSearchDirector.setIcon(scaledSearchDirectorIconScale); // display search icon
        buttonSearchTitle.setIcon(scaledSearchDirectorIconScale);// display search icon


        addNewDigitalItemButton.addActionListener(new ActionListener() {
            /**
             * Add New Digital Button
             * When the add new digital button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddDigitalButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                int lastID = itScreenForDigital.getLastItemID("items.txt",0);
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddDigitalButton(modelTable, lastID);
            }
        });

        deleteSelectedDigitalItemButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected Digital Button
             * When the delete selected digital button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteDigitalButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteDigitalButton(modelTable, allItems);

            }
        });

        createStickerForSelectedButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected Digital Button
             * When the create sticker for selected digital button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForDigital.getItemsArrayList();
                ArrayList<Digital> digitalArrayList = createDigitalClassArrayList(allItems);

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
             * Search Title in Digital
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,digitalArray,labelOfTable,1,13);
                searchScreenDigital.setVisible(true);
            }
        });

        buttonSearchDirector.addActionListener(new ActionListener() {
            /**
             * Search Director in Digital
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedDirector = searchDirectorTextField.getText();
                SearchScreen searchScreenDigital = new SearchScreen(searchedDirector,digitalArray,labelOfTable, 5,13);
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
                    //System.out.println(allItems.get(i).get(1)+"-"+selectedRowID);
                    stickerText = String.format(
                            "%-29s%-35s\n%-29s%-35s\n%-25s%-35s\n%-27s%-35s\n%-26s%-35s\n"+
                                    "%-26s%-35s\n%-22s%-35s\n%-27s%-35s\n%-23s%-35s\n%-20s%-35s\n"+
                                    "%-26s%-35s\n%-26s%-35s\n%-26s%-35s"
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
     * Delete Digital Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteDigitalButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));
            int rowWillBeDeleted=0;
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(0).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) {
                        //System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            itemsArrayList.remove(rowWillBeDeleted);
            String newBookText = "";

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

    /**
     * Add Digital Function
     * This function add new digital by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddDigitalButton(DefaultTableModel modelTable,  int lastID){
        if( !(MainPage.isNumeric(timeTextField.getText(),mainPanel)) || !(MainPage.isNumeric(yearTextField.getText(),mainPanel)) ) {
            return;
        }

        String newBookText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                digitalTextField.getText()+","+statusTextField.getText()+","+
                directionTextField.getText() +","+ companyTextField.getText()+","+
                topicTextField.getText()+","+ languageTextField.getText()+","+
                physicalPropTextField.getText()+","+isbnTextField.getText()+","+
                timeTextField.getText()+","+ yearTextField.getText();

        MainPage.writeToTxt(newBookText,"items.txt",true);

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


    /**
     * Create Digital Array List Function
     * This function creates Digital Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return Digital ArrayList.
     */
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
        return digitalClassArray;
    }
    /**
     * Get Digital Array Function
     * This function crates Article array to display items in table
     * @param digitalArrayList is the ArrayList contains only Digitals
     * @return Digital nested array.
     */
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
        return digitalArray;
    }
}
