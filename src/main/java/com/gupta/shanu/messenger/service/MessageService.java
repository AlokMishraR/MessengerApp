package com.gupta.shanu.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.gupta.shanu.messenger.database.DatabaseClass;
import com.gupta.shanu.messenger.exception.DataNotFoundException;
import com.gupta.shanu.messenger.model.Message;

public class MessageService {
	
	Map<Long,Message> messages;
	public MessageService(){
		messages = DatabaseClass.getMessages();
		messages.put(1L, new Message(1L,"Message1","ShanuGupta"));
		messages.put(2L, new Message(2L,"Message2","ShanuGupta"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	public List<Message> getAllMessagesForYear(int year){
		List<Message> filteredList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message m : messages.values()){
			cal.setTime(m.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				filteredList.add(m);
			}
		}
		return filteredList;
	}
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> filteredList = new ArrayList<Message>(messages.values());
		if(start+size>filteredList.size()) return new ArrayList<Message>();
		return filteredList.subList(start, start+size);
	}
	public Message getMessage(long id){
		if(messages.get(id) == null){
			throw new DataNotFoundException("Message with id " + id + " is not found");
		}
		return messages.get(id);
	}
	public Message addMessage(Message m){
		m.setId(messages.size()+1);
		messages.put(m.getId(),m);
		return m;
	}
	public Message updateMessage(Message m){
		if(m.getId()<=0) return null;
		messages.put(m.getId(),m);
		return m;
	}
	public Message deteteMessage(long id){
		return messages.remove(id);
	}
}
