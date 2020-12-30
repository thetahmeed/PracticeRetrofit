package com.tahmeedul.retrofitjustajson;

import com.tahmeedul.retrofitjustajson.countryinfo.CountryObject;
import com.tahmeedul.retrofitjustajson.jocks.GetJocks;
import com.tahmeedul.retrofitjustajson.myapi.MyApiModel1;
import com.tahmeedul.retrofitjustajson.news.MyNews;
import com.tahmeedul.retrofitjustajson.recipe.RecipeArray;
import com.tahmeedul.retrofitjustajson.sunsetsunrise.MyArray;
import com.tahmeedul.retrofitjustajson.surah.SurahFirstArray;
import com.tahmeedul.retrofitjustajson.surah.TheData;
import com.tahmeedul.retrofitjustajson.weatherapi.Root1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroInterface {

    // 00. For https://jsonplaceholder.typicode.com/comments?postId=1
    @GET("comments")                                                    // endpoints
    Call<List<MyModel>> getPosts(@Query("postId") int postId);          // @QUERY, you can use @PATH, @URL etc

    // 01. For https://jsonplaceholder.typicode.com/posts/7/comments
    @GET("posts/{id}/comments")                                         // endpoints
    Call<List<MyModel>> getComments(@Path("id") int id);                // @QUERY, you can use @PATH, @URL etc

    // 02. Weather Api http://api.openweathermap.org/data/2.5/weather?q=dhaka&appid=a25fb4ecfae2a25c9a7da72cbe18a0d8
    @GET("weather")
    Call<Root1> getWeather(@Query("q") String area, @Query("appid") String token);

    // 03. Prayer Time Api http://api.aladhan.com/v1/timingsByCity?city=Dhaka&country=Bangladesh&method=8
    @GET("timingsByCity")
    Call<com.tahmeedul.retrofitjustajson.prayertime.MyRoot> getTimes(
            @Query("city") String city,
            @Query("country") String country,
            @Query("method") int method

    );

    // 04. Sunset and Sunrise Api https://api.sunrise-sunset.org/json?lat=23.777176&lng=90.399452&date=today
    @GET("json")
    Call<MyArray> getTime(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("date") String date
    );

    // 05. News Api http://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=0cd2f0b68fce4b6eb05c18f7dee3d530
    @GET("top-headlines")
    Call<MyNews> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

    // 06. Recipe Api https://api.spoonacular.com/recipes/random?apiKey=aeb08fdee26c4edea79e5af3059fa2e2
    @GET("random")
    Call<RecipeArray> getRecipe(
            @Query("apiKey") String apiKey
    );

    // 07. Country info Api https://restcountries.eu/rest/v2/name/bangladesh
    @GET("bangladesh")
    Call<List<CountryObject>> getCountryInfo();

    // 08. Get jocks Api https://official-joke-api.appspot.com/jokes/random
    @GET("random")
    Call<GetJocks> getJocks();

    // 09. Get Surah Api http://api.alquran.cloud/v1/surah/114
    @GET("surah/{id}")
    Call<SurahFirstArray> getSurah(@Path("id") int surah_id);

    // 09. Get Surah Api http://api.alquran.cloud/v1/surah/114
    @GET("api")
    Call<MyApiModel1> getMyApi();


}
