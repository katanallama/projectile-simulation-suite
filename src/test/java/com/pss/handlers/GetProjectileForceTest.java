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

        Vector3d projectileVelocity = projectileHandler.getProjectile().getVelocity();
        Vector3d additionalForce = forceHandler.getProjectileForce();
        double roundingThreshold = 0.000001;
        
        // verify that each xyz component (if non-zero) of the additional force
        // is increasing the magnitude of the projectile velocity
        if (Math.abs(projectileVelocity.getX()) > roundingThreshold)
            assertTrue(Math.abs(additionalForce.getX() + projectileVelocity.getX()) > Math.abs(projectileVelocity.getX()));
        if (Math.abs(projectileVelocity.getY()) > roundingThreshold)
            assertTrue(Math.abs(additionalForce.getY() + projectileVelocity.getY()) > Math.abs(projectileVelocity.getY()));
        if (Math.abs(projectileVelocity.getZ()) > roundingThreshold)
            assertTrue(Math.abs(additionalForce.getZ() + projectileVelocity.getZ()) > Math.abs(projectileVelocity.getZ()));
    }

    // TODO add more tests
}
