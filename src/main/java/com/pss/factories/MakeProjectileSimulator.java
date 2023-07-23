package com.pss.factories;

import com.pss.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;
import com.pss.enums.Settings;

public class MakeProjectileSimulator {
    public ProjectileSimulator createProjectileSimulator(IGetConfiguration configurationHandler) {
        IGetProjectile projectileHandler = new GetProjectile(configurationHandler);

        IGetProjectileGravity projectileGravityHandler = getProjectileGravityHandler(configurationHandler,
                projectileHandler);
        IGetProjectileDrag projectileDragHandler = getProjectileDragHandler(configurationHandler, projectileHandler);

        IGetProjectileForce projectileForceHandler = getProjectileForceHandler(configurationHandler,
                projectileHandler);

        double maxStep = configurationHandler.getSetting(Settings.MaxStep);
        double timeStep = configurationHandler.getSetting(Settings.TimeStep);

        return new ProjectileSimulator(projectileHandler, projectileDragHandler, projectileForceHandler,
                projectileGravityHandler, maxStep, timeStep);
    }

    private IGetProjectileGravity getProjectileGravityHandler(IGetConfiguration configurationHandler,
            IGetProjectile projectileHandler) {
        return new GetProjectileGravity(configurationHandler, projectileHandler);
    }

    private IGetProjectileDrag getProjectileDragHandler(IGetConfiguration configurationHandler,
            IGetProjectile projectileHandler) {
        return new GetProjectileDrag(configurationHandler, projectileHandler);
    }

    private IGetProjectileForce getProjectileForceHandler(IGetConfiguration configurationHandler,
            IGetProjectile projectileHandler) {
        return new GetProjectileConstantContinualForce(configurationHandler, projectileHandler);
    }
}
