package com.mobile.abey.weatherapplatest;

/**
 * Created by abey on 27.05.16.
 */
public class Weather {

    public Location location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow()	;
    public Clouds clouds = new Clouds();

    public byte[] iconData;

    public  class CurrentCondition {
        private int[] weatherId = new int[8];
        private String[] condition = new String[8];
        private String[] descr = new String[8];
        private String[] icon = new String[8];
        private String[] date = new String[8];


        private float[] pressure = new float[8];
        private float[] humidity = new float[8];

        public int getWeatherId(int index) {
            return weatherId[index];
        }
        public void setWeatherId(int weatherId,int index) {
            this.weatherId[index] = weatherId;
        }
        public String getCondition(int index) {
            return condition[index];
        }
        public void setCondition(String condition, int index) {
            this.condition[index] = condition;
        }
        public String getDescr(int index) {
            return descr[index];
        }
        public void setDescr(String descr, int index) {
            this.descr[index] = descr;
        }
        public String getIcon(int index) {
            return icon[index];
        }
        public void setIcon(String icon, int index) {
            this.icon[index] = icon;
        }
        public float getPressure(int index) {
            return pressure[index];
        }
        public void setPressure(float pressure,int index) {
            this.pressure[index] = pressure;
        }
        public float getHumidity(int index) {
            return humidity[index];
        }
        public void setHumidity(float humidity,int index) {
            this.humidity[index] = humidity;
        }
        public String getDate(int index) {
            return date[index];
        }
        public void setDate(String date,int index) {
            this.date[index] = date;
        }


    }

    public  class Temperature {
        private float[] temp = new float[8];
        private float[] minTemp = new float[8];
        private float[] maxTemp = new float[8];
        ;

        public float getTemp(int index) {
            return temp[index];
        }
        public void setTemp(float temp,int index) {
            this.temp[index] = temp;
        }
        public float getMinTemp(int index) {
            return minTemp[index];
        }
        public void setMinTemp(float minTemp,int index) {
            this.minTemp[index] = minTemp;
        }
        public float getMaxTemp(int index) {
            return maxTemp[index];
        }
        public void setMaxTemp(float maxTemp,int index) {
            this.maxTemp[index] = maxTemp;
        }



    }

    public  class Wind {
        private float[] speed = new float[8];
        private float[] deg = new float[8];
        public float getSpeed(int index) {
            return speed[index];
        }
        public void setSpeed(float speed,int index) {
            this.speed[index] = speed;
        }
        public float getDeg(int index) {
            return deg[index];
        }
        public void setDeg(float deg,int index) {
            this.deg[index] = deg;
        }


    }

    public  class Rain {
        private String time;
        private float ammount;
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public float getAmmount() {
            return ammount;
        }
        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }



    }

    public  class Snow {
        private String time;
        private float ammount;

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public float getAmmount() {
            return ammount;
        }
        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }


    }

    public  class Clouds {
        private int[] perc = new int[8];

        public int getPerc(int index) {
            return perc[index];
        }

        public void setPerc(int perc,int index) {
            this.perc[index] = perc;
        }


    }

}
