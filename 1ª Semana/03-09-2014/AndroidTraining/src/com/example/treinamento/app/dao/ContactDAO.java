package com.example.treinamento.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.treinamento.app.domain.Contact;

public final class ContactDAO {

	private static final ContactDAO INSTANCE = new ContactDAO();
	private static long SEQUENCE = 1;
	private static final List<Contact> REPOSITORY = new ArrayList<>();

	private ContactDAO() {
		super();
	}

	public static ContactDAO getInstance() {
		return INSTANCE;
	};

	public List<Contact> listAll() {
		return REPOSITORY; 
	}

	public void saveContact(Contact contact) {
		if(contact.getId() == null){
			contact.setId(SEQUENCE++);	
			REPOSITORY.add(contact);
		}
		else{
			REPOSITORY.set(REPOSITORY.indexOf(contact), contact);
		}
	}

	public void removeContact(Contact contact) {
		REPOSITORY.remove(contact);
	}
}
