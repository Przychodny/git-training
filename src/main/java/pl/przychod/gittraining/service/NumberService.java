package pl.przychod.gittraining.service;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.List;

import static pl.przychod.gittraining.service.operations.BiggestSumOfBitsOperation.findIntegerWithTheBiggestSumOfBits;
import static pl.przychod.gittraining.service.operations.SmallestNumberOfZeroBitsOperation.findIntegerWithTheSmallestNumberOfZeroBits;

@Service
public class NumberService {

    public Response<?> execute(Request<?> request, String operation) {
        return switch (operation) {
            case "biggets-sum-of-bits" -> findIntegerWithTheBiggestSumOfBits(integers(request));
            case "biggest-number-with-the-biggest-number-of-zero-bits" ->
                    findIntegerWithTheSmallestNumberOfZeroBits(integers(request));
            default -> throw new IllegalArgumentException("Operation not supported");
        };
    }

    @SuppressWarnings("unchecked")
    private static Request<List<Integer>> integers(Request<?> request) {
        return (Request<List<Integer>>) request;
    }
}
