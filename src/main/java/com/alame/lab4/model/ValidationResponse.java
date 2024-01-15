package com.alame.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidationResponse {
    private boolean successfully;
    private String errors;
}
