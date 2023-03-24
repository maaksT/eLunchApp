package pl.makst.elunchapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodConstraintValidator.class)
@Documented
public @interface PeriodConstraint {
    String message() default "{pl.makst.elunchapp.PeriodConstraint}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
