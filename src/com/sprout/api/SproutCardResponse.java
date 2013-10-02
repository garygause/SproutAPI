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
