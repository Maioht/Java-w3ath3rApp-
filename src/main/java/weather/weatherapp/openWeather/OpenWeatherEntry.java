package weather.weatherapp.openWeather;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class OpenWeatherEntry implements Serializable {

    private Integer weatherId;
    private double temperature;
    private double  humidity;
    private WindInfo wind;
    private SysInfo sys;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeatherId() {
        return this.weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }
    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = BigDecimal.valueOf(temperature - 273.15).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //return temperature kelvins -273,15 = celsius
    }
    public double getHumidity() {
        return this.humidity;
    }


    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
        setHumidity (Double.parseDouble(main.get("humidity").toString()));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));

    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public SysInfo getSys() {
        return sys;
    }

    public void setSys(SysInfo sys) {
        this.sys = sys;
    }
}
