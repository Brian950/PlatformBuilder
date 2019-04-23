package com.logic.strategy;

public class JumpHigher implements Strategy {
    @Override
    public float doOperation(float value) {
        return value + 500;
    }
}
