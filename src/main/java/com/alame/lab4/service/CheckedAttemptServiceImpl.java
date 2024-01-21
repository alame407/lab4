package com.alame.lab4.service;

import com.alame.lab4.model.*;
import com.alame.lab4.repository.CheckedAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckedAttemptServiceImpl implements CheckedAttemptService {
    private final CheckedAttemptRepository repository;
    private final AttemptValidator validator;

    public CheckedAttemptServiceImpl(CheckedAttemptRepository repository, AttemptValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }
    @Override
    public List<CheckedAttempt> getAll(){
        return repository.findAll();
    }
    @Override
    public DefaultPostResponse addAttempt(Attempt attempt){
        ValidationResponse validationResponse = validator.validate(attempt);
        if(!validationResponse.isSuccessfully()){
            return new DefaultPostResponse(false, validationResponse.getErrors(), "");
        }
        CheckedAttempt checkedAttempt = new CheckedAttempt(attempt.getX(), attempt.getY(), attempt.getR(),
                pointInFigure(attempt));
        repository.save(checkedAttempt);
        return new DefaultPostResponse(true, "", "");
    }
    private boolean pointInFigure(Attempt attempt){
        float x = attempt.getX();
        float y = attempt.getY();
        float r = attempt.getR();
        boolean inCircle = x<=0 && y>=0 && (x*x+y*y<=r*r);
        boolean inTriangle = x>=0 && y>=0 && (r-x>=y);
        boolean inRectangle = x>=0 && y<=0 &&  x<=r/2 && y>=-r;
        return inRectangle || inCircle || inTriangle;
    }
}
