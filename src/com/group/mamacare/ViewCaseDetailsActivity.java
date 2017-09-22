package com.group.mamacare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.group.adapters.AncAdapter;
import com.group.collections.AncVisits;
import com.group.mamacare.model.Patient;
import com.group.mamacare.R;

public class ViewCaseDetailsActivity extends Activity {

	TextView txtName, txtdateofbirth, txtphonenumber, txtweith, txtlocation;
	CheckBox checkBoxvaccination, checkBoxdelivered;
	ListView lstancvisits;
	Button btn_add_anc_visit;
	Patient p;
	private AncAdapter ancadpter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_case_details);
		InitUI();
	}

	private void InitUI() {
		p = (Patient) getIntent().getExtras().getParcelable(
				"PatientKeyTwo");
		ancadpter = new AncAdapter(getApplicationContext(), 0);
		lstancvisits = (ListView) findViewById(R.id.lstancvisits);
		txtName = (TextView) findViewById(R.id.txtName);
		txtdateofbirth = (TextView) findViewById(R.id.txtdateofbirth);
		txtphonenumber = (TextView) findViewById(R.id.txtphonenumber);
		txtweith = (TextView) findViewById(R.id.txtweith);
		txtlocation = (TextView) findViewById(R.id.txtlocation);

		checkBoxvaccination = (CheckBox) findViewById(R.id.checkBoxvaccination);
		checkBoxdelivered = (CheckBox) findViewById(R.id.checkBoxdelivered);

		btn_add_anc_visit = (Button) findViewById(R.id.btn_add_anc_visit);

		if (p != null) {
			txtName.setText("Name:" + p.Name);
			txtdateofbirth.setText("DOB:" + p.Dob);
			txtweith.setText("Weight:" + p.Weight);
			txtlocation.setText("Location:" + p.Location);
			txtphonenumber.setText("Phone Number:" + p.Phonenumber);

			if (p.hasdeliverd == 1)
				checkBoxdelivered.setChecked(true);
			if (p.isvacinated == 1)
				checkBoxvaccination.setChecked(true);

			AncVisits vs = new AncVisits(getApplicationContext(), p.key);

			ancadpter.addAll(vs);
			lstancvisits.setAdapter(ancadpter);
			ancadpter.notifyDataSetChanged();
		}

		btn_add_anc_visit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AddAnCVisits();
			}
		});
	}

	private void AddAnCVisits() {
		Intent i = new Intent(getApplicationContext(),
				AddAnCVisitsActivity.class);
		i.putExtra("PatientKey", p.key);
		startActivity(i);
	}

}
