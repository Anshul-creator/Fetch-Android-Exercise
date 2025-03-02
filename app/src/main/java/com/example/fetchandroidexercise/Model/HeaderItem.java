package com.example.fetchandroidexercise.Model;

/**
 * Represents a header item that displays the group identifier
 * A HeaderItem is used to indicate the start of a new group in the RecyclerView
 */
public class HeaderItem implements ListItem {

    private int listId;

    public HeaderItem(int listId) {
        this.listId = listId;
    }

    public int getListId() {
        return listId;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}