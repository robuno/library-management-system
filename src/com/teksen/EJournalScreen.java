package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> EJournalScreen Class </h1>
 * The EJournalScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for EJournalScreen class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class EJournalScreen extends JFrame implements Sticker{
    private JTextField titleTextField; // textfield instance variable
    private JTextField locationTextField; // textfield instance variable
    private JTextField itemTypeTextField; // textfield instance variable
    private JTextField publisherTextField; // textfield instance variable
    private JTextField statusTextField; // textfield instance variable
    private JTextField ISSNTextField; // textfield instance variable
    private JTextField URLTextField; // textfield instance variable
    private JTextField topicTextField; // textfield instance variable
    private JTextField peerReviewedTextField; // textfield instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JTextField searchTopicTextField; // textfield instance variable
    private JButton searchTitleButton; // button instance variable
    private JButton searchTopicButton; // button instance variable
    private JTable table1; // table instance variable
    private JButton addNewEJournalButton; // button instance variable
    private JButton deleteSelectedEJournalButton; // button instance variable
    private JButton createStickerForSelectedButton; // button instance variable
    private JPanel mainPanel; // panel instance variable
    private JRadioButton peerReviewedRadioButton; // button instance variable
    private JRadioButton notPeerReviewedRadioButton; // button instance variable
    String peerReviewedType;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Publisher","ISSN","Topic","URL","Peer Reviewed"};// labels for table


    /**
     * EJournalScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public EJournalScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("E-Journal Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner

        ButtonGroup group = new ButtonGroup(); // make group buttons
        group.add(peerReviewedRadioButton);
        group.add(notPeerReviewedRadioButton);

        this.allItems = allItems; // assign items list

        ArrayList<EJournal> EJournalArrayList = createEJournalClassArrayList(allItems); // create EJournal ArrayList
        String[][] EJournalArray = getEJournalArray(EJournalArrayList);// create EJournal Array
        MainPage.createTable(table1,EJournalArray,labelOfTable); // create table

        ImageIcon searchTopicIcon = new ImageIcon("search.png");// upload search icon
        Image searchTopicIconScale = searchTopicIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchTopicIconScale= new ImageIcon(searchTopicIconScale);// create icon
        searchTopicButton.setIcon(scaledSearchTopicIconScale); // display search icon
        searchTitleButton.setIcon(scaledSearchTopicIconScale); // display search icon
        

        addNewEJournalButton.addActionListener(new ActionListener() {
            /**
             * Add New EJorunal Button
             * When the add new eJournal button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call pressAddEJournalButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEJournal = new MainPage();
                int lastID = itScreenForEJournal.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddEJournalButton(modelTable, lastID);
            }
        });

        deleteSelectedEJournalButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected EJorunal Button
             * When the delete selected eJournal button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteEJournalButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEJournal = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEJournal.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteEJournalButton(modelTable, allItems);
            }
        });


        createStickerForSelectedButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected EJorunal Button
             * When the create sticker for selected EJorunal button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForEJournal = new MainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEJournal.getItemsArrayList();
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
             * Search Title in EJorunal
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenEJournal = new SearchScreen(searchedTitle,EJournalArray,labelOfTable,1,10);
                searchScreenEJournal.setVisible(true);
            }
        });

        searchTopicButton.addActionListener(new ActionListener() {
            /**
             * Search Director in EJorunal
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTopic = searchTopicTextField.getText();
                SearchScreen searchScreenEJournal = new SearchScreen(searchedTopic,EJournalArray,labelOfTable,7,10);
                searchScreenEJournal.setVisible(true);
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
                            "%-29s%-35s\n%-28s%-35s\n%-24s%-35s\n%-26s%-35s\n%-25s%-35s\n"+
                                    "%-23s%-35s\n%-25s%-35s\n%-25s%-35s\n%-25s%-35s\n%-18s%-35s\n"
                            ,
                            "ID:",allItems.get(i).get(0),
                            "Title:",allItems.get(i).get(1),
                            "Location:",allItems.get(i).get(2),
                            "Type:",allItems.get(i).get(3),
                            "Status:",allItems.get(i).get(4),

                            "Publisher:",allItems.get(i).get(5),
                            "ISSN:",allItems.get(i).get(6),
                            "Topic:",allItems.get(i).get(7),
                            "URL:",allItems.get(i).get(8),
                            "Peer Reviewed:",allItems.get(i).get(9)
                    );
                }
            }
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
     * Delete EJorunal Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteEJournalButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
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
            String newEJournalText = "";

            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newEJournalText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newEJournalText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newEJournalText += "\n";
                }
            }

            MainPage.writeToTxt(newEJournalText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }

    /**
     * Add EJorunal Function
     * This function add new EJorunal by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddEJournalButton(DefaultTableModel modelTable,  int lastID){
        if(peerReviewedRadioButton.isSelected()) { // if journal is peer reviewed
            peerReviewedType = "true";
        }
        else if(notPeerReviewedRadioButton.isSelected()) { // journal is not peer reviewed
            peerReviewedType = "false";
        }
        else {
            JOptionPane.showMessageDialog(mainPanel,
                    "Please select a peer reviewed type!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newEJournalText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                itemTypeTextField.getText()+","+statusTextField.getText()+","+
                publisherTextField.getText() +","+ ISSNTextField.getText()+","+
                topicTextField.getText()+","+ URLTextField.getText()+","+
                peerReviewedType;

        MainPage.writeToTxt(newEJournalText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                itemTypeTextField.getText(),
                statusTextField.getText(),
                publisherTextField.getText(),
                ISSNTextField.getText(),
                topicTextField.getText(),
                URLTextField.getText(),
                peerReviewedType,
        });
    }

    /**
     * Create EJorunal Array List Function
     * This function creates EJorunal Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return Article ArrayList.
     */
    public ArrayList<EJournal> createEJournalClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<EJournal> EJournalClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("EJournal")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                EJournal aNewEjournal = new EJournal(
                        Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                        itemsArrayList.get(i).get(3), // String itemType
                        itemsArrayList.get(i).get(1), // String title
                        itemsArrayList.get(i).get(2), // String locationInformation
                        itemsArrayList.get(i).get(4), // String status
                        itemsArrayList.get(i).get(5),
                        itemsArrayList.get(i).get(6),
                        itemsArrayList.get(i).get(7),
                        itemsArrayList.get(i).get(8),
                        Boolean.parseBoolean(itemsArrayList.get(i).get(9))
                );
                EJournalClassArray.add(aNewEjournal);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return EJournalClassArray;
    }

    /**
     * Get EJorunal Array Function
     * This function crates Article array to display items in table
     * @param EJournalArrayList is the ArrayList contains only EJorunal
     * @return Digital nested array.
     */
    public String[][] getEJournalArray(ArrayList<EJournal> EJournalArrayList) {
        String[][] EJournalArray = new String[EJournalArrayList.size()][10];
        for(int i=0; i < EJournalArrayList.size();i++) {
            EJournalArray[i][0] = String.valueOf(EJournalArrayList.get(i).getId());
            EJournalArray[i][1] = EJournalArrayList.get(i).getTitle();
            EJournalArray[i][2] = EJournalArrayList.get(i).getLocationInformation();
            EJournalArray[i][3] = EJournalArrayList.get(i).getItemType();
            EJournalArray[i][4] = EJournalArrayList.get(i).getStatus();
            EJournalArray[i][5] = EJournalArrayList.get(i).getPublisher();
            EJournalArray[i][6] = EJournalArrayList.get(i).getISSN();
            EJournalArray[i][7] = EJournalArrayList.get(i).getTopic();
            EJournalArray[i][8] = EJournalArrayList.get(i).getUrl();
            EJournalArray[i][9] = String.valueOf(EJournalArrayList.get(i).isPeerReviewed());
        }
        return EJournalArray;
    }
}
