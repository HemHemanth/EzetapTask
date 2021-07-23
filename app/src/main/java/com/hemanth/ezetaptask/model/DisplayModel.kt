package com.hemanth.ezetaptask.model

import java.io.Serializable

data class DisplayModel(
    val displayData: ArrayList<DisplayData>
)

data class DisplayData(
    val key: String,
    val value: String
): Serializable
