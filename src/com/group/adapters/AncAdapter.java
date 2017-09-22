package com.group.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.group.mamacare.model.AncVisit;
import com.group.viewholders.AncVisitViewHolder;
import com.group.viewholders.CasesViewHolder;
import com.group.mamacare.R;

public class AncAdapter extends ArrayAdapter<AncVisit> {

	Context cont;

	public AncAdapter(Context context, int resource) {
		super(context, resource);
		cont = context;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		AncVisitViewHolder viewHolder;

		LayoutInflater mInflater = (LayoutInflater) cont
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = mInflater.inflate(R.layout.anc_visit_item_layout, null);
			viewHolder = new AncVisitViewHolder(view, cont);
			view.setTag(viewHolder);
		} else {
			viewHolder = (AncVisitViewHolder) view.getTag();
		}

		viewHolder.populateViews(getItem(position));

		return view;
	}

}
