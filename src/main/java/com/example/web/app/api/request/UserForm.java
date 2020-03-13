package com.example.web.app.api.request;

public class UserForm {
    private Integer id;
    private String name;
    private String middlename;
    private String surname;
    private String gender;
    private String info;

    public UserForm(){}

    public UserForm(Integer id, String name, String middlename, String surname, String gender, String info) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.gender = gender;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
