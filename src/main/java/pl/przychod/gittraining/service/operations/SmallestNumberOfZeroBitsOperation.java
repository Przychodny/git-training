package pl.przychod.gittraining.service.operations;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.Comparator;

@Service("biggest-number-with-the-biggest-number-of-zero-bits")
public class SmallestNumberOfZeroBitsOperation implements NumberOperation {

    @Override
    public Response<Integer> execute(Request<?> request) {
        Integer result = numbers(request)
                .stream()
                .min(Comparator.comparingLong(this::zeroBitCount))
                .orElseThrow();
        return new Response<>(result);
    }

    private int zeroBitCount(Integer number) {
        if (number == null) {
            return 0;
        }
        return Integer.SIZE - Integer.bitCount(number);
    }
}
