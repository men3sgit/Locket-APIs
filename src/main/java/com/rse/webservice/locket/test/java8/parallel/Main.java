package com.rse.webservice.locket.test.java8.parallel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = generateEmployee();

        long start2 = System.currentTimeMillis();
        double value = employees.stream().mapToDouble(Employee::getAge).average().getAsDouble();
        long end2 = System.currentTimeMillis();

        System.out.println("Plain: " + (end2 - start2) +" " + value);

        long start1 = System.currentTimeMillis();
       value =  employees.parallelStream().mapToDouble(Employee::getAge).average().getAsDouble();
        long end1 = System.currentTimeMillis();
        System.out.println("Stream: " + (end1 - start1)+" " + value);
    }

    public static List<Employee> generateEmployee() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 100000000; i++) {
            employees.add(new Employee(i));
        }
        return employees;
    }
}

class Employee {

    private String name;
    private double salary;
    private int age;

    public Employee(int age) {
        this.age = age;
    }

    public String toString() {
        return "E " + age;
    }

    public int getAge() {
        return age;
    }
}
