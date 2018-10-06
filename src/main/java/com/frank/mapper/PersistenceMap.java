package com.frank.mapper;

import java.lang.reflect.Field;

public class PersistenceMap {

    private String domainFieldName;
    private DataMap dataMap;

    protected Field field;
    protected String persitenceFieldName;
    protected String persistenceTypeName;

    public PersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        this.persitenceFieldName = persistenceFieldName;
        this.persistenceTypeName = persistenceTypeName;
        this.domainFieldName = domainFieldName;
        this.dataMap = dataMap;
        initField();
    }

    private void initField() {
        try {
            field = dataMap.getDomainClass().getDeclaredField(this.domainFieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Implementation error: unable to get the field " +
                    this.domainFieldName + " from the domain class " + dataMap.getDomainClass());
        }
    }

    public void setFieldValue(Object instance, Object value) {
        try {
            Object castValue = Class.forName(persistenceTypeName).cast(value);
            field.set(instance, castValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Implementation error: mapping for field " + this.persitenceFieldName + " failed.", e);
        }
    }

    public Object getFieldValue(Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        }
    }

    public String getPersitenceFieldName() {
        return persitenceFieldName;
    }

}
