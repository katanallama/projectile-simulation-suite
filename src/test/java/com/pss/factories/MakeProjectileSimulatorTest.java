package com.pss.factories;

import com.pss.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MakeProjectileSimulatorTest {

    @Test
    void createProjectileSimulator() {
        MakeProjectileSimulator makeProjectileSimulator = new MakeProjectileSimulator();
        IGetConfiguration stubConfig = new BaseGetConfiguration();
        ProjectileSimulator simulator = makeProjectileSimulator.createProjectileSimulator(stubConfig);

        assertNotNull(simulator, "Simulator should not be null");

        assertNotNull(simulator.getProjectile(), "Simulator's projectile should not be null");
    }

    // TODO add more tests
}
