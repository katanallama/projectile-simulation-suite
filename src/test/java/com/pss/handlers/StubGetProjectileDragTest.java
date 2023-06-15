package com.pss.handlers;

import javax.vecmath.Vector3d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StubGetProjectileDragTest {
    @Test
    void getProjectileDrag() {
        StubGetProjectileDrag dragHandler = new StubGetProjectileDrag();

        Vector3d drag = dragHandler.getProjectileDrag();

        assertEquals(0, drag.getX());
        assertEquals(0, drag.getY());
        assertEquals(0, drag.getZ());
    }

    // TODO add more tests
}
