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

        if (velocity.equals(new Vector3d(0, 0, 0)))
            return new Vector3d(0, 0, 0);

        // HACK for testing
        double rho = 1.204;
        double A = 0.1;
        double Cd = 0.04;

        // Calculate the drag force
        double vSquared = velocity.lengthSquared();
        double dragMagnitude = -(0.5 * rho * A * Cd * vSquared);

        Vector3d unitVelocity = new Vector3d(velocity);
        unitVelocity.normalize();
        Vector3d dragForce = new Vector3d(unitVelocity);
        dragForce.scale(dragMagnitude);

        return dragForce;
    }

}
