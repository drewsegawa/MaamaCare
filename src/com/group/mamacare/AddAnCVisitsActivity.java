package com.group.mamacare;

import com.group.adapters.AncVisitTypesAdapter;
import com.group.mamacare.model.AncVisit;
import com.group.mamacare.model.AncVisitTypes;
import com.group.mamacare.model.Patient;
import com.group.mamacare.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAnCVisitsActivity extends Activity {

	Spinner spinvisittypes;
	EditText input_others;
	Button btn_save;
	String pkey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_an_cvisits_layout);
		InitUI();
	}

	private void InitUI() {

		pkey = (String) getIntent().getExtras().getString("PatientKey");
		input_others = (EditText) findViewById(R.id.input_others);
		spinvisittypes = (Spinner) findViewById(R.id.spinvisittypes);
		btn_save = (Button) findViewById(R.id.btn_save);

		spinvisittypes.setAdapter(new AncVisitTypesAdapter(this, getResources()
				.getStringArray(R.array.ancvisists_array)));

		btn_save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AncVisit av = new AncVisit();

				if (spinvisittypes.getSelectedItemPosition() == 0)
					av.ancvisittype = AncVisitTypes.VisitOne;
				else if (spinvisittypes.getSelectedItemPosition() == 1)
					av.ancvisittype = AncVisitTypes.VisitTwo;
				else if (spinvisittypes.getSelectedItemPosition() == 2)
					av.ancvisittype = AncVisitTypes.VisitThree;
				else if (spinvisittypes.getSelectedItemPosition() == 3)
					av.ancvisittype = AncVisitTypes.VisitFour;
				else if (spinvisittypes.getSelectedItemPosition() == 4)
					av.ancvisittype = AncVisitTypes.Other;

				av.Others = input_others.getText().toString();

				av.InsertUpdate(getApplicationContext(), pkey);
				finish();
			}
		});
	}
}
