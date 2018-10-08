package com.frank.persistence.api;

public interface PersistenceMap {

    void setFieldValue(Object instance, Object value);

    Object getFieldValue(Object instance);

    String getPersitenceFieldName();
}
