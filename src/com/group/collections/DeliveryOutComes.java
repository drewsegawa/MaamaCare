package com.group.collections;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.group.data.DataProvider;
import com.group.mamacare.model.DeliveryOutCome;
import com.group.mamacare.model.DeliveryTypes;
import com.group.mamacare.model.Patient;

public class DeliveryOutComes extends ArrayList<DeliveryOutCome> {

	private static final long serialVersionUID = 1L;
	private Context cont;

	public DeliveryOutComes(Context context) {
		cont = context;
		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		Cursor cursor = db.query("DeliveryOutComes", null, null, null, null,
				null, null);
		DeliveryOutCome dooutcome;
		if (cursor.getCount() != 0) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				dooutcome = new DeliveryOutCome();
				dooutcome.key = cursor.getString(cursor.getColumnIndex("key"));
				dooutcome.deliverytypes = DeliveryTypes.valueOf(cursor
						.getString(cursor.getColumnIndex("deliverytypes")));
				dooutcome.Others = cursor.getString(cursor
						.getColumnIndex("Others"));

				dooutcome.patient = GetPatient(db,
						cursor.getString(cursor.getColumnIndex("refpatient")));

				this.add(dooutcome);

			}
		}

		db.close();
	}

	private Patient GetPatient(SQLiteDatabase db, String pkey) {

		String query = String.format("%s=\"%s\"", "key", pkey);
		Cursor cursor = db.query("PatientData", null, query, null, null, null,
				null);
		Patient p = null;
		if (cursor.getCount() != 0) {

			p = new Patient();
			p.key = cursor.getString(cursor.getColumnIndex("key"));
			p.Name = cursor.getString(cursor.getColumnIndex("Name"));
			p.Dob = cursor.getString(cursor.getColumnIndex("Dob"));
			p.isvacinated = cursor.getInt(cursor.getColumnIndex("isvacinated"));
			p.hasdeliverd = cursor.getInt(cursor.getColumnIndex("hasdeliverd"));
			p.Phonenumber = cursor.getString(cursor
					.getColumnIndex("Phonenumber"));
			p.Location = cursor.getString(cursor.getColumnIndex("Location"));

			p.AncVisits = new AncVisits(cont, p.key);

		}

		return p;
	}

}
