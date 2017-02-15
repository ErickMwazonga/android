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

public class IssuedItemsAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public IssuedItemsAdapter(Context context, int resource) {
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
            row = layoutInflater.inflate(R.layout.row_layout_issued, parent, false);

            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView) row.findViewById(R.id.textViewIssuedName);
            contactHolder.tx_serial = (TextView) row.findViewById(R.id.textViewIssuedSerial);
            contactHolder.tx_user = (TextView) row.findViewById(R.id.textViewIssuedUser);
            contactHolder.tx_date_issued = (TextView) row.findViewById(R.id.textViewIssuedDateIssued);

            row.setTag(contactHolder);

        } else {
            contactHolder = (ContactHolder) row.getTag();
        }

        DataProviderIssuedItems dataProviderIssuedItems  = (DataProviderIssuedItems) this.getItem(position);
        contactHolder.tx_name.setText(dataProviderIssuedItems.getName());
        contactHolder.tx_serial.setText(dataProviderIssuedItems.getSerial());
        contactHolder.tx_user.setText(dataProviderIssuedItems.getUser());
        contactHolder.tx_date_issued.setText(dataProviderIssuedItems.getDate_issued());

        return row;
    }
    static class ContactHolder {
        TextView tx_name, tx_serial, tx_user,tx_date_issued;
    }
}
