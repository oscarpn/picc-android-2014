package com.example.treinamento.app.service;

import java.util.List;

import com.example.treinamento.app.dao.ContactDAO;
import com.example.treinamento.app.entity.Contact;

public final class ContactService {

	private static final ContactService INSTANCE = new ContactService();

	private ContactService() {
		super();
	}

	public static ContactService getInstance() {
		return INSTANCE;
	};

	public List<Contact> listAll() {
		return ContactDAO.getInstance().listAll(); 
	}

	public void saveContact(Contact contact) {
		ContactDAO.getInstance().saveContact(contact);
	}
}
