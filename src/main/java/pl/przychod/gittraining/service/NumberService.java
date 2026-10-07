package pl.przychod.gittraining.service;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Numbers;

import java.util.*;

@Service
public class NumberService {

    //Napisz metodę, która dla przekazanej listy Integerów znajduje tego, \
    // którego liczba bitów zerowych jest najmniejsza.

    public Object execute(Numbers numbers, String operation) {
        if ("biggets-sum-of-bits".equals(operation)) {
            return findIntegerWithTheBiggestSumOfBits(numbers);
        } else if ("biggest-number-with-the-biggest-number-of-zero-bits".equals(operation)) {
            return findIntegerWithTheSmallestNumberOfZeroBits(numbers);
        }
        throw new IllegalArgumentException("Operation not supported");
    }

    public Integer findIntegerWithTheBiggestSumOfBits(Numbers numbers) {
        return Optional.ofNullable(numbers.getNumbers())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(n -> Integer.bitCount(n) % 2 == 0)
                .min(Comparator.comparingInt(Integer::bitCount))
                .orElseThrow();
    }

    public Integer findIntegerWithTheSmallestNumberOfZeroBits(Numbers numbers) {
        return Optional.ofNullable(numbers.getNumbers())
                .orElseGet(Collections::emptyList)
                .stream()
                .min(Comparator.comparingLong(NumberService::zeroBitCount))
                .orElseThrow();
    }

    private static int zeroBitCount(Integer number) {
        if (number == null) {
            return 0;
        }
        return Integer.SIZE - Integer.bitCount(number);
    }
}
