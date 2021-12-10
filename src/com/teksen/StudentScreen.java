package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentScreen extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField mailTextField;
    private JTextField studentTextField;
    private JTextField addressTextField;
    private JTextField passwordTextField;
    private JButton addNewStudentButton;
    private JButton deleteSelectedStudentButton;
    private JButton createStudentCardButton;
    private JTextField studentIDTextField;


    public StudentScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Student List Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }

    public StudentScreen(ArrayList<ArrayList<String>> personArrayList) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Student List Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<Student> studentClassArrayList = createStudentClassArrayList(personArrayList);
        String[][] studentArray = getStudentArray(studentClassArrayList);
        createTable(studentArray);

        addNewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForDigital = new mainPage();
                int lastID = itScreenForDigital.getLastItemID("person.txt");

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddStudentButton(modelTable,lastID);
            }
        });
        deleteSelectedStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonScreen prsScreen = new PersonScreen();
                ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteBookButton(modelTable, personArrayList);
            }
        });
        createStudentCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonScreen prsScreen = new PersonScreen();
                ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();

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
                                "%-23s%-35s\n%-21s%-35s\n"
                        ,
                        "ID:",personArrayList.get(i).get(0),
                        "First Name:",personArrayList.get(i).get(1),
                        "Last Name:",personArrayList.get(i).get(2),
                        "E-Mail:",personArrayList.get(i).get(3),
                        "Address:",personArrayList.get(i).get(4),
                        "User Type:",personArrayList.get(i).get(6),
                        "Student ID:",personArrayList.get(i).get(7)

                );
            }
        }

        //System.out.println(stickerText);

        return stickerText;

    }

    public void pressDeleteBookButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> personArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<personArrayList.size(); i++) {
                for(int j = 0; j< personArrayList.get(0).size(); j++) {
                    if( Integer.parseInt(personArrayList.get(i).get(0)) ==selectedRowID) {
                        //System.out.print(personArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            //System.out.println("rowWillBeDeleted: "+rowWillBeDeleted);
            personArrayList.remove(rowWillBeDeleted);
            //getReadingBookArray(allItems);

            String newUserText = "";


            for(int i=0; i< personArrayList.size(); i++) {
                for(int j=0; j<personArrayList.get(i).size();j++) {
                    newUserText += personArrayList.get(i).get(j);
                    if(!personArrayList.get(i).get(j).equals(personArrayList.get(i).get(personArrayList.get(i).size() -1))) {
                        newUserText += ",";
                    }
                }
                if((i) != personArrayList.size()) {
                    newUserText += "\n";
                }
            }
            System.out.println(newUserText);
            writeToTxt(newUserText,"person.txt",false);
            modelTable.removeRow(table1.getSelectedRow());

        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to delete.");
            }
        }
    }

    public void pressAddStudentButton(DefaultTableModel modelTable, int lastID) {
        //System.out.println(lastID);
        String newBookText = "\n"+ ( lastID+1) +","+
                firstNameTextField.getText() +","+lastNameTextField.getText()+","+
                mailTextField.getText()+","+addressTextField.getText()+","+
                passwordTextField.getText() +","+ studentTextField.getText()+","+
                studentIDTextField.getText();

        writeToTxt(newBookText,"person.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                mailTextField.getText(),
                addressTextField.getText(),
                studentTextField.getText(),
                studentIDTextField.getText()
        });
    }



    public ArrayList<Student> createStudentClassArrayList(ArrayList<ArrayList<String>> personArrayList) {
        ArrayList<Student> studentClassArrayList = new ArrayList<>();
        for(int i=0; i<personArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(personArrayList.get(i).get(6).equals("Student")) {
                //System.out.println("itemsarraylist: "+personArrayList.get(i));
                Student aStudent = new Student(
                        Integer.parseInt(personArrayList.get(i).get(0)), // int id
                        personArrayList.get(i).get(3), // String firstName
                        personArrayList.get(i).get(1), // String lastName
                        personArrayList.get(i).get(2), // String mail
                        personArrayList.get(i).get(4), // String address
                        personArrayList.get(i).get(5), // String password
                        personArrayList.get(i).get(6), // String personType
                        personArrayList.get(i).get(7) // String studentID
                );
                studentClassArrayList.add(aStudent);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return studentClassArrayList;
    }
    public String[][] getStudentArray(ArrayList<Student> studentClassArrayList) {
        String[][] studentArray = new String[studentClassArrayList.size()][7];
        for(int i=0; i < studentClassArrayList.size();i++) {
            studentArray[i][0] = String.valueOf(studentClassArrayList.get(i).getId());
            studentArray[i][1] = studentClassArrayList.get(i).getFirstName();
            studentArray[i][2] = studentClassArrayList.get(i).getLastName();
            studentArray[i][3] = studentClassArrayList.get(i).getMail();
            studentArray[i][4] = studentClassArrayList.get(i).getAddress();
            //studentArray[i][5] = studentClassArrayList.get(i).getPassword();
            studentArray[i][5] = studentClassArrayList.get(i).getPersonType();
            studentArray[i][6] = String.valueOf(studentClassArrayList.get(i).getStudentID());;
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */

        return studentArray;
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

    public void createTable(String[][] studentArray) {
        table1.setModel(new DefaultTableModel(
                studentArray,
                new String[]{"ID","First Name","Last Name","E-Mail","Address",
                        "Person Type","Student ID"}
        ));
    }
}
