package com.example.my_application_final;

public class Subject {
    private String name;
    private String mentorCount;
    private int requiredYear; // 1. Add the new field

    // 2. Update the constructor to include requiredYear
    public Subject(String name, String mentorCount, int requiredYear) {
        this.name = name;
        this.mentorCount = mentorCount;
        this.requiredYear = requiredYear;
    }

    public String getName() { return name; }
    public String getMentorCount() { return mentorCount; }

    // 3. Add the getter for requiredYear
    public int getRequiredYear() { return requiredYear; }
}

