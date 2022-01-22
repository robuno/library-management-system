package com.teksen;
/**
 *  <h1> Creates EReadingBook Class! </h1>
 *
 *  The EReadingBook class derived from the EBook class. EReadingBook class is the child class
 *  of EBook class. This class inherits Book, EBook and Item classes and contains private
 *  variables of EReadingBook item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class EReadingBook extends Ebook{
    private String genre; // private instance variable for genre

    /**
     * E-ReadingBook Constructor
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
     * @param genre of the book
     */
    public EReadingBook(int id, String itemType, String title, String locationInformation, String status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, String ISBN, String url,
                     String genre) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN, url); // super keyword for inheritance
        this.genre = genre; // assign genre
    }

    //getter
    public String getGenre() {
        return genre;
    }

    //setter
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
