package pl.przychod.gittraining.service.operations;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

@Service("count-numbers-with-more-than-3-even-digits")
public class CountNumbersWithMoreThan3EvenDigits implements NumberOperation {

    @Override
    public Response<Long> execute(Request<?> request) {
        Long counter = numbers(request)
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
