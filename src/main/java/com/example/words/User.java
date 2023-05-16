package com.example.words;

public class User {
    String name, lastname, age, userrol, email, office;

    public User(String name, String lastname, String age, String userrol, String email, String office) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.userrol = userrol;
        this.email = email;
        this.office = office;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getAge(){
        return age;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getUserrol(){
        return userrol;
    }
    public void setUserrol(String userrol){
        this.userrol = userrol;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice(){
        return office;
    }
    public void setOffice(String office){
        this.office = office;
    }
}