package com.teksen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> ReturnItemScreen Class </h1>
 * The ReturnItemScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 *
 * In this screen, program has a textfield to get the item's ID that
 * will be. Also, it has a button to complete the operation.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class ReturnItemScreen extends JFrame {
    private JPanel mainPanel; // panel instance variable
    private JTextField itemIDTextField;// textfield instance variable
    private JButton returnTheItemButton; // button instance variable


    /**
     * ReturnItemScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listener for return item button.
     * @param allItems is the all items in database
     */
    public ReturnItemScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);// add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Return Item Screen");// set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner

        // set button and field margin
        itemIDTextField.setMargin(new Insets(8, 8, 8, 8));
        returnTheItemButton.setPreferredSize(new Dimension(40, 40));

        returnTheItemButton.addActionListener(new ActionListener() {
            /**
             * Return Item Button
             * This function returns item to the library. It checks the item is searched, in issue list.
             * If it is, it removes from that list and change the status of this item in items list too.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedItemID = itemIDTextField.getText(); // get item id

                if( !(MainPage.isNumeric(searchedItemID,mainPanel)) ) { // if input is not numeric
                    return;
                }

                ArrayList<ArrayList<String>> issueArrayList = getIssueArrayList(); // get issue list
                ArrayList<ArrayList<String>> newReturn = new ArrayList<>();

                int rowWillBeSelected =0;
                int availableFlag=1; // 0:  issued, 1: not issued
                //System.out.print(issueArrayList);
                for(int j=0; j < issueArrayList.size(); j++) {
                    //System.out.println(issueArrayList.get(j).get(0)+"-"+searchedItemID);
                    if(issueArrayList.get(j).get(0).equals(searchedItemID)) {
                        availableFlag =0;
                        rowWillBeSelected = j;
                        break;
                    }
                }

                if(availableFlag==1) { // if item is already in library, not issued
                    JOptionPane.showMessageDialog(mainPanel,
                            "Item is not issued! You cannot return! Please check the library!",
                            "Issue-Return Message",
                            JOptionPane.ERROR_MESSAGE);
                } else{ // if item is issued

                    issueArrayList.remove(rowWillBeSelected); // remove from issue list
                    String newIssueText ="";
                    for(int i=0; i< issueArrayList.size(); i++) {
                        for(int j=0; j<issueArrayList.get(i).size();j++) {
                            newIssueText += issueArrayList.get(i).get(j);
                            if(!issueArrayList.get(i).get(j).equals(issueArrayList.get(i).get(issueArrayList.get(i).size() -1))) {
                                newIssueText += ",";
                            }
                        }
                        newIssueText += "\n";
                    }

                    MainPage.writeToTxt(newIssueText,"issue.txt",false); // update issue list

                    int rowItemWillBeDeleted=0;
                    for(int j=0; j < allItems.size(); j++) {
                        //System.out.println(issueArrayList.get(j).get(0)+"-"+searchedItemID);
                        if(allItems.get(j).get(0).equals(searchedItemID)) { // determine the item in items list
                            availableFlag =0;
                            rowItemWillBeDeleted = j;
                            break;
                        }
                    }
                    allItems.get(rowItemWillBeDeleted).set(4,"Available"); // change the status of issued item to make it available
                    //System.out.println("\nallitems4: "+allItems.get(rowItemWillBeDeleted));

                    String newItemText ="";
                    for(int i=0; i< allItems.size(); i++) {
                        for(int j=0; j<allItems.get(i).size();j++) {
                            newItemText += allItems.get(i).get(j);
                            if(!allItems.get(i).get(j).equals(allItems.get(i).get(allItems.get(i).size() -1))) {
                                newItemText += ","; // get single item's values for data
                            }
                        }
                        if((i+1) != allItems.size()) {
                            newItemText += "\n";
                        }
                    }
                    MainPage.writeToTxt(newItemText,"items.txt",false); // update items list
                    JOptionPane.showMessageDialog(mainPanel,
                            "Item is returned!",
                            "Issue-Return Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
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

            while (myReader.hasNextLine()) {
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return issueArrayList;
    }


}
