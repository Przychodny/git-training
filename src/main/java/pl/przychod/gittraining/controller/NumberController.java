package pl.przychod.gittraining.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;
import pl.przychod.gittraining.service.NumberService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/numbers")
@RequiredArgsConstructor
public class NumberController {

    private final NumberService numberService;

    @PostMapping("/{operation}")
    public Response<?> findNumber(@RequestBody Request<List<Integer>> request, @PathVariable String operation) {
        return numberService.execute(request, operation);
    }
}
