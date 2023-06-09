package com.pss;

import com.pss.interfaces.*;
import javax.vecmath.Vector3d;

public class ProjectileSimulator {
    private IGetProjectile _projectileHandler;
    private IGetProjectileDrag _projectileDragHandler;
    private IGetProjectileForce _projectileForceHandler;
    private IGetProjectileGravity _projectileGravityHandler;

    public ProjectileSimulator(IGetProjectile projectileHandler, IGetProjectileDrag projectileDragHandler,
            IGetProjectileForce projectileForceHandler, IGetProjectileGravity projectileGravityHandler) {
        _projectileHandler = projectileHandler;
        _projectileForceHandler = projectileForceHandler;
        _projectileGravityHandler = projectileGravityHandler;
        _projectileDragHandler = projectileDragHandler;
    }

    public Vector3d updatePosition() {
        Vector3d newVelocity = getNewVelocity();
        Vector3d newPosition = getNewPosition();

        _projectileHandler.getProjectile().setVelocity(newVelocity);
        _projectileHandler.getProjectile().setPosition(newPosition);

        return newPosition;
    }

    private Vector3d getNewVelocity() {
        Vector3d currentVelocity = _projectileHandler.getProjectile().getVelocity();
        if (currentVelocity == null) {
            currentVelocity = new Vector3d();
        }

        currentVelocity.add(_projectileForceHandler.getProjectileForce());
        currentVelocity.add(_projectileGravityHandler.getProjectileGravity());
        currentVelocity.add(_projectileDragHandler.getProjectileDrag());

        return currentVelocity;
    }

    private Vector3d getNewPosition() {
        Vector3d currentPosition = _projectileHandler.getProjectile().getPosition();
        if (currentPosition == null) {
            currentPosition = new Vector3d();
        }

        currentPosition.add(_projectileHandler.getProjectile().getVelocity());

        return currentPosition;
    }
}
