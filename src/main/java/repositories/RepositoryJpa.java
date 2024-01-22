package repositories;

import static java.lang.annotation.ElementType.*;

import jakarta.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE})
public @interface RepositoryJpa {
}
