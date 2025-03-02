package com.example.fetchandroidexercise;

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