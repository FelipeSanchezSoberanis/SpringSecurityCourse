package com.example.demo.Student;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Student {

    private final Integer studentId;
    private final String studentName;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

