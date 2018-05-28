package weather.weatherapp.openWeather;

import java.util.List;
import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:openweather.properties")
@Configuration
@ConfigurationProperties("weather")
public class OpenWeatherProperties {


    @Valid
    private final Api api = new Api();
    private List<String> locations ;

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    private String keyID;


    public Api getApi() {
        return this.api;
    }

    public List<String> getLocations() {
        return this.locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public static class Api {


    }
}
