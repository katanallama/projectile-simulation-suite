# README

`nix build` to build the `.jar`

`nix develop` then `mvn clean test` to run tests

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
