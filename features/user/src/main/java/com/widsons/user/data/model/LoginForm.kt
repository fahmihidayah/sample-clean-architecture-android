package com.widsons.user.data.model

import com.widsons.user.R
import com.widsons.validator.FormValidatable
import com.widsons.validator.ValidateField
import com.widsons.validator.annotation.Email
import com.widsons.validator.annotation.Min
import com.widsons.validator.annotation.TargetView
import com.widsons.validator.rules.EmailRule
import com.widsons.validator.rules.MinLengthRule

class LoginForm(

    var username : String = "",
    var password : String = ""
) : FormValidatable {
    var fields = listOf(
        ValidateField(
            fieldName = "username",
            viewId = R.id.textInputLayoutEmail,
            error = "Username not valid",
            rules = mutableListOf(
                EmailRule()
            )
        ),
        ValidateField(
            fieldName = "password",
            viewId = R.id.textInputLayoutPassword,
            error = "Password min 8",
            rules = mutableListOf(
                MinLengthRule(min = 8)
            )
        )
    )
    override fun getValidateFields() = fields

}