package weather.weatherapp;


public interface WeatherService {

    public WeatherEntry getWeatherByCity(String city);

    public WeatherEntry getWeatherByCoordinates(double lat, double lon);

    public WeatherEntry getWeatherByZipCode(String zipCode, String country);
}
