package pl.przychod.gittraining.service;

import org.springframework.stereotype.Service;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;

import static pl.przychod.gittraining.service.operations.BiggestAsciiCodeOperation.findStringWithTheBiggestAsciiCode;
import static pl.przychod.gittraining.service.operations.FindStringWithContainingOnlyNumbers.findStringsWithContainingOnlyNumbers;
import static pl.przychod.gittraining.service.operations.MostLettersAOperation.findStringWithTheMostLettersA;

@Service
public class StringService {

    public Response<?> execute(Request<?> request, String operation) {
        return switch (operation) {
            case "find-string-with-the-biggest-ascii-code" -> findStringWithTheBiggestAsciiCode(request);
            case "find-string-with-the-most-letters-a" -> findStringWithTheMostLettersA(request);
            case "find-strings-with-containing-only-numbers" -> findStringsWithContainingOnlyNumbers(request);
            case null, default -> throw new IllegalArgumentException("Unsupported operation: " + operation);
        };
    }
}
