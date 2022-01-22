package com.teksen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <h1> ItemsScreen Class </h1>
 * The ItemsScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, textfields, buttons. This class creates a functionality for
 * form of the screen.
 * In this screen, program has a textfields to get user input to search
 * items in database and a table that shows all items by category.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class ItemsScreen extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JTable table1; // table instance variable
    private JTextField searchTitleTextField; // textfield instance variable
    private JButton buttonSearchTitle; // button instance variable

    private ArrayList<Item> itemArrayList;
    static String[] labelOfTable = new String[] {"ID","Title","Location", "Item Type","Status"}; // labels for table


    /**
     * ItemsScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     * This constructor has listener for search item button.
     * @param digitalArrayList is the digital items ArrayList
     * @param readingBookArrayList is the reading book items ArrayList
     * @param eJournalArrayList is the e journal items ArrayList
     * @param eReadingBookArrayList is the e reading book items ArrayList
     * @param eTextBookArrayList is the e text book items ArrayList
     * @param articleArrayList is the article items ArrayList
     * @param textBookArrayList is the text book items ArrayList
     */
    public ItemsScreen(ArrayList<Digital> digitalArrayList,ArrayList<ReadingBook> readingBookArrayList,
                       ArrayList<EJournal> eJournalArrayList, ArrayList<EReadingBook> eReadingBookArrayList,
                       ArrayList<ETextBook> eTextBookArrayList, ArrayList<Article> articleArrayList,
                       ArrayList<TextBook> textBookArrayList) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Items Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner


        // create itemsArrayList by adding all ArrayLists into it
        itemArrayList = createItemArrayList(digitalArrayList,readingBookArrayList,
                eJournalArrayList,eReadingBookArrayList,
                eTextBookArrayList,articleArrayList,
                textBookArrayList);
        String[][] itemArray = getItemArray(itemArrayList); // get items Array
        MainPage.createTable(table1,itemArray,labelOfTable); // create table

        ImageIcon searchTitleIcon = new ImageIcon("search.png");  // upload search icon
        Image searchTitleIconScale = searchTitleIcon.getImage().getScaledInstance(14, 13, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchTitleIconScale= new ImageIcon(searchTitleIconScale);  // upload search icon
        buttonSearchTitle.setIcon(scaledSearchTitleIconScale); // display search icon

        // create listener for search item
        buttonSearchTitle.addActionListener(new ActionListener() {
            /**
             * Search Title in Items
             * This function get text from user and searches the title in database by
             * creating SearchScreen object
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTitle = searchTitleTextField.getText(); // get text from user
                SearchScreen searchScreenDigital = new SearchScreen(searchedTitle,itemArray,labelOfTable,1,5);
                searchScreenDigital.setVisible(true); // display results in Search Screen
            }
        });
    }

    /**
     * Create Item Array List Function
     * This function creates an Items ArrayList with polymorphism.
     * It adds all values in arraylists given in the parameter to the items ArrayList
     * @param digitalArrayList is the digital items ArrayList
     * @param readingBookArrayList is the reading book items ArrayList
     * @param eJournalArrayList is the e journal items ArrayList
     * @param eReadingBookArrayList is the e reading book items ArrayList
     * @param eTextBookArrayList is the e text book items ArrayList
     * @param articleArrayList is the article items ArrayList
     * @param textBookArrayList is the text book items ArrayList
     * @return arrayList has Item objects ( all items in db)
     */
    private ArrayList<Item> createItemArrayList(ArrayList<Digital> digitalArrayList,ArrayList<ReadingBook> readingBookArrayList,
                                                ArrayList<EJournal> eJournalArrayList, ArrayList<EReadingBook> eReadingBookArrayList,
                                                ArrayList<ETextBook> eTextBookArrayList, ArrayList<Article> articleArrayList,
                                                ArrayList<TextBook> textBookArrayList) {
        ArrayList<Item> itemArrayList = new ArrayList<>(); // create Item ArrayList

        itemArrayList.addAll(readingBookArrayList);
        itemArrayList.addAll(digitalArrayList);
        itemArrayList.addAll(eJournalArrayList);
        itemArrayList.addAll(eReadingBookArrayList);
        itemArrayList.addAll(eTextBookArrayList);
        itemArrayList.addAll(articleArrayList);
        itemArrayList.addAll(textBookArrayList);

        return itemArrayList;
    }

    /**
     * Get Items Array Function
     * This function crates Items array to display items in table
     * @param itemArrayList is the ArrayList contains all items
     * @return Items nested array.
     */
    public String[][] getItemArray(ArrayList<Item> itemArrayList) {
        String[][] itemArray = new String[itemArrayList.size()][5];
        for(int i=0; i < itemArrayList.size();i++) {
            itemArray[i][0] = String.valueOf(itemArrayList.get(i).getId());
            itemArray[i][1] = itemArrayList.get(i).getTitle();
            itemArray[i][2] = itemArrayList.get(i).getLocationInformation();
            itemArray[i][3] = itemArrayList.get(i).getItemType();
            itemArray[i][4] = itemArrayList.get(i).getStatus();
        }

        return itemArray;
    }


}
