package my.todo.domain.common;

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

        @Override
        public String getName() {
            return this.getClass().getSimpleName();
        }

        @Override
        public String getMessage() {
            return "Validation error";
        }

    }
}
