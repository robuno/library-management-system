package com.teksen;
/**
 *  <h1> Creates ETextBook Class! </h1>
 *
 *  The ETextBook class derived from the EBook class. ETextBook class is the child class
 *  of EBook class. This class inherits Book, EBook and Item classes and contains private
 *  variables of ETextBook item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class ETextBook extends Ebook{
    private String field; // private instance variable for field

    /**
     * E-TextBook Constructor
     * This constructor assigns values with superclass's constructor.
     * @param id  of the item
     * @param itemType of the item
     * @param title of the item
     * @param locationInformation of the item
     * @param status of the item
     * @param author of the book
     * @param publisher of the book
     * @param language of the book
     * @param year of the book
     * @param edition of the book
     * @param pageNumber of the book
     * @param ISBN of the book
     * @param url of the book
     * @param field of the book
     */
    public ETextBook(int id, String itemType, String title, String locationInformation, String status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, String ISBN, String url,
                     String field) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN, url); // super keyword for inheritance
        this.field = field; // assign field
    }
    //getter
    public String getField() {
        return field;
    }

    //setter
    public void setField(String field) {
        this.field = field;
    }
}
