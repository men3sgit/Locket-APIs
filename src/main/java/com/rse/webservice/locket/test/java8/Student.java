package com.rse.webservice.locket.test.java8;

import lombok.Data;

import java.util.List;
@Data
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private List<Score> scores;

    private Gender gender;


    public enum Gender {
        MALE, FEMALE
    }

}
