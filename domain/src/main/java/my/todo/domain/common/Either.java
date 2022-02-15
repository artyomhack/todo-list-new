package my.todo.domain.common;

public class Either<Error extends DomainError, Data> {

    private final Data data;

    private final Error error;

    public Either(Error error) {
        this(error, null);
    }

    public Either(Data data) {
        this(null, data);
    }

    public Either( Error error, Data data ) {
        this.data = data;
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

}
