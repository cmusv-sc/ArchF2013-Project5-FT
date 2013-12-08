import static org.junit.Assert.assertNotNull;
import main.java.edu.cmu.sv.sdsp.senseBid.Login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class LoginTest {
	
	private Login loginActivity;
	
	@Before
	  public void setup()  {
		loginActivity = Robolectric.buildActivity(Login.class)
	        .create().get();
	  }
	
	 @Test
	  public void checkActivityNotNull() throws Exception {
	    assertNotNull(loginActivity);
	  }

}
