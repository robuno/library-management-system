package com.teksen;

public class Book extends Item {
    private String author;
    private String publisher;
    private String language;
    private int year;
    private int edition;
    private int pageNumber;
    private String ISBN;


    public Book(int id, String itemType, String title, String locationInformation, String status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, String ISBN) {
        super(id,itemType, title, locationInformation, status);
        this.author = author;
        this.publisher = publisher;
        this.language = language;
        this.year = year;
        this.edition = edition;
        this.pageNumber = pageNumber;
        this.ISBN = ISBN;
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
