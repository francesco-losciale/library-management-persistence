package com.frank.mapper.field.mongodb;

import com.frank.mapper.DataMap;
import org.bson.types.Decimal128;

public class BigDecimalPersistenceMap extends StringPersistenceMap {

    public BigDecimalPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    @Override
    public void setFieldValue(Object instance, Object value) {
        try {
            Object castValue = ((Decimal128) Class.forName(this.persistenceTypeName).cast(value)).bigDecimalValue();
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
            return Decimal128.parse(field.get(instance).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: field " + this.persitenceFieldName + " not accessible from the bean");
        }
    }

}
