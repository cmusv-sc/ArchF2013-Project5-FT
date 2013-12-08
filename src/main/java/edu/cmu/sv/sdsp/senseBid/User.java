package main.java.edu.cmu.sv.sdsp.senseBid;

import java.util.ArrayList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

public class User {
	
	private static AccountManager accountManager;
	
	public User(Context context){
		User.accountManager = AccountManager.get(context);
	}
	
	public void setAccountManager(AccountManager accountManager){
		User.accountManager = accountManager;
	}

	// private String firstName;
	// private String lastName;
	// private String status;
	// private String emailId;

	// @Override
	// public final void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	//
	// Log.d("test_2", savedInstanceState.toString());
	//
	// }

	public static ArrayList<String> getEmail() {
		ArrayList<String> AccountList = new ArrayList<String>();
		String token = null;
		final ArrayList<String> gUsernameList = new ArrayList<String>(); 
		Account[] accounts = accountManager.getAccountsByType("com.google");

		gUsernameList.clear();
		// loop
		for (Account account : accounts) {
			gUsernameList.add(account.name);
			Log.d("test_2", gUsernameList.toString());
		}

		return gUsernameList;
	}

	
}
