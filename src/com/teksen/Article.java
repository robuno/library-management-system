package com.teksen;

/**
 *  <h1> Creates Article Class! </h1>
 *
 *  The Article class derived from the Item class. Item class is the superclass
 *  of this class. This class inherits Item and contains private variables of
 *  Article item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class Article extends Item{
    private String author; // private instance variable for author
    private String institution; // private instance variable for institution
    private String source; // private instance variable for source
    private String keyword1; // private instance variable for keyword
    private String database; // private instance variable for database
    private String doi; // private instance variable for doi

    /**
     * Article Constructor
     * This constructor assigns values with superclass's constructor.
     * @param id  of the item
     * @param itemType of the item
     * @param title of the item
     * @param locationInformation of the item
     * @param status of the item
     * @param author of the article
     * @param institution of the article
     * @param source of the article
     * @param keyword1 of the article
     * @param database of the article
     * @param doi of the article
     */
    public Article(int id, String itemType, String title, String locationInformation, String status,
                   String author, String institution, String source,
                   String keyword1, String database, String doi) {
        super(id,itemType, title, locationInformation,status); // super keyword for inheritance
        this.author = author; // assign author
        this.institution = institution; // assign institution
        this.source = source; // assign source
        this.keyword1 = keyword1; // assign keyword
        this.database = database; // assign database
        this.doi = doi; // assign doi
    }
    // setters
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

    // getters
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
