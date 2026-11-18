package pl.przychod.gittraining.service.operations;

import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Collections;
import java.util.List;

public interface NumberOperation {

    Response<?> execute(Request<?> request);

    default List<Integer> numbers(Request<?> request) {
        Object content = request.getContent();
        if (content == null) {
            return Collections.emptyList();
        }
        if (!(content instanceof List<?> list)) {
            throw new IllegalArgumentException("Request content must be a list of integers");
        }
        return list.stream().map(value -> {
            if (value != null && !(value instanceof Integer)) {
                throw new IllegalArgumentException("Request content must be a list of integers");
            }
            return (Integer) value;
        }).toList();
    }
}
