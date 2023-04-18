package com.example.parkpal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper{


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customerDB.db", null, 1);
    }

    //Called The First Time A Database Is Accessed; Contains Code To Generate A New Database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //Called If Database Version Number Changes. Prevents Previous User Apps From Breaking When You Change The Database Design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAccount(CustomerModel newCustomer){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put(COLUMN_CUSTOMER_EMAIL, newCustomer.getEmail());
        CV.put(COLUMN_CUSTOMER_PASSWORD, newCustomer.getPassword());

        long insert = db.insert(CUSTOMER_TABLE, null, CV);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOneCustomer(CustomerModel customer){
        /*Find A Customer In The Database, If Found, Remove Them*/
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM" + CUSTOMER_TABLE + "WHERE" + COLUMN_ID + "=" + customer.getCustomerId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }


    public List<CustomerModel> getAllCustomers(){
        List<CustomerModel> returnList = new ArrayList<>();

        //Get Data From Database
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        //Validate If rawQuery() Returned A Result
        if(cursor.moveToFirst()){
            /*Loop Through The Cursor(Result Set) And Create New Customer Objects.
            Add The New Objects To The Return List*/
            do{
                int customerId = cursor.getInt(0);
                String customerEmail = cursor.getString(1);
                String customerPassword = cursor.getString(2);
                CustomerModel newCustomer = new CustomerModel(customerId, customerEmail, customerPassword);
                returnList.add(newCustomer);
            }while(cursor.moveToNext());

        }
        else{
            /*Failure, Do Not Add Anything To The List*/
        }
        /*Close Both The Cursor & DB When Done*/
        cursor.close();
        db.close();
        return returnList;
    }
}
