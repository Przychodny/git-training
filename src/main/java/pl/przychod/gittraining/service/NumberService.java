package pl.przychod.gittraining.service;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.List;

@Service
public class NumberService {
    //Napisz metodę, która dla przekazanej listy Integerów
    //policzy ile jest w nich łącznie cyfr nieparzystych.

    public Object execute(Numbers numbers, String operation) {
        if ("biggets-sum-of-bits".equals(operation)) {
            return findIntegerWithTheBiggestSumOfBits(numbers);
        }
        if ("biggest-number-with-the-biggest-number-of-zero-bits".equals(operation)) {
            return findIntegerWithTheSmallestNumberOfZeroBits(numbers);
        }
        if ("count-odd-numbers".equals(operation)) {
            return countOddNumbers(numbers);
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

    public  Long countOddNumbers(Numbers numbers) {
        return Optional.ofNullable(numbers.getNumbers())
                .orElseGet(Collections::emptyList)
                .stream()
                .mapToLong(this::countOddDigits)
                .sum();
    }

    private Long countOddDigits(Integer number) {
        return String.valueOf(Math.abs(number))
                .chars()
                .map(Character::getNumericValue)
                .filter(i -> i % 2 != 0)
                .count();
    }

    private static int zeroBitCount(Integer number) {
        if (number == null) {
            return 0;
        }
        return Integer.SIZE - Integer.bitCount(number);
}
