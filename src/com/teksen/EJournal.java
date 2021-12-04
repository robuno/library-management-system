package com.teksen;

public class EJournal extends Item{
    private String publisher;
    private String ISSN;
    private String topic;
    private String url;
    private boolean isPeerReviewed;

    public EJournal(int id, int itemType, String title, String locationInformation, boolean status,
                    String publisher, String ISSN, String topic,
                    String url, boolean isPeerReviewed) {
        super(id,itemType, title, locationInformation, status);
        this.publisher = publisher;
        this.ISSN = ISSN;
        this.topic = topic;
        this.url = url;
        this.isPeerReviewed = isPeerReviewed;
    }


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
