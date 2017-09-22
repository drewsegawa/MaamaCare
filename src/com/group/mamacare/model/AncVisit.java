package com.group.mamacare.model;

import java.util.UUID;

import com.group.data.DataProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class AncVisit implements Parcelable {

	public AncVisitTypes ancvisittype;
	public String Others;
	public String key;

	public AncVisit() {
		key = UUID.randomUUID().toString();
		ancvisittype = AncVisitTypes.Other;
		Others = "";
	}

	private AncVisit(Parcel in) {
		key = in.readString();
		ancvisittype = AncVisitTypes.valueOf(in.readString());
		Others = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(ancvisittype.toString());
		dest.writeString(Others);
	}

	public static final Parcelable.Creator<AncVisit> CREATOR = new Parcelable.Creator<AncVisit>() {
		public AncVisit createFromParcel(Parcel in) {
			return new AncVisit(in);
		}

		public AncVisit[] newArray(int size) {
			return new AncVisit[size];
		}
	};

	public ContentValues toContentValues(String patientKey) {
		ContentValues cv = new ContentValues();
		cv.put("key", key);
		cv.put("ancvisittype", ancvisittype.toString());
		cv.put("Others", Others);
		cv.put("refpatient", patientKey);

		return cv;
	}

	public void InsertUpdate(Context context, String patientKey) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "key", this.key);
		Cursor cursor = db.query("AncVisit", null, query, null, null, null,
				null);

		if (cursor.getCount() == 0) {
			db.insert("AncVisit", null, this.toContentValues(patientKey));
		} else {
			db.update("AncVisit", this.toContentValues(patientKey), query, null);
		}

		db.close();
	}

}
