package com.example.treinamento.app.dao.mapping;

import java.util.ArrayList;
import java.util.List;

import com.example.treinamento.app.domain.Contact;

import android.database.Cursor;
import android.provider.BaseColumns;

public class ContactEntity implements BaseColumns {
	
	public static final String TABLE = "tb_contact";
	
	public static final String COLUM_ID = "id";
	public static final String COLUM_NAME = "name";
	public static final String COLUM_ADDRESS = "address";
	public static final String COLUM_SITE = "site";
	public static final String COLUM_PHONE = "phone";
	public static final String COLUM_RATING = "rating";
	
	public static final String[] ALL_COLUMNS =new String[] {COLUM_ID, COLUM_NAME, COLUM_ADDRESS, COLUM_SITE, COLUM_PHONE, COLUM_RATING};
	
	private ContactEntity() {
		super();
	}
	
	public static Contact bindContact(Cursor cursor){
		if(!cursor.isBeforeFirst() || cursor.moveToNext()){
			Contact contact = new Contact();
			contact.setId(cursor.getLong(cursor.getColumnIndex(COLUM_ID)));
			contact.setName(cursor.getString(cursor.getColumnIndex(COLUM_NAME)));
			contact.setAddress(cursor.getString(cursor.getColumnIndex(COLUM_ADDRESS)));
			contact.setSite(cursor.getString(cursor.getColumnIndex(COLUM_SITE)));
			contact.setPhone(cursor.getString(cursor.getColumnIndex(COLUM_PHONE)));
			int ratingIndex = cursor.getColumnIndex(COLUM_RATING);
			if(!cursor.isNull(ratingIndex)){
				contact.setRating(cursor.getFloat(ratingIndex));
			}
			return contact;
		}
		return null;
	}
	
	public static List<Contact> bindContactList(Cursor cursor){
		List<Contact> contactList = new ArrayList<Contact>();
		while (cursor.moveToNext()) {
			contactList.add(ContactEntity.bindContact(cursor)); //acesso statico da classe
		}
		return contactList;	
	}
	
}
