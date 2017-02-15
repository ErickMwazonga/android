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
 * Created by Erick Mwazonga on 11/15/2016.
 */

public class MaintainedItemsAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public MaintainedItemsAdapter(Context context, int resource) {
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
            row = layoutInflater.inflate(R.layout.row_layout_maintained, parent, false);

            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView) row.findViewById(R.id.textViewMaintainedName);
            contactHolder.tx_serial = (TextView) row.findViewById(R.id.textViewMaintainedSerial);
            contactHolder.tx_reason = (TextView) row.findViewById(R.id.textViewMaintainedReason);
            contactHolder.tx_maintainer = (TextView) row.findViewById(R.id.textViewMaintainedMaintainer);

            row.setTag(contactHolder);

        } else {
            contactHolder = (ContactHolder) row.getTag();
        }

        DataProviderMaintainedItems dataProviderMaintainedItems  = (DataProviderMaintainedItems) this.getItem(position);
        contactHolder.tx_name.setText(dataProviderMaintainedItems.getName());
        contactHolder.tx_serial.setText(dataProviderMaintainedItems.getSerial());
        contactHolder.tx_reason.setText(dataProviderMaintainedItems.getReason());
        contactHolder.tx_maintainer.setText(dataProviderMaintainedItems.getMaintainer());

        return row;
    }
    static class ContactHolder {
        TextView tx_name, tx_serial, tx_reason,tx_maintainer;
    }
}
