package com.dacker.adouble.massmeter.gui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
class SingleSelectionAdapter extends ArrayAdapter {

    private int position = -1;

    public SingleSelectionAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = super.getView(position, convertView, parent);
        if (this.position == position)
            itemView.setBackgroundColor(0xFFDDDDDD);
        else
            itemView.setBackgroundColor(Color.TRANSPARENT);
        return itemView;
    }

    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    public int getPosition() {
        return position;
    }

    public String getSelectedName() {
        if (!selected()) {
            return null;
        }
        return (String) getItem(position);
    }

    public boolean selected() {
        return position >= 0 && position < getCount();
    }

}

