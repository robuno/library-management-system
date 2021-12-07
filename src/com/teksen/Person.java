package com.teksen;

public abstract class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String address;
    private String password;
    private String personType;


    public Person(int id, String firstName, String lastName, String mail,
                  String address, String password, String personType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
        this.password = password;
        this.personType = personType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }



    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPersonType() {
        return personType;
    }
}
