package com.teksen;

public class Admin extends AuthorizedPerson {
    private boolean advancedOptions;

    public Admin(int id, String firstName, String lastName, String mail,
                 String address, String password, String personType,
                 String staffID, boolean advancedOptions) {
        super(id, firstName, lastName, mail, address, password, personType, staffID);
        this.advancedOptions = advancedOptions;
    }


    /*
    @Override
    public String toString() {
        return super.toString()+"\n"+
                isAdvancedOptions();
    }

     */
}
