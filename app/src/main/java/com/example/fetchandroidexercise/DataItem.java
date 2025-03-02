package com.example.fetchandroidexercise;

public class DataItem implements ListItem {

    private Item item;
    private int index;

    public DataItem(Item item, int index) {
        this.item = item;
        this.index = index;
    }

    public Item getItem() {
        return item;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int getType() {
        return TYPE_DATA;
    }

}