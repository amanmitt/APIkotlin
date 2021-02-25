package com.example.apikotlin.home.data

data class ApiData(
                    val page : Int?,
                   val per_page : Int?,
                   val total : Int?,
                   val total_pages : Int?,
                   val data : List<Data>?,
                   val support : Support?)
{}