package fontys.sem3.individual_track.annotation;

import com.auth0.jwt.interfaces.Payload;

public @interface ValidateRole {

    String[] acceptedValues();

    String message() default "{uk.dds.ideskos.validator.ValidateString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
