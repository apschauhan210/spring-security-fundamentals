package com.springsecurity.methodauthorization.security;

import org.springframework.stereotype.Component;

@Component
public class ControllerConditionEvaluator {

    public boolean evaluateCondition() {
        return false;
    }
}
