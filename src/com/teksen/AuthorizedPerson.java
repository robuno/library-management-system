package com.teksen;

public class AuthorizedPerson extends Person {
    private String staffID;

    public AuthorizedPerson(int id, String firstName, String lastName, String mail, String address,
                            String password, String personType, String staffID) {
        super(id, firstName, lastName, mail, address, password, personType);
        this.staffID = staffID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /*
    @Override
    public String toString() {
        return "AuthorizedPerson{" +"\n"+
                super.toString()+"\n"+
                "staffID='" + staffID + '\'' +
                '}';
    }
     */
}
