package com.widsons.validator.sampleObject

import com.widsons.validator.FormValidatable
import com.widsons.validator.ValidateField
import com.widsons.validator.annotation.*
import com.widsons.validator.rules.*

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
class SignUpForm(
//    @Email(error = "Fill valid email")
//    @TargetView(targetId = 1)
//    @ErrorTargetView(errorTargetId = 1)
    var email : String,

//    @NotEmpty(error = "Name should not be empty")
    var name : String,

//    @Min(error = "Password should not be empty", min = 5)
    var password : String,

//    @Equal(error = "Confirm password should not be empty", referenceField = "password")
    var confirmPassword : String,

//    @RequiredChecked(error = "Require check")
    var isAgree : Boolean = false
) : FormValidatable {
    override fun getValidateFields(): List<ValidateField> {
        return listOf(
            ValidateField(
                fieldName = "name",
                viewId = 1,
                error = "Name should not be empty",
                rules = mutableListOf(
                    NotEmptyRule()
                )
            ),
            ValidateField(
                fieldName = "email",
                viewId = 1,
                error = "email should not be empty",
                rules = mutableListOf(
                    EmailRule()
                )
            ),
            ValidateField(
                fieldName = "password",
                viewId = 1,
                error = "password should not be empty",
                rules = mutableListOf(
                    MinLengthRule(min = 5)
                )
            ),
            ValidateField(
                fieldName = "confirmPassword",
                viewId = 1,
                error = "confirm password should not be empty",
                rules = mutableListOf(
                    EqualRule(equalField = password)
                )
            ),
            ValidateField(
                fieldName = "isAgree",
                viewId = 1,
                error = "You should agree with term and condition",
                rules = mutableListOf(
                    ReqTrueRule()
                )
            )
        )
    }
}