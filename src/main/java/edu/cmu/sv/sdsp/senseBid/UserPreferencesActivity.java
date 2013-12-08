package main.java.edu.cmu.sv.sdsp.senseBid;

import edu.cmu.sv.sensebid.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPreferencesActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
		//addPreferencesFromResource(R.xml.userwins);
		
	}
}
