package com.pss.handlers;

import com.pss.interfaces.*;

import javax.vecmath.Vector3d;

public class GetProjectileDrag implements IGetProjectileDrag {

    private IGetConfiguration _configurationHandler;
    private IGetProjectile _projectileHandler;

    public GetProjectileDrag(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        _configurationHandler = configurationHandler;
        _projectileHandler = projectileHandler;
    }

    public double getProjectileDrag() {
        Vector3d velocity = new Vector3d(_projectileHandler.getProjectile().getVelocity());
        double speedSquared = velocity.lengthSquared();

        // Calculate the drag force
        Vector3d dragForce = new Vector3d(velocity);
        dragForce.normalize();
        // dragForce.scale(-0.5 * rho * A * Cd * speedSquared);
        dragForce.scale(0.5 * speedSquared);

        return dragForce.length();
    }

}
