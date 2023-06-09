package com.pss.factories;

import com.pss.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;

public class MakeProjectileSimulator {
    public ProjectileSimulator createProjectileSimulator(IGetConfiguration configurationHandler) {
        IGetProjectile projectileHandler = new GetProjectile(configurationHandler);

        IGetProjectileGravity projectileGravityHandler = getProjectileGravityHandler(configurationHandler, projectileHandler);
        IGetProjectileDrag projectileDragHandler = getProjectileDragHandler(configurationHandler, projectileHandler);
        IGetProjectileForce projectileForceHandler = getProjectileForceyHandler(configurationHandler, projectileHandler);

        return new ProjectileSimulator(projectileHandler, projectileDragHandler, projectileForceHandler, projectileGravityHandler);
    }

    private IGetProjectileGravity getProjectileGravityHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new GetProjectileGravity(configurationHandler, projectileHandler);
    }

    private IGetProjectileDrag getProjectileDragHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new StubGetProjectileDrag();
    }

    private IGetProjectileForce getProjectileForceyHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new StubGetProjectileForce();
    }
}
