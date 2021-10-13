package com.marketing.subscription.utils;

import lombok.extern.log4j.Log4j2;

import java.util.ResourceBundle;

@Log4j2
public class MessageProperties {

    public static String findMessage(String key) {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
            return resourceBundle.getString(key);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "Generic Error Message.";
        }
    }
}
