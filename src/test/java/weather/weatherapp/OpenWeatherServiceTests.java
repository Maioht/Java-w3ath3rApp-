package weather.weatherapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import weather.weatherapp.openWeather.OpenWeatherEntry;
import weather.weatherapp.openWeather.OpenWeatherProperties;
import weather.weatherapp.openWeather.OpenWeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherServiceTests {
    @Autowired
    OpenWeatherProperties properties;
    @Autowired
    OpenWeatherService service;

    @Test
    public void contextLoads() {
        System.out.println(this.properties.getLocations());
        System.out.println(this.properties.getKeyID());
    }

    @Test
    public void getWeatherByCityTest(){
        WeatherEntry weather = service.getWeatherByCity("helsinki");
        Assert.assertEquals("FI",weather.getCountry());
    }

    @Test
    public void getWeatherByZipCodeTest(){
        WeatherEntry weather = service.getWeatherByZipCode("90100", "FI");
        Assert.assertEquals("FI",weather.getCountry());
    }

    @Test(expected = HttpClientErrorException.class)
    public void getWeatherByInvalidZipCodeTest() {
        WeatherEntry weather = service.getWeatherByZipCode("9x010", "FI");
        Assert.assertEquals("FI",weather.getCountry());
    }
    @Test
    public void getWeatherByCoordinatesTest(){
        WeatherEntry weather = service.getWeatherByCoordinates(37.39, -122.09);
        Assert.assertEquals("US",weather.getCountry());
    }
}
