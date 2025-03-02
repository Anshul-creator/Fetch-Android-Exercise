package com.example.fetchandroidexercise;

public class DataItem implements ListItem {

    private Item item;

    public DataItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public int getType() {
        return TYPE_DATA;
    }

}