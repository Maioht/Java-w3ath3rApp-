package weather.weatherapp.openWeather;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;
import weather.weatherapp.WeatherEntry;
import weather.weatherapp.WeatherService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service("openweather")
public class OpenWeatherService implements WeatherService {

    private static final String W_URL =
            "http://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;
    private OpenWeatherProperties properties;


    public OpenWeatherService(RestTemplateBuilder restTemplateBuilder,
                              OpenWeatherProperties properties) {
        this.restTemplate = restTemplateBuilder.build();

        this.properties = properties;
    }
    @Cacheable("weather")
    public WeatherEntry getWeatherByCity(String city) {
        Map<String, Object> urlVariables = new HashMap<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(W_URL);
        builder.queryParam("q", city);
        builder.queryParam("APPID", this.properties.getKeyID());
        URI url = builder.build(urlVariables); // q={city}&APPID={id}
        return transform( invoke(url, OpenWeatherEntry.class));
    }

    @Override
    public WeatherEntry getWeatherByCoordinates(double lat, double lon) {
        Map<String, Object> urlVariables = new HashMap<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(W_URL);
        builder.queryParam("lat", lat);
        builder.queryParam("lon", lon);
        builder.queryParam("APPID", this.properties.getKeyID());
        URI url = builder.build(urlVariables); // q={city}&APPID={id}
        return transform( invoke(url, OpenWeatherEntry.class));
    }

    @Override
    public WeatherEntry getWeatherByZipCode(String zipCode, String country) {
        Map<String, Object> urlVariables = new HashMap<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(W_URL);
        builder.queryParam("zip", getZip(zipCode, country));
        builder.queryParam("APPID", this.properties.getKeyID());
        URI url = builder.build(urlVariables); // q={city}&APPID={id}
        return transform( invoke(url, OpenWeatherEntry.class));
    }

    private String getZip(String zipCode, String country) {
        return zipCode + (country != null ? ", " +country : "");
    }

    private WeatherEntry transform(OpenWeatherEntry invoke) {
        WeatherEntry entry = new WeatherEntry();
        entry.setHumidity(invoke.getHumidity());
        entry.setTemperature(invoke.getTemperature());
        entry.setCountry(invoke.getSys().getCountry());
        entry.setWindspeed(invoke.getWind().getSpeed());
        return entry;
    }


    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }
}
