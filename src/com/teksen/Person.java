package com.teksen;
/**
 * <h1> Creates Person Class! </h1>
 *
 * The person class has variables to hold the person's id, first name, last name,
 * mail, address, password and person type. These variables are all common for
 * Admin, AuhorizedPerson and Student classes.
 *
 * @author Humeyra Dogus - Unat Teksen
 * @version 1.0
 * @since 2021-12-25
 *
 */
public abstract class Person {
    private int id; // private instance variable for id
    private String firstName; // private instance variable for firstName
    private String lastName; // private instance variable for lastName
    private String mail; // private instance variable for mail
    private String address; // private instance variable for address
    private String password; // private instance variable for password
    private String personType; // private instance variable for personType

    // create Person constructor
    public Person(int id, String firstName, String lastName, String mail,
                  String address, String password, String personType) {
        this.id = id; // assign id
        this.firstName = firstName;  // assign firstName
        this.lastName = lastName;  // assign lastName
        this.mail = mail;  // assign mail
        this.address = address;  // assign address
        this.password = password;  // assign password
        this.personType = personType;  // assign personType
    }

    // setters
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

    // getters
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
