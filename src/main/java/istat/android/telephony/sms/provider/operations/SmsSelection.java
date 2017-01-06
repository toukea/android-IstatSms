package istat.android.telephony.sms.provider.operations;


import istat.android.telephony.sms.Sms;

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
public final class SmsSelection extends SmsClause<SmsSelection> {

    SmsSelection(Context context) {
        super(context);
    }

    public List<Sms> execute() {
        return SmsQuery.select(context, this);
    }

    public int count() {
        return SmsQuery.count(context, this);
    }
}
