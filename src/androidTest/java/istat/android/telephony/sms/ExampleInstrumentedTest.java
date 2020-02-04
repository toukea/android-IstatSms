package istat.android.telephony.sms;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import istat.android.telephony.sms.operations.SmsSelection;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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

    @Test
    public void simpleSelect() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        SmsQL sql = new SmsQL(appContext);
        List<Sms> sms = sql.selectSms().execute();
        assertTrue(!sms.isEmpty());
    }

    @Test
    public void smsContentStream() throws Exception {
        // Context of the app under test.
        String uri = "content://sms";
        InputStream stream = new URL(uri).openStream();
        assertNotNull(stream);
    }
}
