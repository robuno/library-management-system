package com.teksen;
/**
 *  <h1> Creates ReadingBook Class! </h1>
 *
 *  The ReadingBook class derived from the Item class. Item class is the superclass
 *  of this class. This class inherits Item and contains private variables of
 *  ReadingBook item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class ReadingBook extends Book {
    private String genre; // private instance variable for genre

    /**
     * ReadingBook Constructor
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
     * @param genre of the book
     */
    public ReadingBook(int id, String itemType, String title, String locationInformation, String status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, String ISBN,
                String genre) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN); // super keyword for inheritance
        this.genre = genre; // assign genre
    }

    // setter
    public void setGenre(String genre) {
        this.genre = genre;
    }

    //getter
    public String getGenre() {
        return genre;
    }

    @Override
    // toString for formatting
    public String toString() {
        return
                super.toString()+","+
                genre;
    }
}
