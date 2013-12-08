package main.java.edu.cmu.sv.sdsp.senseAdapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CalUserAdapter extends ArrayAdapter<String>{

	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	public CalUserAdapter(Context context, int textViewResourceId,
			ArrayList<String> objects) {
		super(context, textViewResourceId, objects);

		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
			

		}
	}

	@Override
	public long getItemId(int position) {
		String item = getItem(position);

		return mIdMap.get(item);
	}

/*	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Log.d("test_2", parent.toString());
		return parent;
		
		
	}*/
	@Override
	public boolean hasStableIds() {
		return true;
	}

}
