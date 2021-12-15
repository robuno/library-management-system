package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ArticleScreen extends JFrame implements Sticker {
    private JPanel mainPanel;
    private JTextField titleTextField;
    private JTextField locationTextField;
    private JTextField articleTextField;
    private JTextField availableTextField;
    private JTextField authorTextField;
    private JTextField instutitionTextField;
    private JTextField sourceTextField;
    private JTextField keywordTextField;
    private JTextField doiTextField;
    private JTextField databaseTextField;
    private JButton addNewArticleButton;
    private JButton deleteSelectedArticleButton;
    private JButton createStickerForSelectedButton;
    private JTable table1;
    private JTextField searchTitleTextField;
    private JButton buttonSearchTitle;
    private JTextField searchAuthorTextField;
    private JButton buttonSearchAuthor;
    private JTextField searchKeywordTextField;
    private JButton buttonSearchKeyword;

    private ArrayList<ArrayList<String>> allItems;
    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Institution","Source","Keyword","Database",
            "DOI"};

    public ArticleScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Article Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public ArticleScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Article Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.allItems = allItems;

        ArrayList<Article> articleArrayList = createArticleClassArrayList(allItems);
        String[][] articleArray = getArticleArray(articleArrayList);
        mainPage.createTable(table1,articleArray,labelOfTable);

        addNewArticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForArticle = new mainPage();
                int lastID = itScreenForArticle.getLastItemID("items.txt",0);

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressAddArticleButton(modelTable, lastID);
            }
        });

        deleteSelectedArticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForArticle = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForArticle.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                pressDeleteArticleButton(modelTable, allItems);
            }
        });

        createStickerForSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPage itScreenForArticle = new mainPage();
                ArrayList<ArrayList<String>> allItems = itScreenForArticle.getItemsArrayList();

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel();
                String stickerText = createSticker(modelTable,allItems);
                StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                stckScreen.setVisible(true);
            }
        });

        buttonSearchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText();
                SearchScreen searchScreenArticle = new SearchScreen(searchedTitle,articleArray,labelOfTable,1,11);
                searchScreenArticle.setVisible(true);
            }
        });

        buttonSearchKeyword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedKeyword = searchKeywordTextField.getText();
                SearchScreen searchScreenArticle = new SearchScreen(searchedKeyword,articleArray,labelOfTable,8,11);
                searchScreenArticle.setVisible(true);

            }
        });

        buttonSearchAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText();
                SearchScreen searchScreenArticle = new SearchScreen(searchedAuthor,articleArray,labelOfTable,5,11);
                searchScreenArticle.setVisible(true);

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
                        "%-25s%-35s\n%-25s%-35s\n%-21s%-35s\n%-23s%-35s\n%-22s%-35s\n"+
                                "%-22s%-35s\n%-22s%-35s\n%-21s%-35s\n%-19s%-35s\n%-19s%-35s\n"+
                                "%-23s%-35s"
                        ,
                        "ID:",allItems.get(i).get(0),
                        "Title:",allItems.get(i).get(1),
                        "Location:",allItems.get(i).get(2),
                        "Type:",allItems.get(i).get(3),
                        "Status:",allItems.get(i).get(4),

                        "Author:",allItems.get(i).get(5),
                        "Institution:",allItems.get(i).get(6),
                        "Source:",allItems.get(i).get(7),
                        "Keyword:",allItems.get(i).get(8),
                        "Database:",allItems.get(i).get(9),

                        "DOI:",allItems.get(i).get(10)
                );
            }
        }
        //System.out.println(stickerText);
        return stickerText;

    }

    public void pressDeleteArticleButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
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

            String newArticleText = "";

            System.out.println(itemsArrayList);
            System.out.println("********");
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newArticleText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) {
                        newArticleText += ",";
                    }
                }
                if((i+1) != itemsArrayList.size()) {
                    newArticleText += "\n";
                }
            }

            System.out.println("newbooktext: "+newArticleText);

            mainPage.writeToTxt(newArticleText,"items.txt",false);
            modelTable.removeRow(table1.getSelectedRow());
        }
    }

    public void pressAddArticleButton(DefaultTableModel modelTable,  int lastID){
        String newArticleText = "\n"+ ( lastID+1) +","+
                titleTextField.getText() +","+locationTextField.getText()+","+
                articleTextField.getText()+","+availableTextField.getText()+","+
                authorTextField.getText() +","+ instutitionTextField.getText()+","+
                sourceTextField.getText()+","+ keywordTextField.getText()+","+
                databaseTextField.getText()+","+doiTextField.getText();

        mainPage.writeToTxt(newArticleText,"items.txt",true);

        modelTable.addRow(new Object[] {
                lastID+1,
                titleTextField.getText(),
                locationTextField.getText(),
                articleTextField.getText(),
                availableTextField.getText(),
                authorTextField.getText(),
                instutitionTextField.getText(),
                sourceTextField.getText(),
                keywordTextField.getText(),
                databaseTextField.getText(),
                doiTextField.getText(),
        });
    }

    public ArrayList<Article> createArticleClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<Article> articleClassArray = new ArrayList<>();
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("Article")) {
                //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
                Article aNewArticle = new Article(
                        Integer.parseInt(itemsArrayList.get(i).get(0)), // int id
                        itemsArrayList.get(i).get(3), // String itemType
                        itemsArrayList.get(i).get(1), // String title
                        itemsArrayList.get(i).get(2), // String locationInformation
                        itemsArrayList.get(i).get(4), // String status
                        itemsArrayList.get(i).get(5), // String author
                        itemsArrayList.get(i).get(6), // String institution
                        itemsArrayList.get(i).get(7), // String source
                        itemsArrayList.get(i).get(8), // String keyword
                        itemsArrayList.get(i).get(9), // String database
                        itemsArrayList.get(i).get(10) // String DOI

                );
                articleClassArray.add(aNewArticle);
                //System.out.println(aNewDigital.getDirector());
            }
        }

        return articleClassArray;
    }

    public String[][] getArticleArray(ArrayList<Article> articleArrayList) {
        String[][] articleArray = new String[articleArrayList.size()][11];
        for(int i=0; i < articleArrayList.size();i++) {
            articleArray[i][0] = String.valueOf(articleArrayList.get(i).getId());
            articleArray[i][1] = articleArrayList.get(i).getTitle();
            articleArray[i][2] = articleArrayList.get(i).getLocationInformation();
            articleArray[i][3] = articleArrayList.get(i).getItemType();
            articleArray[i][4] = articleArrayList.get(i).getStatus();
            articleArray[i][5] = articleArrayList.get(i).getAuthor();
            articleArray[i][6] = articleArrayList.get(i).getInstitution();
            articleArray[i][7] = articleArrayList.get(i).getSource();
            articleArray[i][8] = articleArrayList.get(i).getKeyword1();
            articleArray[i][9] = articleArrayList.get(i).getDatabase();
            articleArray[i][10] = articleArrayList.get(i).getDoi();
        }

        /*
        for(int i=0; i < digitalArray.length; i++) {
            for(int j=0; j < digitalArray[i].length; j++) {
                System.out.print(digitalArray[i][j]+",");
            }
            System.out.println();
        }

         */


        return articleArray;
    }

}
