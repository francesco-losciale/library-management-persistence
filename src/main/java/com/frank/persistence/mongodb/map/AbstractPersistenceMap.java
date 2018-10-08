package com.frank.persistence.mongodb.map;

import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.PersistenceMap;

import java.lang.reflect.Field;

abstract class AbstractPersistenceMap implements PersistenceMap {

    private String domainFieldName;
    private DataMap dataMap;

    protected Field field;
    protected String persitenceFieldName;

    public AbstractPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        this.persitenceFieldName = persistenceFieldName;
        this.domainFieldName = domainFieldName;
        this.dataMap = dataMap;
        initField();
    }

    public void setFieldValue(Object instance, Object value) {
        try {
            Object castValue = castToDomainValue(value);
            field.set(instance, castValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Implementation error: mapping for field " + this.persitenceFieldName + " failed.", e);
        }
    }

    public Object getFieldValue(Object instance) {
        try {
            Object value = field.get(instance);
            Object castValue = castToPersistenceValue(value);
            return castValue;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        }
    }

    public String getPersitenceFieldName() {
        return persitenceFieldName;
    }

    public abstract Object castToPersistenceValue(Object value);

    public abstract Object castToDomainValue(Object value) throws ClassNotFoundException;

    private void initField() {
        try {
            field = dataMap.getDomainClass().getDeclaredField(this.domainFieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Implementation error: unable to get the field " +
                    this.domainFieldName + " from the domain class " + dataMap.getDomainClass());
        }
    }

}
