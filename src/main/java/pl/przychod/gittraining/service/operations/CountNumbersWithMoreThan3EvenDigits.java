package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class CountNumbersWithMoreThan3EvenDigits {

    public static Response<Long> countNumbersWithMoreThan3EvenDigits(Request<List<Integer>> request) {
        Long counter = Optional.ofNullable(request.getContent())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(i -> getEvenDigits(i) > 3)
                .count();
        return new Response<>(counter);
    }

    private static Long getEvenDigits(Integer number) {
        return String.valueOf(number)
                .chars()
                .map(Character::getNumericValue)
                .filter(c -> c % 2 == 0)
                .count();
    }
}
