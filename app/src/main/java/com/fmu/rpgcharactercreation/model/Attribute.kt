package com.fmu.rpgcharactercreation.model

import androidx.room.ColumnInfo

data class Attribute(
    @ColumnInfo(name = "attribute_name")
    val name: String,

    @ColumnInfo(name = "attribute_value")
    val value: Int
)
