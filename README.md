# README

## Build with Nix/Maven

This project utilizes a Nix flake to build the project and provide a development shell with the build dependencies, `jdt-language-server`, and `maven` using `direnv`

To build the project from the flake:

`nix build` to build the `.jar`

`nix develop` then `mvn clean test` to run tests

`nix run` will run the project

## Build with Maven

- Install [Maven](https://maven.apache.org/)
- `mvn compile` to compile the project
- `mvn clean test` to run tests
- `mvn exec:java` to run the main class

## Outputs

Should look something like this:

``` sh
~/projects/projectile-simulation-suite $ mvn clean test
[INFO] Scanning for projects...
[INFO]
[INFO] --------------< com.example:projectile-simulation-suite >---------------
[INFO] Building projectile-simulation-suite 0.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ projectile-simulation-suite ---
[INFO] Deleting /home/bh/projects/projectile-simulation-suite/target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ projectile-simulation-suite ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bh/projects/projectile-simulation-suite/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ projectile-simulation-suite ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/bh/projects/projectile-simulation-suite/target/classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ projectile-simulation-suite ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bh/projects/projectile-simulation-suite/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ projectile-simulation-suite ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/bh/projects/projectile-simulation-suite/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:3.1.0:test (default-test) @ projectile-simulation-suite ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.example.project.CalculatorTests
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.038 s - in com.example.project.CalculatorTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.674 s
[INFO] Finished at: 2023-05-19T14:46:20-06:00
[INFO] ------------------------------------------------------------------------
```


``` sh
~/projects/projectile-simulation-suite $ mvn exec:java
[INFO] Scanning for projects...
[INFO]
[INFO] --------------< com.example:projectile-simulation-suite >---------------
[INFO] Building projectile-simulation-suite 0.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- exec-maven-plugin:3.1.0:java (default-cli) @ projectile-simulation-suite ---
Result: 8
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.079 s
[INFO] Finished at: 2023-05-21T07:05:51-06:00
[INFO] ------------------------------------------------------------------------
```

## TODO
- Once we start actual development the `mainClass` attributes in `pom.xml` will need to be updated with the new `mainClass` name for any of the above commands to work

- the project currently uses jdk11 and jdk17, jdk11 is used to build the application itself while jdk17 is used in the development shell as for some reason new language servers are not backwards compatible with jdk11, do we want to keep using a mix or just move to jdk17?
