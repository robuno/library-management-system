package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingBookScreen extends JFrame{

    private JPanel mainPanel;
    private JTable table1;
    private JPanel addBookPanel;
    private JTextField textFieldTitle;
    private JTextField readingBookTextField;
    private JTextField textFieldLocation;
    private JTextField textFieldAuthor;
    private JTextField textFieldPublisher;
    private JTextField textFieldLang;
    private JTextField textFieldYear;
    private JTextField textFieldEdition;
    private JTextField textFieldPageNo;
    private JTextField textFieldISBN;
    private JTextField textFieldGenre;
    private JButton addNewReadingBookButton;

    public ReadingBookScreen() {
        add(mainPanel);
        setSize(1800,900);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[][] readingBookArray = getReadingBookArray();
        System.out.println(readingBookArray.length);

        createTable(readingBookArray);

        DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
        addNewReadingBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String newBookText = "\n"+ (numberOfItems() +1) +","+
                            textFieldTitle.getText() +","+ textFieldAuthor.getText() +","+
                            readingBookTextField.getText()+","+
                            textFieldEdition.getText()+","+textFieldISBN.getText()+","+
                            textFieldGenre.getText()+","+textFieldLang.getText()+","+
                            textFieldLocation.getText()+","+textFieldPageNo.getText()+","+
                            textFieldPublisher.getText()+","+textFieldYear.getText();
                    File f1 = new File("items.txt");

                    FileWriter fileWritter = new FileWriter(f1.getName(),true);
                    BufferedWriter bw = new BufferedWriter(fileWritter);
                    bw.write(newBookText);
                    bw.close();
                } catch(IOException e2){
                    e2.printStackTrace();
                }

                modelTable.addRow(new Object[] {
                        numberOfItems(),
                        textFieldTitle.getText(),
                        textFieldLocation.getText(),
                        readingBookTextField.getText(),
                        textFieldAuthor.getText(),
                        textFieldEdition.getText(),
                        textFieldISBN.getText(),
                        textFieldGenre.getText(),
                        textFieldLang.getText(),
                        textFieldPageNo.getText(),
                        textFieldPublisher.getText(),
                        textFieldYear.getText()

                });


            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private int numberOfItems() {
        ArrayList<ArrayList<String>> items = new ArrayList<>();

        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleBook = data.split(",",0);

                ArrayList<String> aBook = new ArrayList<String>();

                aBook.add(singleBook[0]);
                aBook.add(singleBook[1]);
                aBook.add(singleBook[2]);
                aBook.add(singleBook[3]);

                items.add(aBook);

                System.out.println(singleBook[0]+","+singleBook[1]+","+singleBook[2]+","+singleBook[3]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(items);

        String[][] readingBooksArray = new String[items.size()][items.get(1).size()];
        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length; j++) {
                readingBooksArray[i][j] = items.get(i).get(j);
            }
        }

        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length; j++) {
                System.out.println(readingBooksArray[i][j]);
            }
        }
        return items.size();
    }
    private String[][] getReadingBookArray() {
        ArrayList<ArrayList<String>> books = new ArrayList<>();

        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] singleBook = data.split(",",0);

                ArrayList<String> aBook = new ArrayList<String>();

                aBook.add(singleBook[0]);
                aBook.add(singleBook[1]);
                aBook.add(singleBook[2]);
                aBook.add(singleBook[3]);

                if(aBook.get(3).equals("ReadingBook")) {
                    books.add(aBook);
                }

                //System.out.println(singleBook[0]+","+singleBook[1]+","+singleBook[2]+","+singleBook[3]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(books);

        String[][] readingBooksArray = new String[books.size()][books.get(1).size()];
        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length; j++) {
                readingBooksArray[i][j] = books.get(i).get(j);
            }
        }

        for(int i =0; i < readingBooksArray.length; i++) {
            for(int j=0; j < readingBooksArray[0].length; j++) {
                //System.out.println(readingBooksArray[i][j]);
            }
        }
        return readingBooksArray;
    }

    private void createReadingBookClassArray(String[][] readingBooksArray) {

    }

    private void createTable(String[][] readingBooksArray) {
        table1.setModel(new DefaultTableModel(
                readingBooksArray, new String[]{"ID","Title","Location","Item Type"}
        ));

    }
}
