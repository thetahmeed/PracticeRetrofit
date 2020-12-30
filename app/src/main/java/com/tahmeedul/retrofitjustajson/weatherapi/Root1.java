package com.tahmeedul.retrofitjustajson.weatherapi;

public class Root1 {

    int timezone;
    String name;
    Root2 main;

    public Root2 getMain() {
        return main;
    }

    public int getTimezone() {
        return timezone;
    }

    public String getName() {
        return name;
    }
}
