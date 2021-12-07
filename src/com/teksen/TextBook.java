package com.teksen;

public class TextBook extends Book {
    private String field;

    public TextBook(int id, String itemType, String title, String locationInformation, String status,
                String author, String publisher, String language,
                int year, int edition, int pageNumber, String ISBN,
                String field) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN);
        this.field = field;
    }



    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
