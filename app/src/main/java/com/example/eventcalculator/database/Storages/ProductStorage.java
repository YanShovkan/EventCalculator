package com.example.eventcalculator.database.Storages;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Product;
import com.example.eventcalculator.database.Models.User;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductStorage implements IProductStorage {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "product";
    final String COLUMN_ID = "productid";
    final String COLUMN_NAME = "product_name";
    final String COLUMN_PRICE = "product_cost";
    final String COLUMN_COUNTPERPEOPLE = "count_per_person";
    final String COLUMN_EVENTID = "eventid";

    public ProductStorage(Context context) {
        sqlHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public ProductStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
        return  db.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<ProductModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ProductModel> list = new ArrayList<>();
        /*if (!cursor.moveToFirst()) {
            return list;
        }*/
        do {
            Product obj = new Product();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(createModel(obj));
        } while (cursor.moveToNext());
        return list;
    }

    public List<ProductModel> getFilteredList(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<ProductModel> list = new Vector<ProductModel>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Product obj = new Product();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(createModel(obj));
        } while (cursor.moveToNext());
        return list;
    }

    public ProductModel getElement(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Product obj = new Product();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
        obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return createModel(obj);
    }

    @Override
    public void insert(ProductModel model) {

    }

    @Override
    public void update(ProductModel model) {

    }

    @Override
    public void delete(ProductModel model) {

    }

    /*public void insert(ProductModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void update(ProductModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }*/

   /* public void delete(ProductModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }*/

    public ProductModel createModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.id);
        productModel.setPrice(product.price);
        productModel.setCountPerPeople(product.countPerPeople);
        productModel.setEventId(product.eventId);
        productModel.setName(product.name);

        return productModel;
    }

    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR));
            users.add(new User(id, name, year));
        }
        cursor.close();
        return  users;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(db, DatabaseHelper.TABLE);
    }

    public User getUser(long id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR));
            user = new User(id, name, year);
        }
        cursor.close();
        return  user;
    }

    public long insert(User user){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_YEAR, user.getYear());

        return  db.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return db.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(User user){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + String.valueOf(user.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_YEAR, user.getYear());
        return db.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}
