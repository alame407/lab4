package com.alame.lab4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "checked_attempts")
public class CheckedAttempt {
    private @Id @GeneratedValue Long id;
    @Column(name = "x")
    private float x;
    @Column(name = "y")
    private float y;
    @Column(name = "r")
    private float r;
    @Column(name = "in_figure")
    private boolean inFigure;
    public CheckedAttempt(float x, float y, float r, boolean inFigure){
        this.x = x;
        this.y = y;
        this.r = r;
        this.inFigure = inFigure;
    }
}
