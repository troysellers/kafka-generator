### Kafka Generator

This is a simple java application that will send a message to a Kafka Topic on a configured interval.
I haven't tested with a local version of Kafka and this only ever runs on Heroku with Heroku Kafka

Clone this repository and provide your own implementation of MessageService class. 
Configure Guice dependency injection in the ApplicationInjector.. 

Or 
[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

## required env variables

These variables will be added automatically to your app when you [configure an Heroku Kafka addon](https://devcenter.heroku.com/articles/kafka-on-heroku#kafka-concepts).
- KAFKA_CLIENT_CERT
- KAFKA_CLIENT_CERT_KEY
- KAFKA_TRUSTED_CERT
- KAFKA_URL

Also, add 
- KAFKA_TOPIC : The topic that you want to send messages to
- INTERVAL : The interval in milliseconds you want a message sent. e.g. if you want every second set this to 1000 

## Run local

You will want to have Java 1.8 installed and Maven 3
```
java -version
mvn -version
```

Get the heroku kafka environment (unless you want to build your own local Kafka cluster...)
```
heroku config -s -a HEROKU_APP_NAME > .env
set -o allexport
source .env
set +o allexport
```

### add .env to the .gitignore (or similar for you chosen repo) 

Build using Maven
```
mvn clean package
```

Run

```
java -jar target/kafka-gen-jar-with-dependencies.jar
```

