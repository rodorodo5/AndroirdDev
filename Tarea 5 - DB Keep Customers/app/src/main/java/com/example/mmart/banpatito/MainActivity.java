package com.example.mmart.banpatito;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mmart.banpatito.Utils.CustomerHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomerHelper db;
    int turnNumber = 0;
    CustomerAdapter customerAdapter;
    ArrayList<Customer> customers = new ArrayList<Customer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new CustomerHelper(this);
        final EditText txtName = (EditText) findViewById(R.id.editName);
        final EditText txtOperations = (EditText) findViewById(R.id.editOperations);
        Button btnAdd = (Button) findViewById(R.id.buttonAdd);
        Button btnQueue = (Button) findViewById(R.id.buttonQueue);
        ListView listView = (ListView) findViewById(R.id.listViewCustomer);
        customerAdapter = new CustomerAdapter(this);
        listView.setAdapter(customerAdapter);
        db.open();
        ArrayList<Customer>DB_Customers = db.getAllCustomers();
        for (Customer cus : DB_Customers){
            customerAdapter.add(cus);
            customerAdapter.notifyDataSetChanged();
            customers.add(cus);
        }
        db.close();
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Eliminar.");
                adb.setMessage("¿Estás seguro?");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.open();
                        db.deleteCustomer(positionToRemove);
                        customerAdapter.clear();
                        customers.clear();
                        ArrayList<Customer>DB_Customers = db.getAllCustomers();
                        for (Customer cus : DB_Customers){
                            customerAdapter.add(cus);
                            customerAdapter.notifyDataSetChanged();
                            customers.add(cus);
                        }
                        db.close();
                    }});
                adb.show();
                customerAdapter.notifyDataSetChanged();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnNumber++;
                String name = txtName.getText().toString();
                int operations = Integer.parseInt(txtOperations.getText().toString());
                if (operations > 0) {
                    Customer customer = new Customer(name, operations);
                    customers.add(customer);
                    db.open();
                    db.addCustomer(name, operations, turnNumber);
                    db.close();
                    customerAdapter.add(customer);
                    customerAdapter.notifyDataSetChanged();
                }
            }
        });

        btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueueActivity.class);
                intent.putExtra("Parcel", customers);
                startActivity(intent);
            }
        });
    }
}

