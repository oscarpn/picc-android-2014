package com.example.treinamento.app.service;

import java.util.List;


import android.text.TextUtils;
import com.example.treinamento.app.R;
import com.example.treinamento.app.dao.ContactDAO;
import com.example.treinamento.app.domain.Contact;
import com.example.treinamento.app.domain.exceptions.BussinessException;

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

	public void saveContact(Contact contact) throws BussinessException {
			validateSave(contact);
			ContactDAO.getInstance().saveContact(contact);
	}

	private void validateSave(Contact contact) throws BussinessException {
		BussinessException exception = new BussinessException();
		if (TextUtils.isEmpty(contact.getName())) {
			exception.getExceptionMap().put(R.id.txt_contact_name, R.string.required_field);
		}
		if (TextUtils.isEmpty(contact.getAddress())) {
			exception.getExceptionMap().put(R.id.txt_contact_address, R.string.required_field);
		}
		if (TextUtils.isEmpty(contact.getSite())) {
			exception.getExceptionMap().put(R.id.txt_contact_site, R.string.required_field);
		}
		if (TextUtils.isEmpty(contact.getPhone())) {
			exception.getExceptionMap().put(R.id.txt_contact_phone, R.string.required_field);
		}
		if(!exception.getExceptionMap().isEmpty()){
			throw exception;
		}
	}

	public void deleteContact(Contact contact) {
		ContactDAO.getInstance().removeContact(contact);
	}
}


