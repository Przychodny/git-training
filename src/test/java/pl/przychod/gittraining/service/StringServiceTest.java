package pl.przychod.gittraining.service;

import org.junit.jupiter.api.Test;
import pl.przychod.gittraining.model.Request;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.przychod.gittraining.service.operations.BiggestAsciiCodeOperation.findStringWithTheBiggestAsciiCode;
import static pl.przychod.gittraining.service.operations.MostLettersAOperation.findStringWithTheMostLettersA;

class StringServiceTest {

    private final StringService service = new StringService();

    @Test
    void dispatchesOperationsUsingTheirExistingNames() {
        Request<List<String>> request = new Request<>(List.of("aaa", "zzy"));
        assertEquals("zzy", service.execute(request, "find-string-with-the-biggest-ascii-code").getContent());
        assertEquals("aaa", service.execute(request, "find-string-with-the-most-letters-a").getContent());
    }

    @Test
    void utilityMethodsPreserveNullFilteringAndFirstResultOnTies() {
        Request<List<String>> request = new Request<>(Arrays.asList(null, "ab", "ba"));
        assertEquals("ab", findStringWithTheBiggestAsciiCode(request).getContent());
        assertEquals("ab", findStringWithTheMostLettersA(request).getContent());
        assertEquals("aa", findStringWithTheMostLettersA(new Request<>(List.of("AAA", "aa"))).getContent());
    }

    @Test
    void emptyOrNullContentHasNoResult() {
        assertThrows(NoSuchElementException.class,
                () -> findStringWithTheBiggestAsciiCode(new Request<>(null)));
        assertThrows(NoSuchElementException.class,
                () -> findStringWithTheMostLettersA(new Request<>(Arrays.asList(null, null))));
    }

    @Test
    void unsupportedOperationIncludesItsNameEvenWhenNull() {
        assertEquals("Unsupported operation: unknown", assertThrows(IllegalArgumentException.class,
                () -> service.execute(new Request<>(List.of()), "unknown")).getMessage());
        assertEquals("Unsupported operation: null", assertThrows(IllegalArgumentException.class,
                () -> service.execute(new Request<>(List.of()), null)).getMessage());
    }

    @Test
    void rejectsContentThatIsNotAListOfStrings() {
        assertThrows(IllegalArgumentException.class,
                () -> service.execute(new Request<>("text"), "find-string-with-the-biggest-ascii-code"));
        assertThrows(IllegalArgumentException.class,
                () -> service.execute(new Request<>(List.of("text", 1)), "find-string-with-the-most-letters-a"));
    }
}
