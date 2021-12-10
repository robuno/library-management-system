package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReturnItemScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField itemIDTextField;
    private JButton returnTheItemButton;

    public ReturnItemScreen() {
        add(mainPanel);
        setSize(1200,600);
        setTitle("Student List Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public ReturnItemScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1200,600);
        setTitle("Student List Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        returnTheItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedItemID = itemIDTextField.getText();

                ArrayList<ArrayList<String>> issueArrayList = getIssueArrayList();
                ArrayList<ArrayList<String>> newReturn = new ArrayList<>();

                int rowWillBeSelected =0;
                int availableFlag=1; // 0:  issued, 1: not issued
                System.out.print(issueArrayList);
                for(int j=0; j < issueArrayList.size(); j++) {
                    //System.out.println(issueArrayList.get(j).get(0)+"-"+searchedItemID);
                    if(issueArrayList.get(j).get(0).equals(searchedItemID)) {
                        availableFlag =0;
                        rowWillBeSelected = j;
                        break;
                    }
                }

                if(availableFlag==1) {
                    System.out.println("Item is not issued! You cannot return! Please check the library!");
                } else{
                    System.out.println("item issued lets return");
                    System.out.println("row: "+rowWillBeSelected);

                    //System.out.println(issueArrayList.get(rowWillBeSelected));

                    issueArrayList.remove(rowWillBeSelected);
                    System.out.print(issueArrayList);

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

                    writeToTxt(newIssueText,"issue.txt",false);

                    int rowItemWillBeDeleted=0;
                    for(int j=0; j < allItems.size(); j++) {
                        //System.out.println(issueArrayList.get(j).get(0)+"-"+searchedItemID);
                        if(allItems.get(j).get(0).equals(searchedItemID)) {
                            availableFlag =0;
                            rowItemWillBeDeleted = j;
                            break;
                        }
                    }
                    allItems.get(rowItemWillBeDeleted).set(4,"Available");
                    //System.out.println("\nallitems4: "+allItems.get(rowItemWillBeDeleted));

                    String newItemText ="";
                    for(int i=0; i< allItems.size(); i++) {
                        for(int j=0; j<allItems.get(i).size();j++) {
                            newItemText += allItems.get(i).get(j);
                            if(!allItems.get(i).get(j).equals(allItems.get(i).get(allItems.get(i).size() -1))) {
                                newItemText += ",";
                            }
                        }
                        if((i+1) != allItems.size()) {
                            newItemText += "\n";
                        }
                    }

                    writeToTxt(newItemText,"items.txt",false);

                }


            }
        });
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

    public void writeToTxt(String data, String file,boolean appendOrWriteAll) {
        try {
            //System.out.println(newBookText);
            File f1 = new File(file);
            FileWriter fileWritter = new FileWriter(f1.getName(),appendOrWriteAll);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(data);
            bw.close();
        } catch(IOException e2){
            e2.printStackTrace();
        }
    }
}
