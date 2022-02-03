package java.todo.domain.common;

import java.util.Objects;
import java.util.Optional;

public class Either<Error, Data> {

    private final Data data;

    private final Error error;

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
