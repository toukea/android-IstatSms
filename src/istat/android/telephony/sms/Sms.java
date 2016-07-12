package istat.android.telephony.sms;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.Context;
import android.telephony.SmsManager;
import istat.android.telephony.sms.tools.SmsHandler;
import istat.android.telephony.sms.tools.Util;

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
public final class Sms /*implements Parcelable*/{
	public static String MESSAGE_TYPE_ALL = "0", MESSAGE_TYPE_INBOX = "1",
			MESSAGE_TYPE_SENT = "2", MESSAGE_TYPE_DRAFT = "3",
			MESSAGE_TYPE_OUTBOX = "4", MESSAGE_TYPE_FAILED = "5",
			MESSAGE_TYPE_QUEUED = "6";
	public String _id, thread_id, address, person, date, protocol, read,
			status, type, reply_path_present, subject, body, service_center,
			locked;

	public Sms() {
	}

	public Sms(String id, String address, String body) {
		this._id = id;
		this.address = address;
		this.body = body;
	}

	public Sms(String address, String body) {
		this.address = address;
		this.body = body;
	}

	// ------------------------------------------------------------
	public void send() {
		Util.sendSMS(address, body, null, null);
	}

	public void send(PendingIntent sendPIntent, PendingIntent receivIntent) {
		Util.sendSMS(address, body, sendPIntent, receivIntent);
	}

	public void send(SmsHandler config) {
		config.sendSms(address, body);
	}

	// ------------------------------------------------------------

	// -------------------------------------------------------------
	public void set_id(String _id) {
		this._id = _id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public void setReply_path_present(String reply_path_present) {
		this.reply_path_present = reply_path_present;
	}

	public void setService_center(String service_center) {
		this.service_center = service_center;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setThread_id(String thread_id) {
		this.thread_id = thread_id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String get_id() {
		return _id;
	}

	public String getAddress() {
		return address;
	}

	public String getBody() {
		return body;
	}

	public String getDate() {
		return date;
	}

	public String getLocked() {
		return locked;
	}

	public String getPerson() {
		return person;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRead() {
		return read;
	}

	public String getReply_path_present() {
		return reply_path_present;
	}

	public String getService_center() {
		return service_center;
	}

	public String getStatus() {
		return status;
	}

	public String getSubject() {
		return subject;
	}

	public String getThread_id() {
		return thread_id;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		try {
			return toJSON().toString();
		} catch (Exception e) {

		}
		return "";
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.accumulate("_id", _id);
		json.accumulate("thread_id", thread_id);
		json.accumulate("address", address);
		json.accumulate("person", person);

		json.accumulate("date", date);
		json.accumulate("protocol", protocol);
		json.accumulate("read", read);
		json.accumulate("status", status);

		json.accumulate("type", type);
		json.accumulate("reply_path_present", reply_path_present);
		json.accumulate("subject", subject);
		json.accumulate("body", body);

		json.accumulate("service_center", service_center);
		json.accumulate("locked", locked);
		return json;
	}
	/*
	 * public static int insertSms(Context context,Sms sms){ return
	 * SmsQuery.insert(context,sms); } public static boolean
	 * smsExistWithId(Context context,String id){ return
	 * SmsQuery.smsIdExist(context, id); }
	 */

}
