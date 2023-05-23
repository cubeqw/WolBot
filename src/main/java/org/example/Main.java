package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;


public class Main extends TelegramLongPollingBot {

    static TelegramBotsApi telegramBotsApi = null;

    public static void main(String[] args) {

        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Main());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            String chatId = message.getChatId().toString();
            sendMessage.setChatId(chatId);
            String text = message.getText();
            if (text.equals("/start")) {
                try {
                    Runtime.getRuntime().exec("wakeonlan 9C:82:B9:95:09:13");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setText("Запущено");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "wakeonlbot";
    }

    @Override
    public String getBotToken() {
        return "5956412398:AAE2ggB7nhxSTg8YuYDxOsdww1_Mhv9aXi8";
    }
}