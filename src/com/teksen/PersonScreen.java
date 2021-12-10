package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonScreen extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public PersonScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Person List Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public ArrayList<ArrayList<String>> getPersonArrayList() {
        ArrayList<ArrayList<String>> personArrayList = new ArrayList<>();
        try {
            File myObj = new File("person.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);
                ArrayList<String> aPerson = new ArrayList<String>();

                for(int i=0; i < singleItem.length;i++) {
                    aPerson.add(singleItem[i]);
                }
                personArrayList.add(aPerson);
                //System.out.println(aPerson);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return personArrayList;
    }





    public void createTable(String[][] personArray) {
        table1.setModel(new DefaultTableModel(
                personArray,
                new String[]{"ID","First Name","Last Name","E-Mail","Address",
                        "Person Type"}
        ));
    }
}
