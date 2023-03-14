package com.widsons.intro.data.model

import com.google.gson.annotations.SerializedName

data class Configuration(

	@field:SerializedName("updated_at")
	var updatedAt: String? = null,

	@field:SerializedName("created_at")
	var createdAt: String? = null,

	@field:SerializedName("pk")
	var pk: Int? = null,

	@field:SerializedName("value")
	var value: String? = null,

	@field:SerializedName("key")
	var key: String? = null
)
