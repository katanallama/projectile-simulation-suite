package com.pss.utils;

import javax.vecmath.Vector3d;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
