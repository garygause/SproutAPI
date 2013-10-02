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

import android.os.Parcel;
import android.os.Parcelable;

public class SproutCard implements Parcelable {

    public static final String URL = "url";
    public static final String OAN = "oan";
    public static final String POINTS = "points";
    public static final String BALANCE = "balance";
    public static final String AVAILABLE_BALANCE = "available_balance";
    public static final String ASSIGNED_DATE = "assigned_date";
    public static final String NICK_NAME = "nick_name";
    public static final String EARNS_POINTS = "earns_points";
    public static final String EARNS_PROMOTIONS = "earns_promotions";
    public static final String VALUE_100_PTS = "value_100_pts";
    public static final String VALUE_250_PTS = "value_250_pts";
    public static final String VALUE_500_PTS = "value_500_pts";
    public static final String VALUE_1000_PTS = "value_1000_pts";

    private String mUrl;
    private long mOan;
    private int mPoints;
    private float mBalance;
    private float mAvailableBalance;
    private String mAssignedDate;
    private String mNickName;
    private boolean mEarnsPoints;
    private boolean mEarnsPromotions;
    private float mValue100Pts;
    private float mValue250Pts;
    private float mValue500Pts;
    private float mValue1000Pts;

    public SproutCard() {};
    
    public SproutCard(Parcel in) {
        readFromParcel(in);
    }
    
    public String getUrl() {
        return mUrl;       
    }
    
    public void setUrl(String url) {
        mUrl = url;
    }
    
    public long getOan() {
        return mOan;
    }
    
    public void setOan(long oan) {
        mOan = oan;
    }

    public int getPoints() {
        return mPoints;
    }
    
    public void setPoints(int points) {
        mPoints = points;
    }

    public float getBalance() {
        return mBalance;
    }
    
    public void setBalance(float balance) {
        mBalance = balance;
    }
    
    public float getAvailableBalance() {
        return mAvailableBalance;
    }
    
    public void setAvailableBalance(float balance) {
        mAvailableBalance = balance;
    }

    public String getAssignedDate() {
        return mAssignedDate;
    }
    
    public void setAssignedDate(String date) {
        mAssignedDate = date;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String name) {
        mNickName = name;
    }
    
    public boolean getEarnsPoints() {
        return mEarnsPoints;
    }
    
    public void setEarnsPoints(boolean earnsPoints) {
        mEarnsPoints = earnsPoints;
    }
    
    public boolean getEarnsPromotions() {
        return mEarnsPromotions;
    }
    
    public void setEarnsPromotions(boolean earnsPromotions) {
        mEarnsPromotions = earnsPromotions;
    }
    
    public float getValue100Pts() {
        return mValue100Pts;
    }

    public void setValue100Pts(float value) {
        mValue100Pts = value;
    }
    
    public float getValue250Pts() {
        return mValue250Pts;
    }

    public void setValue250Pts(float value) {
        mValue250Pts = value;
    }

    public float getValue500Pts() {
        return mValue500Pts;
    }

    public void setValue500Pts(float value) {
        mValue500Pts = value;
    }
    
    public float getValue1000Pts() {
        return mValue1000Pts;
    }

    public void setValue1000Pts(float value) {
        mValue1000Pts = value;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
        dest.writeLong(mOan);
        dest.writeInt(mPoints);
        dest.writeFloat(mBalance);
        dest.writeFloat(mAvailableBalance);
        dest.writeString(mAssignedDate);
        dest.writeString(mNickName);
        dest.writeInt(mEarnsPoints ? 1 : 0);
        dest.writeInt(mEarnsPromotions ? 1 : 0);
        dest.writeFloat(mValue100Pts);
        dest.writeFloat(mValue250Pts);
        dest.writeFloat(mValue500Pts);
        dest.writeFloat(mValue1000Pts);
    }

    private void readFromParcel(Parcel in) {
        mUrl = in.readString();
        mOan = in.readLong();
        mPoints = in.readInt();
        mBalance = in.readFloat();
        mAvailableBalance = in.readFloat();
        mAssignedDate = in.readString();
        mNickName = in.readString();
        mEarnsPoints = (in.readInt() == 1);
        mEarnsPromotions = (in.readInt() == 1);
        mValue100Pts = in.readFloat();
        mValue250Pts = in.readFloat();
        mValue500Pts = in.readFloat();
        mValue1000Pts = in.readFloat();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public SproutCard createFromParcel(Parcel in) {
                    return new SproutCard(in);
                }
                public SproutCard[] newArray(int size) {
                    return new SproutCard[size];
                }
    };
    
}
