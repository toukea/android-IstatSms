package istat.android.telephony.sms.tools;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public final class Util {
    public static String convertTolocalPhoneNumber(String phone) {
        return phone.replaceFirst("^(00\\d{3})", "").replaceFirst(
                "^(\\+\\d{3})", "");
    }

    public static String convertToInternationalPhoneNumber(String phone,
                                                           String prefix) {
        return prefix + phone;
    }

    public static List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<String>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    public static int optSmsBodyPartNumber(String body) {
        if (body.length() <= 160) {
            return 1;
        } else {
            SmsManager sms = SmsManager.getDefault();
            return sms.divideMessage(body).size();
        }
    }

    public static int sendSMS(String address, String body,
                              PendingIntent sendPIntent, PendingIntent receiveIntent) {
        SmsManager sms = SmsManager.getDefault();
        if (body.length() <= 160) {
            sms.sendTextMessage(address, null, body, sendPIntent, receiveIntent);
            return 1;
        } else {
            ArrayList<String> parts = sms.divideMessage(body);
            ArrayList<PendingIntent> sendIntents = new ArrayList<PendingIntent>();
            ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
            for (int i = 0; i <= parts.size(); i++) {
                sendIntents.add(sendPIntent);
                deliveryIntents.add(receiveIntent);
            }
            if (sendPIntent == null)
                sendIntents = null;
            if (receiveIntent == null)
                deliveryIntents = null;
            sms.sendMultipartTextMessage(address, null, parts, sendIntents,
                    deliveryIntents);
            return parts.size();
        }
    }

    public static int sendSMS(String address, String body, Intent sendIntent,
                              Intent receiveIntent, Context context) {
        SmsManager sms = SmsManager.getDefault();
        if (body.length() <= 160) {
            sms.sendTextMessage(address, null, body,
                    PendingIntent.getBroadcast(context, 0, sendIntent, 0),
                    PendingIntent.getBroadcast(context, 0, receiveIntent, 0));
            return 1;
        } else {
            ArrayList<String> parts = sms.divideMessage(body);
            ArrayList<PendingIntent> sendIntents = new ArrayList<PendingIntent>();
            ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
            if (sendIntent == null && receiveIntent == null)
                for (int i = 0; i <= parts.size(); i++) {
                    sendIntents.add(PendingIntent.getBroadcast(context, 0,
                            sendIntent, 0));
                    deliveryIntents.add(PendingIntent.getBroadcast(context, 0,
                            receiveIntent, 0));
                }
            if (sendIntent == null)
                sendIntents = null;
            if (receiveIntent == null)
                deliveryIntents = null;
            sms.sendMultipartTextMessage(address, null, parts, sendIntents,
                    deliveryIntents);
            return parts.size();
        }
    }
}
