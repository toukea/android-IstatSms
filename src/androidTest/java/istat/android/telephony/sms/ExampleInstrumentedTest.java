package istat.android.telephony.sms;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import istat.android.telephony.sms.provider.operation.SmsSelection;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void nestedQuery() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        SmsQL sql = new SmsQL(appContext);
        SmsSelection selection1 = sql.selectSms().whereAddressEqual("40101383");
        SmsSelection selection2 = sql.selectSms().whereBodyLike("hello");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("world");
        int count = sql.updateSms().setBody("Hello World").WHERE(selection1).AND(selection2).OR(selection3).execute();

    }
}
