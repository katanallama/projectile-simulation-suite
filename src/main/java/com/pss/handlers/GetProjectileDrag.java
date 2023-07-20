/**
 * This class calculates the drag force acting on a projectile in a fluid medium.
 * It makes use of the interfaces IGetConfiguration and IGetProjectile to get
 * relevant configurations and properties of the projectile respectively.
 * It implements the IGetProjectileDrag interface.
 */
package com.pss.handlers;

import com.pss.enums.Settings;
import com.pss.interfaces.*;
import javax.vecmath.Vector3d;

public class GetProjectileDrag implements IGetProjectileDrag {

    // handler to fetch the configuration settings
    private IGetConfiguration _configurationHandler;
    // handler to fetch the projectile properties
    private IGetProjectile _projectileHandler;

    /**
     * Class constructor specifying handlers for configuration and projectile properties.
     *
     * @param configurationHandler Handler for getting the configuration settings.
     * @param projectileHandler Handler for getting the projectile properties.
     */
    public GetProjectileDrag(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        _configurationHandler = configurationHandler;
        _projectileHandler = projectileHandler;
    }

    /**
     * Calculates and returns the drag force acting on the projectile.
     *
     * @return The drag force vector, represented as a Vector3d object.
     */
    public Vector3d getProjectileDrag() {
        // fetch and copy the velocity of the projectile
        Vector3d velocity = new Vector3d(_projectileHandler.getProjectile().getVelocity());

        // if the velocity is zero or negative in x or y direction, return zero drag
        if (velocity.equals(new Vector3d(0, 0, 0)) || velocity.x < 0 || velocity.y < 0)
            return new Vector3d(0, 0, 0);

        // fetch fluid density (rho), projectile cross-sectional area (A), and drag coefficient (Cd)
        double rho = _configurationHandler.getSetting(Settings.FluidRho);
        double A = _configurationHandler.getSetting(Settings.ProjectileArea);
        double Cd = _configurationHandler.getSetting(Settings.DragCoefficent);

        // Calculate the magnitude of the drag force based on fluid mechanics principles
        double vSquared = velocity.lengthSquared();
        double dragMagnitude = -(0.5 * rho * A * Cd * vSquared);

        // if drag is in the same direction as velocity (which is physically incorrect), return zero drag
        if (dragMagnitude > 0)
            return new Vector3d(0, 0, 0);

        // calculate unit vector of velocity
        Vector3d unitVelocity = new Vector3d(velocity);
        unitVelocity.normalize();

        // calculate drag force vector by scaling unit velocity with drag magnitude
        Vector3d dragForce = new Vector3d(unitVelocity);
        dragForce.scale(dragMagnitude);

        return dragForce;
    }
}
