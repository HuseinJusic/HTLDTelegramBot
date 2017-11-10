package at.htld.modules.main;

import at.htld.modules.bot.HTLDTelegramBot;
import at.htld.modules.entitiy.Station;
import at.htld.modules.entitiy.User;
import at.htld.modules.handler.DBHandler;
import at.htld.modules.handler.WeatherHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main (String[] args) throws TelegramApiRequestException, SQLException {
        DBHandler h = new DBHandler();

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        botsApi.registerBot(new HTLDTelegramBot());

        System.out.println("Bot gestartet!");

        WeatherHandler handler = new WeatherHandler();

        try {
            System.out.println(handler.getWeatherByCity("Bregenz"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
