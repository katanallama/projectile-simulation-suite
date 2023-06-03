
# Table of Contents

1.  [Problem Specification](#org4d93a12)
    1.  [Inputs](#org2b7b2b8)
        1.  [Simulation parameters:](#org5b680f9)
        2.  [IO Configuration parameters:](#orgd67946c)
    2.  [Outputs](#org3e2b167)
2.  [Design Requirements](#orgc5f9054)
    1.  [Objectives](#orgc30e6c3)
    2.  [Testing Requirements](#org9a63a0d)
    3.  [Constraints](#org955cba8)
        1.  [Ethics](#org3b39951)
        2.  [Accuracy](#org3033135)
        3.  [Reproducible Results](#org5f6278d)
        4.  [Reliability](#org24c5353)
        5.  [User Control and Display](#orgdc0d1f7)
        6.  [Information Management](#orge430437)



<a id="org4d93a12"></a>

# Problem Specification

This project involves the development of a simulator to teach soldiers about kinematics and dynamics in relation to a Howitzer shooting a projectile across an empty field. The goal is to accurately simulate the physics of a fired projectile, considering variables that could affect its trajectory, providing a realistic and educational simulator that helps soldiers understand and predict the effects of different variables on the trajectory and final position of a howitzer-fired projectile.


<a id="org2b7b2b8"></a>

## Inputs


<a id="org5b680f9"></a>

### Simulation parameters:

*Barrel Pose (Position and Orientation)*: determines the initial firing direction and location of the projectile

*Mass of the Projectile*: influences the flight characteristics of the projectile, including the effect of the force of gravity on the projectile

*Drag Coefficient*: describes how &rsquo;aerodynamic&rsquo; an object is. A higher drag coefficient means more resistance to the projectile&rsquo;s motion through the air

*Radius of the Projectile*: influences the flight characteristics of the projectile, including the effect of air resistance (drag)

*Initial Speed*: initial velocity of the projectile at the moment it leaves the howitzer

*Force Applied to the Projectile*: initial force applied by the propellant inside the Howitzer

*Acceleration due to Gravity*: a constant, allowing it as a variable input could be useful for hypothetical scenarios

*Density of the Fluid*: changes with changes in the environment elevation


<a id="orgd67946c"></a>

### IO Configuration parameters:

*input directory* (string): a directory holding the specific run configuration or previous run results

*output directory* (string): a directory to write the results

*seed value* (integer): a seed for a random number generator for deterministic results

*delta* (boolean): whether or not current run will be compared with previous run


<a id="org3e2b167"></a>

## Outputs

-   representation of 3D projectile trajectory

-   representation of the forces acting on the projectile during its flight

-   final position of the projectile

-   real-time kinematic and dynamic statistics (position, velocity, acceleration, forces) for deeper understanding and analysis


<a id="orgc5f9054"></a>

# Design Requirements

Demonstrate how different variables, such as the howitzer position and orientation, affect the trajectory and final impact location of a projectile and display the results of each simulation &ldquo;run&rdquo; in a way anybody can understand.

Create a robust test suite for parts of the simulator to demonstrate complete knowledge of standard test procedures.


<a id="orgc30e6c3"></a>

## Objectives

The simulator should be designed with the following requirements:

*User Interface*: The simulator should have an intuitive user interface that allows users to easily input variables and understand the output.

*Realism*: The simulator should realistically model the physics of a fired projectile, including the effects of gravity and drag.

*Performance*: The simulator should be able to run the simulation smoothly and handle calculations quickly, even for complex trajectories.

*Flexibility*: The simulator should allow for the adjustment of a wide range of variables, allowing for a variety of simulations.

*Education*: The simulator should have educational value, helping soldiers understand the factors that affect the trajectory and final position of a projectile.


<a id="org9a63a0d"></a>

## Testing Requirements

For effective testing of the simulator, ensuring the robustness and reliability of the simulator the following test methods are used.

*Unit Testing*: Test individual functions and components separately. For instance, testing the function calculating the projectile trajectory with a variety of inputs, including boundary values, equivalence classes, decision tables, paths, data flows, and slices.

*Integration Testing*: Select a subset to combine and test together. For instance, testing the function for trajectory calculation together with the drag calculation function and gravity effects to ensure they work correctly in tandem.

*System Testing*: Select a subset of use cases for system testing, to evaluate the system&rsquo;s overall functionality in scenarios that resemble real-world usage.


<a id="org955cba8"></a>

## Constraints


<a id="org3b39951"></a>

### Ethics

The purpose of the simulator is to educate soldiers for combat, which includes the potential destruction of lives and infrastructure, while the team does not condone this, our hope as a development team is that the education from our simulator is used to strengthen a strategy of combat deterrence and de-escalation, but we recognize the likelihood of said education to be used towards actual combat.


<a id="org3033135"></a>

### Accuracy

This simulator is a simplified model of a non-deterministic environment, we cannot guarantee that it precisely replicates the real-world-equivalent actions.


<a id="org5f6278d"></a>

### Reproducible Results

This is an important factor in not only simulation, but development as well. The team strives to produce a project and results that are highly reproducible. From the use of [Nix](https://nixos.org/guides/nix-pills/) as a build tool, to the development of the project. By ensuring that the project build is reproducible across multiple systems, this helps to ensure that the simulation results are also reproducible as the back-end will be consistent from machine to machine.

Any random number generation will have a user exposed and logged `seed` value.


<a id="org24c5353"></a>

### Reliability

The results of the simulator will be deterministic of the given configuration, resulting in expected outputs for a given input.


<a id="orgdc0d1f7"></a>

### User Control and Display

The simulation will run without a GUI; inputs and outputs are to be managed through external files according to the Information Management constraint. At most, the user input will be a one-time prompt at the program startup. At most, the simulation output either displayed through the console and/or recorded separately. The results should be able to be imported into other visualization tools easily.


<a id="orge430437"></a>

### Information Management

The simulation is initialized and run according to a configuration `.json` file with the relevant information which can be overriden with supplied commands. Simulation results saved to the user specified `outdir/`  that includes but is not limited to:

-   simulator configuration

&#x2013; mass : the mass of the projectile (kg)
&#x2013; drag<sub>coeffient</sub> : how aerodynamic the projectile is (number)
&#x2013; initial<sub>force</sub> : the force vector applied by the cannon (N)
&#x2013; gravity<sub>strength</sub> : the strengh of gravity (m/s<sup>2</sup>)
&#x2013; wind<sub>vector</sub> : the constant wind vector (N)
&#x2013; object<sub>area</sub> : the surface area of the projectile (m<sup>2</sup>)
&#x2013; object<sub>force</sub><sub>mode</sub> : the type of external force exerted by the projectile (enum)
&#x2013; wind<sub>vector</sub><sub>field</sub><sub>mode</sub> : the type of vector field that the project will travel through (enum)

-   simulator results

&#x2013; csv file with two columns: Time and postion 
&#x2013; run timestamp

Results logged in `csv` format that is easily interpreted by common analysis and visualization tools such as `matplotlib` and `seaborn`.

