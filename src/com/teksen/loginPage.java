package com.teksen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class loginPage extends JFrame {

    private JTextField textFieldEmail;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton loginButtonStudent;
    private JButton loginButtonStaff;
    private JTextField textField2;
    private JRadioButton administratorRadioButton;
    private JRadioButton authorizedStaffRadioButton;
    private JPanel panel1;
    private JLabel logoLabel;
    private JLabel forgotPassField1;
    private JLabel staffLabel;
    private JLabel studentLabel;
    private JLabel emailLabel;
    private JLabel password1Label;
    private JLabel studentNumberLabel;
    private JLabel password2Label;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox showPasswordCheckBox2;
    private JTable table1;

    static String eMail;


    public loginPage() {
        add(panel1);
        setSize(854, 480);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eMail = textFieldEmail.getText();

        ArrayList<ArrayList<String>> staffs = getPersonList();
        boolean permission = getPermission(eMail,staffs);

        ImageIcon khasLogo = new ImageIcon("loginPage2\\khasLogo.png");
        Image khasLogoScale = khasLogo.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledKhasLogo = new ImageIcon(khasLogoScale);
        logoLabel.setIcon(scaledKhasLogo);

        textFieldEmail.setPreferredSize(new Dimension(50, 25));
        passwordField1.setPreferredSize(new Dimension(50, 25));

        textField2.setPreferredSize(new Dimension(50, 25));
        passwordField2.setPreferredSize(new Dimension(50, 25));

        //loginButtonStaff.setFont(new java.awt.Font("Arial",0, 16));
        loginButtonStaff.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButtonStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StaffLogin();

            }
        });
        //loginButtonStudent.setFont(new java.awt.Font("Arial", 0, 16));
        loginButtonStudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButtonStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLogin();
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
                else{
                    passwordField1.setEchoChar('*');
                }
            }
        });

        showPasswordCheckBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox2.isSelected()){
                    passwordField2.setEchoChar((char)0);
                }
                else{
                    passwordField2.setEchoChar('*');
                }
            }
        });
    }
    public ArrayList<ArrayList<String>> getPersonList() {
        ArrayList<ArrayList<String>> staffs = new ArrayList<>();
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

                staffs.add(aStaff);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return staffs;
    }

    private void StaffLogin() {
        ArrayList<ArrayList<String>> staffs = getPersonList();

        String email, password;
        email = textFieldEmail.getText();
        password = passwordField1.getText();

        seteMail(email);
        System.out.println(email + " " + password);

        for (int i = 0; i < staffs.size(); i++) {
            if (email.equals("") && password.equals("")) {
                JOptionPane.showMessageDialog(panel1, "Please enter your e-mail and password.");
                break;
            }
            else if (email.equals(staffs.get(i).get(3)) && password.equals(staffs.get(i).get(5)) && !staffs.get(i).get(6).equals("Student")) {
                dispose();
                new mainPage(email).setVisible(true);
                break;
            }
            else {
                if ((i + 1) == staffs.size()) {
                    JOptionPane.showMessageDialog(panel1, "Your e-mail or password is incorrect.");
                    break;
                }
            }
        }
    }
    private void StudentLogin() {
        ArrayList<ArrayList<String>> students = getPersonList();

        String studentNumber, password;
        studentNumber = textField2.getText();

        password = passwordField2.getText();
        System.out.println(studentNumber + " " + password);


        for (int i = 0; i < students.size(); i++) {
            //System.out.println(books.get(i).get(1) + " " + books.get(i).get(2));
            if (studentNumber.equals("") && password.equals("")) {
                JOptionPane.showMessageDialog(panel1, "Please enter your e-mail and password.");
                break;
            } else if (studentNumber.equals(students.get(i).get(7)) && password.equals(students.get(i).get(5)) && students.get(i).get(6).equals("Student")) {
                dispose();
                new SingleStudentScreen(studentNumber).setVisible(true);
                break;
            } else {
                if ((i + 1) == students.size()) {
                    JOptionPane.showMessageDialog(panel1, "Your e-mail or password is incorrect.");
                    break;
                }
            }
        }
    }


    public boolean getPermission(String email, ArrayList<ArrayList<String>> personArrayList) {
        boolean permissionAdmin=false;
        for(int i=0; i<personArrayList.size();i++) {
            System.out.println(i+": "+personArrayList.get(i).get(3)+"--"+email);
            System.out.println(i+": "+personArrayList.get(i).get(6)+"--"+"Admin");
            if(personArrayList.get(i).get(3).equals(email)) {

                if(personArrayList.get(i).get(6).equals("Admin")) {

                    permissionAdmin = true;
                    break;
                }

            }
        }

        return permissionAdmin;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String geteMail() {
        return eMail;
    }


}