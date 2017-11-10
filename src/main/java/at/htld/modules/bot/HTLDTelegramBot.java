package at.htld.modules.bot;

import at.htld.modules.entitiy.User;
import at.htld.modules.handler.DBHandler;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;

public class HTLDTelegramBot extends TelegramLongPollingBot {

    DBHandler d = new DBHandler();

    public void onUpdateReceived(Update update) {
        String message_text = update.getMessage().getText();
        User user;
        long chat_id = update.getMessage().getChatId();
        ArrayList<String> split_message = new ArrayList<String>();

        //Jedes Bruchstück der Nachricht bekommt eine eigene position in der Arraylist
        for (int i = 0; i < message_text.split(" ").length; i++) {
            split_message.add(message_text.split(" ")[i]);
        }

        //Send Message Objekt wird vorbereitet
        SendMessage sd = new SendMessage().setChatId(chat_id);

        try {

            /*
            Hier wird nachgeschaut ob ein User mit der Chat id exestiert, wenn dies nicht der Fall ist
            muss man seinen Benutzer konfigurieren
             */
            if(d.getUserByChatId(chat_id) == null){
                if(!split_message.get(0).equals("/config"))
                sd.setText("Konfigurieren sie Ihren Benutzer mit /config");

                if(split_message.get(0).equals("/config")){
                    if(split_message.size() == 4) {
                        user = new User();
                        user.setChat_id(chat_id);
                        user.setVname(split_message.get(1));
                        user.setNname(split_message.get(2));
                        user.setKlass(split_message.get(3));

                        d.saveUser(user);
                        user = d.getUserByChatId(chat_id);
                        sd.setText("Name: " + user.getVname() + " Nachname: " + user.getNname() + " Klasse: " + user.getKlass());
                    }
                    else{
                        sd.setText("Verwendung: /config [Vorname] [Nachname] [Klasse]");
                    }
                }
            }
            else{
                user = d.getUserByChatId(chat_id);

                if(split_message.get(0).equals("/whoami")){
                    sd.setText("Name: " + user.getVname() + " Nachname: " + user.getNname() + " Klasse: " + user.getKlass());
                }else{
                    sd.setText("Befehle : /whoami ..");
                }
            }


            sendMessage(sd);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }catch (SQLException e) {
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
