package com.tahmeedul.retrofitjustajson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tahmeedul.retrofitjustajson.countryinfo.CountryObject;
import com.tahmeedul.retrofitjustajson.jocks.GetJocks;
import com.tahmeedul.retrofitjustajson.myapi.MyApiModel1;
import com.tahmeedul.retrofitjustajson.news.MyNews;
import com.tahmeedul.retrofitjustajson.news.MyObjects;
import com.tahmeedul.retrofitjustajson.prayertime.MyRoot;
import com.tahmeedul.retrofitjustajson.recipe.RObject2;
import com.tahmeedul.retrofitjustajson.recipe.RSetps;
import com.tahmeedul.retrofitjustajson.recipe.RecipeArray;
import com.tahmeedul.retrofitjustajson.recipe.RecipeObject;
import com.tahmeedul.retrofitjustajson.sunsetsunrise.MyArray;
import com.tahmeedul.retrofitjustajson.surah.SurahFirstArray;
import com.tahmeedul.retrofitjustajson.surah.TheAyahs;
import com.tahmeedul.retrofitjustajson.surah.TheData;
import com.tahmeedul.retrofitjustajson.weatherapi.Root1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView mTitle;
    RetroInterface retroInterface;
    MyNews myArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = findViewById(R.id.mTitle);

        // 1. Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")                // For api request 0
                //.baseUrl("https://jsonplaceholder.typicode.com/")                // For api request 1
                //.baseUrl("http://api.openweathermap.org/data/2.5/")              // For api request 2
                //.baseUrl("http://api.aladhan.com/v1/")                           // Fot api request 3
                //.baseUrl("https://api.sunrise-sunset.org/")                      // For api request 4
                //.baseUrl("http://newsapi.org/v2/")                               // For api request 5
                //.baseUrl("https://api.spoonacular.com/recipes/")                 // For api request 6
                //.baseUrl("https://restcountries.eu/rest/v2/name/")               // For api request 7
                //.baseUrl("https://official-joke-api.appspot.com/jokes/")         // For api request 8
                //.baseUrl("http://api.alquran.cloud/v1/")                         // For api request 9
                .baseUrl("http://192.168.43.157:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. Retrofit client
        retroInterface = retrofit.create(RetroInterface.class);

        // 00
        //getPosts();
        // 01
        //getComments();
        // 02
        //getWeather();
        // 03
        //getPrayerTime();
        // 04
        // getSunsetAndSunrise();
        // 05
        //getNews();
        // 06
        //getRecipe();
        // 07
        //getCountryInfo();
        // 08
        //getJocks();
        // 09
        //getSurah();

        // 10 And finally now time to use self made API
        getInfo();

    }

    private void getInfo() {
        Call<MyApiModel1> call = retroInterface.getMyApi();
        call.enqueue(new Callback<MyApiModel1>() {
            @Override
            public void onResponse(Call<MyApiModel1> call, Response<MyApiModel1> response) {
                if (response.isSuccessful()){
                    mTitle.setText("Name: "+response.body().getName()+"\n"+
                            "Email: "+response.body().getEmail()+"\n"+
                            "Phone: "+response.body().getPhone()+"\n");
                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyApiModel1> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getSurah() {
        Call<SurahFirstArray> call = retroInterface.getSurah(1);
        call.enqueue(new Callback<SurahFirstArray>() {
            @Override
            public void onResponse(Call<SurahFirstArray> call, Response<SurahFirstArray> response) {
                if (response.isSuccessful()){

                    String title = response.body().getData().getEnglishName()+" ("+response.body().getData().getNumberOfAyahs()+")";
                    mTitle.setText(title+"\n\n");
                    List<TheAyahs> theData = response.body().getData().getAyahs();
                    for (TheAyahs theAyahs : theData){
                        mTitle.append(theAyahs.getText()+"\n");
                    }

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SurahFirstArray> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });

    }

    private void getJocks() {
        Call<GetJocks> call = retroInterface.getJocks();
        call.enqueue(new Callback<GetJocks>() {
            @Override
            public void onResponse(Call<GetJocks> call, Response<GetJocks> response) {
                if (response.isSuccessful()){
                    String text = response.body().getRandom()+"\n\n"+response.body().getPunchline();

                    mTitle.setText(text);
                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GetJocks> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getCountryInfo() {
        Call<List<CountryObject>> call = retroInterface.getCountryInfo();
        call.enqueue(new Callback<List<CountryObject>>() {
            @Override
            public void onResponse(Call<List<CountryObject>> call, Response<List<CountryObject>> response) {
                if (response.isSuccessful()){
                    mTitle.setText("Name: "+response.body().get(0).getName()+"\n"+
                    "Capital: "+response.body().get(0).getCapital()+"\n"+
                            "Area: "+response.body().get(0).getArea()+"\n"+
                            "Population: "+response.body().get(0).getPopulation()+"\n\n"
                            );

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CountryObject>> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });

    }

    private void getRecipe() {
        Call<RecipeArray> call = retroInterface.getRecipe("aeb08fdee26c4edea79e5af3059fa2e2");
        call.enqueue(new Callback<RecipeArray>() {
            @Override
            public void onResponse(Call<RecipeArray> call, Response<RecipeArray> response) {
                if (response.isSuccessful()) {

                    List<RecipeObject> recipeObject = response.body().getRecipes();

                    List<RSetps> rSetps =  recipeObject.get(0).getAnalyzedInstructions().get(0).getSteps();
                    for (RSetps rSetps1 : rSetps){
                        mTitle.append(rSetps1.getStep()+"\n\n");
                    }

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RecipeArray> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getNews() {
        Call<MyNews> call = retroInterface.getNews("in", "business", "0cd2f0b68fce4b6eb05c18f7dee3d530");

        call.enqueue(new Callback<MyNews>() {
            @Override
            public void onResponse(Call<MyNews> call, Response<MyNews> response) {
                if (response.isSuccessful()){
                    List<MyObjects> myObjects = response.body().getArticles();
                    for (MyObjects myObjects1 : myObjects){
                        mTitle.append(
                                "Source: "+myObjects1.getSource().getName()+"\n"+
                                "Author: "+myObjects1.getAuthor()+"\n"+
                                "Title: "+myObjects1.getTitle()+"\n\n"
                        );
                    }

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyNews> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });

    }

    private void getSunsetAndSunrise() {
        Call<MyArray> call = retroInterface.getTime(23.777176, 90.399452, "today");
        call.enqueue(new Callback<MyArray>() {
            @Override
            public void onResponse(Call<MyArray> call, Response<MyArray> response) {
                if (response.isSuccessful()) {
                    String sunrise = response.body().getResults().getSunrise();
                    String sunset = response.body().getResults().getSunset();

                    mTitle.setText("Sunrise: "+ sunrise+"\n"+"Sunset: "+ sunset);

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyArray> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getPrayerTime() {
        Call<com.tahmeedul.retrofitjustajson.prayertime.MyRoot> call = retroInterface.getTimes("Dhaka", "Bangladesh", 8);
        call.enqueue(new Callback<MyRoot>() {
            @Override
            public void onResponse(Call<MyRoot> call, Response<MyRoot> response) {
                if (response.isSuccessful()) {

                    String fa = response.body().getData().getTimings().getFajr();
                    String ja = response.body().getData().getTimings().getDhuhr();
                    String as = response.body().getData().getTimings().getAsr();
                    String ma = response.body().getData().getTimings().getMaghrib();
                    String es = response.body().getData().getTimings().getIsha();

                    mTitle.setText("Fajr: "+fa+"\n\n"+
                            "Dhuhr: "+ja+"\n\n"+
                            "Asr: "+as+"\n\n"+
                            "Maghrib: "+ma+"\n\n"+
                            "Isha: "+es+"\n\n");

                }else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyRoot> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });

    }

    private void getWeather() {
        // 3. Call
        Call<Root1> call = retroInterface.getWeather("tangail,BD","a25fb4ecfae2a25c9a7da72cbe18a0d8");

        // 4. CallBack
        call.enqueue(new Callback<Root1>() {
            @Override
            public void onResponse(Call<Root1> call, Response<Root1> response) {
                if (response.isSuccessful()) {
                    String location = response.body().getName();
                    int temp = (int) (response.body().getMain().getTemp() - 273.15);
                    mTitle.setText("Location: "+location+"\nTemp: "+temp+"°C");

                } else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Root1> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getPosts() {
        // 3. Call
        Call<List<MyModel>> call = retroInterface.getComments(7);

        // 4. CallBack

        call.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()) {
                    List<MyModel> list = response.body();
                    for (MyModel myModel : list) {
                        mTitle.append(
                                myModel.getId() + "\n"
                                        + myModel.getEmail() + "\n"
                                        + myModel.getUserName()
                                        + "\n\n");
                    }

                } else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

    private void getComments() {
        // 3. Call
        Call<List<MyModel>> call = retroInterface.getComments(7);

        // 4. CallBack

        call.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                if (response.isSuccessful()) {
                    List<MyModel> list = response.body();
                    for (MyModel myModel : list) {
                        mTitle.append(
                                myModel.getId() + "\n"
                                        + myModel.getEmail() + "\n"
                                        + myModel.getUserName()
                                        + "\n\n");
                    }

                } else {
                    mTitle.setText("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                mTitle.setText("Response Error: " + t.getMessage());
            }
        });
    }

}