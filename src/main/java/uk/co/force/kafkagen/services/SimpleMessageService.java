package uk.co.force.kafkagen.services;

import java.util.Random;

import org.json.JSONObject;
/**
 * Simple implementation of the MessageService. 
 * 
 */
public class SimpleMessageService implements MessageService{

	@Override
	public String getMessageData() {
		
		/*
		 * If you want different message values you can just return the string you wish
		 * to see in your Kafka message. 
		 */
		
		Random r = new Random();
		
		JSONObject obj = new JSONObject();
		obj.append("Name", "This is the name - "+r.nextInt(15000000));
		obj.append("Address", "This is an address (not) - "+r.nextInt(15000000));
		
		return obj.toString();
	}

	
}
