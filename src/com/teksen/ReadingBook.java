package com.teksen;

public class ReadingBook extends Book {
    private String genre;

    public ReadingBook(int id, int itemType, String title, String locationInformation, boolean status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, int ISBN,
                String genre) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN);
        this.genre = genre;
    }



    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
