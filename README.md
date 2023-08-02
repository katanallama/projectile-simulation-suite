# Projectile Simulation Suite

Java-based howitzer firing simulator offering an interactive 3D experience to understand complex kinematics and dynamics principles. The simulator considers numerous real-world parameters such as projectile mass, drag coefficient, initial speed, and gravity.

The primary focus of this suite is to serve as a platform for rigorous software testing practices. Apart from being a physics learning tool, it also allows us to design and execute extensive test suites, ensuring the software's reliability and robustness.

This suite visualizes the projectile's trajectory in 3D and provides real-time data output of the projectile's position, making it an efficient tool for both learning and software quality assurance.

![pss simulation](./docs/res/rec.gif)

## Running the Project

Ensure that you have either `Java` and `Maven` or `Nix` installed on your machine.

### Configuration
The configuration file should specify the parameters for the projectile such as mass, drag coefficient, initial speed, and gravity.

For example, a configuration file could look something like this:

```
{
    "gravity" : 9.81,
    "weight" :  20.0,
    "muzzleVelocity" :  10,
    "dragCoefficent" :  0.04,
    "simStep" :  1.5,
    "timeStep" :  0.001
}
```

This would represent a projectile with a mass of 20kg, a drag coefficient of 0.04, an initial speed of 10 m/s, gravity of 9.81 m/s^2 and 1500 time steps. The suite would then calculate the trajectory of the projectile based on these parameters.

Ensure that your configuration file is in `./config` and the name of the file (without the `.json` extension) should be passed as the value of the `-c` or `--config` option when running the simulation.

### Build with Maven

Install [Maven](https://maven.apache.org/)

- `mvn install` to compile the project

- `mvn clean test` to run tests

- `mvn exec:java -Dexec.args="-c testUseCase2 -p 3d"` will run the simulation on `./config/testUseCase2` and output a 3d plot of the trajectory

### Build with Nix/Maven

This project utilizes a Nix flake to build the project and provide a `devShell` with the build dependencies `jdt-language-server` and `maven`using `direnv` to automatically load the environment. Additionally, the flake provides the project as the default package.

**To build the project from the flake:**

If using `direnv` run `direnv allow` in project root

- `nix build` to build project, output found in `./result`

- `nix build -L` to output the full test log

- `nix run .#default -- -c testUseCase2 -p 3d` will run the simulation on `./config/testUseCase2` and output a 3d plot of the trajectory

## Project Documentation

Checkout the [documentation](/docs/README.org)

Visit the [Project Roadmap](/docs/schedule.md)
