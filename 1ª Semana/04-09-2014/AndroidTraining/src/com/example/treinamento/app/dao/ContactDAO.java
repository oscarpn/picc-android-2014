package com.example.treinamento.app.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.treinamento.app.domain.Contact;

public final class ContactDAO extends BaseDAO {

	private static ContactDAO INSTANCE;

	private ContactDAO(Context context) {
		super(context);
	}

	public static ContactDAO getInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = new ContactDAO(context);
		}
		return INSTANCE;
	};

	public List<Contact> listAll() throws SQLException {
		try {
			SQLiteDatabase db = super.getReadableDatabase();
			String[] columns = { "id", "name", "address", "site", "phone",
					"rating" };
			Cursor cursor = db.query("tb_contact", columns, null, null, null,
					null, null);
			List<Contact> contacts = new ArrayList<Contact>();
			while (cursor.moveToNext()) {
				Contact contact = new Contact();
				contact.setId(cursor.getLong(0));
				int nameColumnIndex = cursor.getColumnIndex("name");
				contact.setName(cursor.getString(nameColumnIndex));
				contact.setAddress(cursor.getString(2));
				contact.setSite(cursor.getString(3));
				contact.setPhone(cursor.getString(4));
				contact.setRating(cursor.getFloat(5));

				contacts.add(contact);
			}

			return contacts;
		} catch (SQLException exception) {
			Log.e("DAO", exception.getMessage());
			throw exception;
		} finally {
			super.close();
		}
	}

	public void saveContact(Contact contact) throws SQLException {
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			db.beginTransaction();
			ContentValues cv = new ContentValues();
			cv.put("name", contact.getName());
			cv.put("address", contact.getAddress());
			cv.put("phone", contact.getPhone());
			cv.put("site", contact.getSite());
			cv.put("rating", contact.getRating());
			if (contact.getId() == null) {
				db.insert("tb_contact", null, cv);
			} else {
				String[] parameters = new String[] {contact.getId().toString()};
				db.update("tb_contact", cv, "id=?", parameters );
			}
			db.setTransactionSuccessful();
		} catch (SQLException exception) {
			Log.e("DAO", exception.getMessage());
			throw exception;
		} finally {
			db.endTransaction();
			super.close();
		}
	}

	public void removeContact(Contact contact) throws SQLException{
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			db.beginTransaction();
			String[] parameters = new String[] {contact.getId().toString()};
			db.delete("tb_contact", "id=?", parameters );
			db.setTransactionSuccessful();
		} catch (SQLException exception) {
			Log.e("DAO", exception.getMessage());
			throw exception;
		} finally {
			db.endTransaction();
 			super.close();
		}
	}
}
