package weather.weatherapp;


public class WeatherSummary {
    private final String country;
    private final String city;
    private final Integer code;
    private final double temperature;

    WeatherSummary(String country, String city, Weather weather) {
        this.country = country;
        this.city = city;
        this.code = weather.getWeatherId();
        this.temperature = weather.getTemperature();


    }

  /*  public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getCode() {
        return this.code;
    }
   /* public String getTemperature(){
        double temp = this.temperature - 273.15;
        return String.format("%4.2f", temp);
    }*/


}
