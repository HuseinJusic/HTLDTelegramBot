package at.htld.modules.main;

import at.htld.modules.bot.HTLDTelegramBot;
import at.htld.modules.handler.DBHandler;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;

public class Main {

    public static void main (String[] args) throws TelegramApiRequestException, SQLException {
        DBHandler h = new DBHandler();

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        botsApi.registerBot(new HTLDTelegramBot());

        System.out.println("Bot gestartet!");


    }
}
