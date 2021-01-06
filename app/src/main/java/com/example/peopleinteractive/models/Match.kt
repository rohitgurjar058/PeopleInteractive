package com.example.peopleinteractive.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "matches")
data class Match(

    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "image")
    // used to map image from the JSON to imgSrcUrl in our class
    @Json(name = "image") val imgSrcUrl: String,

    var isAccepted: Boolean,
    var isDeclined: Boolean
)

fun List<Match>.asDomainModel(): List<Match> {
    return map {
        Match(id = it.id, imgSrcUrl = it.imgSrcUrl,
        isAccepted = it.isAccepted, isDeclined = it.isDeclined)
    }
}