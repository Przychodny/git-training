package pl.przychod.gittraining.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.przychod.gittraining.model.Request;
import pl.przychod.gittraining.model.Response;
import pl.przychod.gittraining.service.StringService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/strings")
@RequiredArgsConstructor
public class StringController {

    private final StringService stringService;

    @PostMapping("/{operation}")
    public Response<?> execute(@RequestBody Request<List<String>> request, @PathVariable String operation) {
        return stringService.execute(request, operation);
    }
}
