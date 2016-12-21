package com.philipplgh.sqlight.Mapper;

/**
 * Created by philipp on 12/20/2016.
 */
public interface IMapping {
    String getString(String name);
    boolean getBoolean(String name);
    int getInt(String name);
    double getDouble(String name);
    long getLong(String name);
    void put(String key, String value);
    void put(String key, boolean value);
    void put(String key, int value);
    void put(String key, double value);
    void put(String key, long value);
}
