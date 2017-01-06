package istat.android.telephony.sms.provider.operations;


import istat.android.telephony.sms.Sms;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
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
final class SmsQuery {

    static String[] projection = new String[]{"_id",
            "thread_id",
            "address",
            "person",
            "date",
            "protocol",
            "read",
            "status",
            "type",
            "reply_path_present",
            "subject",
            "body",
            "service_center",
            "locked"};
    static Uri uriSms = Uri.parse("content://sms");

    public SmsQuery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            uriSms = Telephony.Sms.Sent.CONTENT_URI;
        }
    }

    public static int insert(Context context, Sms sms) {
        if (sms == null) return 0;
        int out = 0;
        ContentValues values = new ContentValues();
        if (sms.getThread_id() != null)
            values.put("thread_id", sms.getThread_id());
        if (sms.getAddress() != null)
            values.put("address", sms.getAddress());

        if (sms.getPerson() != null)
            values.put("person", sms.getPerson());

        if (sms.getDate() > 0)
            values.put("date", sms.getDate());

        if (sms.getProtocol() != null)
            values.put("protocol", sms.getProtocol());

        if (sms.getRead() != null)
            values.put("read", sms.getRead());

        if (sms.getStatus() != null)
            values.put("status", sms.getStatus());

        if (sms.getType() != null)
            values.put("type", sms.getType());

        if (sms.getReply_path_present() != null)
            values.put("reply_path_present", sms.getReply_path_present());

        if (sms.getSubject() != null)
            values.put("subject", sms.getSubject());

        if (sms.getBody() != null)
            values.put("body", sms.getBody());

        if (sms.getService_center() != null)
            values.put("service_center", sms.getService_center());

        if (sms.getLocked() != null)
            values.put("locked", sms.getLocked());
        context.getContentResolver().insert(uriSms, values);
        return out;
    }

    /**
     * call this function in order to update a spacific smsSelection. all non definit property would'nt be updated.
     *
     * @param context
     * @param sms
     * @param query
     * @return a interger that represent Lines updated.
     */
    public static int update(Context context, Sms sms, SmsClause<?> query) {
        if (sms == null) return 0;// delete(context, query);
        int out = 0;
        ContentValues values = new ContentValues();
        if (sms.getThread_id() != null)
            values.put("thread_id", sms.getThread_id());
        if (sms.getAddress() != null)
            values.put("address", sms.getAddress());

        if (sms.getPerson() != null)
            values.put("person", sms.getPerson());

        if (sms.getDate() > 0)
            values.put("date", sms.getDate());

        if (sms.getProtocol() != null)
            values.put("protocol", sms.getProtocol());

        if (sms.getRead() != null)
            values.put("read", sms.getRead());

        if (sms.getStatus() != null)
            values.put("status", sms.getStatus());

        if (sms.getType() != null)
            values.put("type", sms.getType());

        if (sms.getReply_path_present() != null)
            values.put("reply_path_present", sms.getReply_path_present());

        if (sms.getSubject() != null)
            values.put("subject", sms.getSubject());

        if (sms.getBody() != null)
            values.put("body", sms.getBody());

        if (sms.getService_center() != null)
            values.put("service_center", sms.getService_center());

        if (sms.getLocked() != null)
            values.put("locked", sms.getLocked());
        context.getContentResolver().update(uriSms, values,
                query.getWhereClose(),
                query.getWhereParams());
        return out;
    }

    public static int delete(Context context, SmsClause<?> query) {
        int out = context.getContentResolver().delete(uriSms, query.getWhereClose(), query.getWhereParams());
        return out;
    }

    public static int count(Context context, SmsClause<?> query) {
        Cursor c = context.getContentResolver().query(uriSms,
                new String[]{projection[0]},
                query.getWhereClose(),
                query.getWhereParams(),
                query.getOrderBy());
        int count = c.getCount();
        c.close();
        return count;
    }

    public static List<Sms> select(Context context, SmsClause<?> query) {
        List<Sms> list = new ArrayList<Sms>();
        Sms sms;
        Cursor c = context.getContentResolver().query(uriSms,
                projection,
                query.getWhereClose(),
                query.getWhereParams(),
                query.getOrderBy());
        while (c.moveToNext()) {
            sms = new Sms();
            sms.set_id(c.getString(0));
            sms.setThread_id(c.getString(1));
            sms.setAddress(c.getString(2));
            sms.setPerson(c.getString(3));
            sms.setDate(c.getString(4));

            sms.setProtocol(c.getString(5));
            sms.setRead(c.getString(6));
            sms.setStatus(c.getString(7));
            sms.setType(c.getString(8));
            sms.setReply_path_present(c.getString(9));
            sms.setSubject(c.getString(10));
            sms.setBody(c.getString(11));
            sms.setService_center(c.getString(12));
            sms.setLocked(c.getString(13));
            list.add(sms);
        }
        c.close();
        return list;
    }

    public static boolean isSmsIdExist(Context context, String id) {
        Cursor c = context.getContentResolver().query(uriSms,
                new String[]{"_id"},
                "_id=?",
                new String[]{id},
                null);
        if (c.getCount() > 0) return true;
        else return false;
    }
}
