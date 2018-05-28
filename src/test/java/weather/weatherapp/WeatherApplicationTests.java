package weather.weatherapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherApplicationTests {
    @Autowired
    WeatherProperties properties;
    @Autowired
    WeatherService service;

    @Test
    public void contextLoads() {
        System.out.println(this.properties.getLocations());
        System.out.println(this.properties.getKeyID());
    }

    @Test
    public void getWeatherTest(){
        Weather weather = service.getWeather("helsinki");
        Assert.assertEquals("Helsinki",weather.getName());
    }
}
