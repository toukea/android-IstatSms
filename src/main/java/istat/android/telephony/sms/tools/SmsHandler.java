package istat.android.telephony.sms.tools;

import istat.android.telephony.sms.Sms;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

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
 * deprecated use {@link SmsSender} or {@link SmsWatcher} instead.
 *
 * @author Toukea Tatsi (Istat)
 */
@SuppressWarnings("WrongConstant")
@Deprecated
public final class SmsHandler {
    public static String INTENT_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static String INTENT_SMS_SEND = "istat.android.telephony.SMS_SEND";
    public static String INTENT_SMS_DELIVERY = "istat.android.telephony.SMS_DELIVERY";
    private Context context;
    private Sms tmpSMS;
    private HandlerConfig sendConfig/*=new HandlerConfig(new Intent(""), 0, 0)*/, deliveryConfig;

    private DeliveryCallBack mDeliveryCallBack;

    private SendCallBack mSendCallBack;
    private SmsWatcher mWatcher;

    public SmsHandler(Context context) {
        this.context = context;
    }

    public int sendSms(Sms sms) {
        this.tmpSMS = sms;
        return sendSms(sms.address, sms.body);
    }

    public int sendSms(String address, String body) {
        this.tmpSMS = new Sms(address, body);
        int out = Util.sendSMS(address, body, getSendIntent() == null ? null : PendingIntent.getBroadcast(context,
                getSendIntentRequestCode(), getSendIntent(),
                getSendIntentFlag()), getDeliveryIntent() == null ? null : PendingIntent.getBroadcast(context,
                getDeliveryIntentRequestCode(), getDeliveryIntent(),
                getDeliveryIntentFlag()));
        if (getSendIntent() != null) {
            IntentFilter flt = new IntentFilter(getSendIntent().getAction());
            context.registerReceiver(mSendReceiver, flt);
        }
        if (getDeliveryIntent() != null) {
            IntentFilter flt = new IntentFilter(getDeliveryIntent().getAction());
            context.registerReceiver(mDeliveryReceiver, flt);
        }
        return out;
    }

    public int[] sendSms(List<String> phoneNumbers, String body) {
        int[] out = new int[phoneNumbers.size()];
        int i = 0;
        for (String phoneNumber : phoneNumbers) {
            out[i] = sendSms(phoneNumber, body);
            i++;
        }
        return out;
    }

    public SmsHandler setDeliveryConfig(HandlerConfig deliveryConfig) {
        this.deliveryConfig = deliveryConfig;
        return this;
    }

    public SmsHandler setDeliveryConfig(Intent intent, int requestCode, int flag) {
        this.deliveryConfig = new HandlerConfig(intent, requestCode, flag);
        return this;
    }

    public SmsHandler setSendConfig(HandlerConfig sendConfig) {
        this.sendConfig = sendConfig;
        return this;
    }

    public SmsHandler setSendConfig(Intent intent, int requestCode, int flag) {
        this.sendConfig = new HandlerConfig(intent, requestCode, flag);
        return this;
    }

    public SmsHandler setSendCallBack(SendCallBack callBack) {
        this.mSendCallBack = callBack;
        return this;
    }

    public SmsHandler setDeliveryCallBack(DeliveryCallBack callBack) {
        this.mDeliveryCallBack = callBack;
        return this;
    }

    // -----------------------------------------------------------
    public Context getContext() {
        return context;
    }

    private BroadcastReceiver mSendReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    // [� send success actions � ];
                    if (mSendCallBack != null)
                        mSendCallBack.onSuccessSending(tmpSMS);
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    if (mSendCallBack != null)
                        mSendCallBack.onSuccessSending(tmpSMS);
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    // [� Radio off failure actions �];
                    if (mSendCallBack != null)
                        mSendCallBack.onSuccessSending(tmpSMS);
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    // [� null PDU failure actions � ];
                    if (mSendCallBack != null)
                        mSendCallBack.onSuccessSending(tmpSMS);
                    break;
            }

        }
    };
    private BroadcastReceiver mDeliveryReceiver = new BroadcastReceiver() {

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

        }
    };
    private BroadcastReceiver mIncomeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mWatcher.onReceiveSms(decode(context, intent), this);
        }
    };

    public void registerSmsWatcher(SmsWatcher watcher, int priority) {
        this.mWatcher = watcher;
        IntentFilter filter = new IntentFilter(INTENT_SMS_RECEIVED);
        filter.setPriority(priority);
        context.registerReceiver(mIncomeReceiver, filter);
    }

    public void registerSmsWatcher(SmsWatcher watcher) {
        this.mWatcher = watcher;
        IntentFilter filter = new IntentFilter(INTENT_SMS_RECEIVED);
        context.registerReceiver(mIncomeReceiver, filter);
    }

    public void unregisterSmsWatcher() {
        context.unregisterReceiver(mIncomeReceiver);
    }

    public void unregisterSendWatcher() {
        context.unregisterReceiver(mSendReceiver);
    }

    public void unregisterdeliveryWatcher() {
        context.unregisterReceiver(mDeliveryReceiver);
    }

    public static Sms decode(Context context, Intent intent) {

        if (intent.getAction()
                .equals(INTENT_SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");

                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    String msg = "";
                    for (int i = 0; i < messages.length; i++) {
                        msg = msg + messages[i].getMessageBody();
                    }
                    return new Sms(messages[0].getDisplayOriginatingAddress(),
                            msg);

                }
            }
        }
        return new Sms(null, null);
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

    //	public BroadcastReceiver getSendReceiver() {
//		return mSendReceiver;
//	}
//	public BroadcastReceiver getDeliveryReceiver() {
//		return mDeliveryReceiver;
//	}
//	public BroadcastReceiver getIncomeReceiver() {
//		return mIncomeReceiver;
//	}
    @Deprecated
    public interface SmsWatcher {
        public void onReceiveSms(Sms sms, BroadcastReceiver receiver);
    }

    @Deprecated
    public interface SendCallBack {
        public void onSuccessSending(Sms sms);

        public void onGenericFail(Sms sms);

        public void onRadioOffFail(Sms sms);

        public void onBadformedSmsFail(Sms sms);
    }

    @Deprecated
    public interface DeliveryCallBack {
        public void onSuccessDelivery(Sms sms);

        public void onFailDelivery(Sms sms);
    }

    @Deprecated
    public static class HandlerConfig {
        int flag = 0, requestCode = 0;
        Intent intent;

        public HandlerConfig(Intent intent, int requestCode, int flag) {
            this.flag = flag;
            this.requestCode = requestCode;
            this.intent = intent;
        }
    }

}
