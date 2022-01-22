package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1> StudentScreen Class </h1>
 * The StudentScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons, table.
 * This class creates a functionality for form of the screen.
 *
 * In this screen, program has textfields to add new student,
 * and buttons for operations like add,delete,create card for student.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */

public class StudentScreen extends JFrame implements Sticker{
    private JPanel mainPanel; // panel instance variable
    private JTable table1; // table instance variable
    private JTextField firstNameTextField; // textfield instance variable
    private JTextField lastNameTextField; // textfield instance variable
    private JTextField mailTextField; // textfield instance variable
    private JTextField studentTextField; // textfield instance variable
    private JTextField addressTextField; // textfield instance variable
    private JTextField passwordTextField; // textfield instance variable
    private JButton addNewStudentButton; // button instance variable
    private JButton deleteSelectedStudentButton; // button instance variable
    private JButton createStudentCardButton; // button instance variable
    private JTextField studentIDTextField; // textfield instance variable
    static String[] labelOfTable = new String[] {"ID","First Name","Last Name",
            "E-Mail","Address",
            "Person Type","Student ID"};


    /**
     * StudentScreen Constructor
     * This constructor adds the components of form to the frame.
     */
    public StudentScreen() {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Student List Screen");// set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner
    }

    /**
     * StudentScreen Constructor
     * This constructor adds the components of form to the frame.
     * This constructor has listeners for "add, delete, create sticker" for student.
     *
     * @param personArrayList is the staff list in databse
     */
    public StudentScreen(ArrayList<ArrayList<String>> personArrayList) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Student List Screen");// set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner

        ArrayList<Student> studentClassArrayList = createStudentClassArrayList(personArrayList); // create Student ArrayList
        String[][] studentArray = getStudentArray(studentClassArrayList); // create Student Array
        MainPage.createTable(table1,studentArray,labelOfTable); // create table

        addNewStudentButton.addActionListener(new ActionListener() {
            /**
             * Add New Student Button
             * When the add new staff button is pressed, get the student's ID
             * to increment it for new student's ID. Create Table Model to update the table
             * after adding new student.
             *
             * Call pressAddStudentButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForDigital = new MainPage();
                int lastID = itScreenForDigital.getLastItemID("person.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddStudentButton(modelTable,lastID);
            }
        });
        deleteSelectedStudentButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected Student Button
             * When the delete selected student button is pressed, get the all person in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteStaffButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lgPage = new LoginPage();
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteStudentButton(modelTable, personArrayList);
            }
        });
        createStudentCardButton.addActionListener(new ActionListener() {
            /**
             * Create Card Selected Student Button
             * When the create card for selected student button is pressed, get the all students in the array
             * to determine the student whose card will be created in database and create table model
             * to get the selected student. Create sticker text to display card for selected student.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lgPage = new LoginPage();
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,personArrayList);
                if(!stickerText.equals("")) {
                    StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                    stckScreen.setVisible(true);
                }

            }
        });
    }

    /**
     * CreateSticker Function
     * This function creates a sticker text to display the selected user's values in
     * sticker screen. To determine the user whose card will be displayed, user needs to select
     * a single row in the table and press the create sticker button.
     * @param modelTable is the table model for articles
     * @param personArrayList is the all staff in database
     * @return sticker text for selected item.
     */
    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> personArrayList) {
        String stickerText ="";
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            for(int i=0; i <personArrayList.size(); i++) {
                if ( Integer.parseInt(personArrayList.get(i).get(0)) == selectedRowID) {
                    //System.out.println(personArrayList.get(i).get(1)+"-"+selectedRowID);
                    stickerText = String.format(
                            "%-28s%-35s\n%-22s%-35s\n%-21s%-35s\n%-26s%-35s\n%-24s%-35s\n"+
                                    "%-22s%-35s\n%-23s%-35s\n"
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
        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to create card for a student.");
            }
            return"";
        }

    }

    /**
     * Delete Student Function
     * This function removes the selected student from database.
     * To determine the which student will be removed,
     * user needs to select the single row from the table.
     * @param modelTable is the table model
     * @param personArrayList is the all users in database
     * @return nothing.
     */
    public void pressDeleteStudentButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> personArrayList) {
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
                if((i+1) != personArrayList.size()) {
                    newUserText += "\n";
                }
            }
            //System.out.println(newUserText);
            MainPage.writeToTxt(newUserText,"person.txt",false);
            modelTable.removeRow(table1.getSelectedRow());

        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to delete.");
            }
        }
    }

    /**
     * Add STudent Function
     * This function add new student by getting new student's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the user in database
     */
    public void pressAddStudentButton(DefaultTableModel modelTable, int lastID) {
        //System.out.println(lastID);
        if( !(MainPage.isNumeric(studentIDTextField.getText(),mainPanel)) ) {
            return;
        }
        String newBookText = "\n"+ ( lastID+1) +","+
                firstNameTextField.getText() +","+lastNameTextField.getText()+","+
                mailTextField.getText()+","+addressTextField.getText()+","+
                passwordTextField.getText() +","+ studentTextField.getText()+","+
                studentIDTextField.getText();

        MainPage.writeToTxt(newBookText,"person.txt",true); // append new student

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

    /**
     * Create STudent Array List
     * This function creates student array list for only student type users.
     * @param personArrayList is the all users in database
     * @return student array list
     */
    public ArrayList<Student> createStudentClassArrayList(ArrayList<ArrayList<String>> personArrayList) {
        ArrayList<Student> studentClassArrayList = new ArrayList<>();
        for(int i=0; i<personArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(personArrayList.get(i).get(6).equals("Student")) {
                //System.out.println("itemsarraylist: "+personArrayList.get(i));
                Student aStudent = new Student(
                        Integer.parseInt(personArrayList.get(i).get(0)), // int id
                        personArrayList.get(i).get(1), // String firstName
                        personArrayList.get(i).get(2), // String lastName
                        personArrayList.get(i).get(3), // String mail
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

    /**
     * Get Student Array
     * This function creates student array for table. It converts student array list
     * to the student array.
     * @param studentClassArrayList
     * @return
     */
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
        return studentArray;
    }
}
