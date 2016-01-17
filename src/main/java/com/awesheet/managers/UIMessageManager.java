package com.awesheet.managers;

import com.awesheet.actions.UIAction;
import com.awesheet.interfaces.IMessageListener;
import com.google.gson.Gson;
import com.awesheet.enums.UIActionType;
import com.awesheet.messages.UIMessage;
import org.cef.browser.CefBrowser;

import java.util.HashSet;
import java.util.Iterator;

public class UIMessageManager {
    private static UIMessageManager instance;

    protected HashSet<IMessageListener> listeners;

    protected CefBrowser cefBrowser;

    protected Gson gson;

    public static UIMessageManager getInstance() {
        if (instance == null) {
            instance = new UIMessageManager();
        }

        return instance;
    }

    protected UIMessageManager() {
        listeners = new HashSet<IMessageListener>();
        gson = new Gson();
    }

    public void setBrowser(CefBrowser browser) {
        cefBrowser = browser;
    }

    public void dispatchAction(UIAction action) {
        // Serialize to JSON.
        String serializedAction = gson.toJson(action);

        // Dispatch action to UI.
        cefBrowser.executeJavaScript("window.store.dispatch(" + serializedAction + ");", "", 0);
    }

    public void onMessage(String message) {
        // Separate message type and data.
        String[] messageParts = message.split(";", 2);

        if (messageParts.length != 2) {
            return;
        }

        // Deserialize the message.
        UIMessage deserializedMessage;

        try {
            deserializedMessage = (UIMessage) gson.fromJson(messageParts[1], Class.forName(messageParts[0]));
        } catch (ClassNotFoundException e) {
            return;
        }

        // And notify the listeners.
        HashSet<IMessageListener> listenersCopy = new HashSet<IMessageListener>(listeners);

        for (IMessageListener listener : listenersCopy) {
            listener.onMessage(deserializedMessage);
        }
    }

    public void registerListener(IMessageListener listener) {
        listeners.add(listener);
    }

    public void deregisterListener(IMessageListener listener) {
        listeners.remove(listener);
    }
}
