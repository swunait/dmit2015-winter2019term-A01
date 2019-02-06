package dmit2015.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import dmit2015.service.MessageService;
import dmit2015.model.Message;

@Named("helloWorldJpa")
@RequestScoped
public class HelloWorldJpaController {
	
	private Message message = new Message();
	private List<Message> messages;
	
	@Inject
	private MessageService messageService;
	
	@PostConstruct
	public void init() {
		messages = messageService.list();
	}
	
	public void submit() {
		messageService.createMessage(message);
		messages.add(message);
		Messages.addGlobalInfo("Add message was successful.");
	}

	public Message getMessage() {
		return message;
	}

	public List<Message> getMessages() {
		return messages;
	}

}