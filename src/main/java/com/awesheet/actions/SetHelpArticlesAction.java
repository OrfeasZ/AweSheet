package com.awesheet.actions;

import com.awesheet.enums.UIActionType;
import com.awesheet.ui.UIHelpArticle;

import java.util.HashMap;

public class SetHelpArticlesAction extends UIAction {
    protected HashMap<Integer, UIHelpArticle> articles;

    public SetHelpArticlesAction(HashMap<Integer, UIHelpArticle> articles) {
        super(UIActionType.SET_HELP_ARTICLES);
        this.articles = articles;
    }
}
