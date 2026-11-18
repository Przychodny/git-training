package pl.przychod.gittraining.service.operations;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

@Service("count-odd-digits")
public class CountOddDigits implements NumberOperation {

    @Override
    public Response<Long> execute(Request<?> request) {
        Long count = numbers(request)
                .stream()
                .mapToLong(this::countOddDigits)
                .sum();
        return new Response<>(count);
    }

    private Long countOddDigits(Integer number) {
        return String.valueOf(Math.abs(number))
                .chars()
                .map(Character::getNumericValue)
                .filter(i -> i % 2 != 0)
                .count();
    }
}
