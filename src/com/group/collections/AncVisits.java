package com.group.collections;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.group.data.DataProvider;
import com.group.mamacare.model.AncVisit;
import com.group.mamacare.model.AncVisitTypes;

public class AncVisits extends ArrayList<AncVisit> {

	private static final long serialVersionUID = 1L;

	public AncVisits() {

	}

	public AncVisits(Context context, String refPatient) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "refpatient", refPatient);
		Cursor cursor = db.query("AncVisit", null, query, null, null, null,
				null);
		AncVisit ancv;
		if (cursor.getCount() != 0) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				ancv = new AncVisit();
				ancv.key = cursor.getString(cursor.getColumnIndex("key"));
				ancv.ancvisittype = AncVisitTypes.valueOf(cursor
						.getString(cursor.getColumnIndex("ancvisittype")));
				ancv.Others = cursor.getString(cursor.getColumnIndex("Others"));

				this.add(ancv);

			}
		}

		db.close();
	}

}
