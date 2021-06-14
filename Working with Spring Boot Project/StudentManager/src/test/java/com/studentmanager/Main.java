package com.studentmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.*;
import com.studentmanager.entity.Student;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        List<Object> studentList = Arrays.asList(
                new Student(1L, "John", "US", LocalDate.parse("2000-11-05"), "peothach@gmail.com", "+84866872164",
                        "F", "user1", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10")),
                new Student(1L, "Smith", "UK", LocalDate.parse("1999-05-08"), "smith@gmail.com", "+84866872164",
                        "F", "user1", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10"))
        );

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
            }
        }).create();
        System.out.println(gson.toJson(studentList));

    }
}
