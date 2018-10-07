package com.frank.mapper.datamap;

public interface DataMap<DomainClass> {

    void addField(String persistenceFieldName, String domainFieldName);

    void addEnumField(String persistenceFieldName, String domainFieldName);

    void addBigDecimalField(String persistenceFieldName, String domainFieldName);

    Class getDomainClass();
}
