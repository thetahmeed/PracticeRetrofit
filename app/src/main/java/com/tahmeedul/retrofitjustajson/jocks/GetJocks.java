package com.tahmeedul.retrofitjustajson.jocks;

import com.google.gson.annotations.SerializedName;

public class GetJocks {

    @SerializedName("setup")
    String random;
    String punchline;

    public String getRandom() {
        return random;
    }

    public String getPunchline() {
        return punchline;
    }
}
