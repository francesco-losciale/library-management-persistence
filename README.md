# Simple poc for persisting domain objects

How to persist domain object which by definition are not defined by simple database entity or DTO classes with public getters/setters? 

A possible solution is to use the [Metadata Mapping pattern](https://martinfowler.com/eaaCatalog/metadataMapping.html) by Martin Fowler.

A complete API has been specified here implemented with MongoDB. 

Most important flaws:

1. Excessive use of Java Reflection
2. Where do you place OrderFieldMetadataEnum which refers to both the persistence field and the domain classes' name? Would you place it in the business component or in the persistence component? It is placed here to keep the dependency inversion principle valid.


### Prerequisites

You need to know the configuration of your Docker Machine and set the correct values in both the MongoDbRepository class and the pom.xml file.


## Built With

* Docker containerization for running integration tests 
* MongoDb for persistence
* SPOCK framework with Groovy language for writing the integration tests


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
