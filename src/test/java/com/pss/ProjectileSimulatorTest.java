package com.pss;

import com.pss.factories.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;
import javax.vecmath.Vector3d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileSimulatorTest {

    @Test
    void testUpdatePosition() {
        IGetConfiguration stubConfig = new StubGetConfiguration();
        MakeProjectileSimulator makeProjectileSimulator = new MakeProjectileSimulator();
        ProjectileSimulator simulator = makeProjectileSimulator.createProjectileSimulator(stubConfig);

        Vector3d initialPosition = new Vector3d(simulator.getProjectile().getPosition());
        Vector3d initialVelocity = new Vector3d(simulator.getProjectile().getVelocity());

        Vector3d newPosition = simulator.updatePosition();

        assertNotEquals(initialPosition, simulator.getProjectile().getPosition(), "Position should be updated");
        assertNotEquals(initialVelocity, simulator.getProjectile().getVelocity(), "Velocity should be updated");

        assertEquals(newPosition, simulator.getProjectile().getPosition(), "Returned value should be the new position");
    }

    // TODO add more tests
}
