package com.awesheet.models;

import com.awesheet.interfaces.IUIBindable;
import com.awesheet.ui.UIHelpArticle;
import com.awesheet.ui.UIModel;

public class HelpArticle implements IUIBindable {
    protected String content;
    protected String title;
    protected int id;

    public HelpArticle(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return id;
    }

    @Override
    public UIModel bind() {
        return new UIHelpArticle(id, title, content);
    }
}
