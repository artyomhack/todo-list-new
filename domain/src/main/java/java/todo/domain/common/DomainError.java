package java.todo.domain.common;

public interface DomainError extends DomainData {
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
            return null;
        }

        @Override
        public String getMessage() {
            return null;
        }
    }
}
