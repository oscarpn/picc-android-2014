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
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.treinamento.app.ContactActivity;
import com.example.treinamento.app.dao.mapping.ContactEntity;
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
			// Opção 1
			SQLiteDatabase db = super.getReadableDatabase();
			String columName = String.format("UPPER(%s)", ContactEntity.COLUM_NAME);
			Cursor cursor = db.query(ContactEntity.TABLE, ContactEntity.ALL_COLUMNS , null, null, null,
					null, columName + "ASC");
			
			/** Opção 2
			  String sqlAll = String.format("SELECT * FROM %s ORDER BY %s", ContactEntity.TABLE, ContactEntity.COLUM_NAME);
			  Cursor cursor = db.rawQuery(sqlAll, null);			
			*/
			// Retirado no dia 08/09 pelo ContatoEntity
//			List<Contact> contacts = new ArrayList<Contact>();
//			while (cursor.moveToNext()) {
//				Contact contact = new Contact();
//				contact.setId(cursor.getLong(0));
//				int nameColumnIndex = cursor.getColumnIndex("name");
//				contact.setName(cursor.getString(nameColumnIndex));
//				contact.setAddress(cursor.getString(2));
//				contact.setSite(cursor.getString(3));
//				contact.setPhone(cursor.getString(4));
//				if(!cursor.isNull(5)){
//					contact.setRating(cursor.getFloat(5));
//				}
//
//				contacts.add(contact);
//			}

			return ContactEntity.bindContactList(cursor);
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
			cv.put(ContactEntity.COLUM_NAME, contact.getName());
			cv.put(ContactEntity.COLUM_ADDRESS, contact.getAddress());
			cv.put(ContactEntity.COLUM_PHONE, contact.getPhone());
			cv.put(ContactEntity.COLUM_SITE, contact.getSite());
			if(contact.getRating().floatValue() == 0){
				cv.putNull(ContactEntity.COLUM_RATING);
			}else{
				cv.put(ContactEntity.COLUM_RATING, contact.getRating());
			}
			if (contact.getId() == null) {
				db.insert(ContactEntity.TABLE, null, cv);
			} else {
				String whereClause = ContactEntity.COLUM_ID + " =?";
				String[] parameters = new String[] {contact.getId().toString()};
				db.update(ContactEntity.TABLE, cv, whereClause, parameters );
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
			//Opção 1 db.delete
			String whereClause = ContactEntity.COLUM_ID + " =?";
			String[] parameters = new String[] {contact.getId().toString()};
			db.delete(ContactEntity.TABLE, whereClause, parameters );
			
			//Opção 2 Compile Statament
//			String sqlDelete = String.format("DELETE FROM %s WHERE %s = ?", ContactEntity.TABLE, ContactEntity.COLUM_ID);
//			SQLiteStatement compileStatement = db.compileStatement(sqlDelete);
//			compileStatement.bindLong(1, contact.getId());
//			compileStatement.execute();
			
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
