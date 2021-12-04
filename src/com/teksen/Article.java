package com.teksen;

public class Article extends Item{
    private String author;
    private String institution;
    private String source;
    private String keyword1;
    private String database;
    private String doi;

    public Article(int id, int itemType, String title, String locationInformation, boolean status,
                   String author, String institution, String source,
                   String keyword1, String database, String doi) {
        super(id,itemType, title, locationInformation,status);
        this.author = author;
        this.institution = institution;
        this.source = source;
        this.keyword1 = keyword1;
        this.database = database;
        this.doi = doi;

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getAuthor() {
        return author;
    }

    public String getInstitution() {
        return institution;
    }

    public String getSource() {
        return source;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public String getDatabase() {
        return database;
    }

    public String getDoi() {
        return doi;
    }
}
