package com.group.collections;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.group.data.DataProvider;
import com.group.mamacare.model.Patient;

public class Patients extends ArrayList<Patient> {

	private static final long serialVersionUID = 1L;

	// select statement adding to this list
	public Patients(Context context) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "hasdeliverd", "0");
		Cursor cursor = db.query("PatientData", null, query, null, null, null,
				null);
		Patient p;
		if (cursor.getCount() != 0) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				p = new Patient();
				p.key = cursor.getString(cursor.getColumnIndex("key"));
				p.Name = cursor.getString(cursor.getColumnIndex("Name"));
				p.Dob = cursor.getString(cursor.getColumnIndex("Dob"));
				p.isvacinated = cursor.getInt(cursor
						.getColumnIndex("isvacinated"));
				p.hasdeliverd = cursor.getInt(cursor
						.getColumnIndex("hasdeliverd"));
				p.Phonenumber = cursor.getString(cursor
						.getColumnIndex("Phonenumber"));
				p.Location = cursor
						.getString(cursor.getColumnIndex("Location"));
				p.Weight = cursor.getString(cursor
						.getColumnIndex("Weight"));

				this.add(p);
				

			}

			for (Patient i : this) {
				i.AncVisits = new AncVisits(context, i.key);
			}
		}

		db.close();
	}

}
