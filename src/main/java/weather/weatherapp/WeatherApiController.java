package weather.weatherapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import weather.weatherapp.openWeather.OpenWeatherEntry;
import weather.weatherapp.openWeather.OpenWeatherService;

@RestController
@RequestMapping ("/weather")

public class WeatherApiController {

    @Autowired
    @Qualifier("openweather")
    private WeatherService weatherService;

    public WeatherApiController(){
    }

    @RequestMapping("/{city}")
    public WeatherEntry getWeatherByCity(
                @PathVariable String city) {
            return this.weatherService.getWeatherByCity(city);

    }


}