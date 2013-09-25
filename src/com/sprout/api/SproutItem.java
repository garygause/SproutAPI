package com.sprout.api;

public class SproutItem {

    public static final String BAR_CODE = "bar_code";
    public static final String BOTTLE_DEPOSIT = "bottle_deposit";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String TAX = "tax";

    private String mBarCode = "";
    private float mBottleDeposit = 0.0f;
    private String mDescription = "";
    private float mPrice = 0.0f;
    private float mTax = 0.0f;

    // method names have to match json fields for jackson parser, i.e. bar_code
    
    public String getBar_code() {
        return mBarCode;
    }

    public void setBar_code(String code) {
        mBarCode = code;
    }

    public float getBottle_deposit() {
        return mBottleDeposit;
    }

    public void setBottle_deposit(float deposit) {
        mBottleDeposit = deposit;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String descr) {
        mDescription = descr;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public float getTax() {
        return mTax;
    }

    public void setTax(float tax) {
        mTax = tax;
    }

}
