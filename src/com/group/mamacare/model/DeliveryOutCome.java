package com.group.mamacare.model;

import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.group.data.DataProvider;

public class DeliveryOutCome implements Parcelable {

	public DeliveryTypes deliverytypes;
	public String Others;
	public String key;
	public Patient patient;

	public DeliveryOutCome() {
		Init();
	}

	private void Init() {
		key = UUID.randomUUID().toString();
		deliverytypes = DeliveryTypes.Others;
		Others = "";
		patient = new Patient();
	}

	private DeliveryOutCome(Parcel in) {
		Init();
		key = in.readString();
		deliverytypes = DeliveryTypes.valueOf(in.readString());
		Others = in.readString();
		patient = in.readParcelable(Patient.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(deliverytypes.toString());
		dest.writeString(Others);
		dest.writeParcelable(patient, 0);
	}

	public static final Parcelable.Creator<DeliveryOutCome> CREATOR = new Parcelable.Creator<DeliveryOutCome>() {
		public DeliveryOutCome createFromParcel(Parcel in) {
			return new DeliveryOutCome(in);
		}

		public DeliveryOutCome[] newArray(int size) {
			return new DeliveryOutCome[size];
		}
	};

	public ContentValues toContentValues(String patientKey) {
		ContentValues cv = new ContentValues();
		cv.put("key", key);
		cv.put("deliverytypes", deliverytypes.toString());
		cv.put("Others", Others);
		cv.put("refpatient", patientKey);

		return cv;
	}

	public void InsertUpdate(Context context, String patientKey) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "key", this.key);
		Cursor cursor = db.query("DeliveryOutComes", null, query, null, null,
				null, null);

		if (cursor.getCount() == 0) {
			db.insert("DeliveryOutComes", null,
					this.toContentValues(patientKey));
		} else {
			db.update("DeliveryOutComes", this.toContentValues(patientKey),
					query, null);
		}

		db.close();
	}

}
