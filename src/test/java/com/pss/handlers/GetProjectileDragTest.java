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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
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
 *      "https://github.com/katanallama/projectile-simulation-suite/blob/main/docs/testing.md#decision-table-testing">Testing
 *      Documentation</a> describes the full test design and procedure.
 */
@ExtendWith(MockitoExtension.class)
public class GetProjectileDragTest {

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
    @DisplayName("-")
    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testVariousCases(String name, Vector3d velocity, double rho, double A, double Cd, Vector3d expected) {
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
     *      "https://github.com/katanallama/projectile-simulation-suite/blob/main/docs/testing.md#decision-table-testing">Testing
     *      Documentation</a> describes the full test design and procedure.
     *
     * @return Stream of Arguments containing different test cases.
     */
    private static Stream<Arguments> testCases() {
        return Stream.of(
                // Vector3d velocity, double rho, double A, double Cd, Vector3d expected
                // Test first if() in GetProjectileDrag.java
                Arguments.of("Decision Table: Zero velocity", new Vector3d(0, 0, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Zero rho", new Vector3d(1, 1, 1), 0, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Zero area", new Vector3d(1, 1, 1), 1, 0, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Zero Cd", new Vector3d(1, 1, 1), 1, 1, 0, new Vector3d(0, 0, 0)),

                // Arguments.of("", new Vector3d(1, 1, 1), 1, 1, 1, new Vector3d(-0.866, -0.866, -0.866)),
                // Arguments.of("", new Vector3d(1, 1, 1), 0.1, 1, 1, new Vector3d(-0.0866, -0.0866, -0.0866)),
                // Arguments.of("", new Vector3d(1, 1, 1), 2, 1, 1, new Vector3d(-1.732, -1.732, -1.732)),
                // Arguments.of("", new Vector3d(1, 1, 1), 1, 0.1, 1, new Vector3d(-0.0866, -0.0866, -0.0866)),
                // Arguments.of("", new Vector3d(1, 1, 1), 1, 100, 1, new Vector3d(-86.6025, -86.6025, -86.6025)),
                // Arguments.of("", new Vector3d(1, 1, 1), 1, 1, 0.1, new Vector3d(-0.0866, -0.0866, -0.0866)),

                // Test second if() in GetProjectileDrag.java
                Arguments.of("Decision Table: Zero velocity in y,z", new Vector3d(1, 0, 0), 1, 1, 1, new Vector3d(-0.5, 0, 0)),
                Arguments.of("Decision Table: Zero velocity in x,z", new Vector3d(0, 1, 0), 1, 1, 1, new Vector3d(0, -0.5, 0)),
                Arguments.of("Decision Table: Zero velocity in x,y", new Vector3d(0, 0, 1), 1, 1, 1, new Vector3d(0, 0, -0.5)),

                Arguments.of("Decision Table: Negative velocity in x", new Vector3d(-1, 0, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative velocity in y", new Vector3d(0, -1, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative velocity in z", new Vector3d(0, 0, -1), 1, 1, 1, new Vector3d(0, 0, 0.5)),

                Arguments.of("Decision Table: Negative velocity in x, y, z", new Vector3d(-1, -1, -1), 1, 1, 1, new Vector3d(0, 0, 0)),

                Arguments.of("Decision Table: Negative velocity in x", new Vector3d(-1, 1, 1), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative velocity in y", new Vector3d(1, -1, 1), 1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative velocity in z", new Vector3d(1, 1, -1), 1, 1, 1, new Vector3d(-0.866, -0.866, 0.866)),

                Arguments.of("Decision Table: Negative rho", new Vector3d(1, 1, 1), -1, 1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative area", new Vector3d(1, 1, 1), 1, -1, 1, new Vector3d(0, 0, 0)),
                Arguments.of("Decision Table: Negative Cd", new Vector3d(1, 1, 1), 1, 1, -1, new Vector3d(0, 0, 0)),
        
                // Test normal use case in getProjectileDrag.java
                Arguments.of("Path Coverage : Normal use case", new Vector3d(1, 1, 1), 1, 1, 1, new Vector3d(-0.866, -0.866, -0.866)),
                // Test zero/negative Vector input case in getProjectileDrag.java
                Arguments.of("Path Coverage : Zero Velocity", new Vector3d(0, 0, 0), 1, 1, 1, new Vector3d(0, 0, 0)),
                // Test zero/negative dragMagnitude calculation case in getProjectileDrag.java
                Arguments.of("Path Coverage : Zero dragMagnitude", new Vector3d(1, 1, 1), -1, 1, 1, new Vector3d(0, 0, 0)),

                // Boundary value cases
                Arguments.of("Boundary value testing : Max z velocity", new Vector3d(230, 230, 900), 1.2, 0.018, 0.3, new Vector3d(-713.13732, -713.13732, -2790.53734)),
                Arguments.of("Boundary value testing : Max x velocity", new Vector3d(900, 230, 230), 1.2, 0.018, 0.3, new Vector3d(-2790.5373, -713.13732, -713.13732)),
                Arguments.of("Boundary value testing : Max y velocity", new Vector3d(230, 900, 230), 1.2, 0.018, 0.3, new Vector3d(-713.13732, -2790.53734, -713.13732)),
                Arguments.of("Boundary value testing : Min y velocity", new Vector3d(230, 0, 230), 1.2, 0.018, 0.3, new Vector3d(-242.39054, 0, -242.39054)),
                Arguments.of("Boundary value testing : Min z velocity", new Vector3d(230, 230, 0), 1.2, 0.018, 0.3, new Vector3d(-242.39054, -242.39054, 0)),
                Arguments.of("Boundary value testing : Min x velocity", new Vector3d(0, 230, 230), 1.2, 0.018, 0.3, new Vector3d(0, -242.39054, -242.39054)),
                Arguments.of("Boundary value testing : Max density", new Vector3d(230, 230, 230), 1.5, 0.018, 0.3, new Vector3d(-371.08322, -371.08322, -371.08322)),
                Arguments.of("Boundary value testing : Min density", new Vector3d(230, 230, 230), 1, 0.018, 0.3, new Vector3d(-247.38881, -247.38881, -247.38881)),
                Arguments.of("Boundary value testing : Max area", new Vector3d(230, 230, 230), 1.2, 0.14, 0.3, new Vector3d(-2308.96229, -2308.96229, -2308.96229)),
                Arguments.of("Boundary value testing : Min area", new Vector3d(230, 230, 230), 1.2, 0.0086, 0.3, new Vector3d(-141.83625, -141.83625, -141.83625)),
                Arguments.of("Boundary value testing : Max drag co", new Vector3d(230, 230, 230), 1.2, 0.018, 0.6, new Vector3d(-593.73316, -593.73316, -593.73316)),
                Arguments.of("Boundary value testing : Min drag co", new Vector3d(230, 230, 230), 1.2, 0.018, 0.1, new Vector3d(-98.95552, -98.95552, -98.95552)),
                Arguments.of("Boundary value testing : Nominal", new Vector3d(230, 230, 230), 1.2, 0.018, 0.6, new Vector3d(-593.73316, -593.73316, -593.73316)),
                
                // Equivalence class cases
                Arguments.of("Equivalence class testing : Very fast x", new Vector3d(700, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-1204.95357, -344.27245, -344.27245)),
                Arguments.of("Equivalence class testing : Fast x", new Vector3d(400, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-446.78692, -223.39346, -223.39346)),
                Arguments.of("Equivalence class testing : Slow x", new Vector3d(200, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-157.96303, -157.96303, -157.96303)),
                Arguments.of("Equivalence class testing : Stationairy x", new Vector3d(0, 200, 200), 1.2, 0.019, 0.2, new Vector3d(0, -128.97627, -128.97627)),
                Arguments.of("Equivalence class testing : Very fast y", new Vector3d(200, 700, 200), 1.2, 0.019, 0.2, new Vector3d(-344.27245, -1204.95357, -344.27245)),
                Arguments.of("Equivalence class testing : Fast y", new Vector3d(200, 400, 200), 1.2, 0.019, 0.2, new Vector3d(-223.39346, -446.78692, -223.39346)),
                Arguments.of("Equivalence class testing : Slow y", new Vector3d(200, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-157.96303, -157.96303, -157.96303)),
                Arguments.of("Equivalence class testing : Stationairy y", new Vector3d(200, 0, 200), 1.2, 0.019, 0.2, new Vector3d(-128.97627, 0, -128.97627)),
                Arguments.of("Equivalence class testing : Very fast z", new Vector3d(200, 200, 700), 1.2, 0.019, 0.2, new Vector3d(-344.27245, -344.27245, -1204.95357)),
                Arguments.of("Equivalence class testing : Fast z", new Vector3d(200, 200, 400), 1.2, 0.019, 0.2, new Vector3d(-223.39346, -223.39346, -446.78692)),
                Arguments.of("Equivalence class testing : Slow z", new Vector3d(200, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-157.96303, -157.96303, -157.96303)),
                Arguments.of("Equivalence class testing : Stationairy z", new Vector3d(200, 200, 0), 1.2, 0.019, 0.2, new Vector3d(-128.97627, -128.97627, 0)),
                Arguments.of("Equivalence class testing : Cold Temp", new Vector3d(200, 200, 200), 1.3, 0.019, 0.2, new Vector3d(-171.12661, -171.12661, -171.12661)),
                Arguments.of("Equivalence class testing : Normal Temp", new Vector3d(200, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-157.96303, -157.96303, -157.96303)),
                Arguments.of("Equivalence class testing : Hot Temp", new Vector3d(200, 200, 200), 1.1, 0.019, 0.2, new Vector3d(-144.79944, -144.79944, -144.79944)),
                Arguments.of("Equivalence class testing : Average Cd", new Vector3d(230, 230, 230), 1.2, 0.019, 0.2, new Vector3d(-208.90611, -208.90611, -208.90611)),
                Arguments.of("Equivalence class testing : Ok Cd", new Vector3d(200, 200, 200), 1.2, 0.019, 0.4, new Vector3d(-315.92606, -315.92606, -315.92606)),
                Arguments.of("Equivalence class testing : Bad Cd", new Vector3d(200, 200, 200), 1.2, 0.019, 0.6, new Vector3d(-473.88910, -473.88910, -473.88910)),
                Arguments.of("Equivalence class testing : Small area", new Vector3d(200, 200, 200), 1.2, 0.0086, 0.2, new Vector3d(-71.49905, -71.49905, -71.49905)),
                Arguments.of("Equivalence class testing : Medium area", new Vector3d(200, 200, 200), 1.2, 0.019, 0.2, new Vector3d(-157.96303, -157.96303, -157.96303)),
                Arguments.of("Equivalence class testing : Large area", new Vector3d(200, 200, 200), 1.2, 0.045, 0.2, new Vector3d(-374.12297, -374.12297, -374.12297))
            );    
    }

}
