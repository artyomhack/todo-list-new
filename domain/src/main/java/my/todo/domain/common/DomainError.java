package my.todo.domain.common;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public interface DomainError {

    String getName();
    String getMessage();


    class BadRequest implements DomainError {

        @Override
        public String getName() {
            return this.getClass().getSimpleName();
        }

        @Override
        public String getMessage() {
            return "Bad request";
        }
    }

    class NotFound implements DomainError {

        @Override
        public String getName() {
            return this.getClass().getSimpleName();
        }

        @Override
        public String getMessage() {
            return "Not found";
        }
    }

    class ValidationError implements DomainError {
        private final Map<String, String> errors;

        public ValidationError(Map<String, String> errors) {
            this.errors = errors;
        }

        @Override
        public String getName() {
            return this.getClass().getSimpleName();
        }

        @Override
        public String getMessage() {
            return "Validation error";
        }

        public Map<String, String> getErrors() {
            return Objects.isNull(errors) || errors.isEmpty()
                    ? null
                    : Collections.unmodifiableMap(errors);
        }

    }
}
