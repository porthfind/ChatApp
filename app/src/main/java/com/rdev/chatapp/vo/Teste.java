package com.rdev.chatapp.vo;

import java.util.List;

/**
 * Created by ritadacostaferreira on 15/08/18.
 */

public class Teste {

    private long idUser;
    private String name;
    private String avatarId;
    private long idMessage;
    private String content;
    private List<Attachment> attach;

    public Teste(long idUser, String name, String avatarId, long idMessage, String content, List<Attachment> attach) {
        this.idUser = idUser;
        this.name = name;
        this.avatarId = avatarId;
        this.idMessage = idMessage;
        this.content = content;
        this.attach = attach;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attachment> getAttach() {
        return attach;
    }

    public void setAttach(List<Attachment> attach) {
        this.attach = attach;
    }
}
