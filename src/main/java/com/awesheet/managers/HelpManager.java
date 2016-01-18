package com.awesheet.managers;

import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;
import com.awesheet.models.HelpArticle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class HelpManager implements IMessageListener {
    private static HelpManager instance = null;

    protected HashMap<Integer, HelpArticle> articles;

    public static HelpManager getInstance() {
        if (instance == null) {
            instance = new HelpManager();
        }

        return instance;
    }

    protected HelpManager() {
        articles = new HashMap<Integer, HelpArticle>();

        UIMessageManager.getInstance().registerListener(this);
        // TODO: Help articles.
    }

    public String getOnlineURL() {
        // TODO
        return "http://google.com";
    }

    public Collection<HelpArticle> getHelpArticles() {
        return articles.values();
    }

    public HelpArticle getHelpArticle(int id) {
        if (!articles.containsKey(id)) {
            return null;
        }

        return articles.get(id);
    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
