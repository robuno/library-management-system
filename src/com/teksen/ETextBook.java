package com.teksen;

public class ETextBook extends Ebook{
    private String field;

    public ETextBook(int id, String itemType, String title, String locationInformation, String status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, String ISBN, String url,
                     String field) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN, url);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
