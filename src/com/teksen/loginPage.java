package com.teksen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> LoginPage Class </h1>
 * The LoginPage program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, textfields to get user input,
 * checkbox to show password textfield.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class LoginPage extends JFrame {
    private JTextField textFieldEmail;  // textfield instance variable
    private JPasswordField passwordField1; // password instance variable
    private JPasswordField passwordField2; // password instance variable
    private JButton loginButtonStudent; // button instance variable
    private JButton loginButtonStaff; // button instance variable
    private JTextField textField2; // textfield instance variable
    private JPanel panel1; // panel instance variable
    private JLabel logoLabel; // label instance variable
    private JCheckBox showPasswordCheckBox; //checkbox instance variable
    private JCheckBox showPasswordCheckBox2; //checkbox instance variable
    private JLabel staffIconLabel; // label instance variable

    static String eMail; // email instance variable
    static ArrayList<Admin> adminArrayList; // admin arraylist instance variable
    static ArrayList<ArrayList<String>> personArrayList; // person arraylist instance variable

    /**
     * LoginPage Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for login buttons.
     */
    public LoginPage() {
        add(panel1); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Login Page"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        ImageIcon khasLogo = new ImageIcon("khaslogo.png"); // upload logo
        Image khasLogoScale = khasLogo.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH); // set logo size
        ImageIcon scaledKhasLogo = new ImageIcon(khasLogoScale); // create logo
        logoLabel.setIcon(scaledKhasLogo);// display logo

        // textfield margins
        textField2.setMargin(new Insets(4, 4, 4, 4));
        textFieldEmail.setMargin(new Insets(4, 4, 4, 4));
        passwordField1.setMargin(new Insets(4, 4, 4, 4));
        passwordField2.setMargin(new Insets(4, 4, 4, 4));

        // button size and text color
        loginButtonStaff.setForeground(Color.WHITE);
        loginButtonStaff.setPreferredSize(new Dimension(40, 40));
        loginButtonStudent.setForeground(Color.WHITE);
        loginButtonStudent.setPreferredSize(new Dimension(40, 40));

        eMail = textFieldEmail.getText(); // assign input for email to variable
        personArrayList = getPersonArrayList(); // create person array list
        adminArrayList = createAdminClassArrayList(personArrayList); // create admin arraylist
        //ArrayList<AuthorizedPerson> authorizedPersonArrayList = createAuthorizedClassArrayList(staffs);

        loginButtonStaff.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // create listener for staff login
        loginButtonStaff.addActionListener(new ActionListener() {
            /**
             * Staff Login Action
             * This function calls staff login function to direct the user to
             * the main page.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                StaffLogin();
            }
        });

        loginButtonStudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // create listener for student login
        loginButtonStudent.addActionListener(new ActionListener() {
            /**
             * Student Login Action
             * This function calls student login function to direct the user to
             * the main page.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLogin();
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            /**
             * PasswordCheckBox Function
             * This function shows or hides password.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
                else{
                    passwordField1.setEchoChar('*'); // hide
                }
            }
        });

        showPasswordCheckBox2.addActionListener(new ActionListener() {
            /**
             * PasswordCheckBox Function
             * This function shows or hides password.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox2.isSelected()){
                    passwordField2.setEchoChar((char)0);
                }
                else{
                    passwordField2.setEchoChar('*'); // hide
                }
            }
        });
    }

    /**
     * Get PersonArrayList Function
     * This function reads person txt and returns the admin type
     * users.
     * @return admin type users in String ArrayList
     */
    public ArrayList<ArrayList<String>> getPersonArrayList() {
        ArrayList<ArrayList<String>> personArrayList = new ArrayList<>(); // create ArrayList
        try {
            File myObj = new File("person.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleStaff = data.split(",", 0);

                ArrayList<String> aStaff = new ArrayList<String>();

                aStaff.add(singleStaff[0]);
                aStaff.add(singleStaff[1]);
                aStaff.add(singleStaff[2]);
                aStaff.add(singleStaff[3]);
                aStaff.add(singleStaff[4]);
                aStaff.add(singleStaff[5]);
                aStaff.add(singleStaff[6]);
                aStaff.add(singleStaff[7]);
                if(singleStaff[6].equals("Admin")) { // if person type is admin
                    aStaff.add(singleStaff[8]);
                }

                personArrayList.add(aStaff);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return personArrayList;
    }

    /**
     * Create Admin Class Array List
     * This function creates Admin ArrayList with Admin Class Objects
     * @param personArrayList is the admin arraylist in string
     * @return admin array list with Admin Objects.
     */
    public ArrayList<Admin> createAdminClassArrayList(ArrayList<ArrayList<String>> personArrayList) {
        ArrayList<Admin> adminClassArray = new ArrayList<>();
        for(int i=0; i<personArrayList.size();i++) {
            if(personArrayList.get(i).get(6).equals("Admin")) {
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
            }
        }

        return adminClassArray;
    }

    /**
     * Create AuthorizedPerson AuthorizedPerson ArrayList
     * This function creates Admin ArrayList with AuthorizedPerson Class Objects
     * @param personArrayList is the AuthorizedPerson arraylist in string
     * @return admin array list with AuthorizedPerson Objects.
     */
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
        return authorizedClassArray;
    }

    /**
     * StaffLogin Function
     * This function directs the user to the staff
     * page (mainPage) if user's inputs match with database.
     */
    private void StaffLogin() {
        String email, password;
        email = textFieldEmail.getText(); // email variable
        password = passwordField1.getText(); // password variable

        for (int i = 0; i < personArrayList.size(); i++) {
            if (email.equals("") && password.equals("")) { // if no input
                JOptionPane.showMessageDialog(panel1, "Please enter your e-mail and password.");
                break;
            }
            else if (email.equals(personArrayList.get(i).get(3)) // email check
                    && password.equals(personArrayList.get(i).get(5)) //password check
                    && !personArrayList.get(i).get(6).equals("Student"))  // type check
            {
                dispose();
                new MainPage(email,personArrayList).setVisible(true); // open staff panel
                break;
            }
            else { // if mail and password dont match with db
                if ((i + 1) == personArrayList.size()) {
                    JOptionPane.showMessageDialog(panel1, "Your e-mail or password is incorrect.");
                    break;
                }
            }
        }
    }

    /**
     * StudentLogin
     * This function directs the user to the student
     * page (mainPage) if user's inputs match with database.
     */
    private void StudentLogin() {
        String studentNumber, password;
        studentNumber = textField2.getText(); // student ID variable
        password = passwordField2.getText(); // password variable

        for (int i = 0; i < personArrayList.size(); i++) {
            if (studentNumber.equals("") && password.equals("")) { // if no input
                JOptionPane.showMessageDialog(panel1, "Please enter your e-mail and password.");
                break;
            } else if (studentNumber.equals(personArrayList.get(i).get(7)) // ID check
                    && password.equals(personArrayList.get(i).get(5)) // password check
                    && personArrayList.get(i).get(6).equals("Student")) // user type check
            {
                dispose();
                new SingleStudentScreen(studentNumber).setVisible(true); // open student page
                break;
            } else { // if id and passwordn dont match with db
                if ((i + 1) == personArrayList.size()) {
                    JOptionPane.showMessageDialog(panel1, "Your e-mail or password is incorrect.");
                    break;
                }
            }
        }
    }

    /**
     * Get Permission Function
     * This function checks the staff user has permission to access
     * manage items(articles, digitals, reading books, etc.) and manage
     * users pages. It checks user types for that purpose.
     * @param email is the staff email
     * @return true or false that shows advanced options are available for admin.
     */
    public boolean getPermission(String email) {
        boolean permissionAdmin=false;
        for(int i=0; i<adminArrayList.size();i++) {
                //System.out.println(i+": "+adminArrayList.get(i).getMail()+"--"+email);
                //System.out.println(i+": "+adminArrayList.get(i).isAdvancedOptions()+"--"+"true");
                if(adminArrayList.get(i).getMail().equals(email)) {
                    if(adminArrayList.get(i).isAdvancedOptions()) { // if the user is admin and its advanced options are true
                        permissionAdmin = true;
                        break;
                    }
            }
        }
        return permissionAdmin;
    }

    //getters
    public String geteMail() {
        return eMail;
    }

    public static ArrayList<Admin> getAdminArrayList() {
        return adminArrayList;
    }

    // setters
    public static void setAdminArrayList(ArrayList<Admin> adminArrayList) {
        LoginPage.adminArrayList = adminArrayList;
    }

    public static void setPersonArrayList(ArrayList<ArrayList<String>> personArrayList) {
        LoginPage.personArrayList = personArrayList;
    }

}