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
import android.widget.Toast;
import edu.cmu.sv.sensebid.R;

//public class Login {
//
//	private String uName;
//	private String uPass;
//
//	public Login(String userName, String userPassword) {
//		
//		
//
//	}

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		User userProvider = new User(this);

		final ArrayList<String> accountList = User
				.getEmail();
		
		
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
	
	@Override
	protected void onStart(){
		super.onStart();
		Toast.makeText(this,  "onStart", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}
}
