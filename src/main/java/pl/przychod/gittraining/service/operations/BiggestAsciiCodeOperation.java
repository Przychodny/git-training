package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Comparator;

import static pl.przychod.gittraining.service.operations.StringOperationSupport.strings;

@UtilityClass
public class BiggestAsciiCodeOperation {

    public static Response<String> findStringWithTheBiggestAsciiCode(Request<?> request) {
        String result = strings(request)
                .max(Comparator.comparingInt(word -> word.chars().sum()))
                .orElseThrow();
        return new Response<>(result);
    }
}
