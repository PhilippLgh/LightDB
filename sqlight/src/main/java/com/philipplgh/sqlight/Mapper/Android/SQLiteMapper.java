package com.philipplgh.sqlight.Mapper.Android;

import android.content.ContentValues;
import android.database.Cursor;

import com.philipplgh.sqlight.Mapper.Mapper;
import com.philipplgh.sqlight.Mapper.MappingsGenerator;

import java.util.Map;

/**
 * Created by philipp on 12/20/2016.
 */
public class SQLiteMapper<T> extends Mapper<T> {

    public SQLiteMapper(Class<T> clazz, Map<String, MappingsGenerator.Mapping> mappings) {
        super(clazz, mappings);
    }

    //will set all columns in content values
    public ContentValues toContentValues(T instance){
        Iterable<String> columns = getFieldNames();
        return toContentValues(instance, columns);
    }

    //will only set the given columns in content values
    public ContentValues toContentValues(T instance, Iterable<String> columns){
        final SQLiteMapping m = new SQLiteMapping(new ContentValues());
        for (String column : columns) {
            //write value from field column on instance into m
            set(column, instance, m);
        }
        return m.getContentValues();
    }

    public T toInstance(Cursor cursor) {
        SQLiteMapping m = new SQLiteMapping(cursor);
        return toInstance(m);
    }
}
