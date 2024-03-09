
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
public class bot extends TelegramLongPollingBot {
    int propulsion = 0;
    String text2;
    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        if(update.hasMessage() && update.getMessage().hasText()) {
            if ("/start".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите специальность: ", "Программист","Бурильщик","Электрик","Механик","Оператор ДНГ","Бухгалтер");
            }else if ("Программист".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1ИС10","1ИС21","1ИС22","1ИС30","1ИС30п","Назад");
                propulsion = 1;
            }else if ("Бурильщик".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1БС01","1БС02","1БС11","1БС12","1БС20","1БС30","Назад");
                propulsion = 1;
            }else if ("Электрик".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1МНЭ01","1МНЭ02","1МНЭ11","1МНЭ12","1МНЭ20","1МНЭ20п","1МНЭ30","1МНЭ30п","Назад");
                propulsion = 1;
            }else if ("Механик".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1МЭ01","1МЭ02","1МЭ30","Назад");
                propulsion = 1;
            }else if ("Оператор ДНГ".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1РЭ01","1РЭ02","1РЭ11","1РЭ12","1РЭ13","1РЭ21","1РЭ22","1РЭ23","1РЭ30п","1РЭ31","1РЭ32","Назад");
                propulsion = 1;
            }else if ("Бухгалтер".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите группу: ", "1ЭБ10","1ЭБ20","1ЭБ30","Назад");
                propulsion = 1;
            }
            else if ("Назад".equals(text)) {
                sendReplyKeyboard(chatId, "Выберите специальность: ", "Программист","Бурильщик","Электрик","Механик","Оператор ДНГ","Бухгалтер");
                propulsion = 0;
            }
            else if (propulsion ==1) {
                sendReplyKeyboard(chatId, "Выберите расписание: ", "Расписание на неделю","Расписание на сегодня","Расписание на завтра","Назад");
                text2 = update.getMessage().getText();
                propulsion = 0;
            }
            else if ("Расписание на неделю".equals(text)) {
                 Site site = new Site();
                 site.enterGroup(text2);
                 sendTextMessage(chatId, "Расписание на неделю для "+text2);
                 sendTextMessage(chatId, site.generalInfo());
                 site.quit();
                System.out.println(text);
            }else if ("Расписание на сегодня".equals(text)) {
                Site site = new Site();
                site.enterGroup(text2);
                sendTextMessage(chatId, "Расписание на сегодня для "+text2);
                sendTextMessage(chatId, site.todayInfo());
                site.quit();
            }else if ("Расписание на завтра".equals(text)) {
                Site site = new Site();
                site.enterGroup(text2);
                sendTextMessage(chatId, "Расписание на завтра для "+text2);
                sendTextMessage(chatId, site.tomorrowInfo());
                site.quit();
            } else {
                propulsion = 0;
                sendReplyKeyboard(chatId, "Привет!", "/start");
                if(propulsion ==1){
                    sendReplyKeyboard(chatId, "Выберите специальность: ", "Программист","Бурильщик","Электрик","Механик","Оператор ДНГ","Бухгалтер");
                }
            }
        }
    }
    void sendReplyKeyboard(long chatId, String text, String... buttonLabels) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        int buttonsPerRow = (int) Math.ceil(buttonLabels.length / 2.0);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow currentRow = new KeyboardRow();

        for (int i = 0; i < buttonLabels.length; i++) {
            KeyboardButton button = new KeyboardButton(buttonLabels[i]);
            currentRow.add(button);

            if ((i + 1) % buttonsPerRow == 0 || i == buttonLabels.length - 1) {
                keyboardRows.add(currentRow);
                currentRow = new KeyboardRow();
            }
        }

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendTextMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "cvjfnBot";
    }
    @Override
    public String getBotToken() {
        return "7085851168:AAHqVzEAO4KTjPYdMq2rNMKvz4QNwcxMej0";
    }
}