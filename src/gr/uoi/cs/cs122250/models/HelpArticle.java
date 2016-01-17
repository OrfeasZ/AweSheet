package gr.uoi.cs.cs122250.models;

public class HelpArticle {
    protected String content;
    protected String title;
    protected int id;

    HelpArticle(int id, String title, String content){
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
}
