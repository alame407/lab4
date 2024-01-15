package com.alame.lab4.service;

import com.alame.lab4.model.Attempt;
import com.alame.lab4.model.CheckedAttempt;
import com.alame.lab4.model.DefaultPostResponse;

import java.util.List;

public interface CheckedAttemptService {
    List<CheckedAttempt> getAll();

    DefaultPostResponse addAttempt(Attempt attempt);
}
