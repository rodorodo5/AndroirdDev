package com.example.mmart.banpatito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class QueueActivity extends AppCompatActivity {
    CustomerAdapter customerAdapter;
    ListView listView;
    ArrayList<Customer> customers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        listView = (ListView) findViewById(R.id.listView);
        customerAdapter = new CustomerAdapter(this);
        listView.setAdapter(customerAdapter);

        customers = this.getIntent().getParcelableArrayListExtra("Parcel");
        makeQueue(customers);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void makeQueue(ArrayList<Customer> customers) {
        int quantity = customers.size();
        customerAdapter.clear();
        while(quantity > 0) {
            for (Customer c : customers) {
                if (c.checkFlag()) {
                    int currentOp = c.getOperations();
                    if (currentOp > 0) {
                        customerAdapter.add(new Customer(c.getName(), c.getOperations()));
                        c.setOperations(currentOp - 1);
                    } else {
                        c.setFlag(false);
                        quantity--;
                    }
                }
            }
        }
        customerAdapter.notifyDataSetChanged();
    }
}
