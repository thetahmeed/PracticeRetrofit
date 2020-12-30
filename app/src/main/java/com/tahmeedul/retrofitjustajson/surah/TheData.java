package com.tahmeedul.retrofitjustajson.surah;

import java.util.List;

public class TheData {

    String englishName;
    String numberOfAyahs;
    List<TheAyahs> ayahs;

    public String getEnglishName() {
        return englishName;
    }

    public String getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public List<TheAyahs> getAyahs() {
        return ayahs;
    }
}
