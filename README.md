### Kafka Generator

This is a simple java application that will send a message to a Kafka Topic on a configured interval.
I haven't tested with a local version of Kafka and this only ever runs on Heroku with Heroku Kafka

TODO - Document

## required env variables

These variables will be added automatically to your app when you configure an Heroku Kafka addon.
- KAFKA_CLIENT_CERT
- KAFKA_CLIENT_CERT_KEY
- KAFKA_TRUSTED_CERT
- KAFKA_URL

Also, add 
- KAFKA_TOPIC : The topic that you want to send messages to
- INTERVAL : The interval in milliseconds you want a message sent. e.g. if you want every second set this to 1000 

