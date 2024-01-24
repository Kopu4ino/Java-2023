package edu.hw11.Task2;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class ArithmeticUtilsInterceptor {
    private ArithmeticUtilsInterceptor() {
    }

    @RuntimeType
    public static int intercept(@AllArguments int[] args) {
        return args[0] * args[1];
    }
}

