package com.example.carousell.data.remote.dto

data class CarousellDto(
    val banner_url: String,
    val description: String,
    val id: String,
    val rank: Int,
    var time_created: Int,
    val title: String,
    var currentTime: String

){
    constructor(banner_url: String, description: String, id: Int, rank: Int, time_created: Int, title: String)
    :this("","", "", Int.MIN_VALUE, Int.MIN_VALUE, "", "")
}