package com.teksen;
/**
 *  <h1> Creates EJournal Class! </h1>
 *
 *  The EJournal class derived from the Item class. Item class is the superclass
 *  of this class. This class inherits Item and contains private variables of
 *  e-journal item.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class EJournal extends Item{
    private String publisher; // private instance variable for publisher
    private String ISSN; // private instance variable for ISSN
    private String topic; // private instance variable for topic
    private String url; // private instance variable for url
    private boolean isPeerReviewed; // private instance variable for is e-jornal peer reviewed

    // create EJournal constructor
    public EJournal(int id, String itemType, String title, String locationInformation, String status,
                    String publisher, String ISSN, String topic,
                    String url, boolean isPeerReviewed) {
        super(id,itemType, title, locationInformation, status); // super keyword for inheritance
        this.publisher = publisher; // assign publisher
        this.ISSN = ISSN; // assign ISSN
        this.topic = topic; // assign topic
        this.url = url; // assign url
        this.isPeerReviewed = isPeerReviewed; // assign isPeerReviewed
    }

    //setters
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        isPeerReviewed = peerReviewed;
    }

    //getters
    public String getPublisher() {
        return publisher;
    }

    public String getISSN() {
        return ISSN;
    }

    public String getTopic() {
        return topic;
    }

    public String getUrl() {
        return url;
    }

    public boolean isPeerReviewed() {
        return isPeerReviewed;
    }

}
