package gr.uoi.cs.cs122250.interfaces;

import gr.uoi.cs.cs122250.messages.UIMessage;

public interface IMessageListener {
    void onMessage(UIMessage message);
}
