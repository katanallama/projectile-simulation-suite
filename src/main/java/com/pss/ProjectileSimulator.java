package com.pss;

import com.pss.interfaces.*;
import com.pss.models.*;
import javax.vecmath.Vector3d;

public class ProjectileSimulator {
    private IGetProjectile _projectileHandler;
    private IGetProjectileDrag _projectileDragHandler;
    private IGetProjectileForce _projectileForceHandler;
    private IGetProjectileGravity _projectileGravityHandler;

    private double timeStep; // Time step for simulation

    public ProjectileSimulator(IGetProjectile projectileHandler, IGetProjectileDrag projectileDragHandler,
            IGetProjectileForce projectileForceHandler, IGetProjectileGravity projectileGravityHandler,
            double timeStep) {
        _projectileHandler = projectileHandler;
        _projectileForceHandler = projectileForceHandler;
        _projectileGravityHandler = projectileGravityHandler;
        _projectileDragHandler = projectileDragHandler;
        this.timeStep = timeStep;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public Projectile getProjectile() {
        return _projectileHandler.getProjectile();
    }

    public Vector3d updatePosition() {
        Vector3d newVelocity = getNewVelocity();
        _projectileHandler.getProjectile().setVelocity(newVelocity);

        Vector3d newPosition = getNewPosition();
        _projectileHandler.getProjectile().setPosition(newPosition);

        return newPosition;
    }

    private Vector3d getNewVelocity() {
        Vector3d currentVelocity = new Vector3d(_projectileHandler.getProjectile().getVelocity());
        Vector3d totalForce = new Vector3d(_projectileForceHandler.getProjectileForce());

        totalForce.add(_projectileGravityHandler.getProjectileGravity());
        totalForce.add(_projectileDragHandler.getProjectileDrag());

        Vector3d acceleration = new Vector3d(totalForce);
        acceleration.scale(1 / _projectileHandler.getProjectile().getWeight()); // a = F/m

        Vector3d deltaV = new Vector3d(acceleration);
        deltaV.scale(timeStep); // deltaV = a*t
        currentVelocity.add(deltaV);

        return currentVelocity;
    }

    private Vector3d getNewPosition() {
        Vector3d currentPosition = _projectileHandler.getProjectile().getPosition();
        if (currentPosition == null) {
            currentPosition = new Vector3d(0, 0, 0);
        }

        Vector3d velocity = new Vector3d(_projectileHandler.getProjectile().getVelocity());
        velocity.scale(timeStep); // scale velocity by the timestep

        currentPosition.add(velocity); // add the scaled velocity to the current position

        return currentPosition;
    }
}
