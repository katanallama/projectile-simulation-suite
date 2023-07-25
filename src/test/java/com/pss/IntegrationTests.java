package com.pss;

import java.util.HashMap;

import javax.vecmath.Vector3d;

import org.junit.jupiter.api.Test;

import com.pss.enums.Settings;
import com.pss.factories.MakeProjectileSimulator;
import com.pss.handlers.BaseGetConfiguration;
import com.pss.interfaces.IGetConfiguration;
import com.pss.utils.TestUtilities;

public class IntegrationTests {
    private IGetConfiguration getTestConfiguration() {
        HashMap<String, Object> configs = new HashMap<String, Object>();

        configs.put(Settings.ContinualForce.getName(), 10d);
        configs.put(Settings.DragCoefficent.getName(), 0.2d);
        configs.put(Settings.Gravity.getName(), 9.81d);
        configs.put(Settings.Weight.getName(), 45d);
        configs.put(Settings.InitialDirection.getName(), new Vector3d(1, 1, 1));
        configs.put(Settings.MuzzleVelocity.getName(), 400d);
        configs.put(Settings.TimeStep.getName(), 1d);        
        configs.put(Settings.MaxStep.getName(), 5d);        
        configs.put(Settings.FluidRho.getName(), 1.2d);
        configs.put(Settings.ProjectileArea.getName(), 0.018d);

        IGetConfiguration configHandler = new BaseGetConfiguration(configs);

        return configHandler;
    }

    private HashMap<Integer, Vector3d> getExpectedResults() {
        HashMap<Integer, Vector3d> expected = new HashMap<>();

        expected.put(1, new Vector3d(226.63435, 226.63435, 216.82435));
        expected.put(2, new Vector3d(449.18932, 449.18932, 419.93590));
        expected.put(3, new Vector3d(667.87474, 667.87474, 609.70596));
        expected.put(4, new Vector3d(882.88530, 882.88530, 786.47706));
        expected.put(5, new Vector3d(1094.40177, 1094.40177, 950.56550));

        return expected;
    }

    @Test
    void IntegrationTestProjectileSimulator() {
        MakeProjectileSimulator factory = new MakeProjectileSimulator();
        ProjectileSimulator sut = factory.createProjectileSimulator(getTestConfiguration());
        Vector3d result;
        HashMap<Integer, Vector3d> expectedResults = getExpectedResults();

        for(int i = 1; i <= 5; i++) {
            result = sut.updatePosition();

            TestUtilities.assertEqualsVector(expectedResults.get(i), result, 0.0001);
        }
    }
}
