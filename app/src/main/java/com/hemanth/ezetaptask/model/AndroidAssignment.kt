package com.hemanth.ezetaptask.model

data class AndroidAssignment(
    val logo_url: String,
    val heading_text: String,
    val uidata: ArrayList<UiData>
)

data class UiData(
    var uitype: String = "",
    var value: String = "",
    var key: String = "",
    var hint: String = ""
)
