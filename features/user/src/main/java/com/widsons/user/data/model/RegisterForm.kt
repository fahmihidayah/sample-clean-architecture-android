package com.widsons.user.data.model

import com.widsons.user.R
import com.widsons.validator.FormValidatable
import com.widsons.validator.ValidateField
import com.widsons.validator.rules.EmailRule

class RegisterForm(
    var email : String = "",
    var password : String = "",
    var confirmPassword : String = "",
    var firstName : String = "",
    var lastName: String = ""
) : FormValidatable {
    var fields = mutableListOf(
        ValidateField(
            fieldName = "email",
            viewId = R.id.textInputLayoutEmail,
            error = "Username not valid",
            rules = mutableListOf(
                EmailRule()
            )

        )
    )
    override fun getValidateFields() = fields
}