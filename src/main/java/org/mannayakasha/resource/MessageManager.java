package org.mannayakasha.resource;

import java.util.ResourceBundle;

/**
 * Message manager for logicMessages.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("logicMessages");

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}