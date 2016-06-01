package com.mobile.abey.weatherapplatest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.abey.weatherapplatest.Location;
import com.mobile.abey.weatherapplatest.Weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
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
 */

public class MainActivity extends Activity {


    private TextView cityText;
    private TextView[] condDescr = new TextView[8];
    private TextView[] temp = new TextView[8];
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;

    // aam2kor: get morn,day,eve temperatures
    private TextView maxtemp;
    private TextView mintemp;


    private TextView hum;
    private TextView[] date = new TextView[8];
    private ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String city = "Vienna";

        cityText = (TextView) findViewById(R.id.cityText);


        condDescr[0] = (TextView) findViewById(R.id.condDescr1);
        condDescr[1] = (TextView) findViewById(R.id.condDescr2);
        condDescr[2] = (TextView) findViewById(R.id.condDescr3);
        condDescr[3] = (TextView) findViewById(R.id.condDescr4);
        condDescr[4] = (TextView) findViewById(R.id.condDescr5);
        condDescr[5] = (TextView) findViewById(R.id.condDescr6);
        condDescr[6] = (TextView) findViewById(R.id.condDescr7);
        condDescr[7] = (TextView) findViewById(R.id.condDescr8);

        temp[0] = (TextView) findViewById(R.id.temp1);
        temp[1] = (TextView) findViewById(R.id.temp2);
        temp[2] = (TextView) findViewById(R.id.temp3);
        temp[3] = (TextView) findViewById(R.id.temp4);
        temp[4] = (TextView) findViewById(R.id.temp5);
        temp[5] = (TextView) findViewById(R.id.temp6);
        temp[6] = (TextView) findViewById(R.id.temp7);
        temp[7] = (TextView) findViewById(R.id.temp8);

        date[0] = (TextView) findViewById(R.id.date1);
        date[1] = (TextView) findViewById(R.id.date2);
        date[2] = (TextView) findViewById(R.id.date3);
        date[3] = (TextView) findViewById(R.id.date4);
        date[4] = (TextView) findViewById(R.id.date5);
        date[5] = (TextView) findViewById(R.id.date6);
        date[6] = (TextView) findViewById(R.id.date7);
        date[7] = (TextView) findViewById(R.id.date8);

        //hum = (TextView) findViewById(R.id.hum);
        //press = (TextView) findViewById(R.id.press);
        //windSpeed = (TextView) findViewById(R.id.windSpeed);
        //windDeg = (TextView) findViewById(R.id.windDeg);
        //imgView = (ImageView) findViewById(R.id.condIcon);

        // aam2kor: get morn,day,eve temperatures
        //maxtemp = (TextView) findViewById(R.id.maxtemp);
        //mintemp = (TextView) findViewById(R.id.mintemp);



        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();

            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);


                // Let's retrieve the icon
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon(0)));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }




        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            for(int i=0; i<8; i++){
                cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
                condDescr[i].setText(weather.currentCondition.getCondition(i) + "(" + weather.currentCondition.getDescr(i) + ")");
                temp[i].setText("Avrg: " + Math.round((weather.temperature.getTemp(i) - 273.15)) + "\u00B0C");
                //+"\n" + "Max: " + Math.round((weather.temperature.getMaxTemp(i) - 273.15)) + "\u00B0C" +"\n" + "Min: " + Math.round((weather.temperature.getMinTemp(i) - 273.15)) + "\u00B0C" );
                date[i].setText(weather.currentCondition.getDate(i));
            }


            //hum.setText("" + weather.currentCondition.getHumidity() + "%");
            //press.setText("" + weather.currentCondition.getPressure() + " hPa");
            //windSpeed.setText("" + weather.wind.getSpeed() + " mps");
            //windDeg.setText("" + weather.wind.getDeg() + "\u00B0");

            //maxtemp.setText("" + Math.round((weather.temperature.getMaxTemp() - 273.15)) + "\u00B0C");
            //mintemp.setText("" + Math.round((weather.temperature.getMinTemp() - 273.15)) + "\u00B0C");


        }







    }
}
