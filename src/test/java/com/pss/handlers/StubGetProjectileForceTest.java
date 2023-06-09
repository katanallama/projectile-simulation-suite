package com.pss.handlers;

import javax.vecmath.Vector3d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StubGetProjectileForceTest {
    @Test
    void getProjectileForce() {
        StubGetProjectileForce forceHandler = new StubGetProjectileForce();

        Vector3d force = forceHandler.getProjectileForce();

        assertEquals(0, force.getX());
        assertEquals(0, force.getY());
        assertEquals(0, force.getZ());
    }
}
