package com.group.mamacare.model;

import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.group.collections.AncVisits;
import com.group.data.DataProvider;

public class Patient implements Parcelable {

	public String Name;
	public String Dob;
	public int isvacinated;
	public int hasdeliverd;
	public String Phonenumber;
	public String Location;
	public String key;
	public String Weight;

	public AncVisits AncVisits;

	public Patient() {
		Init();
	}

	private void Init() {
		key = UUID.randomUUID().toString();
		Name = "";
		Dob = "";
		isvacinated = 0;
		hasdeliverd = 0;
		Phonenumber = "";
		Location = "";
		Weight = "";
		AncVisits = new AncVisits();
	}

	private Patient(Parcel in) {
		Init();
		key = in.readString();
		Name = in.readString();
		Dob = in.readString();
		isvacinated = in.readInt();
		hasdeliverd = in.readInt();
		Phonenumber = in.readString();
		Location = in.readString();
		Weight = in.readString();
		in.readTypedList(AncVisits, AncVisit.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(Name);
		dest.writeString(Dob);
		dest.writeInt(isvacinated);
		dest.writeInt(hasdeliverd);
		dest.writeString(Phonenumber);
		dest.writeString(Location);
		dest.writeString(Weight);
		dest.writeTypedList(AncVisits);
	}

	public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
		public Patient createFromParcel(Parcel in) {
			return new Patient(in);
		}

		public Patient[] newArray(int size) {
			return new Patient[size];
		}
	};

	public ContentValues toContentValues() {
		ContentValues cv = new ContentValues();
		cv.put("key", key);
		cv.put("Name", Name);
		cv.put("Dob", Dob);
		cv.put("isvacinated", isvacinated);
		cv.put("hasdeliverd", hasdeliverd);
		cv.put("Phonenumber", Phonenumber);
		cv.put("Location", Location);
		cv.put("Weight", Weight);
		return cv;
	}

	public void InsertUpdate(Context context) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "key", this.key);
		Cursor cursor = db.query("PatientData", null, query, null, null, null,
				null);

		if (cursor.getCount() == 0) {
			db.insert("PatientData", null, this.toContentValues());
		} else {
			db.update("PatientData", this.toContentValues(), query, null);
		}

		db.close();
	}

}
