package com.daffa.listsub

import java.io.Serializable

data class DataModel(
    val seri: String,
    val cpu: String,
    val os: String,
    val display: String,
    val chipset: String,
    val graphics: String,
    val memory: String,
    val drive: String
    ,
    val webcam: String,
    val keyboard: String,
    val connect: String,
    val jack: String,
    val adapter: String,
    val dimension: String,
    val port: String,
    val image: String,
    val support: String
) : Serializable