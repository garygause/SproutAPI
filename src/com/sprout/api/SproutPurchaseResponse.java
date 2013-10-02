/*
 * Copyright (C) 2013 SproutOne, see LICENSE for details.
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
 * 
 * @author Gary Gause
 * 
 */
package com.sprout.api;

public class SproutPurchaseResponse {
    
    // matches response from sprout /card/{oan}/transaction/purchase api

    public static final String CREATED = "created";
    public static final String OAN = "oan";
    public static final String AMOUNT = "amount";
    public static final String NEW_BALANCE = "new_balance";
    public static final String CARD_TRANSACTION_ID = "card_transaction_id";
    public static final String DETAIL_RECORD_ID = "detail_record_id";
    public static final String URL = "url";
    public static final String DETAIL_URL = "detail_url";

    private String mCreated;
    private long mOan;
    private float mAmount;
    private float mNewBalance;
    private int mCardTransactionId;
    private int mDetailRecordId;
    private String mUrl;
    private String mDetailUrl;

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public long getOan() {
        return mOan;
    }

    public void setOan(long oan) {
        mOan = oan;
    }

    public float getAmount() {
        return mAmount;
    }

    public void setAmount(float amount) {
        mAmount = amount;
    }

    public float getNewBalance() {
        return mNewBalance;
    }

    public void setNewBalance(float balance) {
        mNewBalance = balance;
    }

    public int getCardTransactionId() {
        return mCardTransactionId;
    }

    public void setCardTransactionId(int id) {
        mCardTransactionId = id;
    }

    public int getDetailRecordId() {
        return mDetailRecordId;
    }

    public void setDetailRecordId(int id) {
        mDetailRecordId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDetailUrl() {
        return mDetailUrl;
    }

    public void setDetailUrl(String url) {
        mDetailUrl = url;
    }
}
