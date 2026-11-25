package pl.przychod.gittraining.service;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import java.util.*;

@Service
public class StringService {

    //Napisz metodę, która dla przekazanej listy Stringów znajduje tego, którego suma kodów ascii jest największa.

    public Response<String> execute(Request<List<String>> request, String operation) {
        if ("find-string-with-the-biggest-ascii-code".equals(operation)) {
            return new Response<>(findStringWithTheBiggestAsciiCode(request.getContent()));
        }
        if ("find-string-with-the-most-letters-a".equals(operation)) {
            return new Response<>(findStringWithTheMostLettersA(request.getContent()));
        }
        throw new IllegalArgumentException("Unsupported operation: " + operation);
    }

    private String findStringWithTheBiggestAsciiCode(List<String> strings) {
        return Optional.ofNullable(strings)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(this::sumAsciiCodes))
                .orElseThrow();
    }

    private String findStringWithTheMostLettersA(List<String> strings) {
        return Optional.ofNullable(strings)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingLong(this::letterCounter))
                .orElseThrow();
    }

    private int sumAsciiCodes(String word) {
        return word.chars().sum();
    }

    private long letterCounter(String word) {
        return word.chars()
                .filter(c -> c == 'a')
                .count();
    }
}
