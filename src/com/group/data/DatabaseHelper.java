package com.group.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	DatabaseHelper(Context context, String name) {
		super(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("create dbs tables", "");
		CreateTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
	}

	private void CreateTables(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE Users ( OID INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,userName TEXT,password TEXT,gender TEXT)");
		db.execSQL("CREATE TABLE PatientData ( OID INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,Name TEXT,Dob TEXT,isvacinated INTEGER,hasdeliverd INTEGER,Phonenumber TEXT,Location TEXT,Weight TEXT)");
		db.execSQL("CREATE TABLE AncVisit ( OID INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,ancvisittype TEXT,Others TEXT,refpatient TEXT)");
		db.execSQL("CREATE TABLE DeliveryOutComes ( OID INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,deliverytypes TEXT,Others TEXT,refpatient TEXT)");
	}

}
