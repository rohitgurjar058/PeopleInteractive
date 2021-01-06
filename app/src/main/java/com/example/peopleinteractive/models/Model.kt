package com.example.peopleinteractive.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("results")
    @Expose
    val results: List<Result>,

    @SerializedName("info")
    @Expose
    val info: Info
)