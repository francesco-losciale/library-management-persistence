package com.frank.mapper.field.mongodb;

import com.frank.mapper.DataMap;

public class EnumPersistenceMap extends StringPersistenceMap {

    public EnumPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    @Override
    public void setFieldValue(Object instance, Object value) {
        try {
            Object castValue = Enum.valueOf((Class<Enum>) Class.class.forName(persistenceTypeName), (String) value);
            field.set(instance, castValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Implementation error: mapping for field " + this.persitenceFieldName + " failed.", e);
        }
    }

    @Override
    public Object getFieldValue(Object instance) {
        try {
            return field.get(instance).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        }
    }

}
