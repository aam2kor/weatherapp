package com.mobile.abey.weatherapplatest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.abey.weatherapplatest.Location;
import com.mobile.abey.weatherapplatest.Weather;

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
public class JSONWeatherParser {

    public static Weather getWeather(String data) throws JSONException  {
        Weather weather = new Weather();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info
        Location loc = new Location();

        //Old Code
        /*JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));*/
        JSONObject cityObj = getObject("city", jObj);
        JSONObject coordObj = getObject("coord", cityObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        //Old Code
        /*JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));*/
        loc.setCountry(getString("country", cityObj));
        loc.setCity(getString("name", cityObj));
        weather.location = loc;

        // We get weather info (This is an array)
        //Old Code
        //JSONArray jArr = jObj.getJSONArray("weather");
        JSONArray jArr_list = jObj.getJSONArray("list");
        for(int index=0; index<8; index++){
            JSONObject JSONlist = jArr_list.getJSONObject(index);;
            JSONArray jArr_weather = JSONlist.getJSONArray("weather");
            JSONObject JSONWeather = jArr_weather.getJSONObject(0);

            // We use only the first value
            //JSONObject JSONWeather = jArr.getJSONObject(0);
            weather.currentCondition.setWeatherId(getInt("id", JSONWeather),index);
            weather.currentCondition.setDescr(getString("description", JSONWeather),index);
            weather.currentCondition.setCondition(getString("main", JSONWeather),index);
            weather.currentCondition.setIcon(getString("icon", JSONWeather),index);
            weather.currentCondition.setDate(getString("dt_txt",JSONlist),index);

            JSONObject mainObj = getObject("main", JSONlist);
            weather.currentCondition.setHumidity(getInt("humidity", mainObj),index);
            weather.currentCondition.setPressure(getInt("pressure", mainObj),index);
            weather.temperature.setMaxTemp(getFloat("temp_max", mainObj),index);
            weather.temperature.setMinTemp(getFloat("temp_min", mainObj),index);
            weather.temperature.setTemp(getFloat("temp", mainObj),index);
        /*weather.currentCondition.setHumidity(getInt("humidity", JSONlist));
        weather.currentCondition.setPressure(getInt("pressure", JSONlist));
        JSONObject tempObj = getObject("temp", JSONlist);
        weather.temperature.setMaxTemp(getFloat("max", tempObj));
        weather.temperature.setMinTemp(getFloat("min", tempObj));
        weather.temperature.setTemp(getFloat("day", tempObj));*/

            // aam2kor: get morn,day,eve temperatures
            //JSONArray jArr_list = jObj.getJSONArray("list");
            //JSONObject JSONlist = jArr_list.getJSONObject(0);
        /*weather.temperature.setdayTemp(getFloat("day",tempObj));
        weather.temperature.setmornTemp(getFloat("morn",tempObj));
        weather.temperature.seteveTemp(getFloat("eve",tempObj));*/


            // Wind
            JSONObject wObj = getObject("wind", JSONlist);
            weather.wind.setSpeed(getFloat("speed", wObj),index);
            weather.wind.setDeg(getFloat("deg", wObj),index);

            // Clouds
            JSONObject cObj = getObject("clouds", JSONlist);
            weather.clouds.setPerc(getInt("all", cObj),index);

            // We download the icon to show

        }


        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
