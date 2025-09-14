package umc8th.spring.validation.anntation;


import jakarta.validation.Constraint;
import umc8th.spring.validation.validator.StoreExistValidator;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = StoreExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {

}