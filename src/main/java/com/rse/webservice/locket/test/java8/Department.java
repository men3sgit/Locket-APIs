package com.rse.webservice.locket.test.java8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Department {
    private int id;
    private String name;
    private List<Student> students = new ArrayList<>();


}
