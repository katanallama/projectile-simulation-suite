# Projectile Simulation Suite

Java package to simulate projectile motion in 3 dimensions. Provides an interactive 3d plot of the trajectory and a console output of the position over time.

![pss simulation](./docs/res/rec.gif)

## Project Documentation

All project related documentation found [here](/docs/README.org)

### Build with Nix/Maven

This project utilizes a Nix flake to build the project and provide a `devShell` with the build dependencies `jdt-language-server`and `maven`using `direnv` to automatically load the environment. Additionally, the flake provides the project as the default package.

**To build the project from the flake:**

If using `direnv` run `direnv allow` in project root

- `nix build` to build project, output found in `./result`

- `nix build -L` to output the full test log

- `nix run` will build and run the project

#### Adding new dependencies

To update the dependencies, update `pom.xml` run `nix build` and set the [mvnHash](./default.nix) in `./default.nix` :

`outputHash = "";` remove the old hash

`direnv reload`, so long as you have `direnv` installed or,

`nix build`

```
warning: found empty hash, assuming 'sha256-AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA='
[1/0/3 built, 0.0 MiB DL] building maven-repository (buildPhase): Downloading from central: https://redirenv:
error: hash mismatch in fixed-output derivation '/nix/store/p635baxv930v2bb4k527j6rk54a3nfpl-maven-repository.drv':
         specified: sha256-AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=
            got:    sha256-CrXCiJgU5kKywK7D01YrSk+teXkAMvrYmbNCh7lWX0I=
```

Update the hash in `./default.nix`:

`outputHash = "sha256-CrXCiJgU5kKywK7D01YrSk+teXkAMvrYmbNCh7lWX0I=";`

`direnv reload`

The environment should reload on the next command as `direnv` is set to watch `*.nix` and `pom.xml`

### Build with Maven

Install [Maven](https://maven.apache.org/)

- `mvn install` to compile the project

- `mvn clean test` to run tests

- `mvn exec:java` to run the main class

## [Project Roadmap](/docs/schedule.md)
