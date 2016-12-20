package uk.co.force.kafkagen;

import com.google.inject.AbstractModule;

import uk.co.force.kafkagen.services.MessageService;
import uk.co.force.kafkagen.services.SimpleMessageService;
/**
 * Google Guice configuration 
 */
public class ApplicationInjector extends AbstractModule{

	@Override
	protected void configure() {
		
		/*
		 * Change this binding if you want to provide a different message service implementation
		 */
		bind(MessageService.class).to(SimpleMessageService.class);
	}
}
