package at.htld.modules.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class HTLDTelegramBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        ArrayList<String> split_message = new ArrayList<String>();

        //Jedes Bruchstück der Nachricht bekommt eine eigene position in der Arraylist
        for (int i = 0; i < message_text.split(" ").length; i++) {
            split_message.add(message_text.split(" ")[i]);
        }

        //Send Message Objekt wird vorbereitet
        SendMessage sd = new SendMessage().setChatId(chat_id);

        sd.setText("test");

        try {
            sendMessage(sd);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "HTLDornbirn_Bot";
    }

    public String getBotToken() {
        return "454919011:AAEopk6fvCDtsjAArMQJ6u3fQishj3aposk";
    }
}
