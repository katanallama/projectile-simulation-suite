package com.pss.models;

import org.junit.jupiter.api.Test;
import javax.vecmath.Vector3d;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectileTest {

    @Test
    public void testSetAndGetVelocity() {
        Vector3d vector3d = new Vector3d(2.0, 3.0, 4.0);
        Projectile projectile = new Projectile();
        projectile.setVelocity(vector3d);

        assertEquals(vector3d, projectile.getVelocity());
    }

    @Test
    public void testSetAndGetWeight() {
        double weight = 50.0;
        Projectile projectile = new Projectile();
        projectile.setWeight(weight);

        assertEquals(weight, projectile.getWeight());
    }

    // TODO add more tests
}
