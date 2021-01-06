package com.example.peopleinteractive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("age")
    @Expose
    private int age;
}
