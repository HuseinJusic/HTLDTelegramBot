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

    public String getWeatherByCity(String cname) {

        CurrentWeather cwd = null;
        try {
            cwd = owm.currentWeatherByCityName(cname);
        } catch (IOException e) {
            return "Stadt nicht gefunden";
        }
        return "Temperatur: " + cwd.getMainInstance().getMaxTemperature();

    }

}
