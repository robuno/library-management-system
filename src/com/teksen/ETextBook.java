package com.teksen;

public class ETextBook extends Ebook{
    private String field;

    public ETextBook(int id, int itemType, String title, String locationInformation, boolean status,
                     String author, String publisher, String language,
                     int year, int edition, int pageNumber, int ISBN, String url,
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
