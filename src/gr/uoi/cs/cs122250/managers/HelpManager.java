package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.models.HelpArticle;

import java.util.HashSet;

public class HelpManager implements IMessageListener {

    protected HashSet<HelpArticle> articles;

    HelpManager() {
        this.articles = new HashSet<HelpArticle>();
    }

    public String getOnlineURL() {
        return "";
    }

    public HashSet<HelpArticle> getHelpArticles() {
        return this.articles;
    }

    public HelpArticle getHelpArticle(int id) {
        return null;
    }

    @Override
    public void onMessage(String message, Object data) {

    }
}
