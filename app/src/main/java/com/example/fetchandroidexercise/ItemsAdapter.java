package com.example.fetchandroidexercise;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> items;
    private static final int TYPE_HEADER = ListItem.TYPE_HEADER;
    private static final int TYPE_DATA = ListItem.TYPE_DATA;

    public ItemsAdapter(List<ListItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

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

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeaderViewHolder(view);
        }

        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new DataViewHolder(view);
        }

    }

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