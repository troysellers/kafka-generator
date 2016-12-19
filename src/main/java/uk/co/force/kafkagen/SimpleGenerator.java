package uk.co.force.kafkagen;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.co.force.kafkagen.services.MessageService;

public class SimpleGenerator {

	private Logger logger;
	private MessageService messageService;
	private Producer<byte[], byte[]> kafkaProducer;
	
	public SimpleGenerator() {
		
		logger = LoggerFactory.getLogger(SimpleGenerator.class);
		logger.info("Started...");
		// get the injected message service that will generate the messages we send to Kafka
		// if you want to change the message, simply configure a concrete implementation of the message service
		// in the ApplicationInjector class
		Injector injector = Guice.createInjector(new ApplicationInjector());
		MessageService messageService = injector.getInstance(MessageService.class);
		
		logger.info("{}", messageService.getMessageData());
		
		KafkaConfig config = new KafkaConfig();
		Producer<byte[], byte[]> kafkaProducer = new KafkaProducer<>(config.getKafkaProps());
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.info("Attempting to shutdown kafka");
				kafkaProducer.close();
			}
		});
	}
	
	public static void main (String[] args) {
		
		new SimpleGenerator();

	}
	
	
	
	
}

