package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EJournalScreen extends JFrame{
    private JTextField titleTextField;
    private JTextField locationTextField;
    private JTextField itemTypeTextField;
    private JTextField publisherTextField;
    private JTextField statusTextField;
    private JTextField ISSNTextField;
    private JTextField URLTextField;
    private JTextField topicTextField;
    private JTextField peerReviewedTextField;
    private JTextField searchTitleTextField;
    private JTextField searchTopicTextField;
    private JButton searchTitleButton;
    private JButton searchTopicButton;
    private JTable table1;
    private JButton addNewEJournalButton;
    private JButton deleteSelectedEJournalButton;
    private JButton createStickerForSelectedButton;
    private JPanel mainPanel;
    private JRadioButton peerReviewedRadioButton;
    private JRadioButton notPeerReviewedRadioButton;
    String peerReviewedType;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Publisher","ISSN","Topic","URL","Peer Reviewed"};

    public EJournalScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E-Journal Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public EJournalScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("E-Journal Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(peerReviewedRadioButton);
        group.add(notPeerReviewedRadioButton);

        this.allItems = allItems;

        ArrayList<EJournal> EJournalArrayList = createEJournalClassArrayList(allItems);
        String[][] EJournalArray = getEJournalArray(EJournalArrayList);
        mainPage.createTable(table1,EJournalArray,labelOfTable);

        /**
         *
         * HUM EKLEDI *********************
         *
         *
         */

        ImageIcon searchTopicIcon = new ImageIcon("search.png");
        Image searchTopicIconScale = searchTopicIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchTopicIconScale= new ImageIcon(searchTopicIconScale);
        searchTopicButton.setIcon(scaledSearchTopicIconScale);

        ImageIcon searchTitleIcon = new ImageIcon("search.png");
        Image searchTitleIconScale = searchTitleIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchTitleIconScale= new ImageIcon(searchTitleIconScale);
        searchTitleButton.setIcon(scaledSearchTitleIconScale);
        

        addNewEJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEJournal = new mainPage();
                int lastID = itScreenForEJournal.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddEJournalButton(modelTable, lastID);
            }
        });

        deleteSelectedEJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEJournal = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEJournal.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteEJournalButton(modelTable, allItems);
            }
        });


        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForEJournal = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForEJournal.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                System.out.println(stickerText);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);
            }
        });

        searchTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenEJournal = new SearchScreen(searchedTitle,EJournalArray,labelOfTable,1,10);
                searchScreenEJournal.setVisible(true);
            }
        });

        searchTopicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTopic = searchTopicTextField.getText();
                SearchScreen searchScreenEJournal = new SearchScreen(searchedTopic,EJournalArray,labelOfTable,7,10);
                searchScreenEJournal.setVisible(true);

            }
        });


    }
    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        int selectedRowNo = table1.getSelectedRow();
        //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
        int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

        String stickerText ="";
        for(int i=0; i <allItems.size(); i++) {
            if ( Integer.parseInt(allItems.get(i).get(0)) == selectedRowID) {
                System.out.println(allItems.get(i).get(1)+"-"+selectedRowID);
                stickerText = String.format(
                        "%-29s%-35s\n%-28s%-35s\n%-24s%-35s\n%-26s%-35s\n%-25s%-35s\n"+
                                "%-23s%-35s\n%-25s%-35s\n%-25s%-35s\n%-25s%-35s\n%-18s%-35s\n"
                        ,
                        "ID:",allItems.get(i).get(0),
                        "Title:",allItems.get(i).get(1),
                        "Location:",allItems.get(i).get(2),
                        "Type:",allItems.get(i).get(3),
                        "Status:",allItems.get(i).get(4),

                        "Publisher:",allItems.get(i).get(5),
                        "ISSN:",allItems.get(i).get(6),
                        "Topic:",allItems.get(i).get(7),
                        "URL:",allItems.get(i).get(8),
                        "Peer Reviewed:",allItems.get(i).get(9)
                );
            }
        }
        return stickerText;

    }
    public void pressDeleteEJournalButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        System.out.println(itemsArrayList);
        System.out.println("********");

        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));

            int rowWillBeDeleted=0;
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(i).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) {
                        System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }

            System.out.println("rowwillbedeleted"+rowWillBeDeleted);
            itemsArrayList.remove(rowWillBeDeleted);

            String newEJournalText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newEJournalText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newEJournalText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newEJournalText += "\n";
                }
            }

            System.out.println("newbooktext: "+newEJournalText);

            mainPage.writeToTxt(newEJournalText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }
    public void pressAddEJournalButton(DefaultTableModel modelTable,  int lastID){
        if(peerReviewedRadioButton.isSelected()) {
            peerReviewedType = "true";
        }
        else if(notPeerReviewedRadioButton.isSelected()) {
            peerReviewedType = "false";
        }
        else {
            JOptionPane.showMessageDialog(mainPanel,
                    "Please select a peer reviewed type!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newEJournalText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                itemTypeTextField.getText()+","+statusTextField.getText()+","+
                publisherTextField.getText() +","+ ISSNTextField.getText()+","+
                topicTextField.getText()+","+ URLTextField.getText()+","+
                peerReviewedType;

        mainPage.writeToTxt(newEJournalText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                itemTypeTextField.getText(),
                statusTextField.getText(),
                publisherTextField.getText(),
                ISSNTextField.getText(),
                topicTextField.getText(),
                URLTextField.getText(),
                peerReviewedType,
        });


    }
    public ArrayList<EJournal> createEJournalClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<EJournal> EJournalClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("EJournal")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                EJournal aNewEjournal = new EJournal(
                        Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                        itemsArrayList.get(i).get(3), // String itemType
                        itemsArrayList.get(i).get(1), // String title
                        itemsArrayList.get(i).get(2), // String locationInformation
                        itemsArrayList.get(i).get(4), // String status
                        itemsArrayList.get(i).get(5),
                        itemsArrayList.get(i).get(6),
                        itemsArrayList.get(i).get(7),
                        itemsArrayList.get(i).get(8),
                        Boolean.parseBoolean(itemsArrayList.get(i).get(9))
                );
                EJournalClassArray.add(aNewEjournal);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return EJournalClassArray;
    }
    public String[][] getEJournalArray(ArrayList<EJournal> EJournalArrayList) {
        String[][] EJournalArray = new String[EJournalArrayList.size()][10];
        for(int i=0; i < EJournalArrayList.size();i++) {
            EJournalArray[i][0] = String.valueOf(EJournalArrayList.get(i).getId());
            EJournalArray[i][1] = EJournalArrayList.get(i).getTitle();
            EJournalArray[i][2] = EJournalArrayList.get(i).getLocationInformation();
            EJournalArray[i][3] = EJournalArrayList.get(i).getItemType();
            EJournalArray[i][4] = EJournalArrayList.get(i).getStatus();
            EJournalArray[i][5] = EJournalArrayList.get(i).getPublisher();
            EJournalArray[i][6] = EJournalArrayList.get(i).getISSN();
            EJournalArray[i][7] = EJournalArrayList.get(i).getTopic();
            EJournalArray[i][8] = EJournalArrayList.get(i).getUrl();
            EJournalArray[i][9] = String.valueOf(EJournalArrayList.get(i).isPeerReviewed());
        }

        return EJournalArray;
    }


}
