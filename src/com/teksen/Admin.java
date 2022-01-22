package com.teksen;
/**
 *  <h1> Creates Admin Class! </h1>
 *
 *  The Admin class derived from the AuthorizedPerson class. Admin class is the
 *  child class of AuthorizedPerson class. This class inherits Person, AuthorizedPerson
 *  and contains private variables of Admin class.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */

public class Admin extends AuthorizedPerson {
    private boolean advancedOptions; // private instance variable for advancedOptions

    /**
     * Admin Constructor
     * This is the constructor for Admin Class.
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
     * @param advancedOptions access option for admin instances
     */
    public Admin(int id, String firstName, String lastName, String mail,
                 String address, String password, String personType,
                 String staffID, boolean advancedOptions) {
        super(id, firstName, lastName, mail, address, password, personType, staffID); // super keyword for inheritance
        this.advancedOptions = advancedOptions; // assign advancedOptions
    }
    // getter
    public boolean isAdvancedOptions() {
        return advancedOptions;
    }

    // setter
    public void setAdvancedOptions(boolean advancedOptions) {
        this.advancedOptions = advancedOptions;
    }
}
