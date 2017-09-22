package com.group.mamacare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.group.adapters.CasesAdapter;
import com.group.collections.Patients;
import com.group.mamacare.model.Patient;
import com.group.mamacare.R;

public class ViewCasesActivity extends Activity {

	private ListView lstcases;
	private CasesAdapter casesadpter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cases_layout);
		InitUI();
	}

	private void InitUI() {

		casesadpter = new CasesAdapter(getApplicationContext(), 0);
		lstcases = (ListView) findViewById(R.id.lstcases);
		Patients pdata = new Patients(getApplicationContext());

		casesadpter.addAll(pdata);
		lstcases.setAdapter(casesadpter);
		casesadpter.notifyDataSetChanged();

		lstcases.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				Patient p = (Patient) casesadpter.getItem(position);

				Intent i = new Intent(getApplicationContext(),
						ViewCaseDetailsActivity.class);
				i.putExtra("PatientKeyTwo", p);
				startActivity(i);
			}
		});

		lstcases.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Patient p = (Patient) casesadpter.getItem(position);
				Intent i = new Intent(getApplicationContext(),
						RegisterPatientActivity.class);
				i.putExtra("PatientKey", p);
				startActivity(i);
				return false;
			}
		});

	}
}
