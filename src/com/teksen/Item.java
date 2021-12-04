package com.teksen;

public abstract class Item {
    private int id;
    private int itemType;
    private String title;
    private String locationInformation;
    private boolean status;

    public Item(int id, int itemType, String title, String locationInformation, boolean status) {
        this.id = id;
        this.itemType = itemType;
        this.title = title;
        this.locationInformation = locationInformation;
        this.status = status;

    }

    public int getItemType() {
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

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", locationInformation='" + locationInformation + '\'' +
                ", status=" + status +
                '}';
    }
}
