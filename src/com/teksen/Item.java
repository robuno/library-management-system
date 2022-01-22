package com.teksen;
/**
 * <h1> Creates Item Class! </h1>
 *
 * The item class has variables to hold the items id, item type, title,
 * location information and status. These variables are all common for
 * Article, Book, Digital, Ebook, EJournal, EReadingBook, ETextBook and
 * TextBook classes.
 *
 * @author Humeyra Dogus - Unat Teksen
 * @version 1.0
 * @since 2021-12-25
 *
 */
public abstract class Item {
    private int id; // private instance variable for id
    private String itemType; // private instance variable for item type
    private String title; // private instance variable for title
    private String locationInformation; // private instance variable for location information
    private String status; // private instance variable for status

    /**
     * Item Constructor
     * This constructor assigns values with given parameters
     * @param id  of the item
     * @param itemType of the item
     * @param title of the item
     * @param locationInformation of the item
     * @param status of the item
     */
    public Item(int id, String itemType, String title, String locationInformation, String status) {
        this.id = id; // assign id
        this.itemType = itemType; // assign itemType
        this.title = title; // assign title
        this.locationInformation = locationInformation; // assign locationInformation
        this.status = status; // assign status

    }
    // getters
    public String getItemType() {
        return itemType;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationInformation() {
        return locationInformation;
    }

    public String getStatus() {
        return status;
    }

    @Override
    // toString method for formatting
    public String toString() {
        return  id+","+
                title+","+
                locationInformation+","+
                itemType+","+
                status;
    }
}
