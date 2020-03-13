package com.example.web.app.model;

public class User {
    private String name;
    private String surname;
    private String lastname;
    private String gender;
    private String info;

    public User(String name, String surname, String lastname, String gender, String info) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.gender = gender;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}