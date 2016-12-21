package com.philipplgh.sqlight.Mapper;

import com.philipplgh.sqlight.Mapper.Android.SQLiteMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by philipp on 12/20/2016.
 */
public class MappingsGenerator {

    /*
    * Provided with a class, this method will return a Map<String, Mapping> where the key is a field name
    * for each field in the given class and the value is a Mapping that provides a mechanism
    * to translate between IMapping and instances of class
    *
    * Example: the input class has a field "name" of type String:
    * the returned map will contain a key "name" and mapping that when given an instance of IMapping
    * will call for
    * read()  : getString("name") and set the field with the value on a provided instance of class
    * write() : will use the value of the instance's field and call putString("name", value) on the IMapping instance
    * */
    private static <T> Map<String, Mapping> generateMappings(Class<T> klazz){

        Map<String, Mapping> mappings = new HashMap<>();

        for (Field f : klazz.getFields()) {
            final Field _f = f;

            String fieldName = f.getName().toLowerCase();

            //ignore special auto-generated fields:
            if(fieldName.equals("serialversionuid")){continue;}
            if(fieldName.equals("$change")){continue;}

            //extract type from field
            final String type = f.getType().getSimpleName().toLowerCase();

            mappings.put(fieldName, new Mapping<T>(fieldName) {

                @Override
                public Object get(T instance){
                    try {
                        return _f.get(instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                public void read(T instance, IMapping c) {
                    String fieldName = this.name;
                    try {
                        switch (type) {
                            case "string":
                                _f.set(instance, c.getString(fieldName));
                                break;
                            case "uuid":
                                String id = c.getString(fieldName);
                                _f.set(instance, id == null ? null : UUID.fromString(id));
                                break;
                            case "boolean":
                                _f.set(instance, c.getBoolean(fieldName));
                                break;
                            case "int":
                                _f.set(instance, c.getInt(fieldName));
                                break;
                            case "double":
                                _f.set(instance, c.getDouble(fieldName));
                                break;
                            case "long":
                                _f.set(instance, c.getLong(fieldName));
                                break;
                        }
                    } catch (Exception e) {
                        //Log.e(e, "exception in mapper");
                    }
                }

                @Override
                public void write(T instance, IMapping c) {
                    String fieldName = this.name;
                    try {
                        switch (type) {
                            case "string":
                                c.put(fieldName, (String) _f.get(instance));
                                break;
                            case "uuid":
                                UUID id = (UUID)_f.get(instance);
                                c.put(fieldName, id!=null ? id.toString() : null);
                                break;
                            case "boolean":
                                c.put(fieldName, (boolean) _f.get(instance));
                                break;
                            case "int":
                                c.put(fieldName, (int) _f.get(instance));
                                break;
                            case "double":
                                c.put(fieldName, (double) _f.get(instance));
                                break;
                            case "long":
                                c.put(fieldName, (long) _f.get(instance));
                                break;
                        }
                    } catch (Exception e) {
                        //Log.e(e, "exception in mapper");
                    }
                }
            });

        }

        return mappings;
    }

    public static <T> SQLiteMapper<T> generateSQLiteMapper(Class<T> klazz) {
        return new SQLiteMapper<>(klazz, generateMappings(klazz));
    }

    public static abstract class Mapping<M>{

        protected final String name;

        public Mapping(String fieldName){
            this.name = fieldName;
        }

        public abstract Object get(M instance);

        //read from wrapper to instance
        public abstract void read(M instance, IMapping c);

        //write instance to wrapper
        public abstract void write(M instance, IMapping c);

    }

}
