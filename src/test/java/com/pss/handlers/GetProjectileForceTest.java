package com.pss.handlers;

import javax.vecmath.Vector3d;
import org.junit.jupiter.api.Test;

import com.pss.interfaces.IGetConfiguration;

import static org.junit.jupiter.api.Assertions.*;

class GetProjectileForceTest {
    @Test
    void getProjectileForce() {
        IGetConfiguration configHandler = new StubGetConfiguration();
        GetProjectile projectileHandler = new GetProjectile(configHandler);
        GetProjectileConstantContinualForce forceHandler = new GetProjectileConstantContinualForce(configHandler, projectileHandler);

        Vector3d force = forceHandler.getProjectileForce();

        assertEquals(0, force.getX());
        assertEquals(0, force.getY());
        assertEquals(0, force.getZ());
    }

    // TODO add more tests
}
