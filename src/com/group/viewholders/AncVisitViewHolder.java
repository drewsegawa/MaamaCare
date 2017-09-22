package com.group.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.group.mamacare.model.AncVisit;
import com.group.mamacare.R;

public class AncVisitViewHolder extends BaseViewHolder {

	private TextView txtvisittype, txtothers;

	public AncVisitViewHolder(Context context) {
		super(context);

	}

	public AncVisitViewHolder(View view, Context context) {
		super(context);

		txtvisittype = (TextView) view.findViewById(R.id.txtvisittype);
		txtothers = (TextView) view.findViewById(R.id.txtothers);

	}

	public void populateViews(AncVisit visit) {
		txtvisittype.setText(visit.ancvisittype.toString());
		txtothers.setText(visit.Others);

	}

}
