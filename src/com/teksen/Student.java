package com.teksen;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Student extends Person{
    private String studentID;

    public Student(int id, String firstName, String lastName, String mail,
                   String address, String password, String personType,
                   String studentID) {
        super(id, firstName, lastName, mail, address, password, personType);
        this.studentID = studentID;
    }



    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }
}
