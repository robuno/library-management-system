package com.teksen;

public class Ebook extends Book{
    private String url;

    public Ebook(int id, String itemType, String title, String locationInformation, String status,
                 String author, String publisher, String language,
                 int year, int edition, int pageNumber, String ISBN,
                 String url) {
        super(id, itemType, title, locationInformation, status,
                author, publisher, language,
                year, edition, pageNumber, ISBN);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
