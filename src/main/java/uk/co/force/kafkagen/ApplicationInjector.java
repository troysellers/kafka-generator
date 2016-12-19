package uk.co.force.kafkagen;

import com.google.inject.AbstractModule;

import uk.co.force.kafkagen.services.MessageService;
import uk.co.force.kafkagen.services.SimpleMessageService;

public class ApplicationInjector extends AbstractModule{

	@Override
	protected void configure() {
		bind(MessageService.class).to(SimpleMessageService.class);
	}
}
