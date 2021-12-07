package com.teksen;

public class EReadingBook extends Ebook{
    private String genre;

    public EReadingBook(int id, String itemType, String title, String locationInformation, String status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, String ISBN, String url,
                     String genre) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN, url);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
