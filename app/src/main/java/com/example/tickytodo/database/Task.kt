package com.example.tickytodo.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val checkbox: Boolean,
    val description: String,
    val color: Int,
    val date: String
): Parcelable