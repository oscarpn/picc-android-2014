package com.example.treinamento.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseDAO extends SQLiteOpenHelper {

	

	private static final String DB_NAME = "AndroidTraining";
	private static final int DB_VERSION = 1;

	public BaseDAO(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create = "CREATE TABLE tb_contact(id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT NOT NULL, phone TEXT NOT NULL, site TEXT NOT NULL, rating REAL);";
		db.execSQL(create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String create = "DROP TABLE IF EXISTS tb_contact;";
		db.execSQL(create);
	}

}
