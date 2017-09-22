package com.group.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class AncVisitTypesAdapter extends ArrayAdapter<String> {

	public AncVisitTypesAdapter(Context context, String[] data) {
		super(context, android.R.layout.simple_list_item_1, data);
	}

}
