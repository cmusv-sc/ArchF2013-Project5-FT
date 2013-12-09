/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley.
All rights reserved.

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Please contact http://www.cmu.edu/silicon-valley/ if you have any
questions.
*/
package main.java.edu.cmu.sv.sdsp.senseAdapters;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.edu.cmu.sv.sdsp.senseBid.Reservation;
import android.content.Context;
import android.widget.ArrayAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class CalArrayAdapter.
 */
public class CalArrayAdapter extends ArrayAdapter<Reservation>{

	/** The m id map. */
	HashMap<Reservation, Integer> mIdMap = new HashMap<Reservation, Integer>();

	/**
	 * Instantiates a new cal array adapter.
	 *
	 * @param context the context
	 * @param textViewResourceId the text view resource id
	 * @param objects the objects
	 */
	public CalArrayAdapter(Context context, int textViewResourceId,
			ArrayList<Reservation> objects) {
		super(context, textViewResourceId, objects);

		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
			

		}
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		Reservation item = getItem(position);

		return mIdMap.get(item);
	}

/*	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Log.d("test_2", parent.toString());
		return parent;
		
		
	}*/
	/* (non-Javadoc)
 * @see android.widget.BaseAdapter#hasStableIds()
 */
@Override
	public boolean hasStableIds() {
		return true;
	}

}
