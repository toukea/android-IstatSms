package istat.android.telephony.sms.provider.operations;

import istat.android.telephony.sms.Sms;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

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
abstract class SmsClause<T extends SmsClause<T>> {
    protected String whereClose = null;
    protected List<String> whereParams = new ArrayList<String>();
    protected String orderBy = null;
    protected String groupBy = null;
    protected String having = null;
    Context context;

    public SmsClause(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public T whereLockedEqual(String value) {
        if (whereClose == null)
            whereClose = "locked = ? ";
        else
            whereClose += " AND locked = ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereService_centerEqual(String value) {
        if (whereClose == null)
            whereClose = "service_center = ?";
        else
            whereClose += " service_center = ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T whereBodyLike(String value) {
        if (whereClose == null)
            whereClose = "body LIKE \"" + value + "\"";
        else
            whereClose += " AND body LIKE \"" + value + "\"";
        return (T) this;
    }

    public T whereAddressLike(String value) {
        if (whereClose == null)
            whereClose = "address LIKE \"" + value + "\"";
        else
            whereClose += " AND address LIKE \"" + value + "\"";
        return (T) this;
    }

    public T whereBodyEqual(String value) {
        if (whereClose == null)
            whereClose = "body  = ?";
        else
            whereClose += " AND body = ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andWhereBodyEqual(String value) {
        if (whereClose == null)
            whereClose = "body  = ?";
        else
            whereClose += " AND body = ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orWhereBodyEqual(String value) {
        if (whereClose == null)
            whereClose = "body  = ?";
        else
            whereClose += " OR body = ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T whereSubjectLike(String value) {
        if (whereClose == null)
            whereClose = "subject LIKE \"" + value + "\"";
        else
            whereClose += " AND subject LIKE \"" + value + "\"";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T wherereply_path_presentEqual(String value) {
        if (whereClose == null)
            whereClose = "reply_path_present= ?";
        else
            whereClose += " AND reply_path_present= ?" + whereParams.add(value);
        return (T) this;
    }

    public T whereTypeEqual(String value) {
        if (whereClose == null)
            whereClose = "type= ?";
        else
            whereClose += " AND type= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereStatusEqual(String value) {
        if (whereClose == null)
            whereClose = "status= ?";
        else
            whereClose += " AND status= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereReadEqual(String value) {
        if (whereClose == null)
            whereClose = "read= ?";
        else
            whereClose += " AND read= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereProtocolEqual(String value) {
        if (whereClose == null)
            whereClose = "protocol= ?";
        else
            whereClose += " AND protocol= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereDateLessThan(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date < ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereDateGreatOrEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date >= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereDateGreatThan(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date > ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereDateLessOrEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date <= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereDateEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T wherePersonEqual(String value) {
        if (whereClose == null)
            whereClose = "person= ?";
        else
            whereClose += " AND person= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereAddressEqual(String value) {
        if (whereClose == null)
            whereClose = "address= ?";
        else
            whereClose += " AND address= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereThreadIdEqual(String value) {
        if (whereClose == null)
            whereClose += "thread_id= ?";
        else
            whereClose += " AND thread_id= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T whereIdEqual(String value) {
        if (whereClose == null)
            whereClose += " _id= ?";
        else
            whereClose += " AND _id= ?";
        whereParams.add(value);
        return (T) this;
    }

    // ---------------------------------------------------
    public T andLockedEqual(String value) {
        if (whereClose == null)
            whereClose = "locked = ?";
        else
            whereClose += " AND locked = ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T andService_centerEqual(String value) {
        if (whereClose == null)
            whereClose = "service_center = ?";
        else
            whereClose += " service_center = ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T andBodyLike(String value) {
        if (whereClose == null)
            whereClose = "body LIKE \"" + value + "\"";
        else
            whereClose += " AND body LIKE \"" + value + "\"";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T andSubjectLike(String value) {
        if (whereClose == null)
            whereClose = "subject LIKE \"" + value + "\"";
        else
            whereClose += " AND subject LIKE \"" + value + "\"";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T andReply_path_presentEqual(String value) {
        if (whereClose == null)
            whereClose = "reply_path_present= ?";
        else
            whereClose += " AND reply_path_present= ?" + whereParams.add(value);
        return (T) this;
    }

    public T andTypeEqual(String value) {
        if (whereClose == null)
            whereClose = "type= ?";
        else
            whereClose += " AND type= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andStatusEqual(String value) {
        if (whereClose == null)
            whereClose = "status= ?";
        else
            whereClose += " AND status= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andReadEqual(String value) {
        if (whereClose == null)
            whereClose = "read= ?";
        else
            whereClose += " AND read= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andProtocolEqual(String value) {
        if (whereClose == null)
            whereClose = "protocol= ?";
        else
            whereClose += " AND protocol= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andDateLessThan(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date < ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andDateGreatOrEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date >= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andDateGreatThan(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date > ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andDateLessOrEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date <= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andDateEqual(String value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " AND date= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andPersonEqual(String value) {
        if (whereClose == null)
            whereClose = "person= ?";
        else
            whereClose += " AND person= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andAdressEqual(String value) {
        if (whereClose == null)
            whereClose = "address= ?";
        else
            whereClose += " AND address= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andThreadIdEqual(String value) {
        if (whereClose == null)
            whereClose += "thread_id= ?";
        else
            whereClose += " AND thread_id= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T andIdEqual(String value) {
        if (whereClose == null)
            whereClose += " _id= ?";
        else
            whereClose += " AND _id= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orService_centerEqual(String value) {
        if (whereClose == null)
            whereClose = "service_center = ?";
        else
            whereClose += " service_center = ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T orBodyLike(String value) {
        if (whereClose == null)
            whereClose = "body LIKE \"" + value + "\"";
        else
            whereClose += " OR body LIKE \"" + value + "\"";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T orSubjectLike(String value) {
        if (whereClose == null)
            whereClose = "subject LIKE \"" + value + "\"";
        else
            whereClose += " OR subject LIKE \"" + value + "\"";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T orReply_path_presentEqual(String value) {
        if (whereClose == null)
            whereClose = "reply_path_present= ?";
        else
            whereClose += " OR reply_path_present= ?" + whereParams.add(value);
        return (T) this;
    }

    public T orTypeEqual(String value) {
        if (whereClose == null)
            whereClose = "type= ?";
        else
            whereClose += " OR type= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orStatusEqual(String value) {
        if (whereClose == null)
            whereClose = "status= ?";
        else
            whereClose += " OR status= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orReadEqual(String value) {
        if (whereClose == null)
            whereClose = "read= ?";
        else
            whereClose += " OR read= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orProtocolEqual(String value) {
        if (whereClose == null)
            whereClose = "protocol= ?";
        else
            whereClose += " OR protocol= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orDateLessThan(long value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " OR date < ?";
        whereParams.add("" + value);
        return (T) this;
    }

    public T orDateGreatOrEqual(long value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " OR date >= ?";
        whereParams.add("" + value);
        return (T) this;
    }

    public T orDateGreatThan(long value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " OR date > ?";
        whereParams.add("" + value);
        return (T) this;
    }

    public T orDateLessOrEqual(long value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " OR date <= ?";
        whereParams.add("" + value);
        return (T) this;
    }

    public T orDateEqual(long value) {
        if (whereClose == null)
            whereClose = "date= ?";
        else
            whereClose += " OR date= ?";
        whereParams.add("" + value);
        return (T) this;
    }

    public T orPersonEqual(String value) {
        if (whereClose == null)
            whereClose = "person= ?";
        else
            whereClose += " OR person= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orAdressEqual(String value) {
        if (whereClose == null)
            whereClose = "address= ?";
        else
            whereClose += " OR address= ?";
        whereParams.add(value);
        return (T) this;
    }

    public T orThreadIdEqual(String value) {
        if (whereClose == null)
            whereClose += "thread_id= ?";
        else
            whereClose += " OR thread_id= ?";
        whereParams.add(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T orIdEqual(String value) {
        if (whereClose == null)
            whereClose += " _id= ?";
        else
            whereClose += " OR _id= ?";
        whereParams.add(value);
        return (T) this;
    }

    // ----------------------------------------------------
    public T WHERE(SmsSelection close) {
        this.whereClose = close.whereClose;
        this.whereParams = close.whereParams;
        return (T) this;
    }

    public T OR(SmsSelection close) {
        this.whereClose = "(" + this.whereClose + ") OR (" + close.whereClose
                + ")";
        this.whereParams.addAll(close.whereParams);

        return (T) this;
    }

    public T AND(SmsSelection close) {
        this.whereClose = "(" + this.whereClose + ") AND (" + close.whereClose
                + ")";
        this.whereParams.addAll(close.whereParams);
        return (T) this;
    }

    // ----------------------------------------------------
    public void orderByID(String value) {
        if (orderBy == null)
            orderBy = "_id " + value;
        else
            orderBy += ",_id " + value;

    }

    public T orderByThread_Id(String value) {
        if (orderBy == null)
            orderBy = "thread_id " + value;
        else
            orderBy += ",thread_id " + value;

        return (T) this;
    }

    public T orderByDate(String value) {
        if (orderBy == null)
            orderBy = "Date " + value;
        else
            orderBy += ",Date" + value;

        return (T) this;
    }

    // -----------------------------------------------------
    public T orderBy(String value) {
        orderBy = value;

        return (T) this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getWhereClose() {
        return whereClose;
    }

    public String[] getWhereParams() {
        if (whereParams.size() == 0)
            return null;
        String[] tmpS = new String[whereParams.size()];
        int i = 0;
        for (String tmp : whereParams) {
            tmpS[i] = tmp;
            i++;
        }
        return tmpS;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setWhereClose(String whereClose) {
        this.whereClose = whereClose;
    }

    public void setWhereParams(String[] whereParams) {
        this.whereParams = java.util.Arrays.asList(whereParams);
    }

    // ----------------------------------------------------
    List<Sms> executeQuery(Context context) {
        return SmsQuery.select(context, this);
    }

}
