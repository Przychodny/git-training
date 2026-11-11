package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class BiggestSumOfBitsOperation {

    public static Response<Integer> findIntegerWithTheBiggestSumOfBits(Request<List<Integer>> request) {
        Integer result = Optional.ofNullable(request.getContent())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(n -> Integer.bitCount(n) % 2 == 0)
                .min(Comparator.comparingInt(Integer::bitCount))
                .orElseThrow();
        return new Response<>(result);
    }
}
