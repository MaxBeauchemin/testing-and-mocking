package com.sample.dto

import com.sample.enum.BioClass

data class Animal(
    val id: Int,
    val name: String,
    val bioClass: BioClass
)
