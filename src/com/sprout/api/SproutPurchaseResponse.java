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
