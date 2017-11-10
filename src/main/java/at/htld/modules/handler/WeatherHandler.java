package at.htld.modules.handler;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;

/**
 * Created by Elias Gmeiner on 10.11.2017.
 */
public class WeatherHandler {
    private OpenWeatherMap owm;

    public WeatherHandler() {
        owm = new OpenWeatherMap("c621c449fdadcabea70ebc79e8e3abf9");
    }

    public String getWeatherByCity(String cname) throws IOException {

        CurrentWeather cwd = owm.currentWeatherByCityName(cname);
        return "Temperatur: " + cwd.getMainInstance().getMaxTemperature();

    }

}
