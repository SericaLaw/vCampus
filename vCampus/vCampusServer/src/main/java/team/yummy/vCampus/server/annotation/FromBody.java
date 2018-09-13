package team.yummy.vCampus.server.annotation;

import java.lang.annotation.*;
import java.util.HashMap;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FromBody {
    Class value() default HashMap.class;
}
