package com.teksen;
/**
 *  <h1> Creates TextBook Class! </h1>
 *
 *  The TextBook class derived from the Book class. TextBook class is the child class
 *  of Book class. This class inherits Book and Item classes and contains private
 *  variables of TextBook item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class TextBook extends Book {
    private String field; // private instance variable for field

    /**
     * TextBook Constructor
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
     * @param field of the book
     */
    public TextBook(int id, String itemType, String title, String locationInformation, String status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, String ISBN,
                String field) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN); // super keyword for inheritance
        this.field = field; // assign field
    }
    // setter
    public void setField(String field) {
        this.field = field;
    }

    // getter
    public String getField() {
        return field;
    }
}
