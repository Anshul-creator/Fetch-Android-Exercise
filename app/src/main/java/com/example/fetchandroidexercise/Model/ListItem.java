package com.example.fetchandroidexercise.Model;

/**
 * An interface for items to be displayed in the RecyclerView
 * This interface is implemented by both header and data item classes, so that the adapter
 * can work with a combined list of items and decide the layout based on the item type
 */
public interface ListItem {

    int TYPE_HEADER = 0; // Constant to represent header items
    int TYPE_DATA = 1;   // Constant to represent data items
    int getType();

}