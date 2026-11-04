package pl.przychod.gittraining.service.operations;

import lombok.experimental.UtilityClass;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class SmallestNumberOfZeroBitsOperation {

    public static Response<Integer> findIntegerWithTheSmallestNumberOfZeroBits(Request<List<Integer>> request) {
        Integer result = Optional.ofNullable(request.getContent())
                .orElseGet(Collections::emptyList)
                .stream()
                .min(Comparator.comparingLong(SmallestNumberOfZeroBitsOperation::zeroBitCount))
                .orElseThrow();
        return new Response<>(result);
    }

    private static int zeroBitCount(Integer number) {
        if (number == null) {
            return 0;
        }
        return Integer.SIZE - Integer.bitCount(number);
    }
}
