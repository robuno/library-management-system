package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class mainPage extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JButton manageDigital;
    private JButton manageReadingBooksButton;
    private JButton checkItemLlistButton;
    private JButton issueAnItemButton;
    private JButton manageStudentsButton;
    private JButton returnAnItemButton;
    private JPanel manageAllPanel;
    private JButton manageAuthorizationButton;
    private JPanel libraryUsersPanel;
    private JButton manageArticleButton;
    private JButton manageEJournalButton;
    private JButton manageEReadingBookButton;
    private JButton manageETextBookButton;
    private JButton manageTextBooksButton;

    private ArrayList<ArrayList<String>> allItems;
    private String eMail;


    public mainPage() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Library Management System Main Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // String[][] readingBookArray = getItemArray(allItems);
        //createTable(readingBookArray);
        allButtonActions();
    }

    public mainPage(String email) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Library Management System Main Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.eMail = email;
        loginPage lgPage = new loginPage();
        //System.out.println("--------mail: "+lgPage.geteMail());

        this.allItems = getItemsArrayList();
        // String[][] readingBookArray = getItemArray(allItems);
        allButtonActions();

    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void allButtonActions() {
        PersonEditScreen prsScreen = new PersonEditScreen();
        ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();
        loginPage newLoginPage = new loginPage();
        String email = geteMail();
        //System.out.println("mail parameter: "+ email);
        boolean permissionAdmin = newLoginPage.getPermission(email, personArrayList);
        //createTable(readingBookArray);
        manageDigital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("permisison:"+permissionAdmin);
                if(permissionAdmin) {
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
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("permisison:"+permissionAdmin);
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
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    PersonEditScreen prsScreen = new PersonEditScreen();
                    ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();
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
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                PersonEditScreen prsScreen = new PersonEditScreen();
                ArrayList<ArrayList<String>> personArrayList = prsScreen.getPersonArrayList();

                IssueAnItemScreen isuScreen = new IssueAnItemScreen(personArrayList,allItems);
                isuScreen.setVisible(true);
            }
        });
        returnAnItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> allItems = getItemsArrayList();
                ReturnItemScreen retScreen = new ReturnItemScreen(allItems);
                retScreen.setVisible(true);
            }
        });
        manageAuthorizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permissionAdmin) {
                    PersonEditScreen prsEdScreen = new PersonEditScreen();
                    prsEdScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"You do not have permission to manage items.",
                            "Permission Information",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        manageArticleButton.addActionListener(new ActionListener() {
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return items;
    }


    public int getLastItemID(String fileName, int searchedIndex) {
        int lastID = 0;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleItem = data.split(",",0);


                lastID = Integer.parseInt(singleItem[searchedIndex]);

                //System.out.println(aBook);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lastID;
    }

    public static boolean isNumeric(String strNum,JPanel mainPanel) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(mainPanel,
                    "There is/are invalid values for item variables! Please check inputs!",
                    "Issue-Return Message",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("NUMBER FORMAT EXCEPTION IS CAUGHT");
            return false;
        }
        return true;
    }

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

    public static void createTable(JTable table1, String[][] TextBookArray,String[] labelOfTable) {
        table1.setModel(new DefaultTableModel(
                TextBookArray,
                labelOfTable
        ));
    }



}
