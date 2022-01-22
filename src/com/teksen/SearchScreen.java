package com.teksen;
import javax.swing.*;
/**
 * <h1> SearchScreen Class </h1>
 * The SearchScreen program implements that
 * creates an instance variable to display the frame includes
 * panel, table. This class creates a
 * functionality for form of the screen.
 *
 * In this screen, table shows the matched items that are searched.
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class SearchScreen extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JTable table1;// table instance variable
    private JTextArea searchInfoTextArea; // text area instance variable

    /**
     * SearchScreen Constructor
     * This constructor adds the components of form to the frame.
     */
    public SearchScreen() {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Search Screen"); // set title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // enable to close window from x in the top right corner
    }

    /**
     * SearchScreen Constructor
     * This constructor adds the components of form to the frame. Then,
     * creates the table to show matched items. It assigns variables to show these
     * items.
     * @param searchedTitle is the string that is searched.
     * @param itemArray item list in array form
     * @param labelOfTable label of table
     * @param indexOfSearched index of the single row
     * @param lengthOfArray length of the single row
     */
    public SearchScreen(String searchedTitle,String[][] itemArray, String[] labelOfTable,int indexOfSearched, int lengthOfArray) {
        add(mainPanel); // add panel to the frame
        setSize(1400,700); // set initial size
        setTitle("Search Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner
        String[][] searchedItemsArray = getSearchedItems(searchedTitle, itemArray,indexOfSearched, lengthOfArray); //create searched items array
        MainPage.createTable(table1,searchedItemsArray,labelOfTable); // create table
    }

    /**
     * GetSearched Items Array
     * This function creates an array with items whose variable matches with wanted input's value.
     * @param searchedTitle text to be searched
     * @param itemArray all items in array form
     * @param indexOfSearched index of the searched text in a single row
     * @param lengthOfArray length of single row
     * @return full of items to be searched
     */
    public String[][] getSearchedItems(String searchedTitle,String[][] itemArray, int indexOfSearched, int lengthOfArray) {
        String[][] searchedDigitalItemsArray = new String[itemArray.length][lengthOfArray]; // create fixed array

        int arrayIndex = 0;
        for(int i=0; i< itemArray.length;i++) {
            if(itemArray[i][indexOfSearched].toLowerCase().equals(searchedTitle.toLowerCase())) { // match items with searched text
                for(int j=0; j<lengthOfArray;j++) {
                    searchedDigitalItemsArray[arrayIndex][j] = itemArray[i][j]; // add only matched items
                }
                arrayIndex+=1;
            }
        }

        String[][] newSearchedDigitalItemsArray = new String[arrayIndex][lengthOfArray]; // new array to delete blank entries

        for(int i=0; i< newSearchedDigitalItemsArray.length;i++) {
            for(int j=0; j<lengthOfArray;j++) {
                newSearchedDigitalItemsArray[i][j] = searchedDigitalItemsArray[i][j]; // create array with only matched items
            }
            //System.out.println();
        }

        if( newSearchedDigitalItemsArray.length !=0) {
            searchInfoTextArea.setText("Searched items are found!"+"\n"+ // show matched items and count
                    "Number of items found: "+newSearchedDigitalItemsArray.length);
        } else {
            searchInfoTextArea.setText("Searched item is not found in the library!");
        }

        return newSearchedDigitalItemsArray;
    }

}
