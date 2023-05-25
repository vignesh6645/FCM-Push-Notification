package com.example.PushNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    @Autowired
    FCMService fcmService;

    private String token="your Fcm token";
    @Scheduled(fixedDelay = 5000)
    public void sendPushNotificationWithData() {
        PushNotificationRequest pushNotificationRequest=new PushNotificationRequest();
        pushNotificationRequest.setMessage("Push notifications from Spring Boot server");
        pushNotificationRequest.setTitle("Push Notification");
        pushNotificationRequest.setToken(token);
        Map<String, String> appData= new HashMap<>();
        appData.put("name", "PushNotification");
        try {
            fcmService.sendMessage(appData, pushNotificationRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
