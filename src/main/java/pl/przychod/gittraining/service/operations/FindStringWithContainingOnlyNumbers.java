package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.List;
import java.util.Objects;

import static pl.przychod.gittraining.service.operations.StringOperationSupport.strings;

@UtilityClass
public class FindStringWithContainingOnlyNumbers {

    public static Response<List<String>> findStringsWithContainingOnlyNumbers(Request<?> request) {
        List<String> result = strings(request)
                .filter(Objects::nonNull)
                .filter(FindStringWithContainingOnlyNumbers::hasOnlyNumbers)
                .toList();
        return new Response<>(result);
    }

    private static boolean hasOnlyNumbers(String string) {
        return string.matches("^-?\\d+(\\.\\d+)?$");
    }
}
