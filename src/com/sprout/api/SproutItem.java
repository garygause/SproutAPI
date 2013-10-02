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
