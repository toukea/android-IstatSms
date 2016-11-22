package istat.android.telephony.sms.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import istat.android.telephony.sms.Sms;

public final class SmsWatcher {
    public final static int MAX_PRIORITY = 2147483647;
    public static String INTENT_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    Context context;

    static SmsWatcher instance;

    public static SmsWatcher getInstance(Context context) {
        if (instance != null)
            return instance;
        instance = new SmsWatcher(context);
        return instance;
    }

    public SmsWatcher(Context context) {
        this.context = context;
    }

    public static SmsPart decode(Context context, Intent intent) {

        if (intent.getAction().equals(INTENT_SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");

                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                }
                if (messages.length > -1) {
                    String msg = "";
                    for (int i = 0; i < messages.length; i++) {
                        msg = msg + messages[i].getMessageBody();
                    }
                    return new SmsPart(
                            messages[0].getDisplayOriginatingAddress(), msg);

                }
            }
        }
        return new SmsPart(null, null);
    }

    public void startWatching(SmsListener listener, int priority) {
        this.mListener = listener;
        IntentFilter filter = new IntentFilter(INTENT_SMS_RECEIVED);
        filter.setPriority(priority);
        context.registerReceiver(mIncomingReceiver, filter);
    }

    public void startWatching(SmsListener listener) {
        this.mListener = listener;
        IntentFilter filter = new IntentFilter(INTENT_SMS_RECEIVED);
        context.registerReceiver(mIncomingReceiver, filter);
    }

    public boolean stopWatching() {
        boolean out = mListener != null;
        try {
            if (out) {
                context.unregisterReceiver(mIncomingReceiver);
                mListener = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public interface SmsListener {
        public void onReceiveSms(SmsPart sms, BroadcastReceiver receiver);
    }

    private BroadcastReceiver mIncomingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mListener != null) {
                mListener.onReceiveSms(decode(context, intent), this);
            }
        }
    };
    private SmsListener mListener;

    public static class SmsPart {
        public String address = "", body = "";
        public long date = System.currentTimeMillis();

        public SmsPart(String address, String body) {
            this.address = address;
            this.body = body;
        }

        public Sms asSms() {
            Sms sms = new Sms(address, body);
            sms.date = date;
            return sms;
        }
    }
}
