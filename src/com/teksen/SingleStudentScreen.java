package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class SingleStudentScreen extends JFrame{
    private JPanel mainPanel;
    private JButton showAllItemsInButton;
    private JLabel labelStudentID;
    private JTable table1Student;
    private String studentIDSearched;
    static String[] labelOfTable = new String[] {"Item ID","Title","Location","Item Type","Status"};


    public SingleStudentScreen(String studentIDSearched) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Student Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.studentIDSearched = studentIDSearched;
        labelStudentID.setText("You logged with your student ID: "+ studentIDSearched);

        IssueAnItemScreen issueScreen = new IssueAnItemScreen();
        ArrayList<ArrayList<String>> issuedArrayList = issueScreen.getIssueArrayList();
        String[][] issuedItemArray = issueScreen.getIssuedItemstArray(issuedArrayList);
        String[][] issuedStudentItemArray = getStudentIssuedItemsArray(issuedItemArray, studentIDSearched);
        mainPage.createTable(table1Student,issuedStudentItemArray,labelOfTable);

        showAllItemsInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage theMPage = new mainPage();
                ArrayList<ArrayList<String>> allItems = theMPage.getItemsArrayList();
                ReadingBookScreen rdScreen2 = new ReadingBookScreen(allItems);
                DigitalScreen digiScreen2 = new DigitalScreen(allItems);
                EJournalScreen eJScreen2 = new EJournalScreen(allItems);
                EReadingBookScreen eRScreen2 = new EReadingBookScreen(allItems);
                ETextBookScreen eTextScreen2 = new ETextBookScreen(allItems);
                ArticleScreen artScreen2 = new ArticleScreen(allItems);
                TextBookScreen txtBkScreen2 = new TextBookScreen(allItems);


                ArrayList<Digital> digitalArrayList = digiScreen2.createDigitalClassArrayList(allItems);
                ArrayList<ReadingBook> readingBookArrayList = rdScreen2.createReadingBookClassArrayList(allItems);
                ArrayList<EJournal> eJournalArrayList = eJScreen2.createEJournalClassArrayList(allItems);
                ArrayList<EReadingBook> eReadingBookArrayList = eRScreen2.createEReadingBookClassArrayList(allItems);
                ArrayList<ETextBook> eTextBookArrayList = eTextScreen2.createETextBookClassArrayList(allItems);
                ArrayList<Article> articleArrayList = artScreen2.createArticleClassArrayList(allItems);
                ArrayList<TextBook> textBookArrayList = txtBkScreen2.createTextBookClassArrayList(allItems);

                ItemsScreen itemsScreen = new ItemsScreen( digitalArrayList, readingBookArrayList,
                        eJournalArrayList,eReadingBookArrayList,
                        eTextBookArrayList,articleArrayList,
                        textBookArrayList);
                itemsScreen.setVisible(true);
            }
        });
    }
    public String[][] getStudentIssuedItemsArray(String[][] issuedItemArray,String studentIDSearched ) {
        String[][] issuedStudentItemsArray = new String[issuedItemArray.length][5];
        int counterIssuedItemsForStudent =0;

        for(int i=0; i< issuedItemArray.length;i++) {
            if (issuedItemArray[i][5].equals(studentIDSearched)) {
                for(int j=0; j< 5; j++) {
                    issuedStudentItemsArray[i][j] = issuedItemArray[i][j];
                }
                counterIssuedItemsForStudent +=1;
            }
        }
        String[][] newIssuedStudentItemsArray = new String[counterIssuedItemsForStudent][5];
        int counterForNewArray=0;
        for(int i=0; i<issuedItemArray.length;i++) {
            if(issuedStudentItemsArray[i][0] !=null) {
                for(int j=0; j <5;j++) {
                    newIssuedStudentItemsArray[counterForNewArray][j] = issuedStudentItemsArray[i][j];
                }
                counterForNewArray+=1;
            }
        }
        return newIssuedStudentItemsArray;
    }


}
