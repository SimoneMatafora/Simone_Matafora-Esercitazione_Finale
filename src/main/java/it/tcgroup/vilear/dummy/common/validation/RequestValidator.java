package it.tcgroup.vilear.dummy.common.validation;


import it.tcgroup.vilear.dummy.common.exception.BadRequestException;
import it.tcgroup.vilear.dummy.common.exception.Code;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

@Component
public class RequestValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> void validateRequest(T t, Code code) {

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            String message = String.format("'%s': %s", code.value(),
                    Arrays.toString(constraintViolations.stream().map(cv -> String.format("'%s': %s", cv.getPropertyPath(), cv.getMessage())).toArray()));
            throw new BadRequestException(message, code);
        }
    }
}
