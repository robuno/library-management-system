package com.teksen;

public abstract class Item {
    private int id;
    private String itemType;
    private String title;
    private String locationInformation;
    private String status;

    public Item(int id, String itemType, String title, String locationInformation, String status) {
        this.id = id;
        this.itemType = itemType;
        this.title = title;
        this.locationInformation = locationInformation;
        this.status = status;

    }

    public String getItemType() {
        return itemType;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationInformation() {
        return locationInformation;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  id+","+
                title+","+
                locationInformation+","+
                itemType+","+
                status;
    }
}
