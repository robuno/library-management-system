package com.teksen;

public class Admin extends AuthorizedPerson {
    static boolean advancedOptions = true;

    public Admin(int id, String firstName, String lastName, String mail,
                 String address, String password, String personType,
                 String staffID) {
        super(id, firstName, lastName, mail, address, password, personType, staffID);
    }

    public boolean isAdvancedOptions() {
        return advancedOptions;
    }

    /*
    @Override
    public String toString() {
        return super.toString()+"\n"+
                isAdvancedOptions();
    }

     */
}
