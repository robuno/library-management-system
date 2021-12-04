package com.teksen;

public class Ebook extends Book{
    private String url;

    public Ebook(int id, int itemType, String title, String locationInformation, boolean status,
                 String author, String publisher, String language,
                 int year, int edition, int pageNumber, int ISBN,
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
