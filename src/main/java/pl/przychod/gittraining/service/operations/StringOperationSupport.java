package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@UtilityClass
class StringOperationSupport {

    static Stream<String> strings(Request<?> request) {
        Object content = request.getContent();
        if (content == null) {
            return Stream.empty();
        }
        if (!(content instanceof List<?> list)) {
            throw new IllegalArgumentException("Request content must be a list of strings");
        }
        return list.stream()
                .filter(Objects::nonNull)
                .map(value -> {
                    if (!(value instanceof String string)) {
                        throw new IllegalArgumentException("Request content must be a list of strings");
                    }
                    return string;
                });
    }
}
