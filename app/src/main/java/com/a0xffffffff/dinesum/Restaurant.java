package com.a0xffffffff.dinesum;

//import com.google.android.gms.location.places.Places;

public class Restaurant {
    private String mRestaurantID;
    private String mRestaurantName;
    private String mRestaurantAddress;
    private String mRestaurantCity;

    public Restaurant() {

    }

    public Restaurant(String restaurantID) {
        mRestaurantID = restaurantID;
        // TODO: later will be passing in restaurant as Place
    }

    public String getRestaurantID() {
        return mRestaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        mRestaurantID = restaurantID;
    }

    public String getRestaurantName(){
        return mRestaurantName;
    }

    public void setRestaurantName(String restaurantName){
        mRestaurantName = restaurantName;
    }

    public String getRestaurantAddress(){
        return mRestaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress){
        mRestaurantAddress = restaurantAddress;
    }

    public String getRestaurantCity(){
        return mRestaurantCity;
    }

    public void setRestaurantCity(String restaurantCity){
        mRestaurantCity = restaurantCity;
    }

}
