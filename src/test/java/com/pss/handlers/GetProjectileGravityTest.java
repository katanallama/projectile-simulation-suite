package com.pss.handlers;

import com.pss.interfaces.*;

import javax.vecmath.Vector3d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetProjectileGravityTest {
    @Test
    void getProjectileGravity() {
        IGetConfiguration configHandler = new BaseGetConfiguration();
        IGetProjectile projectileHandler = new GetProjectile(configHandler);
        GetProjectileGravity gravityHandler = new GetProjectileGravity(configHandler, projectileHandler);

        Vector3d gravity = gravityHandler.getProjectileGravity();

        assertEquals(0, gravity.getX());
        assertEquals(0, gravity.getY());
        // assertEquals(-98.10000000000001, gravity.getZ());
    }

    // TODO add more tests
}
