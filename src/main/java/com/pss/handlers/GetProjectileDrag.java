package com.pss.handlers;

import com.pss.enums.Settings;
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

        
        double rho = _configurationHandler.getSetting(Settings.FluidRho);
        double A = _configurationHandler.getSetting(Settings.ProjectileArea);
        double Cd = _configurationHandler.getSetting(Settings.DragCoefficent);

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
