/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        String messageParts[] = message.split(";", 2);

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

    public void onMessage(UIMessage message) {
        // And notify the listeners.
        HashSet<IMessageListener> listenersCopy = new HashSet<IMessageListener>(listeners);

        for (IMessageListener listener : listenersCopy) {
            listener.onMessage(message);
        }
    }

    public void registerListener(IMessageListener listener) {
        listeners.add(listener);
    }

    public void deregisterListener(IMessageListener listener) {
        listeners.remove(listener);
    }
}
