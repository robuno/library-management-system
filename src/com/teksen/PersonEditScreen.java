package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> PersonEditScreen Class </h1>
 * The IssueAnItemScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 *
 * In this screen, program has textfields to add new staff, and buttons for
 * operations like add,delete,create card for staff.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */

public class PersonEditScreen extends JFrame implements Sticker{
    private JPanel mainPanel; // panel instance variable
    private JTable table1; // table instance variable
    private JTextField firstNameTextField; // textfield instance variable
    private JTextField lastNameTextField; // textfield instance variable
    private JTextField eMailTextField; // textfield instance variable
    private JTextField addressTextField; // textfield instance variable
    private JTextField staffIDtextField; // textfield instance variable
    private JRadioButton authorizedPersonRadioButton; // button instance variable
    private JRadioButton adminRadioButton; // button instance variable
    private JButton addNewPersonButton; // button instance variable
    private JButton deleteSelectedPersonButton; // button instance variable
    private JTextField passwordTextField; // textfield instance variable
    private JPanel addPersonPanel;  // panel instance variable
    private JButton createStaffCardForButton; // button instance variable
    private String personType;
    static String[] labelOfTable = new String[] {"ID","First Name","Last Name","E-Mail","Address",
            "Password","Person Type","Staff/Student ID"};

    private ArrayList<ArrayList<String>> personArrayList;

    /**
     * Person EditScreen Constructor
     * This constructor adds the components of form to the frame.
     * This constructor has listeners for "add, delete, create sticker" for staff.
     *
     * @param personArrayList is the staff arraylist
     */
    public PersonEditScreen(ArrayList<ArrayList<String>> personArrayList) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Person List Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner

        String[][] personArray = getPersonArray(personArrayList); // create staff array
        MainPage.createTable(table1,personArray,labelOfTable); // create table
        LoginPage lgPage = new LoginPage();

        ButtonGroup bGroup = new ButtonGroup(); // make group radio buttons
        bGroup.add(authorizedPersonRadioButton);
        bGroup.add(adminRadioButton);


        addNewPersonButton.addActionListener(new ActionListener() {
            /**
             * Add New Staff Button
             * When the add new staff button is pressed, get the staff's ID
             * to increment it for staff's ID. Create Table Model to update the table
             * after adding new staff.
             *
             * Call pressAddPersonButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForPerson = new MainPage();
                int lastID = itScreenForPerson.getLastItemID("person.txt",0);

                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                int lastStaffID = getLastStaffID(personArrayList);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddPersonButton(modelTable, lastID, lastStaffID);
            }
        });

        deleteSelectedPersonButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected Article Button
             * When the delete selected staff button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteStaffButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteStaffButton(modelTable, personArrayList);
            }
        });
        createStaffCardForButton.addActionListener(new ActionListener() {
            /**
             * Create Card Selected Staff Button
             * When the create card for selected staff button is pressed, get the all staff in the array
             * to determine the staff whose card will be created in database and create table model
             * to get the selected item. Create sticker text to display card for selected staff.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            "%-25s%-35s\n%-19s%-35s\n%-17s%-35s\n%-22s%-35s\n%-20s%-35s\n"+
                                    "%-17s%-35s\n%-22s%-35s"
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
        } else {
            if(table1.getRowCount() == 0 ) {
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else {
                JOptionPane.showMessageDialog(table1,"Please select single row to create card for staff.");
            }
            return"";
        }

    }

    /**
     * Delete Staff Function
     * This function removes the selected staff from database.
     * To determine the which staff will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model
     * @param personArrayList is the all users in database
     * @return nothing.
     */
    public void pressDeleteStaffButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> personArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<personArrayList.size(); i++) {
                for(int j = 0; j< personArrayList.get(0).size(); j++) {
                    if( Integer.parseInt(personArrayList.get(i).get(0)) ==selectedRowID) {
                        //System.out.print(allItems.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            personArrayList.remove(rowWillBeDeleted);

            String newPersonText = "";

            for(int i=0; i< personArrayList.size(); i++) {
                for(int j=0; j<personArrayList.get(i).size();j++) {
                    newPersonText += personArrayList.get(i).get(j);
                    if(!personArrayList.get(i).get(j).equals(personArrayList.get(i).get(personArrayList.get(i).size() -1))) {
                        newPersonText += ",";
                    }
                }
                if((i+1) != personArrayList.size()) {
                    newPersonText += "\n";
                }
            }

            MainPage.writeToTxt(newPersonText,"person.txt",false);
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
     * Add Staff Function
     * This function add new staff by getting new staff's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the user in database
     * @param lastStaffID is the last staffid of the user in database
     */
    public void pressAddPersonButton(DefaultTableModel modelTable,  int lastID, int lastStaffID){
        String advancedOptions ="false";
        if(authorizedPersonRadioButton.isSelected()) {
            personType = "AuthorizedPerson";
        }
        else if(adminRadioButton.isSelected()) {
            personType = "Admin";
            advancedOptions = "true"; // write true for admin person type to the database
        }
        else {
            JOptionPane.showMessageDialog(addPersonPanel,
                    "Please select a person type!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        StringBuilder newPersonText = new StringBuilder("\n" + (lastID + 1) + "," +
                firstNameTextField.getText() + "," + lastNameTextField.getText() + "," +
                eMailTextField.getText() + "," + addressTextField.getText() + "," +
                passwordTextField.getText() + "," + personType + "," +
                Integer.parseInt(String.valueOf(lastStaffID + 1)));

        LoginPage lgPage= new LoginPage();
        ArrayList<Admin> adminArrayList = lgPage.getAdminArrayList();

        for (Admin admin : adminArrayList) {
            if (personType.equals(admin.getPersonType())) {
                newPersonText.append(",").append(advancedOptions);
            }
            break;
        }

        MainPage.writeToTxt(newPersonText.toString(),"person.txt",true);

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

    /**
     * Get LastStaffID
     * Returns that the last staff's ID in the database
     * @param personArrayList is the staff ArrayList
     * @return lastStaff ID in int.
     */
    public int getLastStaffID(ArrayList<ArrayList<String>> personArrayList) {
        int lastStaffID=0;
        for(int i=0; i < personArrayList.size();i++) {
            if(!personArrayList.get(i).get(6).equals("Student")) { // make sure that person is staff
                lastStaffID = Integer.parseInt(personArrayList.get(i).get(7));
            }
        }
        return lastStaffID;
    }


    /**
     * Get StaffArray
     * This function creates Staff Array for table. Converts arraylist to array.
     * @param personArrayList staff ArrayList
     * @return staff Array
     */
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
