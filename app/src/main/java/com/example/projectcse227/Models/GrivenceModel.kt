package com.example.projectcse227.Models

class GrivenceModel {


    var address: String? = null
    var category: String? = null
    var date: String? = null
    var fullname: String? = null
    var grivence: String? = null
    var time: String? = null
    var ward: String? = null

    constructor()
    constructor(
        address: String?,
        category: String?,
        date: String?,
        fullname: String?,
        grivence: String?,
        time: String?,
        ward: String?
    ) {
        this.address = address
        this.category = category
        this.date = date
        this.fullname = fullname
        this.grivence = grivence
        this.time = time
        this.ward = ward
    }
}