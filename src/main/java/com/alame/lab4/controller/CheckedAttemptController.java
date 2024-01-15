package com.alame.lab4.controller;

import com.alame.lab4.model.Attempt;
import com.alame.lab4.model.CheckedAttempt;
import com.alame.lab4.model.DefaultPostResponse;
import com.alame.lab4.service.CheckedAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(originPatterns = "http://localhost:29381")
@RestController
public class CheckedAttemptController {
    private final CheckedAttemptService checkedAttemptService;

    public CheckedAttemptController(CheckedAttemptService checkedAttemptService) {
        this.checkedAttemptService = checkedAttemptService;
    }
    @GetMapping("/attempts")
    List<CheckedAttempt> all(){
        return checkedAttemptService.getAll();
    }
    @PostMapping("/attempts")
    DefaultPostResponse newCheckedAttempt(@RequestBody Attempt attempt){
        return checkedAttemptService.addAttempt(attempt);
    }
}
