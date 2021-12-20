package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonEditScreen extends JFrame implements Sticker{
    private JPanel mainPanel;
    private JTable table1;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField eMailTextField;
    private JTextField addressTextField;
    private JTextField staffIDtextField;
    private JRadioButton authorizedPersonRadioButton;
    private JRadioButton adminRadioButton;
    private JButton addNewPersonButton;
    private JButton deleteSelectedPersonButton;
    private JTextField passwordTextField;
    private JPanel addPersonPanel;
    private JButton createStaffCardForButton;
    private String personType;
    static String[] labelOfTable = new String[] {"ID","First Name","Last Name","E-Mail","Address",
            "Password","Person Type","Staff/Student ID"};



    public PersonEditScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loginPage lgPage = new loginPage();
        ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
        String[][] personArray = getPersonArray(personArrayList);
        mainPage.createTable(table1,personArray,labelOfTable);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(authorizedPersonRadioButton);
        bGroup.add(adminRadioButton);

        //createAdminClassArrayList(personArrayList);
        //createAuthorizedClassArrayList(personArrayList);


        addNewPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForPerson = new mainPage();
                int lastID = itScreenForPerson.getLastItemID("person.txt",0);
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                int lastStaffID = getLastStaffID(personArrayList);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddPersonButton(modelTable, lastID, lastStaffID);
            }
        });

        deleteSelectedPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, personArrayList);
            }
        });
        createStaffCardForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,personArrayList);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);

            }
        });
    }


    public PersonEditScreen(ArrayList<ArrayList<String>> personArrayList ) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        String[][] personArray = getPersonArray(personArrayList);
        mainPage.createTable(table1,personArray,labelOfTable);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(authorizedPersonRadioButton);
        bGroup.add(adminRadioButton);


        addNewPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForPerson = new mainPage();
                int lastID = itScreenForPerson.getLastItemID("person.txt",0);
                int lastStaffID = getLastStaffID(personArrayList);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddPersonButton(modelTable, lastID, lastStaffID);
            }
        });

        deleteSelectedPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, personArrayList);
            }
        });
        createStaffCardForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,personArrayList);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);

            }
        });
    }

    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> personArrayList) {
        int selectedRowNo = table1.getSelectedRow();
        //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
        int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

        String stickerText ="";
        for(int i=0; i <personArrayList.size(); i++) {
            if ( Integer.parseInt(personArrayList.get(i).get(0)) == selectedRowID) {
                System.out.println(personArrayList.get(i).get(1)+"-"+selectedRowID);
                stickerText = String.format(
                        "%-25s%-35s\n%-25s%-35s\n%-21s%-35s\n%-23s%-35s\n%-23s%-35s\n"+
                                "%-23s%-35s\n%-21s%-35s"
                        ,
                        "ID:",personArrayList.get(i).get(0),
                        "First Name:",personArrayList.get(i).get(1),
                        "Last Name:",personArrayList.get(i).get(2),
                        "E-Mail:",personArrayList.get(i).get(3),
                        "Address:",personArrayList.get(i).get(4),

                        "Person Type:",personArrayList.get(i).get(6),
                        "Staff ID:",personArrayList.get(i).get(7)
                );
            }
        }

        //System.out.println(stickerText);

        return stickerText;

    }

    public void pressDeleteBookButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<allItems.size(); i++) {
                for(int j = 0; j< allItems.get(0).size(); j++) {
                    if( Integer.parseInt(allItems.get(i).get(0)) ==selectedRowID) {
                        //System.out.print(allItems.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            //System.out.println("rowWillBeDeleted: "+rowWillBeDeleted);
            allItems.remove(rowWillBeDeleted);
            //getReadingBookArray(allItems);

            String newPersonText = "";

            /*
            ArrayList<ArrayList<String>> books = getReadingBookArrayList(allItems);
            ArrayList<ReadingBook> readingBooksClassArrayList = createReadingBookClassArray(books);
            for(int i=0; i< readingBooksClassArrayList.size();i++) {
                System.out.println(readingBooksClassArrayList.get(i));
                newBookText += readingBooksClassArrayList.get(i)+"\n";
            }
             */


            for(int i=0; i< allItems.size(); i++) {
                for(int j=0; j<allItems.get(i).size();j++) {
                    newPersonText += allItems.get(i).get(j);
                    if(!allItems.get(i).get(j).equals(allItems.get(i).get(allItems.get(i).size() -1))) {
                        newPersonText += ",";
                    }
                }
                if((i+1) != allItems.size()) {
                    newPersonText += "\n";
                }
            }

            mainPage.writeToTxt(newPersonText,"person.txt",false);
            modelTable.removeRow(table1.getSelectedRow());



        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to delete.");
            }
        }
    }


    public void pressAddPersonButton(DefaultTableModel modelTable,  int lastID, int lastStaffID){

        String advancedOptions;
        if(authorizedPersonRadioButton.isSelected()) {
            personType = "AuthorizedPerson";
            advancedOptions = "false";
        }
        else if(adminRadioButton.isSelected()) {
            personType = "Admin";
            advancedOptions = "true";
        }
        else {
            JOptionPane.showMessageDialog(addPersonPanel,
                    "Please select a person type!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("******advanced: "+advancedOptions);
        String newPersonText = "\n"+ (lastID+1) +","+
                firstNameTextField.getText() +","+lastNameTextField.getText()+","+
                eMailTextField.getText()+","+addressTextField.getText()+","+
                passwordTextField.getText() +","+ personType+","+
                Integer.parseInt(String.valueOf(lastStaffID +1))+","+
                        advancedOptions;

        mainPage.writeToTxt(newPersonText,"person.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                eMailTextField.getText(),
                addressTextField.getText(),
                passwordTextField.getText(),
                personType,
                lastStaffID+1
        });
    }
    public ArrayList<Admin> createAdminClassArrayList(ArrayList<ArrayList<String>> personArrayList) {

        ArrayList<Admin> adminClassArray = new ArrayList<>();
        for(int i=0; i<personArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(personArrayList.get(i).get(6).equals("Admin")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                Admin aNewAdmin = new Admin(
                        Integer.parseInt(personArrayList.get(i).get(0)), // int id
                        personArrayList.get(i).get(1), // String first name
                        personArrayList.get(i).get(2), // String last name
                        personArrayList.get(i).get(3), // String mail
                        personArrayList.get(i).get(4), // String address
                        personArrayList.get(i).get(5), // String password
                        personArrayList.get(i).get(6), // String user type
                        personArrayList.get(i).get(7),  // String staffID
                        Boolean.parseBoolean(personArrayList.get(i).get(8))  // boolean advanced options

                );
                adminClassArray.add(aNewAdmin);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        System.out.print("***admn:");
        for(int i=0;i < adminClassArray.size();i++) {
            System.out.print(adminClassArray.get(i).getPersonType()+",");
        }

        return adminClassArray;
    }

    public ArrayList<AuthorizedPerson> createAuthorizedClassArrayList(ArrayList<ArrayList<String>> personArrayList) {

        ArrayList<AuthorizedPerson> authorizedClassArray = new ArrayList<>();
        for(int i=0; i<personArrayList.size();i++) {
            if(personArrayList.get(i).get(6).equals("AuthorizedPerson")) {
                AuthorizedPerson aNewAU = new AuthorizedPerson(
                        Integer.parseInt(personArrayList.get(i).get(0)), // int id
                        personArrayList.get(i).get(1), // String first name
                        personArrayList.get(i).get(2), // String last name
                        personArrayList.get(i).get(3), // String mail
                        personArrayList.get(i).get(4), // String address
                        personArrayList.get(i).get(5), // String password
                        personArrayList.get(i).get(6), // String user type
                        personArrayList.get(i).get(7)  // String staffID
                );
                authorizedClassArray.add(aNewAU);
            }
        }

        System.out.print("***authrarray:");
        for(int i=0;i < authorizedClassArray.size();i++) {
            System.out.print(authorizedClassArray.get(i).getPersonType()+",");
        }


        return authorizedClassArray;
    }

    public int getLastStaffID(ArrayList<ArrayList<String>> personArrayList) {
        int lastStaffID=0;
        for(int i=0; i < personArrayList.size();i++) {
            if(!personArrayList.get(i).get(6).equals("Student")) {
                lastStaffID = Integer.parseInt(personArrayList.get(i).get(7));
            }
        }
        return lastStaffID;
    }


    public String[][] getPersonArray(ArrayList<ArrayList<String>> personArrayList) {
        String[][] personArray = new String[personArrayList.size()][8];
        for(int i=0; i < personArrayList.size();i++) {

            for(int j=0;j < 8;j++) {
                    personArray[i][j] = String.valueOf(personArrayList.get(i).get(j));
            }
        }
        return personArray;
    }

}
