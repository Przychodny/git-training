package pl.przychod.gittraining.service.operations;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Comparator;
import java.util.Objects;

@Service("biggets-sum-of-bits")
public class BiggestSumOfBitsOperation implements NumberOperation {

    @Override
    public Response<Integer> execute(Request<?> request) {
        Integer result = numbers(request)
                .stream()
                .filter(Objects::nonNull)
                .filter(n -> Integer.bitCount(n) % 2 == 0)
                .min(Comparator.comparingInt(Integer::bitCount))
                .orElseThrow();
        return new Response<>(result);
    }
}
