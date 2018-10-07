package com.frank.mapper.maps;

import com.frank.mapper.datamap.mongodb.MongoDbDataMap;

import java.lang.reflect.Field;

public abstract class AbstractPersistenceMap {

    private String domainFieldName;
    private MongoDbDataMap dataMap;

    protected Field field;
    protected String persitenceFieldName;
    protected String persistenceTypeName;

    public AbstractPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, MongoDbDataMap dataMap) {
        this.persitenceFieldName = persistenceFieldName;
        this.persistenceTypeName = persistenceTypeName;
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
