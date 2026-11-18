package pl.przychod.gittraining.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;
import pl.przychod.gittraining.service.operations.NumberOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NumberServiceTest {

    @Autowired
    private NumberService numberService;

    @Test
    void selectsOperationsByTheirExistingNames() {
        Request<List<Integer>> request = new Request<>(List.of(3, 7, 8));

        assertEquals(3, numberService.execute(request, "biggets-sum-of-bits").getContent());
        assertEquals(7, numberService.execute(request, "biggest-number-with-the-biggest-number-of-zero-bits").getContent());
        assertEquals(2L, numberService.execute(request, "count-odd-digits").getContent());
    }

    @Test
    void rejectsUnknownOperation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> numberService.execute(new Request<>(List.of(1)), "unknown"));
        assertEquals("Operation not supported", exception.getMessage());
    }

    @Test
    void delegatesRequestAndResponseWithoutAssumingContentTypes() {
        Request<String> request = new Request<>("content");
        Response<String> response = new Response<>("result");
        NumberOperation operation = actual -> {
            assertSame(request, actual);
            return response;
        };
        NumberService service = new NumberService(Map.of("custom", operation));

        assertSame(response, service.execute(request, "custom"));
    }

    @Test
    void preservesEmptyAndNullElementBehavior() {
        assertEquals(0L, numberService.execute(new Request<>(null), "count-odd-digits").getContent());
        assertThrows(NoSuchElementException.class,
                () -> numberService.execute(new Request<>(null), "biggets-sum-of-bits"));
        assertEquals(3, numberService.execute(new Request<>(Arrays.asList(null, 3)),
                "biggets-sum-of-bits").getContent());
    }

    @Test
    void rejectsInvalidContentForNumberOperations() {
        assertThrows(IllegalArgumentException.class,
                () -> numberService.execute(new Request<>("not a list"), "count-odd-digits"));
        assertThrows(IllegalArgumentException.class,
                () -> numberService.execute(new Request<>(List.of("not an integer")), "count-odd-digits"));
    }
}
