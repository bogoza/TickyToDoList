package com.example.tickytodo.dataClasses

import java.io.Serializable

class HomeData (
    val checkBox: Boolean,
    val color: Int,
    val date: String,
    val description: String
):Serializable