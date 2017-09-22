package com.group.mamacare;

import com.group.mamacare.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

	Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		InitUI();
	}

	private void InitUI() {

		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				StartMainActivity();
				finish();
			}
		});
	}

	private void StartMainActivity() {

		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

}
