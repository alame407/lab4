package com.alame.lab4.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AttemptValidatorImpl implements AttemptValidator{
    private final static List<Float> possibleX = List.of(-5f, -4f, -3f, -2f, -1f, 0f, 1f, 2f, 3f);
    private final static List<Float> possibleR = List.of(1f, 2f, 3f);
    @Override
    public ValidationResponse validate(Attempt attempt) {
        if (!possibleX.contains(attempt.getX())){
            return new ValidationResponse(false, "x must be one of " + possibleX);
        }
        if (!(-5 <attempt.getY() && attempt.getY()<3)){
            return new ValidationResponse(false, "y must be in range (-5;3)");
        }
        if (!possibleR.contains(attempt.getR())){
            return new ValidationResponse(false, "r must be one of " + possibleR);
        }
        return new ValidationResponse(true, "");
    }
}
