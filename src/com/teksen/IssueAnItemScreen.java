package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IssueAnItemScreen extends JFrame{
    private JPanel mainPanel;
    private JTextField studentIDTextField;
    private JTextField itemIDTextField;
    private JButton issueTheItemButton;

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



        issueTheItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedStudentID = studentIDTextField.getText();
                String searchedItemID = itemIDTextField.getText();

                ArrayList<ArrayList<String>> issueArrayList = getIssueArrayList();
                //System.out.println(issueArrayList);
                ArrayList<ArrayList<String>> newIssue = new ArrayList<>();

                int availableFlag =1;

                for(int i=0; i< issueArrayList.size();i++) {
                    if(issueArrayList.get(i).get(0).equals(searchedItemID)) {
                        System.out.println("Item is not available to issue!");
                        availableFlag =0;

                    }
                }

                if(availableFlag == 1) {
                    for(int i=0; i<allItems.size();i++) {
                        if(allItems.get(i).get(0).equals(searchedItemID)) {
                            //System.out.println(allItems.get(i));
                            newIssue.add(allItems.get(i));
                        }
                    }

                    newIssue.get(0).set(4,"Not Available");

                    addIssuedItemToItemsTxt(newIssue);
                    for(int i=0; i<newIssue.size();i++) {
                        if(newIssue.get(i).get(0).equals(searchedItemID)) {
                            //System.out.println(issueArrayList);
                            //System.out.println(issueArrayList.get(i));

                            newIssue.get(i).add(searchedStudentID);

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

                    writeToTxt(newBookText,"issue.txt",true);
                }

            }
        });

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
        writeToTxt(newBookText,"items.txt",false);

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
