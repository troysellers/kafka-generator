package uk.co.force.kafkagen;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.co.force.kafkagen.services.MessageService;

public class SimpleGenerator {

	protected Logger logger;
	protected MessageService messageService;
	protected Producer<String,String> kafkaProducer;
	
	public static void main (String[] args) {
		new SimpleGenerator();
	}	
	
	public SimpleGenerator() {
		
		logger = LoggerFactory.getLogger(SimpleGenerator.class);
		logger.info("Started...");
		
		// get the injected message service that will generate the messages we send to Kafka
		// if you want to change the message, simply configure a concrete implementation of the message service
		// in the ApplicationInjector class
		Injector injector = Guice.createInjector(new ApplicationInjector());
		messageService = injector.getInstance(MessageService.class);	

		// initialise kafka producer
		KafkaConfig config = new KafkaConfig();
		kafkaProducer = new KafkaProducer<>(config.getKafkaProps());			
		
		Timer timer = new Timer();
		timer.schedule(new ProducerTask(), 0, 1000); //start immediately and post every second
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				kafkaProducer.close();
			}
		});
		
	}	
	
	class ProducerTask extends TimerTask {

		@Override
		public void run() {
	
			String message = messageService.getMessageData();
			logger.info("Getting ready to send message :- \n{}", message);
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("interactions", message);
			
			// send and process asynchronous
			kafkaProducer.send(producerRecord, new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if(exception != null) 
						exception.printStackTrace();
					else
						logger.info("We sent message to Kafka Topic as offset {}", metadata.offset());
				}
			});
		}
	}
}

