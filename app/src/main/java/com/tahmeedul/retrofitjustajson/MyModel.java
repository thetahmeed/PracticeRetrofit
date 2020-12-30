package com.tahmeedul.retrofitjustajson;

import com.google.gson.annotations.SerializedName;

public class MyModel {

    // var name should be same as the json key name
    String id;
    String email;
    // But you can change by using @SerializedName("herewillbetherealone")
    @SerializedName("name")
    String userName;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
