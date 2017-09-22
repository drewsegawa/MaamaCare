package com.group.mamacare;

import com.group.mamacare.model.Patient;
import com.group.mamacare.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button btn_register_patient, btn_view_patient, btn_view_deliveries,
			btn_register_user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);

		InitUI();
	}

	private void InitUI() {

		btn_register_patient = (Button) findViewById(R.id.btn_register_patient);
		btn_view_patient = (Button) findViewById(R.id.btn_view_patient);
		btn_view_deliveries = (Button) findViewById(R.id.btn_view_deliveries);
		btn_register_user = (Button) findViewById(R.id.btn_register_user);

		btn_register_patient.setOnClickListener(this);
		btn_view_patient.setOnClickListener(this);
		btn_view_deliveries.setOnClickListener(this);
		btn_register_user.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.btn_register_patient) {

			Patient p = null;
			Intent i = new Intent(getApplicationContext(),
					RegisterPatientActivity.class);
			i.putExtra("PatientKey", p);
			startActivity(i);

		} else if (v.getId() == R.id.btn_view_patient) {
			Intent i = new Intent(getApplicationContext(),
					ViewCasesActivity.class);
			startActivity(i);

		} else if (v.getId() == R.id.btn_view_deliveries) {
			Log.e("btn_view_deliveries", "btn_view_deliveries");

		} else if (v.getId() == R.id.btn_register_user) {
			Log.e("btn_register_user", "btn_register_user");

		}

	}

}
