package ru.khinkal.springDemo.learning.lesson05validation

import jakarta.validation.Validator

class RegistrationValidationService(
    private val validator: Validator,
) {

    fun validate(request: RegistrationRequest): List<FieldViolation> {
        TODO("Lesson 05: validate RegistrationRequest and map violations")
    }
}
