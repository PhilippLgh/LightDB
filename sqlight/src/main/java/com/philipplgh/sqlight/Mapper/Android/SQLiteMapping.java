package com.philipplgh.sqlight.Mapper.Android;

import android.content.ContentValues;
import android.database.Cursor;

import com.philipplgh.sqlight.Mapper.IMapping;


/**
 * Created by philipp on 5/15/2016.
 */
public class SQLiteMapping implements IMapping {

    private final Cursor cursor;

    private final ContentValues contentValues;
    public ContentValues getContentValues() {
        return contentValues;
    }

    public SQLiteMapping(Cursor cursor){
        this.cursor = cursor;
        this.contentValues = null;
    }

    public SQLiteMapping(ContentValues contentValues) {
        this.cursor = null;
        this.contentValues = contentValues;
    }

    public Object get(String columnName){
        return contentValues.get(columnName);
    }

    @Override
    public String getString(String columnName) {
        int index = cursor.getColumnIndex(columnName);
        return getStringOrNull(cursor, index);
    }

    @Override
    public boolean getBoolean(String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if(index==-1){
            //Log.w("column %s does not exist",columnName);
            return false;
        }
        return cursor.getInt(index) != 0;
    }

    @Override
    public int getInt(String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if(index==-1){
            //Log.w("column %s does not exist",columnName);
            return -1;
        }
        return cursor.getInt(index);
    }

    @Override
    public double getDouble(String name) {
        int index = cursor.getColumnIndex(name);
        return cursor.getDouble(index);
    }

    @Override
    public long getLong(String name) {
        int index = cursor.getColumnIndex(name);
        if(index==-1){
            //Log.e("column index for %s not found on cursor", name);
            return 0;
        }
        return cursor.getLong(index);
    }

    @Override
    public void put(String key, String value) {
        contentValues.put(key, value);
    }

    @Override
    public void put(String key, boolean value) {
        contentValues.put(key, value);
    }

    @Override
    public void put(String key, int value) { contentValues.put(key, value); }

    @Override
    public void put(String key, double value) { contentValues.put(key, value); }

    @Override
    public void put(String key, long value) { contentValues.put(key, value); }

    private String getStringOrNull(Cursor cursor, int columnIndex){
        if(columnIndex==-1){return null; }
        String ret = cursor.isNull(columnIndex) ? null : cursor.getString(columnIndex);
        if(ret!= null && ret.equals("null")){ return null; }
        return ret;
    }
}
