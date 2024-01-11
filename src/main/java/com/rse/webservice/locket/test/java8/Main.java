package com.rse.webservice.locket.test.java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(createStudent("John", "Doe", Student.Gender.MALE, createSampleScores()));
        students.add(createStudent("Jane", "Smith", Student.Gender.FEMALE, createSampleScores()));
        students.add(createStudent("Bob", "Johnson", Student.Gender.MALE, createSampleScores()));
        students.add(createStudent("Alice", "Williams", Student.Gender.FEMALE, createSampleScores()));
        students.add(createStudent("Charlie", "Brown", Student.Gender.MALE, createSampleScores()));
        students.add(createStudent("Eva", "Miller", Student.Gender.FEMALE, createSampleScores()));
        students.add(createStudent("David", "Lee", Student.Gender.MALE, createSampleScores()));
        students.add(createStudent("Grace", "Jones", Student.Gender.FEMALE, createSampleScores()));
        students.add(createStudent("Samuel", "Moore", Student.Gender.MALE, createSampleScores()));
        students.add(createStudent("Sophia", "Clark", Student.Gender.FEMALE, createSampleScores()));


        Map<String, Integer> map = new HashMap<>();
        map.put("Men", 100);
        map.put("Linh", 20);
        map.put("Cuong", 10);
        map.put("Hoai", 20);
        map.put("Nam", 20);
        map.put("Hieu", 300);
        map.put("Phat", 24);
        map.put("Han", 1000);
        map.put("My", 420);


    }


    private static Student createStudent(String firstName, String lastName, Student.Gender gender, List<Score> scores) {
        Student student = new Student();
        student.setId(1); // Set an appropriate ID
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender);
        student.setScores(scores);

        return student;
    }

    private static List<Score> createSampleScores() {
        Score score1 = createScore("Math", 90.5);
        Score score2 = createScore("English", 85.0);

        return Arrays.asList(score1, score2);
    }

    private static Score createScore(String name, double value) {
        Score score = new Score();
        score.setId(1); // Set an appropriate ID
        score.setName(name);
        score.setValue(value);

        return score;
    }
}
