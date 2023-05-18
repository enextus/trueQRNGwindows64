package org.trueQRNGwindows64;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void checkResultSuccess() {
        assertTrue(App.checkResult(0));
    }

    @Test
    void checkResultFailure() {
        assertFalse(App.checkResult(1));
    }

    // more tests to come...

}
