package istat.android.telephony.sms;

import istat.android.telephony.sms.provider.operations.SmsDelete;
import istat.android.telephony.sms.provider.operations.SmsInsert;
import istat.android.telephony.sms.provider.operations.SmsQueryProvider;
import istat.android.telephony.sms.provider.operations.SmsSelection;
import istat.android.telephony.sms.provider.operations.SmsUpdate;

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
public final class SmsQL {
    Context context;

    public SmsQL(Context context) {
        this.context = context;
    }

    public SmsSelection selectSms() {
        return SmsQueryProvider.getSelectStatement(context);
    }

    public SmsInsert insertSms() {
        return SmsQueryProvider.getInsertStatement(context);
    }

    public SmsDelete deleteSms() {
        return SmsQueryProvider.getDeleteStatement(context);
    }

    public SmsUpdate updateSms() {
        return SmsQueryProvider.getUpdateStatement(context);
    }

    public int insertOrUpdate(Sms sms) {
        if (selectSms().whereIdEqual(sms._id).execute().size() > 0)
            return updateSms().setLike(sms).execute();
        else
            return insertSms().setAs(sms).execute();
    }

    public boolean smsExist(String id) {
        return selectSms().whereIdEqual(id).execute().size() > 0 ? true : false;
    }
}
