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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class IssueAnItemScreen extends JFrame{
    private JPanel mainPanel;
    private JTextField studentIDTextField;
    private JTextField itemIDTextField;
    private JButton issueTheItemButton;
    private JTable table1;
    static String[] labelOfTable = new String[] {"Item ID","Title","Location","Item Type","Status","Student ID","Issue Date"};

    public IssueAnItemScreen() {
        add(mainPanel);
        setSize(1200,600);
        setTitle("Issue Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public IssueAnItemScreen(ArrayList<ArrayList<String>> personArrayList ,ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1200,600);
        setTitle("Issue Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<ArrayList<String>> issuedArrayList = getIssueArrayList();
        String[][] issuedItemArray = getIssuedItemstArray(issuedArrayList);
        mainPage.createTable(table1,issuedItemArray,labelOfTable);



        issueTheItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedStudentID = studentIDTextField.getText();
                String searchedItemID = itemIDTextField.getText();

                if( !(mainPage.isNumeric(searchedStudentID,mainPanel)) || !(mainPage.isNumeric(searchedItemID,mainPanel))) {
                    return;
                }

                try {
                    ArrayList<ArrayList<String>> newIssue2= getNewIssuedItem( allItems, searchedItemID);
                    System.out.println(newIssue2.get(0).get(3));
                } catch(IndexOutOfBoundsException ine) {
                    JOptionPane.showMessageDialog(mainPanel,
                            "There is no book with this id in the library!",
                            "Issue-Return Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("arrayda böyle bi item yok");
                    return;
                }

                ArrayList<ArrayList<String>> issueArrayList = getIssueArrayList();
                //System.out.println(issueArrayList);



                int availableFlag =1;

                for(int i=0; i< issueArrayList.size();i++) {
                    if(issueArrayList.get(i).get(0).equals(searchedItemID) ) {
                        JOptionPane.showMessageDialog(mainPanel,
                                "Item is not available to issue!",
                                "Issue-Return Message",
                                JOptionPane.INFORMATION_MESSAGE);
                        availableFlag =0;

                    }
                }

                int availableFlag2 =1;

                for(int i=0; i< personArrayList.size();i++) {
                    if(personArrayList.get(i).get(7).equals(searchedStudentID) && personArrayList.get(i).get(6).equals("Student")) {
                        availableFlag2 =1;
                        break;
                    }
                    else {
                        availableFlag2 =0;
                        if( i == personArrayList.size()-1) {
                            //System.out.println("-"+personArrayList.get(i).get(7)+"\n-"+searchedStudentID);
                            JOptionPane.showMessageDialog(mainPanel,
                                    "There is no student with given student number!",
                                    "Issue-Return Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

                if(availableFlag == 1 && availableFlag2 ==1) {
                    ArrayList<ArrayList<String>> newIssue= getNewIssuedItem( allItems, searchedItemID);

                    if (newIssue.get(0).get(3).equals("ReadingBook") ||
                        newIssue.get(0).get(3).equals("TextBook") ||
                        newIssue.get(0).get(3).equals("Digital"))
                    {
                        newIssue.get(0).set(4,"Not Available");
                    }


                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    addIssuedItemToItemsTxt(newIssue);
                    for(int i=0; i<newIssue.size();i++) {
                        if(newIssue.get(i).get(0).equals(searchedItemID)) {
                            //System.out.println(issueArrayList);
                            //System.out.println(issueArrayList.get(i));

                            newIssue.get(i).add(searchedStudentID);
                            newIssue.get(i).add(dtf.format(now));
                            // -------TAKVIM BILGISI EKLE newIssue.get(i).add(TAKVIM)

                            //System.out.println(issueArrayList);
                            //System.out.println(issueArrayList.get(i));
                        }
                    }
                    System.out.println("new: "+newIssue);

                    String newBookText = "";
                    for(int i=0; i< newIssue.size(); i++) {
                        for(int j=0; j<newIssue.get(i).size();j++) {
                            newBookText += newIssue.get(i).get(j);
                            if(!newIssue.get(i).get(j).equals(newIssue.get(i).get(newIssue.get(i).size() -1))) {
                                newBookText += ",";
                            }
                        }
                    }
                    newBookText +="\n";



                    mainPage.writeToTxt(newBookText,"issue.txt",true);

                    ArrayList<ArrayList<String>> issuedArrayList = getIssueArrayList();
                    DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                    System.out.println(issuedArrayList.get(issuedArrayList.size()-1).get(0));

                    modelTable.addRow(new Object[] {
                            issuedArrayList.get(issuedArrayList.size()-1).get(0),
                            issuedArrayList.get(issuedArrayList.size()-1).get(1),
                            issuedArrayList.get(issuedArrayList.size()-1).get(2),
                            issuedArrayList.get(issuedArrayList.size()-1).get(3),
                            issuedArrayList.get(issuedArrayList.size()-1).get(4),
                            searchedStudentID,
                            dtf.format(now)
                            // issuedArrayList.get(issuedArrayList.size()-1).get(7) TAJKVIM BILGISI
                    });
                }


            }
        });
    }

    public ArrayList<ArrayList<String>> getNewIssuedItem(ArrayList<ArrayList<String>> allItems,String searchedItemID) {
        ArrayList<ArrayList<String>> newIssue = new ArrayList<>();
        for(int i=0; i<allItems.size();i++) {
            if(allItems.get(i).get(0).equals(searchedItemID)) {
                newIssue.add(allItems.get(i));
            }
        }
        return newIssue;

    }

    public void addIssuedItemToItemsTxt(ArrayList<ArrayList<String>> newIssue) {
        mainPage mPage = new mainPage();
        ArrayList<ArrayList<String>> allItems = mPage.getItemsArrayList();

        String idToChange = newIssue.get(0).get(0);

        int rowWillBeDeleted =0;
        for(int i=0; i< allItems.size();i++) {
            if(allItems.get(i).get(0).equals(idToChange)) {
                rowWillBeDeleted =i;
            }
        }
        System.out.println("rowdelete: "+rowWillBeDeleted);

        System.out.println(allItems.get(rowWillBeDeleted));
        allItems.remove(rowWillBeDeleted);
        allItems.add(rowWillBeDeleted,newIssue.get(0));

        System.out.println(allItems.get(rowWillBeDeleted));

        String newBookText ="";
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
        mainPage.writeToTxt(newBookText,"items.txt",false);

    }


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


    public String[][] getIssuedItemstArray(ArrayList<ArrayList<String>> issueArrayList) {
        String[][] issueArray = new String[issueArrayList.size()][7];
        for(int i=0; i < issueArrayList.size();i++) {

            issueArray[i][0] = String.valueOf(issueArrayList.get(i).get(0));
            issueArray[i][1] = String.valueOf(issueArrayList.get(i).get(1));
            issueArray[i][2] = String.valueOf(issueArrayList.get(i).get(2));
            issueArray[i][3] = String.valueOf(issueArrayList.get(i).get(3));
            issueArray[i][4] = String.valueOf(issueArrayList.get(i).get(4));
            issueArray[i][5] = String.valueOf(issueArrayList.get(i).get(issueArrayList.get(i).size()-2));
            issueArray[i][6] = String.valueOf(issueArrayList.get(i).get(issueArrayList.get(i).size()-1));
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }
         */
        return issueArray;
    }

}
