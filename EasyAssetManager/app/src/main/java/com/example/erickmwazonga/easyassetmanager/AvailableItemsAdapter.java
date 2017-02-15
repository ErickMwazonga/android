package com.example.erickmwazonga.easyassetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick Mwazonga on 11/14/2016.
 */

public class AvailableItemsAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public AvailableItemsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ContactHolder contactHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_items, parent, false);

            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView) row.findViewById(R.id.textViewItemName);
            contactHolder.tx_serial = (TextView) row.findViewById(R.id.textViewItemSerial);
            contactHolder.tx_description = (TextView) row.findViewById(R.id.textViewItemDescription);
            contactHolder.tx_quantity = (TextView) row.findViewById(R.id.textViewItemQuantity);

            row.setTag(contactHolder);

        } else {
            contactHolder = (ContactHolder) row.getTag();
        }

        DataProviderAvailableItems  dataProviderAvailableItems = (DataProviderAvailableItems) this.getItem(position);
        contactHolder.tx_name.setText(dataProviderAvailableItems.getName());
        contactHolder.tx_serial.setText(dataProviderAvailableItems.getSerial());
        contactHolder.tx_description.setText(dataProviderAvailableItems.getDescription());
        contactHolder.tx_quantity.setText(dataProviderAvailableItems.getQuantity());

        return row;
    }
    static class ContactHolder {
        TextView tx_name, tx_serial, tx_description,tx_quantity;
    }
}
