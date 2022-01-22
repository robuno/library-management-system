package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> MainPage Class </h1>
 * The MainPage program implements that
 * creates an instance variable to display the frame includes
 * panel, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has buttons to open new screens.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class MainPage extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JButton manageDigitalButton; // button instance variable
    private JButton manageReadingBooksButton; // button instance variable
    private JButton checkItemLlistButton; // button instance variable
    private JButton issueAnItemButton; // button instance variable
    private JButton manageStudentsButton; // button instance variable
    private JButton returnAnItemButton; // button instance variable
    private JPanel manageAllPanel; // panel instance variable
    private JButton manageAuthorizationButton; // button instance variable
    private JPanel libraryUsersPanel; // panel instance variable
    private JButton manageArticleButton; // button instance variable
    private JButton manageEJournalButton;// button instance variable
    private JButton manageEReadingBookButton; // button instance variable
    private JButton manageETextBookButton; // button instance variable
    private JButton manageTextBooksButton; // button instance variable
    private JButton backToTheLoginButton; // button instance variable

    private ArrayList<ArrayList<String>> allItems; // items.txt ArrayList
    private ArrayList<ArrayList<String>> personArrayList; // staff ArrayList
    private String eMail; // emailArrayList


    /**
     * MainPage Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons.
     */
    public MainPage() {
        add(mainPanel);// add panel to the frame
        setSize(1400,700);// set initial size
        setTitle("Library Management System Main Page");// set initial size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner
        allButtonActions(); // call function to enable functionality of all buttons
    }

    /**
     * MainPage Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for all buttons.
     * @param email is the staff's email logged in.
     * @param personArrayList staff ArrayList
     */
    public MainPage(String email, ArrayList<ArrayList<String>> personArrayList) {
        add(mainPanel);// add panel to the frame
        setSize(1400,700);// set initial size
        setTitle("Library Management System Main Page");// set initial size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner
        this.eMail = email; // assign e mail address
        this.personArrayList  = personArrayList; // assign staff Array List

        // set button sizes
        manageDigitalButton.setPreferredSize(new Dimension(40, 40));
        manageReadingBooksButton.setPreferredSize(new Dimension(40, 40));
        manageReadingBooksButton.setPreferredSize(new Dimension(40, 40));
        checkItemLlistButton.setPreferredSize(new Dimension(40, 40));
        issueAnItemButton.setPreferredSize(new Dimension(40, 40));
        manageStudentsButton.setPreferredSize(new Dimension(40, 40));
        returnAnItemButton.setPreferredSize(new Dimension(40, 40));
        manageAuthorizationButton.setPreferredSize(new Dimension(40, 40));
        manageArticleButton.setPreferredSize(new Dimension(40, 40));
        manageEJournalButton.setPreferredSize(new Dimension(40, 40));
        manageEReadingBookButton.setPreferredSize(new Dimension(40, 40));
        manageETextBookButton.setPreferredSize(new Dimension(40, 40));
        manageTextBooksButton.setPreferredSize(new Dimension(40, 40));
        backToTheLoginButton.setPreferredSize(new Dimension(40, 40));

        this.allItems = getItemsArrayList(); // create all items in db
        allButtonActions();// call function to enable functionality of all buttons
    }


    /**
     * All Button Actions Function
     * This function enables functionality of all buttons in mainPage screen.
     * It chechks user has permission to access these screens and opens the screens
     * when the button is pressed if he/she has permission
     * @return nothing.
     */
    public void allButtonActions() {
        LoginPage lgPage = new LoginPage(); // create loginPage object
        ArrayList<Admin> adminArrayList = lgPage.getAdminArrayList(); // get adminArrayList
        String email = getEMail(); // get Email
        boolean permissionAdmin = lgPage.getPermission(email); // get permission
        ArrayList<ArrayList<String>> personArrayList = getPersonArrayList();

        backToTheLoginButton.addActionListener(new ActionListener() {
            /**
             * Back to the login button function
             * This function returns the user to the login page
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lgPage.setVisible(true); // open login page
                dispose();
            }
        });

        manageDigitalButton.addActionListener(new ActionListener() {
            /**
             * Manage Digital Button
             * This function directs the staff to manage digital
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("permisison:"+permissionAdmin);
                if(permissionAdmin) { // check permission
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    DigitalScreen digiScreen = new DigitalScreen(allItems);
                    digiScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        manageReadingBooksButton.addActionListener(new ActionListener() {
            /**
             * Manage ReadingBooks Button
             * This function directs the staff to manage reading book
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    ReadingBookScreen rBScreen = new ReadingBookScreen(allItems);
                    rBScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        checkItemLlistButton.addActionListener(new ActionListener() {
            /**
             * List All Items Button
             * This function directs the staff to manage digital
             * items screen.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReadingBookScreen rdScreen2 = new ReadingBookScreen(allItems);
                DigitalScreen digiScreen2 = new DigitalScreen(allItems);
                EJournalScreen eJScreen2 = new EJournalScreen(allItems);
                EReadingBookScreen eRScreen2 = new EReadingBookScreen(allItems);
                ETextBookScreen eTextScreen2 = new ETextBookScreen(allItems);
                ArticleScreen artScreen2 = new ArticleScreen(allItems);
                TextBookScreen txtBkScreen2 = new TextBookScreen(allItems);


                ArrayList<Digital> digitalArrayList = digiScreen2.createDigitalClassArrayList(allItems);
                ArrayList<ReadingBook> readingBookArrayList = rdScreen2.createReadingBookClassArrayList(allItems);
                ArrayList<EJournal> eJournalArrayList = eJScreen2.createEJournalClassArrayList(allItems);
                ArrayList<EReadingBook> eReadingBookArrayList = eRScreen2.createEReadingBookClassArrayList(allItems);
                ArrayList<ETextBook> eTextBookArrayList = eTextScreen2.createETextBookClassArrayList(allItems);
                ArrayList<Article> articleArrayList = artScreen2.createArticleClassArrayList(allItems);
                ArrayList<TextBook> textBookArrayList = txtBkScreen2.createTextBookClassArrayList(allItems);

                ItemsScreen itemsScreen = new ItemsScreen( digitalArrayList, readingBookArrayList,
                        eJournalArrayList,eReadingBookArrayList,
                        eTextBookArrayList,articleArrayList,
                        textBookArrayList);
                itemsScreen.setVisible(true);
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            /**
             * Manage Students Button
             * This function directs the staff to manage students
             * screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    LoginPage lgPage = new LoginPage();
                    ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                    StudentScreen stuScreen = new StudentScreen(personArrayList);
                    stuScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        issueAnItemButton.addActionListener(new ActionListener() {
            /**
             * Manage Issues Button
             * This function directs the staff to manage issued items.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                LoginPage lgPage = new LoginPage();
                ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();

                IssueAnItemScreen isuScreen = new IssueAnItemScreen(personArrayList,allItems);
                isuScreen.setVisible(true);
            }
        });
        returnAnItemButton.addActionListener(new ActionListener() {
            /**
             * Return Item Button
             * This function directs the staff to manage return items screen.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReturnItemScreen retScreen = new ReturnItemScreen(allItems);
                retScreen.setVisible(true);
            }
        });
        /**
         * Manage Authorization People Button
         * This function directs the staff to manage staff
         * screen if he/she has permission.
         * @param e is the pressing event.
         */

        manageAuthorizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    LoginPage lgPage = new LoginPage();
                    ArrayList<ArrayList<String>> personArrayList = lgPage.getPersonArrayList();
                    PersonEditScreen prsEdScreen = new PersonEditScreen(personArrayList);
                    prsEdScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        manageArticleButton.addActionListener(new ActionListener() {
            /**
             * Manage Article Button
             * This function directs the staff to manage article
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    ArticleScreen artScreen = new ArticleScreen(allItems);
                    artScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        manageEJournalButton.addActionListener(new ActionListener() {
            /**
             * Manage EJournal Button
             * This function directs the staff to manage ejournal
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    EJournalScreen ejournalScreen = new EJournalScreen(allItems);
                    ejournalScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        manageEReadingBookButton.addActionListener(new ActionListener() {
            /**
             * Manage EReadingBook Button
             * This function directs the staff to manage ereading book
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    EReadingBookScreen eReadingBookScreen = new EReadingBookScreen(allItems);
                    eReadingBookScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        manageETextBookButton.addActionListener(new ActionListener() {
            /**
             * Manage ETextBook Button
             * This function directs the staff to manage etext book
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    ETextBookScreen eTextBookScreen = new ETextBookScreen(allItems);
                    eTextBookScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        manageTextBooksButton.addActionListener(new ActionListener() {
            /**
             * Manage TextBook Button
             * This function directs the staff to manage text book
             * items screen if he/she has permission.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                    TextBookScreen aTextBookScreen = new TextBookScreen(allItems);
                    aTextBookScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    /**
     * Get Items ArrayList
     * This function creates an ArrayList for all items in database.
     * @return all items String nested ArrayList.
     */
    public ArrayList<ArrayList<String>> getItemsArrayList() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);
                ArrayList<String> aBook = new ArrayList<String>();

                for(int i=0; i < singleItem.length;i++) {
                    aBook.add(singleItem[i]);
                }
                items.add(aBook);
                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return items;
    }


    /**
     * Get Last Item's ID
     * This function returns the last item's ID in the database file.
     * Or it can return the last item's another integer value in the database file
     * It is used to add new item to the database and add new staff with new staff ID to the
     * database.
     * @param fileName is the filename that we are searching for that item.
     * @param searchedIndex is the index that we are searching for in the list
     * @return the last item's id.
     */
    public int getLastItemID(String fileName, int searchedIndex) {
        int lastID = 0;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);
                lastID = Integer.parseInt(singleItem[searchedIndex]); // find the last ID
                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lastID;
    }

    /**
     * Check Input Numeric Function
     * This function checks the user's input in the textfield is numeric or not.
     * If the input is not numeric, the item will not be added to the databse with
     * wrong values, error message will be displayed
     * @param strNum is the input
     * @param mainPanel is the panel of the screen
     * @return boolean value for numeric or not
     */
    public static boolean isNumeric(String strNum,JPanel mainPanel) {
        if (strNum == null) {
            return false;
        }
        try { // try to convert string to double
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) { // catch error, input is not numeric
            JOptionPane.showMessageDialog(mainPanel,
                    "There is/are invalid values for item variables! Please check inputs!",
                    "Issue-Return Message",
                    JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("NUMBER FORMAT EXCEPTION IS CAUGHT");
            return false; // input is not numeric
        }
        return true; // input is numeric
    }

    /**
     * WriteToTxt Function
     * This function writes the updated or new array to the txt file.
     * @param data is the values that will be placed to txt file
     * @param file is the file name
     * @param appendOrWriteAll append (true) or rewrite (false) choose
     */
    public static void writeToTxt(String data, String file,boolean appendOrWriteAll) {
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


    /**
     * Create Table Function
     * This function creates a table with given array.
     * @param table1 is the table name
     * @param TextBookArray is the list that will be displayed
     * @param labelOfTable is the labels for the table
     */
    public static void createTable(JTable table1, String[][] TextBookArray,String[] labelOfTable) {
        table1.setModel(new DefaultTableModel(
                TextBookArray,
                labelOfTable
        ));
    }

    //getters and setters
    public String getEMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public ArrayList<ArrayList<String>> getPersonArrayList() {
        return personArrayList;
    }

    public void setPersonArrayList(ArrayList<ArrayList<String>> personArrayList) {
        this.personArrayList = personArrayList;
    }

}
