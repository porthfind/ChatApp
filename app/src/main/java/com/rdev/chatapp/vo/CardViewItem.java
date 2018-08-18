package com.rdev.chatapp.vo;

/**
 * Created by ritadacostaferreira on 16/08/18.
 */

public class CardViewItem {

    private long id;
    private long userId;
    private String content;
    private String titulo;
    private String url;
    private String thumbnailurl;

    public CardViewItem() {
    }

    public CardViewItem(long id, long userId, String content, String titulo, String url, String thumbnailurl) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.titulo = titulo;
        this.url = url;
        this.thumbnailurl = thumbnailurl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }
}
