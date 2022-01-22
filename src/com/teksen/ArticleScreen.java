package com.teksen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <h1> ArticleScreen Class </h1>
 * The ArticleScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has item buttons, add/delete item buttons,
 * create sticker for selected item button and a table that lists items
 * for Article class.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */

public class ArticleScreen extends JFrame implements Sticker {
    private JPanel mainPanel; // panel instance variable
    private JTextField titleTextField; // textfield instance variable
    private JTextField locationTextField; // textfield instance variable
    private JTextField articleTextField; // textfield instance variable
    private JTextField availableTextField; // textfield instance variable
    private JTextField authorTextField; // textfield instance variable
    private JTextField instutitionTextField; // textfield instance variable
    private JTextField sourceTextField; // textfield instance variable
    private JTextField keywordTextField; // textfield instance variable
    private JTextField doiTextField; // textfield instance variable
    private JTextField databaseTextField; // textfield instance variable
    private JButton addNewArticleButton; // button instance variable
    private JButton deleteSelectedArticleButton; // button instance variable
    private JButton createStickerForSelectedButton; // button instance variable
    private JTable table1; // table instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JButton buttonSearchTitle; // button instance variable
    private JTextField searchAuthorTextField; // textfield instance variable
    private JButton buttonSearchAuthor; // button instance variable
    private JTextField searchKeywordTextField; // textfield instance variable
    private JButton buttonSearchKeyword; // button instance variable

    static String[] labelOfTable = new String[] {"ID","Title","Location","Item Type","Status",
            "Author","Institution","Source","Keyword","Database",
            "DOI"}; // labels for table

    /**
     * Article Screen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listeners for "add, delete, create sticker" for item and search item buttons.
     * @param allItems is the all items in database
     */
    public ArticleScreen(ArrayList<ArrayList<String>> allItems) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Article Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner

        ArrayList<Article> articleArrayList = createArticleClassArrayList(allItems); // create Article arraylist
        String[][] articleArray = getArticleArray(articleArrayList); // create Article array for table values
        MainPage.createTable(table1,articleArray,labelOfTable); // create table

        ImageIcon searchAuthorIcon = new ImageIcon("search.png"); // upload search icon
        Image searchAuthorIconScale = searchAuthorIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH); // set icon size
        ImageIcon scaledSearchAuthorIconScale= new ImageIcon(searchAuthorIconScale); // create icon
        buttonSearchAuthor.setIcon(scaledSearchAuthorIconScale); // display search icon
        buttonSearchTitle.setIcon(scaledSearchAuthorIconScale); // display search icon
        buttonSearchKeyword.setIcon(scaledSearchAuthorIconScale); // display search icon

        // create listener for add new article button
        addNewArticleButton.addActionListener(new ActionListener() {
            /**
             * Add New Article Button
             * When the add new article button is pressed, get the last item's ID
             * to increment it for new item's ID. Create Table Model to update the table
             * after adding new item.
             *
             * Call AddArticleButton function to perform adding operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForArticle = new MainPage(); // create MainPage object
                int lastID = itScreenForArticle.getLastItemID("items.txt",0); // get last item's ID in database
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel(); // create model table
                pressAddArticleButton(modelTable, lastID); // call adding function
            }
        });

        // create listener for delete new article button
        deleteSelectedArticleButton.addActionListener(new ActionListener() {
            /**
             * Delete Selected Article Button
             * When the delete selected article button is pressed, get the all items in the array
             * to determine which object is going to be deleted in database and create table model
             * to update list.
             *
             * Call pressDeleteArticleButton function to perform removing operation
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForArticle = new MainPage(); // create MainPage object
                ArrayList<ArrayList<String>> allItems = itScreenForArticle.getItemsArrayList(); // get all items in database
                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel(); // create model table
                pressDeleteArticleButton(modelTable, allItems);  // call deleting function
            }
        });

        // create listener for creating new article button
        createStickerForSelectedButton.addActionListener(new ActionListener() {
            /**
             * Create Sticker Selected Article Button
             * When the create sticker for selected article button is pressed, get the all items in the array
             * to determine the item whose sticker will be created in database and create table model
             * to get the selected item. Create sticker text to display sticker for selected item.
             *
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage itScreenForArticle = new MainPage(); // create MainPage object
                ArrayList<ArrayList<String>> allItems = itScreenForArticle.getItemsArrayList(); // get all items in database

                DefaultTableModel modelTable =(DefaultTableModel)table1.getModel(); // create model table
                String stickerText = createSticker(modelTable,allItems);
                if(!stickerText.equals("")) {
                    StickerScreen stckScreen = new StickerScreen(stickerText, modelTable, table1);
                    stckScreen.setVisible(true);
                }
            }
        });

        buttonSearchTitle.addActionListener(new ActionListener() {
            /**
             * Search Title in Articles
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText(); // get text from user
                SearchScreen searchScreenArticle = new SearchScreen(searchedTitle,articleArray,labelOfTable,1,11); // search title
                searchScreenArticle.setVisible(true);  // display results in Search Screen
            }
        });

        buttonSearchKeyword.addActionListener(new ActionListener() {
            /**
             * Search Keyword in Articles
             * This function get text from user and searches the keyword in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedKeyword = searchKeywordTextField.getText(); // get text from user
                SearchScreen searchScreenArticle = new SearchScreen(searchedKeyword,articleArray,labelOfTable,8,11); // search keyword
                searchScreenArticle.setVisible(true); // display results in Search Screen
            }
        });

        buttonSearchAuthor.addActionListener(new ActionListener() {
            /**
             * Search Author in Articles
             * This function get text from user and searches the author in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedAuthor = searchAuthorTextField.getText(); // get text from user
                SearchScreen searchScreenArticle = new SearchScreen(searchedAuthor,articleArray,labelOfTable,5,11); // search author
                searchScreenArticle.setVisible(true); // display results in Search Screen
            }
        });
    }

    /**
     * CreateSticker Function
     * This function creates a sticker text to display the selected item's values in
     * sticker screen. To determine the item whose sticker will be displayed, user needs to select
     * a single row in the table and press the create sticker button.
     * @param modelTable is the table model for articles
     * @param allItems is the all items in database
     * @return sticker text for selected item.
     */
    public String createSticker( DefaultTableModel modelTable, ArrayList<ArrayList<String>> allItems) {
        if(table1.getSelectedRowCount() ==1) { // if one row is selected
            int selectedRowNo = table1.getSelectedRow(); // get row no of selected row
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))); // get id of the selected item

            String stickerText ="";
            for(int i=0; i <allItems.size(); i++) {
                if ( Integer.parseInt(allItems.get(i).get(0)) == selectedRowID) { // determine the all values for selected item
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
            return stickerText; // return sticker text for selected item
        } else {
            if(table1.getRowCount() == 0 ) { // if no row is selected
                JOptionPane.showMessageDialog(table1,"Table is empty.");
            } else { // if more than one row is selected
                JOptionPane.showMessageDialog(table1,"Please select single row to create sticker.");
            }
            return""; // return nothing
        }
    }

    /**
     * Delete Article Function
     * This function removes the selected item from database. To determine the which item will be removed,
     * user needs to select the single item from the table.
     * @param modelTable is the table model for articles
     * @param itemsArrayList is the all items in database
     * @return nothing.
     */
    public void pressDeleteArticleButton( DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList) {
        if(table1.getSelectedRowCount() ==1) { // if one row is selected
            int selectedRowNo = table1.getSelectedRow(); // get row no of selected row
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))); // get id of the selected item
            int rowWillBeDeleted=0; // determine the row will be deleted
            for(int i=0; i<itemsArrayList.size(); i++) {
                for(int j = 0; j< itemsArrayList.get(i).size(); j++) {
                    if( Integer.parseInt(itemsArrayList.get(i).get(0)) ==selectedRowID) { // determine the row number in db will be deleted
                        //System.out.print(itemsArrayList.get(i).get(j)+",");
                        rowWillBeDeleted =i;
                    }
                }
            }
            itemsArrayList.remove(rowWillBeDeleted); // remove the item from database

            String newArticleText = "";
            for(int i=0; i< itemsArrayList.size(); i++) {
                for(int j=0; j<itemsArrayList.get(i).size();j++) {
                    newArticleText += itemsArrayList.get(i).get(j);
                    if(!itemsArrayList.get(i).get(j).equals(itemsArrayList.get(i).get(itemsArrayList.get(i).size() -1))) { // if the selected value is not the last value of the line
                        newArticleText += ","; // rearrange all items in database after remove operation
                    }
                }
                if((i+1) != itemsArrayList.size()) { // after assigning all items to the variable
                    newArticleText += "\n"; // write new item
                }
            }

            MainPage.writeToTxt(newArticleText,"items.txt",false); // write updated items to database
            modelTable.removeRow(table1.getSelectedRow()); // update the database immediately
        }
    }

    /**
     * Add Article Function
     * This function add news article by getting new item's values from user
     * to database and the table.
     * @param modelTable is the table model.
     * @param lastID is the last id of the item in database
     * @return nothing.
     */
    public void pressAddArticleButton(DefaultTableModel modelTable,  int lastID){
        String newArticleText = "\n"+ ( lastID+1) +","+ // increment last item's database
                titleTextField.getText() +","+locationTextField.getText()+","+
                articleTextField.getText()+","+availableTextField.getText()+","+
                authorTextField.getText() +","+ instutitionTextField.getText()+","+
                sourceTextField.getText()+","+ keywordTextField.getText()+","+
                databaseTextField.getText()+","+doiTextField.getText();

        MainPage.writeToTxt(newArticleText,"items.txt",true); // write new item to end of the database

        // add new item to the database immediately
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

    /**
     * Create Article Array List Function
     * This function creates Article Array List by filtering from all items in the
     * database. This arraylist will be used to show selected items in the table.
     * @param itemsArrayList is the all items in database
     * @return Article ArrayList.
     */
    public ArrayList<Article> createArticleClassArrayList(ArrayList<ArrayList<String>> itemsArrayList) {
        ArrayList<Article> articleClassArray = new ArrayList<>(); // declare Article ArrayList
        for(int i=0; i<itemsArrayList.size();i++) {
            //System.out.println("itemsarraylist: "+itemsArrayList.get(i));
            if(itemsArrayList.get(i).get(3).equals("Article")) { // if the item type is article
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

    /**
     * Get Article Array Function
     * This function crates Article array to display items in table
     * @param articleArrayList is the ArrayList contains only Articles
     * @return Article nested array.
     */
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
        return articleArray;
    }

}
