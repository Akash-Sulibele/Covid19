package com.example.covid19app.models


data class Timeline(
    var cases: LinkedHashMap<String,Int>?,
    var deaths: LinkedHashMap<String,Int>?,
    var recovered: LinkedHashMap<String,Int>?
)