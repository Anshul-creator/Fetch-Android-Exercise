package com.example.fetchandroidexercise.Model;

/**
 * Represents an individual item retrieved from the JSON API
 * Each Item has:
 * an id: a unique identifier for the item
 * a listId: used for grouping items together
 * a name: the display name of the item (which may be empty or null)
 */
public class Item {

    private int id;        // Unique identifier for the item
    private int listId;    // Identifier for the group/list this item belongs to
    private String name;   // The name of the item (may be null or blank)


    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

}