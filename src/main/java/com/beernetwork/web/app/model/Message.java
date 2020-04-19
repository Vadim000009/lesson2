package com.beernetwork.web.app.model;

public class Message {
    private Integer id;
    private String foreignKeySender;
    private String foreignKeyRecipient;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForeignKeySender() {
        return foreignKeySender;
    }

    public void setForeignKeySender(String foreignKeySender) {
        this.foreignKeySender = foreignKeySender;
    }

    public String getForeignKeyRecipient() {
        return foreignKeyRecipient;
    }

    public void setForeignKeyRecipient(String foreignKeyRecipient) {
        this.foreignKeyRecipient = foreignKeyRecipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
