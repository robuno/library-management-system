package com.teksen;
/**
 *  <h1> Creates AuthorizedPerson Class! </h1>
 *
 *  The AuthorizedPerson class derived from the Person class. Person class is the
 *  superclass of this class. This class inherits Person and contains private
 *  variables of AuthorizedPerson class.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class AuthorizedPerson extends Person {
    private String staffID; // private instance variable for staffID

    /**
     * AuthorizedPerson Constructor
     * This is the constructor for AuthorizedPerson Class.
     * It calls superclass's constructor to assign variables and also assigns
     * its own variable.
     * @param id is the id of the user
     * @param firstName is the first name of the user
     * @param lastName is the last name of the user
     * @param mail is the email of the user
     * @param address is the address of the user
     * @param password is the password of the user
     * @param personType Student-AuthorizedPerson-Admin type of the user
     * @param staffID id of the staff
     */
    public AuthorizedPerson(int id, String firstName, String lastName, String mail, String address,
                            String password, String personType, String staffID) {
        super(id, firstName, lastName, mail, address, password, personType); // super keyword for inheritance
        this.staffID = staffID; // assign staffID
    }

    // getter
    public String getStaffID() {
        return staffID;
    }

    // setter
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

}
