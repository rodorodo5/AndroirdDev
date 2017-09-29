package com.example.mmart.banpatito.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mmart.banpatito.Customer;

import java.util.ArrayList;

/**
 * Created by Sam on 22/09/2017.
 */

public class CustomerHelper {

    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] CUSTOMER_TABLE_COLUMNS = {
        DBUtils.CUSTOMER_ID,
            DBUtils.CUSTOMER_NAME,
            DBUtils.CUSTOMER_OPERATIONS,
            DBUtils.CUSTOMER_POSTIION
    };

    public CustomerHelper(Context context){
        dbHelper = new DBUtils(context);

    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Customer addCustomer(String name, int operations, int position){
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_NAME, name);
        values.put(DBUtils.CUSTOMER_OPERATIONS, operations);
        values.put(DBUtils.CUSTOMER_POSTIION, position);

        long lCustomerID = database.insert(DBUtils.CUSTOMER_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME,
                CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_ID+" = " + lCustomerID, null, null, null, null);
        cursor.moveToFirst();
        Customer oCustomer = parseCustomer(cursor);
        cursor.close();
        return oCustomer;
    }

    public int deleteCustomer(int nCustomerID){
        return database.delete(DBUtils.CUSTOMER_TABLE_NAME, DBUtils.CUSTOMER_ID+ " = " + nCustomerID, null);
    }

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer>oLCustomers = new ArrayList<Customer>();
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME, CUSTOMER_TABLE_COLUMNS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLCustomers.add(parseCustomer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLCustomers;
    }

    private Customer parseCustomer(Cursor cursor){
        Customer oCustomer = new Customer();
        oCustomer.setName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        oCustomer.setOperations(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_OPERATIONS)));
        oCustomer.setTurn(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_POSTIION)));
        return oCustomer;
    }
}
