package com.pss.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.vecmath.Vector3d;

import com.pss.enums.State;

public class TestUtilities {

    /**
     * Asserts that two Vector3d objects are equal with a given delta for
     * floating-point precision.
     *
     * @param expected The expected Vector3d instance.
     * @param actual   The actual Vector3d instance that is compared with the
     *                 expected instance.
     * @param epsilon  The maximum delta between the x, y, and z components of the
     *                 two vectors for which both vectors are still considered
     *                 equal.
     *
     * @throws AssertionError if the difference of any pair of corresponding
     *                        components is greater than epsilon.
     */
    public static void assertEqualsVector(Vector3d expected, Vector3d actual, double epsilon) {
        assertEquals(expected.x, actual.x, epsilon);
        assertEquals(expected.y, actual.y, epsilon);
        assertEquals(expected.z, actual.z, epsilon);
    }

    public static List<State> getStatesList(int repeatCount) {
        List<State> expectedStates = new ArrayList<>(Arrays.asList(
                State.T_SIM_INIT,
                State.SIM_INITIALIZED,
                State.T_START_SIM));

        for (int i = 0; i < repeatCount; i++) {
            expectedStates.add(State.T_UPDATE_POSITION);
        }

        expectedStates.addAll(Arrays.asList(
                State.SIM_COMPLETE,
                State.SIM_OUTPUT_RESULT));

        return expectedStates;
    }

}
