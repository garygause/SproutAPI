package com.sprout.api;

import java.util.ArrayList;

public class SproutCardResponse {
    
    // matches response from sprout /card api
    public static final String COUNT = "count";
    public static final String NEXT = "next";
    public static final String PREVIOUS = "previous";
    public static final String RESULTS = "results";
    
    private int mCount;
    private String mNext;
    private String mPrevious;
    private ArrayList<SproutCard> mResults;
    
    public int getCount() {
        return mCount;
    }
    
    public void setCount(int count) {
        mCount = count;
    }
    
    public String getNext() {
        return mNext;
    }
    
    public void setNext(String next) {
        mNext = next;
    }
    
    public String getPrevious() {
        return mPrevious;
    }
    
    public void setPrevious(String previous) {
        mPrevious = previous;
    }
    
    public ArrayList<SproutCard> getResults() {
        return mResults;
    }
    
    public void setResults(ArrayList<SproutCard> results) {
        mResults = results;
    }
    
}
