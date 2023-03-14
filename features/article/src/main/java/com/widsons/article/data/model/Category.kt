package com.widsons.article.data.model

import com.google.gson.annotations.SerializedName

data class Category(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("pk")
	val pk: Int? = null
)
