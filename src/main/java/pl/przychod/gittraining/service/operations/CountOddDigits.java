package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class CountOddDigits {

    public static Response<Long> countOddDigits(Request<List<Integer>> request) {
        Long count = Optional.ofNullable(request.getContent())
                .orElseGet(Collections::emptyList)
                .stream()
                .mapToLong(CountOddDigits::countOddDigits)
                .sum();
        return new Response<>(count);
    }

    private static Long countOddDigits(Integer number) {
        return String.valueOf(Math.abs(number))
                .chars()
                .map(Character::getNumericValue)
                .filter(i -> i % 2 != 0)
                .count();
    }
}
