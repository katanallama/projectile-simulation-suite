# README

## Project Documentation

All project related documentation found [here](/docs/README.org)

## Build with Nix/Maven

This project utilizes a Nix flake to build the project and provide a `devShell` with the build dependencies, `jdt-language-server`, and `maven` using `direnv`

To build the project from the flake:

If using `direnv` run `direnv allow` in project root

`nix build` to build project, output found in `./result`

`nix develop` then `mvn clean test` to run tests

`nix run` will run the project

### Adding new dependencies

Since this project makes use of `mvn2nix`, to update the dependencies, update `pom.xml` and run:

`nix run github:fzakaria/mvn2nix#mvn2nix > mvn2nix-lock.json`

`nix flake update` then `nix develop` to update the flakes inputs and get back into the `devShell`

## Build with Maven

- Install [Maven](https://maven.apache.org/)
- `mvn compile` to compile the project
- `mvn clean test` to run tests
- `mvn exec:java` to run the main class

## [Project Roadmap](/docs/schedule.md)
