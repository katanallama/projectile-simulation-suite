package com.pss.models;

import javax.vecmath.Vector3d;

public class Projectile {
    private Vector3d Position;
    private Vector3d Velocity;
    private double Weight;

    public Projectile() {
        Position = new Vector3d();
        Velocity = new Vector3d();
    }

    public Vector3d getPosition() {
        return Position;
    }

    public void setPosition(double x, double y, double z) {
        Position.setX(x);
        Position.setY(y);
        Position.setZ(z);
    }

    public void setPosition(Vector3d position) {
        Position = position;
    }

    public Vector3d getVelocity() {
        return Velocity;
    }

    public void setVelocity(double x, double y, double z) {
        Velocity.setX(x);
        Velocity.setY(y);
        Velocity.setZ(z);
    }

    public void setVelocity(Vector3d velocity) {
        Velocity = velocity;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }
}
