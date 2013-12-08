import java.util.ArrayList;

import main.java.edu.cmu.sv.sdsp.senseBid.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.accounts.Account;
import android.accounts.AccountManager;

@RunWith(RobolectricTestRunner.class)
public class UserTest {
	private AccountManager mockAccountManager;
	
	private void setUp(){
		mockAccountManager = Mockito.mock(AccountManager.class);
		Account account = new Account("name", "type");
		Account[] accounts = new Account[1];
		accounts[0] = account;
		Mockito.when(mockAccountManager.getAccountsByType("com.google")).thenReturn(accounts);	
	}
	
	@Test
	public void testGetEmail(){
		setUp();
		User user = new User(null);
		user.setAccountManager(mockAccountManager);
		final ArrayList<String> gUsernameList = User.getEmail();
		assert gUsernameList.size() == 1;
	}

}
