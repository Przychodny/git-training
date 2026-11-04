package pl.przychod.gittraining.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.przychod.gittraining.model.Numbers;
import pl.przychod.gittraining.service.NumberService;

@RestController
@RequestMapping("/api/v1/numbers")
@RequiredArgsConstructor
public class NumberController {

    private final NumberService numberService;

    @PostMapping("/{operation}")
    public Object findNumber(@RequestBody Numbers numbers, @PathVariable String operation) {
        return numberService.execute(numbers, operation);
    }
}
