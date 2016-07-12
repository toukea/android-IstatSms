package istat.android.telephony.sms.provider.operation;

import istat.android.telephony.sms.Sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
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
 * 
 * @author Toukea Tatsi (Istat)
 * 
 */
// public class Clause< Clause extends Clause<?> > SmsMethod
abstract class SmsClause<Clause extends SmsClause<?>> {
	protected String whereClose = null;
	protected List<String> whereParams = new ArrayList<String>();
	protected String orderBy = null;
	Context context;

	public SmsClause(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public Clause whereLockedEqual(String value) {
		if (whereClose == null)
			whereClose = "locked = ? ";
		else
			whereClose += " AND locked = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereService_centerEqual(String value) {
		if (whereClose == null)
			whereClose = "service_center = ?";
		else
			whereClose += " service_center = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause whereBodyLike(String value) {
		if (whereClose == null)
			whereClose = "body LIKE \"" + value + "\"";
		else
			whereClose += " AND body LIKE \"" + value + "\"";
		return (Clause) this;
	}

	public Clause whereBodyEqual(String value) {
		if (whereClose == null)
			whereClose = "body  = ?";
		else
			whereClose += " AND body = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andWhereBodyEqual(String value) {
		if (whereClose == null)
			whereClose = "body  = ?";
		else
			whereClose += " AND body = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orWhereBodyEqual(String value) {
		if (whereClose == null)
			whereClose = "body  = ?";
		else
			whereClose += " OR body = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause whereSubjectLike(String value) {
		if (whereClose == null)
			whereClose = "subject LIKE \"" + value + "\"";
		else
			whereClose += " AND subject LIKE \"" + value + "\"";
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause wherereply_path_presentEqual(String value) {
		if (whereClose == null)
			whereClose = "reply_path_present= ?";
		else
			whereClose += " AND reply_path_present= ?" + whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereTypeEqual(String value) {
		if (whereClose == null)
			whereClose = "type= ?";
		else
			whereClose += " AND type= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereStatusEqual(String value) {
		if (whereClose == null)
			whereClose = "status= ?";
		else
			whereClose += " AND status= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereReadEqual(String value) {
		if (whereClose == null)
			whereClose = "read= ?";
		else
			whereClose += " AND read= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereProtocolEqual(String value) {
		if (whereClose == null)
			whereClose = "protocol= ?";
		else
			whereClose += " AND protocol= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereDateLessThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date < ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereDateGreatOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date >= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereDateGreatThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date > ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereDateLessOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date <= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereDateEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause wherePersonEqual(String value) {
		if (whereClose == null)
			whereClose = "person= ?";
		else
			whereClose += " AND person= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereAdressEqual(String value) {
		if (whereClose == null)
			whereClose = "address= ?";
		else
			whereClose += " AND address= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereThreadIdEqual(String value) {
		if (whereClose == null)
			whereClose += "thread_id= ?";
		else
			whereClose += " AND thread_id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause whereIdEqual(String value) {
		if (whereClose == null)
			whereClose += " _id= ?";
		else
			whereClose += " AND _id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	// ---------------------------------------------------
	public Clause andLockedEqual(String value) {
		if (whereClose == null)
			whereClose = "locked = ?";
		else
			whereClose += " AND locked = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause andService_centerEqual(String value) {
		if (whereClose == null)
			whereClose = "service_center = ?";
		else
			whereClose += " service_center = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause andBodyLike(String value) {
		if (whereClose == null)
			whereClose = "body LIKE \"" + value + "\"";
		else
			whereClose += " AND body LIKE \"" + value + "\"";
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause andSubjectLike(String value) {
		if (whereClose == null)
			whereClose = "subject LIKE \"" + value + "\"";
		else
			whereClose += " AND subject LIKE \"" + value + "\"";
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause andReply_path_presentEqual(String value) {
		if (whereClose == null)
			whereClose = "reply_path_present= ?";
		else
			whereClose += " AND reply_path_present= ?" + whereParams.add(value);
		return (Clause) this;
	}

	public Clause andTypeEqual(String value) {
		if (whereClose == null)
			whereClose = "type= ?";
		else
			whereClose += " AND type= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andStatusEqual(String value) {
		if (whereClose == null)
			whereClose = "status= ?";
		else
			whereClose += " AND status= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andReadEqual(String value) {
		if (whereClose == null)
			whereClose = "read= ?";
		else
			whereClose += " AND read= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andProtocolEqual(String value) {
		if (whereClose == null)
			whereClose = "protocol= ?";
		else
			whereClose += " AND protocol= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andDateLessThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date < ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andDateGreatOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date >= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andDateGreatThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date > ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andDateLessOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date <= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andDateEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " AND date= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andPersonEqual(String value) {
		if (whereClose == null)
			whereClose = "person= ?";
		else
			whereClose += " AND person= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andAdressEqual(String value) {
		if (whereClose == null)
			whereClose = "address= ?";
		else
			whereClose += " AND address= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andThreadIdEqual(String value) {
		if (whereClose == null)
			whereClose += "thread_id= ?";
		else
			whereClose += " AND thread_id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause andIdEqual(String value) {
		if (whereClose == null)
			whereClose += " _id= ?";
		else
			whereClose += " AND _id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orService_centerEqual(String value) {
		if (whereClose == null)
			whereClose = "service_center = ?";
		else
			whereClose += " service_center = ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause orBodyLike(String value) {
		if (whereClose == null)
			whereClose = "body LIKE \"" + value + "\"";
		else
			whereClose += " OR body LIKE \"" + value + "\"";
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause orSubjectLike(String value) {
		if (whereClose == null)
			whereClose = "subject LIKE \"" + value + "\"";
		else
			whereClose += " OR subject LIKE \"" + value + "\"";
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause orReply_path_presentEqual(String value) {
		if (whereClose == null)
			whereClose = "reply_path_present= ?";
		else
			whereClose += " OR reply_path_present= ?" + whereParams.add(value);
		return (Clause) this;
	}

	public Clause orTypeEqual(String value) {
		if (whereClose == null)
			whereClose = "type= ?";
		else
			whereClose += " OR type= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orStatusEqual(String value) {
		if (whereClose == null)
			whereClose = "status= ?";
		else
			whereClose += " OR status= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orReadEqual(String value) {
		if (whereClose == null)
			whereClose = "read= ?";
		else
			whereClose += " OR read= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orProtocolEqual(String value) {
		if (whereClose == null)
			whereClose = "protocol= ?";
		else
			whereClose += " OR protocol= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orDateLessThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " OR date < ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orDateGreatOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " OR date >= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orDateGreatThan(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " OR date > ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orDateLessOrEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " OR date <= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orDateEqual(String value) {
		if (whereClose == null)
			whereClose = "date= ?";
		else
			whereClose += " OR date= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orPersonEqual(String value) {
		if (whereClose == null)
			whereClose = "person= ?";
		else
			whereClose += " OR person= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orAdressEqual(String value) {
		if (whereClose == null)
			whereClose = "address= ?";
		else
			whereClose += " OR address= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	public Clause orThreadIdEqual(String value) {
		if (whereClose == null)
			whereClose += "thread_id= ?";
		else
			whereClose += " OR thread_id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	@SuppressWarnings("unchecked")
	public Clause orIdEqual(String value) {
		if (whereClose == null)
			whereClose += " _id= ?";
		else
			whereClose += " OR _id= ?";
		whereParams.add(value);
		return (Clause) this;
	}

	// ----------------------------------------------------
	public Clause WHERE(Clause close) {
		this.whereClose = close.whereClose;
		this.whereParams = close.whereParams;
		return (Clause) this;
	}

	public Clause OR(Clause close) {
		this.whereClose = "(" + this.whereClose + ") OR (" + close.whereClose
				+ ")";
		this.whereParams.addAll(close.whereParams);

		return (Clause) this;
	}

	public Clause AND(Clause close) {
		this.whereClose = "(" + this.whereClose + ") AND (" + close.whereClose
				+ ")";
		this.whereParams.addAll(close.whereParams);
		return (Clause) this;
	}

	// ----------------------------------------------------
	public void orderByID(String value) {
		if (orderBy == null)
			orderBy = "_id " + value;
		else
			orderBy += ",_id " + value;

	}

	public Clause orderByThread_Id(String value) {
		if (orderBy == null)
			orderBy = "thread_id " + value;
		else
			orderBy += ",thread_id " + value;

		return (Clause) this;
	}

	public Clause orderByDate(String value) {
		if (orderBy == null)
			orderBy = "Date " + value;
		else
			orderBy += ",Date" + value;

		return (Clause) this;
	}

	// -----------------------------------------------------
	public Clause orderBy(String value) {
		orderBy = value;

		return (Clause) this;
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
		String[] tmp = new String[whereParams.size()];
		int i = 0;
		for (String tmps : whereParams) {
			tmp[i] = tmps;
			i++;
		}
		return tmp;
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
	public List<Sms> execute(Context context) {
		return SmsQuery.execute(context, this);
	}

}
