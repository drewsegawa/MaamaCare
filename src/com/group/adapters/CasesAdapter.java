package com.group.adapters;

import com.group.mamacare.R;
import com.group.mamacare.model.Patient;
import com.group.viewholders.CasesViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CasesAdapter extends ArrayAdapter<Patient> {

	Context cont;

	public CasesAdapter(Context context, int resource) {
		super(context, resource);
		cont = context;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		CasesViewHolder viewHolder;

		LayoutInflater mInflater = (LayoutInflater) cont
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = mInflater.inflate(R.layout.case_item_layout, null);
			viewHolder = new CasesViewHolder(view, cont);
			view.setTag(viewHolder);
		} else {
			viewHolder = (CasesViewHolder) view.getTag();
		}

		viewHolder.populateViews(getItem(position));

		return view;
	}

}
