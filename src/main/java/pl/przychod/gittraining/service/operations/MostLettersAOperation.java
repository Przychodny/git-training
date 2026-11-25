package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Comparator;

import static pl.przychod.gittraining.service.operations.StringOperationSupport.strings;

@UtilityClass
public class MostLettersAOperation {

    public static Response<String> findStringWithTheMostLettersA(Request<?> request) {
        String result = strings(request)
                .max(Comparator.comparingLong(word -> word.chars().filter(c -> c == 'a').count()))
                .orElseThrow();
        return new Response<>(result);
    }
}
