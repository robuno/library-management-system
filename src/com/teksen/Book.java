package com.teksen;
/**
 *  <h1> Creates Book Class! </h1>
 *
 *  The Book class derived from the Item class. Item class is the superclass
 *  of this class. This class inherits Item and contains private variables of
 *  book item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class Book extends Item {
    private String author; // private instance variable for author
    private String publisher; // private instance variable for publisher
    private String language; // private instance variable for language
    private int year; // private instance variable for year
    private int edition; // private instance variable for edition
    private int pageNumber; // private instance variable for pageNumber
    private String ISBN; // private instance variable for ISBN

    /**
     * Book Constructor
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
     */
    public Book(int id, String itemType, String title, String locationInformation, String status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, String ISBN) {
        super(id,itemType, title, locationInformation, status);// super keyword for inheritance
        this.author = author; // assign author
        this.publisher = publisher; // assign publisher
        this.language = language; // assign language
        this.year = year; // assign year
        this.edition = edition; // assign edition
        this.pageNumber = pageNumber; // assign pageNumber
        this.ISBN = ISBN; // assign ISBN
    }


    //setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    //getters
    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }

    public int getEdition() {
        return edition;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    // toString method for formatting
    public String toString() {
        return
                super.toString()+","+
                author+","+
                publisher+","+
                language+","+
                year+","+
                edition+","+
                pageNumber+","+
                ISBN;
    }
}
