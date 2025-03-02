package com.example.fetchandroidexercise.UI;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchandroidexercise.Model.DataItem;
import com.example.fetchandroidexercise.Model.HeaderItem;
import com.example.fetchandroidexercise.Model.Item;
import com.example.fetchandroidexercise.Model.ListItem;

import java.util.List;

/**
 * RecyclerView adapter for displaying a mixed list of header and data items
 *
 * This adapter handles two types of items:
 *   - Header items: Display the group (listId) identifier in bold, larger text
 *   - Data items: Display an item with a counter
 *
 * The adapter expects a list containing both HeaderItem and DataItem objects, and uses
 * the view type defined in each ListItem to determine which ViewHolder to use
 */
public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> items; // List containing both header and data items
    private static final int TYPE_HEADER = ListItem.TYPE_HEADER;

    /**
     * Constructor for the adapter
     * @param items: List of ListItem objects (headers and data) to be displayed
     */
    public ItemsAdapter(List<ListItem> items) {
        this.items = items;
    }

    /**
     * Determines the view type of the item at the given position
     * This is used by onCreateViewHolder to inflate the correct layout
     * @param position: The position of the item in the data set
     * @return The view type constant defined in ListItem
     */
    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    /**
     * Returns the total number of items in the data set
     * @return The number of items (headers and data) in the list
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder for header items
     * Displays the header text in bold with an increased text size
     */
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView headerText;
        public HeaderViewHolder(View itemView) {

            super(itemView);
            headerText = itemView.findViewById(android.R.id.text1);
            headerText.setTypeface(headerText.getTypeface(), Typeface.BOLD);
            headerText.setTextSize(20);

        }
        public void bind(HeaderItem headerItem) {
            String text = "List ID: " + headerItem.getListId();
            headerText.setText(text);
        }

    }

    /**
     * ViewHolder for data items
     * Displays the data item with a counter prefix
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        public DataViewHolder(View itemView) {

            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text1.setTypeface(text1.getTypeface(), Typeface.NORMAL);

        }
        public void bind(DataItem dataItem) {

            Item item = dataItem.getItem();
            String text = dataItem.getIndex() + ". " + item.getName();
            text1.setText(text);

        }

    }

    /**
     * Inflates the appropriate layout based on the item view type
     * @param parent The parent ViewGroup
     * @param viewType The type of view to be created
     * @return A new ViewHolder instance corresponding to the view type
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeaderViewHolder(view);
        }

        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DataViewHolder(view);
        }

    }

    /**
     * Binds the data to the ViewHolder at the specified position
     * Determines the type of item (header or data) and calls the corresponding bind method
     * @param holder The ViewHolder which should be updated
     * @param position The position of the item in the data set
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_HEADER) {
            ((HeaderViewHolder) holder).bind((HeaderItem) items.get(position));
        }

        else {
            ((DataViewHolder) holder).bind((DataItem) items.get(position));
        }

    }

}