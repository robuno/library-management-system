package com.teksen;

public class Digital extends Item{
    private String director;
    private String company;
    private String topic;
    private String language;
    private String physicalProperty;
    private int ISBN;
    private int time;
    private int year;

    public Digital(int id, int itemType, String title, String locationInformation, boolean status,
                   String director, String company, String topic,
                   String language, String physicalProperty,
                   int ISBN, int time, int year) {
        super(id,itemType, title, locationInformation, status);
        this.director = director;
        this.company = company;
        this.topic = topic;
        this.language = language;
        this.physicalProperty =physicalProperty;
        this.ISBN = ISBN;
        this.time = time;
        this.year = year;
    }


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

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setYear(int year) {
        this.year = year;
    }

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

    public int getISBN() {
        return ISBN;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }
}
