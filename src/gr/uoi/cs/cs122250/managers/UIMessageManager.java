package gr.uoi.cs.cs122250.managers;

import com.google.gson.Gson;
import gr.uoi.cs.cs122250.enums.UIActionType;
import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.messages.UIMessage;
import org.cef.browser.CefBrowser;

import java.util.HashSet;

public class UIMessageManager {
    private class UIAction {
        private UIActionType type;
        private Object data;

        public UIAction(UIActionType type, Object data) {
            this.type = type;
            this.data = data;
        }
    }

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

    public void dispatchAction(UIActionType type, Object data) {
        UIAction action = new UIAction(type, data);
        String serializedAction = gson.toJson(action);

        // Dispatch action to UI.
        cefBrowser.executeJavaScript("window.store.dispatch(" + serializedAction + ");", "", 0);
    }

    public void onMessage(String message) {
        String[] messageParts = message.split(",", 2);

        if (messageParts.length != 2) {
            return;
        }

        UIMessage deserializedMessage;

        try {
            deserializedMessage = (UIMessage) gson.fromJson(messageParts[1], Class.forName(messageParts[0]));
        } catch (ClassNotFoundException e) {
            return;
        }

        for (IMessageListener listener : listeners) {
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
