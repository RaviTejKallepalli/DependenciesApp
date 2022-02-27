package com.ravitej.dependenciesapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

// TODO: Should have DTOs and DomainModel objects and we should have converters
@JsonClass(generateAdapter = true)
data class CharacterList(
    val info: Info,
    val results: List<CharacterDetail>
)

@Entity(tableName = "characters")
@JsonClass(generateAdapter = true)
data class CharacterDetail(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any?
)
