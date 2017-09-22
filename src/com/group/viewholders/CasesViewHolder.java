package com.group.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.group.mamacare.R;
import com.group.mamacare.model.Patient;

public class CasesViewHolder extends BaseViewHolder {

	private TextView txtName, txtdateofbirth, txtphonenumber;

	public CasesViewHolder(Context context) {
		super(context);

	}

	public CasesViewHolder(View view, Context context) {
		super(context);

		txtName = (TextView) view.findViewById(R.id.txtName);
		txtdateofbirth = (TextView) view.findViewById(R.id.txtdateofbirth);
		txtphonenumber = (TextView) view.findViewById(R.id.txtphonenumber);

	}

	public void populateViews(Patient patient) {

		txtName.setText(patient.Name);
		txtdateofbirth.setText(patient.Dob);
		txtphonenumber.setText(patient.Phonenumber);

	}

}
