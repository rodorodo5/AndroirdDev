package com.example.rodolfo.firstapp.Utils;

/**
 * Created by rodo on 9/29/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class CustomerHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase db;
    private String[] CUSTOMERS_TABLE_COLUMNS=
            {
                    DBUtils.CUSTOMER_ID,
                    DBUtils.CUSTOMER_NAME,
                    DBUtils.CUSTOMER_OPERATIONS,
                    DBUtils.CUSTOMER_POSITION
            };

    public CustomerHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Customers addCustomer(String name, int operations, int position) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_NAME,name);
        values.put(DBUtils.CUSTOMER_OPERATIONS,operations);
        values.put(DBUtils.CUSTOMER_POSITION,position);

        long lCustomerId = db.insert(DBUtils.CUSTOMER_TABLE_NAME,null,values);

        Cursor cursor = db.query(DBUtils.CUSTOMER_TABLE_NAME,
                CUSTOMERS_TABLE_COLUMNS,DBUtils.CUSTOMER_ID+ " = " + lCustomerId,
                null,null,null,null);
        cursor.moveToFirst();
        Customers oCustomer = parseCustomer(cursor);
        cursor.close();
        return oCustomer;
    }

    public int deleteCustomer(int nCustomerID){
        return db.delete(DBUtils.CUSTOMER_TABLE_NAME,DBUtils.CUSTOMER_ID + " = " + nCustomerID, null);
    }

    public ArrayList<Customers> getAllCustomers() {
        ArrayList<Customers> oCustomers = new ArrayList<>();
        Cursor cursor = db.query(DBUtils.CUSTOMER_TABLE_NAME, CUSTOMERS_TABLE_COLUMNS, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            oCustomers.add(parseCustomer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oCustomers;
    }

    private Customers parseCustomer(Cursor cursor) {
        Customers oCustomer = new Customers();
        oCustomer.setCustomerName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        oCustomer.setOperationNumber(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_OPERATIONS)));
        return oCustomer;
    }