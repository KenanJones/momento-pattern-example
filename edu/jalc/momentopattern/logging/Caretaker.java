package edu.jalc.momentopattern.logging;

import edu.jalc.momentopattern.logging.messages.Warn;
import edu.jalc.momentopattern.logging.observers.*;
import edu.jalc.momentopattern.logging.observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Caretaker {

	private ArrayList<Message> messages = new ArrayList<Message>();
	private ArrayList<Observer> observers = new ArrayList<>();

	Caretaker(){
		this.observers.add(new ConsoleLogger());
		this.observers.add(new LogBeeper());
	}

	Caretaker add(final Message message){
		this.messages.add(message);
		this.observers.parallelStream().forEach((observer) -> observer.observe(message));
		return this;
	}

	Message get(int messageId){
		return this.messages.get(messageId);
	}

	ArrayList<Message> getAll(State context){

		ArrayList<Message> filtered = null;

		if(context == State.WARN){
			filtered =  (ArrayList<Message>) this.getWarnings();
		}

		return filtered;
	}

	private List<Message> getWarnings(){

		return messages.parallelStream().filter((message) -> message instanceof Warn).collect(Collectors.toList());

	}


}
