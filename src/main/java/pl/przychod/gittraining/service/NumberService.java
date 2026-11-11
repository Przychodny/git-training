package pl.przychod.gittraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;
import pl.przychod.gittraining.service.operations.NumberOperation;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NumberService {

    private final Map<String, NumberOperation> operations;

    public Response<?> execute(Request<?> request, String operation) {
        NumberOperation selectedOperation = operations.get(operation);
        if (selectedOperation == null) {
            throw new IllegalArgumentException("Operation not supported");
        }
        return selectedOperation.execute(request);
    }
}
