package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.models.HelpArticle;

import java.util.HashSet;

public class UIMessageManager {

    protected HashSet<IMessageListener> listeners;

    UIMessageManager() {
        this.listeners = new HashSet<IMessageListener>();
    }

    public void dispatchMessage(String message, Object data) {

    }

    public void registerListener(IMessageListener listener) {
        this.listeners.add(listener);
    }

    public void deregisterListener(IMessageListener listener) {
        this.listeners.remove(listener);
    }
}
