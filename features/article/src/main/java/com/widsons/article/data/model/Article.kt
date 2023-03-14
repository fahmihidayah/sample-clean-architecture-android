package com.widsons.article.data.model

import com.google.gson.annotations.SerializedName

data class Article(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("pk")
	val pk: Int? = null,

	@field:SerializedName("categories")
	val categories: List<Int?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("favorite")
	val favorite: Int? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("is_featured")
	val isFeatured: Boolean? = null,

	@field:SerializedName("is_publish")
	val isPublish: Boolean? = null
)

data class Author(

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
