import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();

        Bot bot = new Bot();
        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {

        String s = "⠀";
        String msg;
        int msgInt;

        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        msg = update.getMessage().getText();
        msgInt = msg.length();

        sendMessage.setText(msg.replaceAll("\n", s+"\n"));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (msgInt > 2200) {
            sendMessage.setText("Количество символов в тексте " + msgInt + "\n" + "Должно быть небольше 2200");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public String getBotUsername() {
        return "@insta_alinacatt_bot";
    }


    public String getBotToken() {
        return "1075669237:AAFVkaEeOR8Htcddsli3gBbtgexNbJrTm0E";
    }
}
