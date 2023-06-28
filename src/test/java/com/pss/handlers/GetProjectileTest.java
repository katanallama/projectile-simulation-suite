package com.pss.handlers;

import com.pss.interfaces.*;
import com.pss.models.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetProjectileTest {
    @Test
    void getProjectile() {
        IGetConfiguration configHandler = new StubGetConfiguration();
        GetProjectile projectileHandler = new GetProjectile(configHandler);

        Projectile projectile = projectileHandler.getProjectile();

        assertNotNull(projectile);
        // assertEquals(10.0, projectile.getWeight());
    }

    // TODO add more tests
}
