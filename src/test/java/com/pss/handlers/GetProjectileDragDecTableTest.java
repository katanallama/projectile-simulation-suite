package com.pss.handlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import javax.vecmath.Vector3d;

import com.pss.enums.Settings;
import com.pss.interfaces.IGetConfiguration;
import com.pss.interfaces.IGetProjectile;
import com.pss.models.Projectile;
import com.pss.utils.TestUtilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/**
 * Tests the drag force on the projectile based on different configurations
 * using decision table based testing methods.
 *
 * @see <a href=
 *      "https://docs.oracle.com/en/java/https://github.com/katanallama/projectile-simulation-suite/blob/main/docs/testing.md#decision-table-testing">Testing
 *      Documentation</a> describes the full test design and procedure.
 */
@ExtendWith(MockitoExtension.class)
public class GetProjectileDragDecTableTest {

    @Mock
    private IGetConfiguration configurationHandler;
    @Mock
    private IGetProjectile projectileHandler;

    private Projectile mockedProjectile;

    /**
     * This setup method essentially prepares the 'world' for the tests by setting
     * up the necessary mocks and their behaviors.
     *
     * Setup the mocks and define their behaviors before the actual test methods
     * run. Here, a mock of Projectile class is created and it is made to return
     * when getProjectile() method is called on projectileHandler and when
     * getSetting() method is called on configurationHandler, it returns 0.0d.
     *
     * This way, when the tests run, * they use these predefined mocks and their
     * behaviors instead of using the * actual implementations. This ensures that
     * the tests are not dependent on the * actual implementations of these methods
     * which leads to more robust and * isolated tests.
     */
    @BeforeEach
    public void setup() {
        mockedProjectile = mock(Projectile.class);
        when(projectileHandler.getProjectile()).thenReturn(mockedProjectile);
        when(configurationHandler.getSetting(any(Settings.class))).thenReturn(0.0d);
    }

    /**
     * Test method which uses different test cases provided by the method provideTestCases.
     * In this test, the strictness is set to LENIENT because we are using a BeforeEach/Stream.
     * Mockito has very eager checking on unecessary stubbing and because this test is distributed
     * across 3 functions we silence the checking specifically in this function.
     *
     * @param velocity The velocity vector of the projectile
     * @param rho The density of the fluid
     * @param A The cross-sectional area of the projectile
     * @param Cd The drag coefficient of the projectile
     * @param expected The expected drag vector
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testVariousCases(Vector3d velocity, double rho, double A, double Cd, Vector3d expected) {
        when(mockedProjectile.getVelocity()).thenReturn(velocity);

        when(configurationHandler.getSetting(Settings.FluidRho)).thenReturn(rho);
        when(configurationHandler.getSetting(Settings.ProjectileArea)).thenReturn(A);
        when(configurationHandler.getSetting(Settings.DragCoefficent)).thenReturn(Cd);

        GetProjectileDrag getProjectileDrag = new GetProjectileDrag(configurationHandler, projectileHandler);
        Vector3d result = getProjectileDrag.getProjectileDrag();

        TestUtilities.assertEqualsVector(expected, result, 0.0001);
    }

    /**
     * This method provides distinct test cases for the parameterized test by
     * streaming arguments for the test function.
     *
     * @see <a href=
     *      "https://docs.oracle.com/en/java/https://github.com/katanallama/projectile-simulation-suite/blob/main/docs/testing.md#decision-table-testing">Testing
     *      Documentation</a> describes the full test design and procedure.
     *
     * @return Stream of Arguments containing different test cases.
     */
    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                // Vector3d velocity, double rho, double A, double Cd, Vector3d expected
                // Test first if() in GetProjectileDrag.java
                Arguments.of(new Vector3d(0, 0, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, 1), 0, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 0, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 1, 0, new Vector3d(0, 0, 0)),

                Arguments.of(new Vector3d(1, 1, 1), 1, 1, 1, new Vector3d(-0.866, -0.866, -0.866)),
                Arguments.of(new Vector3d(1, 1, 1), 0.1, 1, 1, new Vector3d(-0.0866, -0.0866, -0.0866)),
                Arguments.of(new Vector3d(1, 1, 1), 2, 1, 1, new Vector3d(-1.732, -1.732, -1.732)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 0.1, 1, new Vector3d(-0.0866, -0.0866, -0.0866)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 100, 1, new Vector3d(-86.6025, -86.6025, -86.6025)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 1, 0.1, new Vector3d(-0.0866, -0.0866, -0.0866)),

                // Test second if() in GetProjectileDrag.java
                Arguments.of(new Vector3d(1, 0, 0), 1, 1, 1, new Vector3d(-0.5, 0, 0)),
                Arguments.of(new Vector3d(0, 1, 0), 1, 1, 1, new Vector3d(0, -0.5, 0)),
                Arguments.of(new Vector3d(0, 0, 1), 1, 1, 1, new Vector3d(0, 0, -0.5)),

                Arguments.of(new Vector3d(-1, 0, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(0, -1, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(0, 0, -1), 1, 1, 1, new Vector3d(0, 0, 0.5)),

                Arguments.of(new Vector3d(-1, -1, -1), 1, 1, 1, new Vector3d(0, 0, 0)),

                Arguments.of(new Vector3d(-1, 1, 1), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, -1, 1), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, -1), 1, 1, 1, new Vector3d(-0.866, -0.866, 0.866)),

                Arguments.of(new Vector3d(1, 1, 1), -1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, 1), 1, -1, 1, new Vector3d(0, 0, 0)),
                Arguments.of(new Vector3d(1, 1, 1), 1, 1, -1, new Vector3d(0, 0, 0)));
    }

}
