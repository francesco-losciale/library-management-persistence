# Business Object persistence on MongoDb (work in progress)

A simple POC for persisting objecs in a MongoDb database.

## Getting Started

git clone https://github.com/francesco-losciale/library-management-persistence.git

mvn install


### Prerequisites

You need to know the configuration of your Docker Machine and set the correct values in both the MongoDbRepository class and the pom.xml file.

## Built With

* Docker containerization for running integration tests 
* MongoDb for persistence
* SPOCK framework with Groovy language for writing the integration tests
