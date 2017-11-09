package com.a0xffffffff.dinesum;

import java.util.Date;
import java.util.Calendar;

public class RequestData {

    private String mStartTime;
    private String mEndTime;
    private String mPartyName;
    private int mNumParty;
    private Restaurant mRestaurant;
    private double mPayment;

    public RequestData() {
    }

    public RequestData(String startTime, String endTime, String partyName, int numParty,
                       String restaurantID, double payment) {

        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mPartyName = partyName;
        this.mNumParty = numParty;
        this.mRestaurant = new Restaurant(restaurantID);
        this.mPayment = payment;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public double getmPayment() {
        return mPayment;
    }

    public void setmPayment(double mPayment) {
        this.mPayment = mPayment;
    }

    public String getmPartyName() {
        return mPartyName;
    }

    public void setmPartyName(String mPartyName) {
        this.mPartyName = mPartyName;
    }

    public void setmRestaurant(Restaurant mRestaurant) {
        this.mRestaurant = mRestaurant;
    }

    public Restaurant getmRestaurant() {
        return mRestaurant;
    }

    public int getmNumParty() {
        return mNumParty;
    }

    public void setmNumParty(int mNumParty) {
        this.mNumParty = mNumParty;
    }

}
