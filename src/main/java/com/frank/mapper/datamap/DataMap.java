package com.frank.mapper.datamap;

public interface DataMap<DomainClass> {

    void addField(String persistenceFieldName, String persistenceTypeName, String domainFieldName);

    void addEnumField(String persistenceFieldName, String persistenceTypeName, String domainFieldName);

    void addBigDecimalField(String persistenceFieldName, String persistenceTypeName, String domainFieldName);

}
