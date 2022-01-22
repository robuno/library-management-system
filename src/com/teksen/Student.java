package com.teksen;
/**
 *  <h1> Creates Student Class! </h1>
 *
 *  The Student class derived from the Person class. Person class is the
 *  superclass of this class. This class inherits Person and contains private
 *  variables of Student class.
 *
 *  @author Humeyra Dogus - Unat Teksen
 *  @version 1.0
 *  @since 2021-12-25
 */
public class Student extends Person{
    private String studentID; // private instance variable for studentID

    /**
     * Student Constructor
     * This is the constructor for Student Class.
     * It calls superclass's constructor to assign variables and also assigns
     * its own variable.
     * @param id is the id of the user
     * @param firstName is the first name of the user
     * @param lastName is the last name of the user
     * @param mail is the email of the user
     * @param address is the address of the user
     * @param password is the password of the user
     * @param personType Student-AuthorizedPerson-Admin type of the user
     * @param studentID is the id of the student
     */
    public Student(int id, String firstName, String lastName, String mail,
                   String address, String password, String personType,
                   String studentID) {
        super(id, firstName, lastName, mail, address, password, personType); // super keyword for inheritance
        this.studentID = studentID; // assign studentID
    }
    // setter
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // getter
    public String getStudentID() {
        return studentID;
    }
}
