package com.teksen;
/**
 *  <h1> Creates Digital Class! </h1>
 *
 *  The Digital class derived from the Item class. Item class is the superclass
 *  of this class. This class inherits Item and contains private variables of
 *  digital item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class Digital extends Item{
    private String director; // private instance variable for director
    private String company; // private instance variable for company
    private String topic; // private instance variable for topic
    private String language; // private instance variable for language
    private String physicalProperty; // private instance variable for physicalProperty
    private String ISBN; // private instance variable for ISBN
    private int time; // private instance variable for time
    private int year; // private instance variable for year

    /**
     *
     * Digital Constructor
     * This constructor assigns values with superclass's constructor.
     * @param id  of the item
     * @param itemType of the item
     * @param title of the item
     * @param locationInformation of the item
     * @param status of the item
     * @param director of the digital
     * @param company of the digital
     * @param topic of the digital
     * @param language of the digital
     * @param physicalProperty of the digital
     * @param ISBN of the digital
     * @param time of the digital
     * @param year of the digital
     */
    public Digital(int id, String itemType, String title, String locationInformation, String status,
                   String director, String company, String topic,
                   String language, String physicalProperty,
                   String ISBN, int time, int year) {
        super(id,itemType, title, locationInformation, status); // super keyword for inheritance
        this.director = director; // private instance variable for director
        this.company = company; // private instance variable for company
        this.topic = topic; // private instance variable for topic
        this.language = language; // private instance variable for language
        this.physicalProperty = physicalProperty; // private instance variable for physicalProperty
        this.ISBN = ISBN; // private instance variable for ISBN
        this.time = time; // private instance variable for time
        this.year = year; // private instance variable for year
    }

    // setters
    public void setDirector(String director) {
        this.director = director;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPhysicalProperty(String physicalProperty) {
        this.physicalProperty = physicalProperty;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //getters
    public String getDirector() {
        return director;
    }

    public String getCompany() {
        return company;
    }

    public String getTopic() {
        return topic;
    }

    public String getLanguage() {
        return language;
    }

    public String getPhysicalProperty() {
        return physicalProperty;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }
}
