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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 * Provides with a list of users associated with the mobile phone
 */
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

<<<<<<< Updated upstream
	public static ArrayList<String> getEmail() {
=======
	/**
	 * Gets the email.
	 *
	 * @param context the context
	 * @return the email
	 */
	static ArrayList<String> getEmail(Context context) {
>>>>>>> Stashed changes
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
