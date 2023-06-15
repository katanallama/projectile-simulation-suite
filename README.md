# README

## Project Documentation

All project related documentation found [here](/docs/README.org)

### Build with Nix/Maven

This project utilizes a Nix flake to build the project and provide a `devShell` with the build dependencies; `jdt-language-server`; `maven`; and the built `pss` package using `direnv` to automatically load the environment. Additionally, the flake provides the project as the default package.

**To build the project from the flake:**

If using `direnv` run `direnv allow` in project root

- `nix build` to build project, output found in `./result`

- `nix build -L` to output the full test log

- `nix run` will build and run the project

#### Adding new dependencies

To update the dependencies, update `pom.xml` and run:

`direnv reload`, so long as you have `direnv` installed

The environment should reload on the next command as `direnv` is set to watch `*.*.nix` and `pom.xml`

### Build with Maven

Install [Maven](https://maven.apache.org/)

- `mvn install` to compile the project

- `mvn clean test` to run tests

- `mvn exec:java` to run the main class

## [Project Roadmap](/docs/schedule.md)
