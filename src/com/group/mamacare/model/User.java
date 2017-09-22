package com.group.mamacare.model;

import java.util.UUID;

import com.group.data.DataProvider;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	public static final String userSharedPrefKey = "com.core.traffica.model.user.userDetails";
	public static final String usernameKey = "com.core.traffica.model.user.username";
	public static final String passwordKey = "com.core.traffica.model.user.password";
	public static final String genderKey = "com.core.traffica.model.user.gender";
	public static final String userKey = "com.core.traffica.model.user.key";

	private String userName;
	private String password;
	private Gender gender;
	String key;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
		Init();
	}

	private User(Parcel in) {
		Init();
		key = in.readString();
		password = in.readString();
		userName = in.readString();
		gender = Gender.valueOf(in.readString());
	}

	private void Init() {
		userName = "";
		password = "";
		gender = Gender.Male;
		key = UUID.randomUUID().toString();
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeString(key);
		dest.writeString(password);
		dest.writeString(userName);
		dest.writeString(gender.toString());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public void SetUserFromPrefs(Context context, String PrefName) {

		SharedPreferences prefs = context.getSharedPreferences(PrefName, 0);
		setUserName(prefs.getString(User.usernameKey, ""));
		setPassword(prefs.getString(User.passwordKey, ""));
	}

	public ContentValues toContentValues() {
		ContentValues cv = new ContentValues();
		cv.put("key", key);
		cv.put("userName", userName);
		cv.put("password", password);
		cv.put("gender", gender.toString());
		return cv;
	}

	public void InsertUpdate(Context context) {

		DataProvider provider = new DataProvider();
		SQLiteDatabase db = provider.getDatabase(context);

		String query = String.format("%s=\"%s\"", "key", this.key);
		Cursor cursor = db.query("Users", null, query, null, null, null, null);

		if (cursor.getCount() == 0) {
			db.insert("Users", null, this.toContentValues());
		} else {
			db.update("Users", this.toContentValues(), query, null);
		}

		db.close();
	}

}
