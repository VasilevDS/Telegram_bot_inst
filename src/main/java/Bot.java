import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        String s = "⠀";
        String msg;
        int msgInt;

        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        msg = update.getMessage().getText();
        msgInt = msg.length();

        sendMessage.setText(msg.replaceAll("\n", s+"\n"));

        if(msg.equals("/start"))
            sendMessage.setText("Привет!\n"+"\n"+"Отправь текст, в который нужно добавить волшебные символы, чтобы гребаный instagram понимал що таке абзац");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (msgInt > 2200) {
            sendMessage.setText("*⛔️ В вашем тексте больше 2200 символов! \n*" + "\n"
                    + "Instagram разрешает публиковать посты с количеством символов не превышающим 2200 символов, " +
                    "а у вас получилось " + msgInt + " символов.");
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
