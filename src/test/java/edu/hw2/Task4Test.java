package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class X {
    static Task4.CallingInfo foo_X() {
        return Task4.callingInfo();
    }
}

class Task4Test {
    @Test
    void callingInfoDefaultCall() {
        var callInfo = Task4.callingInfo();
        assertEquals(callInfo.className(), "edu.hw2.Task4Test");
        assertEquals(callInfo.methodName(), "callingInfoDefaultCall");
    }

    @Test
    void callingInfoCallFromOtherMethod() {
        var callInfo = X.foo_X();

        assertEquals(callInfo.className(), "edu.hw2.X");
        assertEquals(callInfo.methodName(), "foo_X");
    }

}
