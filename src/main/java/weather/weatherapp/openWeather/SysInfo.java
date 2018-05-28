package weather.weatherapp.openWeather;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SysInfo {

    private String country;

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
