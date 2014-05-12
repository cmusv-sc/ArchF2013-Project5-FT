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
package main.java.edu.cmu.sv.sdsp.senseBid;

import java.util.ArrayList;

import main.java.edu.cmu.sv.sdsp.senseAdapters.CalUserAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import edu.cmu.sv.sensebid.R;


// TODO: Auto-generated Javadoc
/**
 * The Class Login.
 * Gets a ArrayList from User.class with the accounts associated to the mobile device in use and display it for the user to chose.
 * 
 */
public class Login extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		User userProvider = new User(this);

		final ArrayList<String> accountList = User
				.getEmail(getBaseContext());
		
		
		final CalUserAdapter adapter = new CalUserAdapter(this,
				android.R.layout.simple_list_item_1, accountList);
		ListView listView = (ListView) findViewById(R.id.listAccounts);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Log.d("test_2", accountList.toString());


				Intent k = new Intent(getApplicationContext(),
						ShowCalendar.class);
				startActivity(k);

			}
		});

	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
//	@Override
//	protected void onStart(){
//		super.onStart();
//		Toast.makeText(this,  "onStart", Toast.LENGTH_SHORT).show();
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onResume()
//	 */
//	@Override
//	protected void onResume(){
//		super.onResume();
//		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onRestart()
//	 */
//	@Override
//	protected void onRestart(){
//		super.onRestart();
//		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onPause()
//	 */
//	@Override
//	protected void onPause(){
//		super.onPause();
//		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onStop()
//	 */
//	@Override
//	protected void onStop(){
//		super.onStop();
//		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onDestroy()
//	 */
//	@Override
//	protected void onDestroy(){
//		super.onDestroy();
//		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
//	}
}
