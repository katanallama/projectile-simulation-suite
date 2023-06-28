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

    public Vector3d getProjectileDrag() {
        Vector3d velocity = new Vector3d(_projectileHandler.getProjectile().getVelocity());
        double speedSquared = velocity.lengthSquared();

        if (velocity.equals(new Vector3d(0,0,0)))
            return new Vector3d(0,0,0);

        // Calculate the drag force
        Vector3d dragForce = new Vector3d(velocity);
        // TODO dragForce.scale(-0.5 * rho * A * Cd * speedSquared);
        dragForce.scale((double)0.5 * speedSquared);

        return dragForce;
    }

}

