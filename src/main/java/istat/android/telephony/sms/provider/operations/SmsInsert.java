package istat.android.telephony.sms.provider.operations;

import istat.android.telephony.sms.Sms;

import android.content.Context;

import java.util.Date;
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
//@Deprecated
public final class SmsInsert {
    private Sms sms = new Sms();
    Context context;

    SmsInsert(Context context) {
        this.context = context;
    }

    public SmsInsert setAs(Sms smsLike) {
        sms = smsLike;
        return this;
    }

    /**
     * please, use {@link #setAs(Sms) instead.}
     *
     * @param smsLike
     * @return
     */
    @Deprecated
    public SmsInsert setSms(Sms smsLike) {
        return setAs(smsLike);
    }

    public int execute() {
        return SmsQuery.insert(context, sms);
    }

    public SmsInsert set_id(String _id) {
        sms._id = _id;
        return this;
    }

    public SmsInsert setAddress(String address) {
        sms.address = address;
        return this;
    }

    public SmsInsert setBody(String body) {
        sms.body = body;
        return this;
    }

    /**
     * use {@link #setDate(long)} or {@link #setDate(Date)}  instead.
     *
     * @param date
     * @return
     */
    @Deprecated
    public SmsInsert setDate(String date) {
        sms.date = Long.valueOf(date);
        return this;
    }

    public SmsInsert setDate(Date date) {
        sms.date = date.getTime();
        return this;
    }

    public SmsInsert setDate(long date) {
        sms.date = date;
        return this;
    }

    public SmsInsert setLocked(String locked) {
        sms.locked = locked;
        return this;
    }

    public SmsInsert setPerson(String person) {
        sms.person = person;
        return this;
    }

    public SmsInsert setProtocol(String protocol) {
        sms.protocol = protocol;
        return this;
    }

    public SmsInsert setRead(String read) {
        sms.read = read;
        return this;
    }

    public SmsInsert setReply_path_present(String reply_path_present) {
        sms.reply_path_present = reply_path_present;
        return this;
    }

    public SmsInsert setService_center(String service_center) {
        sms.service_center = service_center;
        return this;
    }

    public SmsInsert setStatus(String status) {
        sms.status = status;
        return this;
    }

    public SmsInsert setSubject(String subject) {
        sms.subject = subject;
        return this;
    }

    public SmsInsert setThread_id(String thread_id) {
        sms.thread_id = thread_id;
        return this;
    }

    public SmsInsert setType(String type) {
        sms.type = type;
        return this;
    }

}
