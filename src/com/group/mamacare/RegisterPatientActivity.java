package com.group.mamacare;

import com.group.mamacare.model.DeliveryOutCome;
import com.group.mamacare.model.DeliveryTypes;
import com.group.mamacare.model.Patient;
import com.group.mamacare.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterPatientActivity extends Activity {

	Patient p;
	Button btn_save;
	EditText input_name, input_dob, input_weight, input_location,
			input_phonenumber;
	CheckBox checkBoxvaccination, checkBoxdelivered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_patient_layout);
		InitUI();
	}

	private void InitUI() {
		Object o = getIntent().getExtras().getParcelable("PatientKey");
		if (o != null)
			p = (Patient) o;

		input_name = (EditText) findViewById(R.id.input_name);
		input_dob = (EditText) findViewById(R.id.input_dob);
		input_weight = (EditText) findViewById(R.id.input_weight);
		input_location = (EditText) findViewById(R.id.input_location);
		input_phonenumber = (EditText) findViewById(R.id.input_phonenumber);

		checkBoxvaccination = (CheckBox) findViewById(R.id.checkBoxvaccination);
		checkBoxdelivered = (CheckBox) findViewById(R.id.checkBoxdelivered);

		btn_save = (Button) findViewById(R.id.btn_save);

		if (p != null) {
			input_name.setText(p.Name);
			input_dob.setText(p.Dob);
			input_weight.setText(p.Weight);
			input_location.setText(p.Location);
			input_phonenumber.setText(p.Phonenumber);

			if (p.hasdeliverd == 1)
				checkBoxdelivered.setChecked(true);
			if (p.isvacinated == 1)
				checkBoxvaccination.setChecked(true);
		}

		btn_save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SaveData();

			}
		});
	}

	private void SaveData() {

		if (p == null)
			p = new Patient();

		p.Name = input_name.getText().toString();
		p.Dob = input_dob.getText().toString();
		p.Weight = input_weight.getText().toString();
		p.Location = input_location.getText().toString();
		p.Phonenumber = input_phonenumber.getText().toString();

		if (checkBoxvaccination.isChecked())
			p.isvacinated = 1;

		if (checkBoxdelivered.isChecked()) {
			p.hasdeliverd = 1;

			// save to delivery out put
			DeliveryOutCome dcome = new DeliveryOutCome();
			dcome.deliverytypes = DeliveryTypes.None;
			dcome.Others = "update this record";
			dcome.patient = p;
			dcome.InsertUpdate(getApplicationContext(), p.key);
		}

		p.InsertUpdate(getApplicationContext());

		finish();

	}
}
