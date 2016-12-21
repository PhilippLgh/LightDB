package com.philipplgh.sqlight.Mapper;

import java.util.Map;

/**
 * Created by philipp on 12/20/2016.
 */
public class Mapper<T> {

    private final Map<String, MappingsGenerator.Mapping> mappings;

    Class<T> clazz;
    public Class<T> getClazz() {
        return clazz;
    }

    public Mapper(Class<T> clazz, Map<String, MappingsGenerator.Mapping> mappings){
        this.mappings = mappings;
        this.clazz = clazz;
    }

    private MappingsGenerator.Mapping getFieldMapping(String fieldName){
        return mappings.get(fieldName);
    }

    public Object getValue(T instance, String fieldName) {
        return getFieldMapping(fieldName).get(instance);
    }

    protected Iterable<String> getFieldNames(){
        return mappings.keySet();
    }

    protected void set(String fieldName, T instance, IMapping m) {
        getFieldMapping(fieldName).write(instance, m);
    }

    protected T toInstance(IMapping mapping){
        T instance = newInstance();
        Iterable<String> fields = getFieldNames();
        for (String field : fields){
            getFieldMapping(field).read(instance, mapping);
        }
        return instance;
    }

    public T newInstance(){
        try {
            T instance = getClazz().newInstance();
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeField(String fieldName) {
        mappings.remove(fieldName);
    }
}
