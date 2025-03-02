package com.example.fetchandroidexercise.Model;

/**
 * Wraps an Item object for display as a data row in the RecyclerView
 * In addition to the underlying Item, a DataItem holds an index that represents
 * the item's order within its group
 * This index is displayed as a counter in that particular group
 */
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