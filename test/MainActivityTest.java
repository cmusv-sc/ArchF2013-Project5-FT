import static org.junit.Assert.assertNotNull;
import main.java.edu.cmu.sv.sdsp.senseBid.Login;
import main.java.edu.cmu.sv.sdsp.senseBid.SenseDroidMainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.junit.Assert.assertThat;

import android.content.Intent;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	private SenseDroidMainActivity mainActivity;
	
	@Before
	  public void setup()  {
		mainActivity = Robolectric.buildActivity(SenseDroidMainActivity.class)
	        .create().get();
	  }
	
	 @Test
	  public void checkActivityNotNull() throws Exception {
	    assertNotNull(mainActivity);
	    ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        String intentName =shadowIntent.getComponent().getClassName();
        String expectedIntentName = Login.class.getName(); 
        assert(intentName.equals(expectedIntentName));
        
	  }
	 
	
}
