package com.teksen;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Student extends Person{
    private int studentID;

    public Student(int id, String firstName, String lastName, String mail,
                   String address, String password, String personType,
                   int studentID) {
        super(id, firstName, lastName, mail, address, password, personType);
        this.studentID = studentID;
    }



    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }
}
