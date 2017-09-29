package com.example.mmart.banpatito;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class CustomerAdapter extends ArrayAdapter<Customer> {

    public CustomerAdapter(Context context) {
        super(context, R.layout.customer_row, R.id.textCustomer);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);

        TextView txtNumber = (TextView) objectView.findViewById(R.id.textNumber);
        TextView txtCustomer = (TextView) objectView.findViewById(R.id.textCustomer);
        TextView txtOperations = (TextView) objectView.findViewById(R.id.textOperations);

        final Customer customer = this.getItem(position);
        txtNumber.setText((position + 1) + " | ");
        txtCustomer.setText(customer.getName() + " - ");
        txtOperations.setText(customer.getOperations() + "");

        return objectView;
    }
}
