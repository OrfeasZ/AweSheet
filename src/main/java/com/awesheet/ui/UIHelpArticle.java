package com.awesheet.ui;

public class UIHelpArticle extends UIModel {
    protected int id;
    protected String title;
    protected String content;

    public UIHelpArticle(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
