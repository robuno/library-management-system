package com.teksen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h1> IssueAnItemScreen Class </h1>
 * The IssueAnItemScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has a textfield to get the item's ID that
 * will be issued and student's ID who will get that item. Also, it has
 * a button to complete the operation.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */

public class IssueAnItemScreen extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JTextField studentIDTextField; // textfield instance variable
    private JTextField itemIDTextField; // textfield instance variable
    private JButton issueTheItemButton; // button instance variable
    private JTable table1; // table instance variable
    static String[] labelOfTable = new String[]
            {"Item ID","Title","Location","Item Type",
                    "Status","Student ID","Issue Date"};  // labels for table

    /**
     * IssueAnItemScreen  Constructor
     * This constructor adds the components of form to the frame.
     */
    public IssueAnItemScreen() {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Issue Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner
    }

    /**
     * IssueAnItemScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listener for issue item button.
     * @param personArrayList is the all users in database
     * @param allItems is the all items in database
     */
    public IssueAnItemScreen(ArrayList<ArrayList<String>> personArrayList ,ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Issue Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        ArrayList<ArrayList<String>> issuedArrayList = getIssueArrayList(); // get issued items from database in arraylist
        String[][] issuedItemArray = getIssuedItemstArray(issuedArrayList); // create issued items in array
        MainPage.createTable(table1,issuedItemArray,labelOfTable); // create table in screen

        // create listener for issue button
        issueTheItemButton.addActionListener(new ActionListener() {
            /**
             * Issue Item Button
             * This function issues item and writes this item and student who gets this item in
             * issue database. It checks whether the user exists in database, item is available to issue.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedStudentID = studentIDTextField.getText(); // value of student ID to be searched
                String searchedItemID = itemIDTextField.getText(); // value of item ID to be searched

                // check whether user input numeric or not
                if( !(MainPage.isNumeric(searchedStudentID,mainPanel)) || !(MainPage.isNumeric(searchedItemID,mainPanel))) {
                    return;
                }

                // check whether the item is available in library
                try {
                    ArrayList<ArrayList<String>> newIssue2= getNewIssuedItem( allItems, searchedItemID); // try to item get from the list
                } catch(IndexOutOfBoundsException ine) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "There is no item with this id in the library!",
                            "Issue-Return Message",
                            JOptionPane.INFORMATION_MESSAGE); // show message
                    return;
                }

                ArrayList<ArrayList<String>> issueArrayList = getIssueArrayList();
                //System.out.println(issueArrayList);

                // check whether the item is already issued or not
                int availableFlag =1; // item is available
                for(int i=0; i< issueArrayList.size();i++) {
                    if(issueArrayList.get(i).get(0).equals(searchedItemID) ) { // if item is in issued database
                        if(!issueArrayList.get(i).get(3).equals("EReadingBook") &&
                                !issueArrayList.get(i).get(3).equals("ETextBook") &&
                                !issueArrayList.get(i).get(3).equals("Article") &&
                                !issueArrayList.get(i).get(3).equals("EJournal")
                        ) {
                            JOptionPane.showMessageDialog(mainPanel,
                                    "Item is not available to issue!",
                                    "Issue-Return Message",
                                    JOptionPane.INFORMATION_MESSAGE); // show message
                            availableFlag =0; // item is not available
                        }


                    }
                }

                int availableFlag2 =1; // student exists

                // check whether the student exists in the database or not
                for(int i=0; i< personArrayList.size();i++) {
                    if(personArrayList.get(i).get(7).equals(searchedStudentID) && personArrayList.get(i).get(6).equals("Student")) {
                        availableFlag2 =1; // student exists
                        break;
                    }
                    else {
                        availableFlag2 =0; // student doesnt exists
                        if( i == personArrayList.size()-1) {
                            //System.out.println("-"+personArrayList.get(i).get(7)+"\n-"+searchedStudentID);
                            JOptionPane.showMessageDialog(mainPanel,
                                    "There is no student with given student number!",
                                    "Issue-Return Message",
                                    JOptionPane.INFORMATION_MESSAGE); // show message
                        }
                    }
                }

                // if student exists and item is available to issue
                if(availableFlag == 1 && availableFlag2 ==1) {
                    ArrayList<ArrayList<String>> newIssue= getNewIssuedItem( allItems, searchedItemID);
                    if (newIssue.get(0).get(3).equals("ReadingBook") ||
                        newIssue.get(0).get(3).equals("TextBook") ||
                        newIssue.get(0).get(3).equals("Digital"))
                    {
                        newIssue.get(0).set(4,"Not Available"); // change the status of items if items are given in condition
                    }

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // create data format
                    LocalDateTime now = LocalDateTime.now(); // create local time for issue date

                    if(!newIssue.get(0).get(3).equals("EReadingBook") &&
                            !newIssue.get(0).get(3).equals("ETextBook") &&
                            !newIssue.get(0).get(3).equals("Article") &&
                            !newIssue.get(0).get(3).equals("EJournal")
                    ){
                        addIssuedItemToItemsTxt(newIssue);
                    }

                    for(int i=0; i<newIssue.size();i++) {
                        if(newIssue.get(i).get(0).equals(searchedItemID)) {
                            newIssue.get(i).add(searchedStudentID); // add student's ID for issue list in database
                            newIssue.get(i).add(dtf.format(now)); // add local time for issue list in database
                        }
                    }

                    String newBookText = "";
                    for(int i=0; i< newIssue.size(); i++) {
                        for(int j=0; j<newIssue.get(i).size();j++) {
                            newBookText += newIssue.get(i).get(j);
                            if(!newIssue.get(i).get(j).equals(newIssue.get(i).get(newIssue.get(i).size() -1))) {
                                newBookText += ","; // single item's values and separate them with commas for database writing op.
                            }
                        }
                    }
                    newBookText +="\n"; // create a new future entry for database

                    MainPage.writeToTxt(newBookText,"issue.txt",true); // add issue list to the database

                    ArrayList<ArrayList<String>> issuedArrayList = getIssueArrayList(); // create issued items arraylist
                    DefaultTableModel modelTable =(DefaultTableModel)table1.getModel(); // create model table for table
                    //System.out.println(issuedArrayList.get(issuedArrayList.size()-1).get(0));

                    modelTable.addRow(new Object[] {
                            issuedArrayList.get(issuedArrayList.size()-1).get(0),
                            issuedArrayList.get(issuedArrayList.size()-1).get(1),
                            issuedArrayList.get(issuedArrayList.size()-1).get(2),
                            issuedArrayList.get(issuedArrayList.size()-1).get(3),
                            issuedArrayList.get(issuedArrayList.size()-1).get(4),
                            searchedStudentID, // add ID of student who gets the item
                            dtf.format(now) // add issue date
                    });
                }
            }
        });
    }


    /**
     * Get New Issued Item Function
     * This item gets the item that will be issued from the list which has all items in database.
     * It determines the item will be issued in this array and gets all variables for this item.
     * @param allItems is the all items in database
     * @param searchedItemID ID of the item to be searched
     * @return single item's values in String ArrayList
     */
    public ArrayList<ArrayList<String>> getNewIssuedItem(ArrayList<ArrayList<String>> allItems,String searchedItemID) {
        ArrayList<ArrayList<String>> newIssue = new ArrayList<>(); // single item's values ArrayList
        for(int i=0; i<allItems.size();i++) { // check in the all items
            if(allItems.get(i).get(0).equals(searchedItemID)) {
                newIssue.add(allItems.get(i)); // add whole line to the arrayList
            }
        }
        return newIssue;
    }

    /**
     * Add Issued Items To Items Txt
     * This function adds the item which is issued recently to the issued items list in database
     * @param newIssue is the item that is issued recently in ArrayList
     * @return nothing.
     */
    public void addIssuedItemToItemsTxt(ArrayList<ArrayList<String>> newIssue) {
        MainPage mPage = new MainPage();
        ArrayList<ArrayList<String>> allItems = mPage.getItemsArrayList(); // create array list for all items in database
        String idToChange = newIssue.get(0).get(0); // get the issued item's ID
        int rowWillBeDeleted =0;
        for(int i=0; i< allItems.size();i++) {
            if(allItems.get(i).get(0).equals(idToChange)) { // match the issued item's ID in database
                rowWillBeDeleted =i;
            }
        }

        allItems.remove(rowWillBeDeleted);
        allItems.add(rowWillBeDeleted,newIssue.get(0)); // change the status of the item in items.txt DB

        String newBookText ="";
        for(int i=0; i< allItems.size(); i++) {
            for(int j=0; j<allItems.get(i).size();j++) {
                newBookText += allItems.get(i).get(j);
                if(!allItems.get(i).get(j).equals(allItems.get(i).get(allItems.get(i).size() -1))) {
                    newBookText += ","; // single item's values and separate them with commas for database writing op.
                }
            }
            if((i+1) != allItems.size()) {
                newBookText += "\n";  //create a new future entry for database
            }
        }
        MainPage.writeToTxt(newBookText,"items.txt",false); // update items.txt

    }


    /**
     * Get Issue ArrayList
     * This function creates an ArrayList for issued items.
     * @return issued items String nested ArrayList.
     */
    public ArrayList<ArrayList<String>> getIssueArrayList() {
        ArrayList<ArrayList<String>> issueArrayList = new ArrayList<>();
        try {
            File myObj = new File("issue.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) { // read all lines in txt
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);
                ArrayList<String> anIssue = new ArrayList<String>();

                for(int i=0; i < singleItem.length;i++) {
                    anIssue.add(singleItem[i]);
                }
                issueArrayList.add(anIssue);
                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return issueArrayList;
    }


    /**
     * Get Issued Items
     * This function converts the issued items ArrayList to fixed Array.
     * This conversion is required to list items in the table
     * @param issueArrayList is the issued items ArrayList
     * @return issued items array
     */
    public String[][] getIssuedItemstArray(ArrayList<ArrayList<String>> issueArrayList) {
        String[][] issueArray = new String[issueArrayList.size()][7]; // create fixed size array
        for(int i=0; i < issueArrayList.size();i++) {
            issueArray[i][0] = String.valueOf(issueArrayList.get(i).get(0));
            issueArray[i][1] = String.valueOf(issueArrayList.get(i).get(1));
            issueArray[i][2] = String.valueOf(issueArrayList.get(i).get(2));
            issueArray[i][3] = String.valueOf(issueArrayList.get(i).get(3));
            issueArray[i][4] = String.valueOf(issueArrayList.get(i).get(4));
            issueArray[i][5] = String.valueOf(issueArrayList.get(i).get(issueArrayList.get(i).size()-2));
            issueArray[i][6] = String.valueOf(issueArrayList.get(i).get(issueArrayList.get(i).size()-1));
        }
        return issueArray;
    }

}
