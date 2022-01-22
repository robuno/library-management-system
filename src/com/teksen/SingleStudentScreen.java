package com.teksen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> SingleStudentScreen Class </h1>
 * The SingleStudentScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, buttons, table. This class creates a functionality for
 * form of the screen.
 *
 * In this screen user can display his/her issued items, ID and all items
 * in the library.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */

public class SingleStudentScreen extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JButton showAllItemsInButton; // button instance variable
    private JButton backToTheLoginButton; // button instance variable
    private JLabel labelStudentID; // label i.v
    private JTable table1Student; // table instance variable
    private String studentIDSearched;
    static String[] labelOfTable = new String[] {"Item ID","Title","Location","Item Type","Status"};// labels for table


    /**
     * SingleStudentScreen Constructor
     * This constructor adds the components of form to the frame.
     * It assigns student's ID and displays it.
     * Shows issued items for user logged in.
     * @param studentIDSearched is the user's student ID.
     */
    public SingleStudentScreen(String studentIDSearched) {
        add(mainPanel);// add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Student Screen");// set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner
        this.studentIDSearched = studentIDSearched; // assing student's ID
        labelStudentID.setText("You logged with your student ID: "+ studentIDSearched); // show student's ID

        IssueAnItemScreen issueScreen = new IssueAnItemScreen();
        ArrayList<ArrayList<String>> issuedArrayList = issueScreen.getIssueArrayList(); // get all issued items
        String[][] issuedItemArray = issueScreen.getIssuedItemstArray(issuedArrayList); // create array with issued items
        String[][] issuedStudentItemArray = getStudentIssuedItemsArray(issuedItemArray, studentIDSearched); // create issued items for single student
        MainPage.createTable(table1Student,issuedStudentItemArray,labelOfTable); // create table for issued items

        showAllItemsInButton.addActionListener(new ActionListener() {
            /**
             * Show All Items Button
             * This function displays all items in library. It creates mainpage
             * object to create arraylists for all classes
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage theMPage = new MainPage();
                ArrayList<ArrayList<String>> allItems = theMPage.getItemsArrayList();
                ReadingBookScreen rdScreen2 = new ReadingBookScreen(allItems);
                DigitalScreen digiScreen2 = new DigitalScreen(allItems);
                EJournalScreen eJScreen2 = new EJournalScreen(allItems);
                EReadingBookScreen eRScreen2 = new EReadingBookScreen(allItems);
                ETextBookScreen eTextScreen2 = new ETextBookScreen(allItems);
                ArticleScreen artScreen2 = new ArticleScreen(allItems);
                TextBookScreen txtBkScreen2 = new TextBookScreen(allItems);

                ArrayList<Article> articleArrayList = artScreen2.createArticleClassArrayList(allItems);
                ArrayList<Digital> digitalArrayList = digiScreen2.createDigitalClassArrayList(allItems);
                ArrayList<ReadingBook> readingBookArrayList = rdScreen2.createReadingBookClassArrayList(allItems);
                ArrayList<EJournal> eJournalArrayList = eJScreen2.createEJournalClassArrayList(allItems);
                ArrayList<EReadingBook> eReadingBookArrayList = eRScreen2.createEReadingBookClassArrayList(allItems);
                ArrayList<ETextBook> eTextBookArrayList = eTextScreen2.createETextBookClassArrayList(allItems);
                ArrayList<TextBook> textBookArrayList = txtBkScreen2.createTextBookClassArrayList(allItems);

                ItemsScreen itemsScreen = new ItemsScreen( digitalArrayList, readingBookArrayList,
                        eJournalArrayList,eReadingBookArrayList,
                        eTextBookArrayList,articleArrayList,
                        textBookArrayList);
                itemsScreen.setVisible(true);
            }

        });
        backToTheLoginButton.addActionListener(new ActionListener() {
            /**
             * Back To The Login Page Button
             * This function lets user exit from the student window and
             * return to the login page.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage logPage = new LoginPage();
                logPage.setVisible(true); // open login page
                dispose(); // close student screen
            }
        });
    }

    /**
     * Get Student's Issued Items Array
     * This function filters all issued items to display issued items of only student whose account
     * is opened.
     * @param issuedItemArray is the list of issued items
     * @param studentIDSearched is the student ID to check he/she has issued items or not
     * @return list of issued items for given student
     */
    public String[][] getStudentIssuedItemsArray(String[][] issuedItemArray,String studentIDSearched ) {
        String[][] issuedStudentItemsArray = new String[issuedItemArray.length][5];
        int counterIssuedItemsForStudent =0; // counter for new array length

        for(int i=0; i< issuedItemArray.length;i++) {
            if (issuedItemArray[i][5].equals(studentIDSearched)) { // if student id exists in issued items list
                for(int j=0; j< 5; j++) {
                    issuedStudentItemsArray[i][j] = issuedItemArray[i][j];
                }
                counterIssuedItemsForStudent +=1;
            }
        }
        String[][] newIssuedStudentItemsArray = new String[counterIssuedItemsForStudent][5]; // create new array for issued items for student
        int counterForNewArray=0;
        for(int i=0; i<issuedItemArray.length;i++) {
            if(issuedStudentItemsArray[i][0] !=null) { // if there is an issued item for that student
                for(int j=0; j <5;j++) {
                    newIssuedStudentItemsArray[counterForNewArray][j] = issuedStudentItemsArray[i][j]; // assign this item to the new array
                }
                counterForNewArray+=1;
            }
        }
        return newIssuedStudentItemsArray;
    }


}
