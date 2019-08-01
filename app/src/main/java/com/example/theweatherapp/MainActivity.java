package com.example.theweatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

public class MainActivity extends AppCompatActivity {
private ImageView iconView;
private TextView cityName;
private TextView temp;
private TextView description;
private TextView humidity;
private TextView wind;
private TextView sunrise;
private TextView pressure;
private TextView sunset;
private TextView updated;

Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);

        renderWeatherData("Spokane,US");
    }

    public void renderWeatherData (String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city+"&units=metric"});

    }

    public class WeatherTask extends AsyncTask<String ,Void, Weather>{
        public WeatherTask() {
            super();
        }

        @Override
        protected void onPostExecute(Weather weather) {

            //new WeatherTask().execute();
            super.onPostExecute(weather);
        }

        @Override
        protected Weather doInBackground(String... params) {
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));
            weather = JSONWeatherParser.getWeather(data);
            Log.v("Data:",weather.place.getCity());
            return weather;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
