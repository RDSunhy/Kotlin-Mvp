package com.example.kotlin_mvp.bean

import java.io.Serializable

data class ImagesBean(
    val error: Boolean,
    val results: List<Result>
): Serializable{
    data class Result(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String
    ): Serializable
}

