package com.teksen;

public class EReadingBook extends Ebook{
    private String genre;

    public EReadingBook(int id, int itemType, String title, String locationInformation, boolean status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, int ISBN, String url,
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
