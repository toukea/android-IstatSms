package istat.android.telephony.sms.tools;

import istat.android.telephony.sms.Sms;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;

/*
 * Copyright (C) 2014 Istat Dev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Toukea Tatsi (Istat)
 */
public final class SmsSender {
    public static final String INTENT_SMS_SEND = "istat.android.telephony.SMS_SEND";
    public static final String INTENT_SMS_DELIVERY = "istat.android.telephony.SMS_DELIVERY";
    public static int REQUEST_CODE_SEND = (int) (Math.random() * 10);
    public static int REQUEST_CODE_DELIVERY = (int) (Math.random() * 10);
    private Context context;
    private Sms tmpSMS;
    private HandlerConfig sendConfig, deliveryConfig;
    private DeliveryCallBack mDeliveryCallBack;
    private SendCallBack mSendCallBack;
    private BroadcastReceiver mSendReceiver, mDeliveryReceiver;

    public SmsSender(Context context) {
        this.context = context;
    }

    public int sendSms(Sms sms, SendCallBack sendCallBack,
                       DeliveryCallBack deliveryCallBack) {
        this.tmpSMS = sms;
        setDeliveryCallBack(deliveryCallBack);
        setSendCallBack(sendCallBack);
        return sendSms(sms.address, sms.body, sendCallBack, deliveryCallBack);
    }

    public int sendSms(Sms sms, SendCallBack sendCallBack) {
        return sendSms(sms, sendCallBack, null);
    }

    public int sendSms(Sms sms) {
        return sendSms(sms, null, null);
    }

    public int sendSms(String address, String body, SendCallBack sendCallBack,
                       DeliveryCallBack deliveryCallBack) {
        setSendCallBack(sendCallBack);
        setDeliveryCallBack(deliveryCallBack);
        return sendSms(address, body);
    }

    public int sendSms(String address, String body, SendCallBack sendCallBack) {
        return sendSms(address, body, sendCallBack, null);
    }

    @SuppressWarnings("WrongConstant")
    public int sendSms(String address, String body) {
        this.tmpSMS = new Sms(address, body);
        int out = Util.optSmsBodyPartNumber(body);
        if (getSendIntent() != null) {
            IntentFilter flt = new IntentFilter(getSendIntent().getAction());
            context.registerReceiver(createSendReceiver(this.tmpSMS, out), flt);
        }

        if (getDeliveryIntent() != null) {
            IntentFilter flt = new IntentFilter(getDeliveryIntent().getAction());
            context.registerReceiver(createDeliveryReceiver(this.tmpSMS, out),
                    flt);
        }
        Util.sendSMS(
                address,
                body,
                getSendIntent() == null ? null : PendingIntent.getBroadcast(
                        context, getSendIntentRequestCode(), getSendIntent(),
                        getSendIntentFlag()),
                getDeliveryIntent() == null ? null : PendingIntent
                        .getBroadcast(context, getDeliveryIntentRequestCode(),
                                getDeliveryIntent(), getDeliveryIntentFlag()));
        return out;
    }

    public int[] sendSms(List<String> phoneNumbers, String body,
                         SendCallBack sendCallBack, DeliveryCallBack deliveryCallBack) {
        setDeliveryCallBack(deliveryCallBack);
        setSendCallBack(sendCallBack);
        int[] out = new int[phoneNumbers.size()];
        int i = 0;
        for (String phoneNumber : phoneNumbers) {
            out[i] = sendSms(phoneNumber, body);
            i++;
        }
        return out;
    }

    public int[] sendSms(List<String> phoneNumbers, String body,
                         SendCallBack sendCallBack) {
        return sendSms(phoneNumbers, body, sendCallBack, null);
    }

    public int[] sendSms(List<String> phoneNumbers, String body) {
        return sendSms(phoneNumbers, body, null, null);
    }

    SmsSender setDeliveryConfig(HandlerConfig deliveryConfig) {
        this.deliveryConfig = deliveryConfig;
        return this;
    }

    SmsSender setDeliveryConfig(Intent intent, int requestCode, int flag) {
        this.deliveryConfig = new HandlerConfig(intent, requestCode, flag);
        return this;
    }

    SmsSender setSendConfig(HandlerConfig sendConfig) {
        this.sendConfig = sendConfig;
        return this;
    }

    SmsSender setSendConfig(Intent intent, int requestCode, int flag) {
        this.sendConfig = new HandlerConfig(intent, requestCode, flag);
        return this;
    }

    // -----------------------------------------------------------
    public Context getContext() {
        return context;
    }

    public void cancel() {
        unregisterDeliveryWatcher();
        unregisterSendWatcher();
    }

    private void unregisterSendWatcher() {
        try {
            if (mSendCallBack != null)
                context.unregisterReceiver(mSendReceiver);
            mSendCallBack = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unregisterDeliveryWatcher() {
        try {
            if (mDeliveryCallBack != null)
                context.unregisterReceiver(mDeliveryReceiver);
            mDeliveryCallBack = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver createSendReceiver(final Sms sms,
                                                 final int smsCount) {
        return mSendReceiver = new BroadcastReceiver() {
            int successCount = 0;

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // [� send success actions � ];
                        if (mSendCallBack != null) {
                            successCount++;
                            if (successCount >= smsCount) {
                                mSendCallBack.onSuccessSending(tmpSMS);
                                unregisterSendWatcher();
                            }
                        }
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        if (mSendCallBack != null) {
                            mSendCallBack.onGenericFail(tmpSMS);
                            unregisterSendWatcher();
                        }
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // [� Radio off failure actions �];
                        if (mSendCallBack != null) {
                            mSendCallBack.onRadioOffFail(tmpSMS);
                            unregisterSendWatcher();
                        }
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // [� null PDU failure actions � ];
                        if (mSendCallBack != null) {
                            mSendCallBack.onBadFormedSmsFail(tmpSMS);
                            unregisterSendWatcher();
                        }
                        break;
                }

            }
        };
    }

    private BroadcastReceiver createDeliveryReceiver(final Sms sms,
                                                     final int smsCount) {
        return mDeliveryReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // [� send success actions � ];
                        if (mDeliveryCallBack != null)
                            mDeliveryCallBack.onSuccessDelivery(tmpSMS);
                        break;
                    default:
                        if (mDeliveryCallBack != null)
                            mDeliveryCallBack.onFailDelivery(tmpSMS);
                        break;

                }
                if (mDeliveryCallBack != null) {
                    unregisterDeliveryWatcher();
                }

            }
        };

    }

    private SmsSender setSendCallBack(SendCallBack callBack) {
        this.mSendCallBack = callBack;
        if (sendConfig == null && mSendCallBack != null) {
            sendConfig = new HandlerConfig(new Intent(INTENT_SMS_SEND),
                    REQUEST_CODE_SEND, 0);
        }
        return this;
    }

    private SmsSender setDeliveryCallBack(DeliveryCallBack callBack) {
        this.mDeliveryCallBack = callBack;
        if (deliveryConfig == null && mDeliveryCallBack != null) {
            deliveryConfig = new HandlerConfig(new Intent(INTENT_SMS_DELIVERY),
                    REQUEST_CODE_DELIVERY, 0);
        }
        return this;
    }

    private Intent getSendIntent() {
        return sendConfig != null && sendConfig.intent != null ? sendConfig.intent
                : null;
    }

    private Intent getDeliveryIntent() {
        return deliveryConfig != null && deliveryConfig.intent != null ? deliveryConfig.intent
                : null;
    }

    private int getSendIntentFlag() {
        return sendConfig != null ? sendConfig.flag : 0;
    }

    private int getDeliveryIntentFlag() {
        return deliveryConfig != null ? deliveryConfig.flag : 0;
    }

    private int getSendIntentRequestCode() {
        return sendConfig != null ? sendConfig.requestCode : 0;
    }

    private int getDeliveryIntentRequestCode() {
        return deliveryConfig != null ? deliveryConfig.requestCode : 0;
    }

    // public BroadcastReceiver getSendReceiver() {
    // return mSendReceiver;
    // }
    // public BroadcastReceiver getDeliveryReceiver() {
    // return mDeliveryReceiver;
    // }
    // public BroadcastReceiver getIncomeReceiver() {
    // return mIncomeReceiver;
    // }
    public interface SendCallBack {
        public void onSuccessSending(Sms sms);

        public void onGenericFail(Sms sms);

        public void onRadioOffFail(Sms sms);

        public void onBadFormedSmsFail(Sms sms);
    }

    public interface DeliveryCallBack {
        public void onSuccessDelivery(Sms sms);

        public void onFailDelivery(Sms sms);
    }

    public static class HandlerConfig {
        int flag = 0, requestCode = 0;
        Intent intent;

        public HandlerConfig(Intent intent, int requestCode, int flag) {
            this.flag = flag;
            this.requestCode = requestCode;
            this.intent = intent;
        }
    }

    public static void startSmsDefaultApplication(Context context, Sms sms) {
        startSmsDefaultApplication(context, sms.address, sms.body);
    }

    public static void startSmsDefaultApplication(Context context,
                                                  String address, String body) {
        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", address);
        smsIntent.putExtra("sms_body", body);
        context.startActivity(smsIntent);

    }

}
