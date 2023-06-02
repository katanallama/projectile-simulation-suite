# {
#   inputs = {
#     nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
#     mvn2nix.url = "github:fzakaria/mvn2nix";
#     utils.url = "github:numtide/flake-utils";
#   };

#   outputs = { nixpkgs, mvn2nix, utils, ... }:
#     let
#       overlay = final: prev: {
#         projectile-simulation-suite = final.callPackage ./default.nix { };
#         jdt-language-server = final.callPackage ./jdt-language-server.nix { };
#       };

#       pkgsForSystem = system:
#         import nixpkgs {
#           overlays = [ mvn2nix.overlay overlay ];
#           inherit system;
#         };
#     in utils.lib.eachSystem utils.lib.defaultSystems (system: rec {
#       legacyPackages = pkgsForSystem system;
#       packages = utils.lib.flattenTree {
#         inherit (legacyPackages) projectile-simulation-suite;
#       };
#       defaultPackage = legacyPackages.projectile-simulation-suite;
#       devShell = legacyPackages.mkShellNoCC {
#         name = "java";
#         buildInputs = [
#           legacyPackages.jdk17
#           legacyPackages.maven
#           legacyPackages.jdt-language-server
#           packages.projectile-simulation-suite
#         ];
#         shellHook = ''
#           export JAVA_HOME=${legacyPackages.jdk17}/
#           export JDTLS_PATH=${legacyPackages.jdt-language-server}/share/java/
#         '';
#       };
#     });
# }
{
  description = "A Nix flake for building a projectile simulation suite";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    mvn2nix.url = "github:fzakaria/mvn2nix";
  };

  outputs = { self, nixpkgs, mvn2nix }:

    let
      system = "x86_64-linux";

      pkgs = import nixpkgs {
        overlays = [ mvn2nix.overlay ];
        system = system;
      };

      overlay = self: super: {
        projectile-simulation-suite = self.callPackage ./default.nix { };
        jdt-language-server = self.callPackage ./jdt-language-server.nix { };
      };

      appPkgs = pkgs.extend overlay;

    in
    {
      defaultPackage.${system} = appPkgs.projectile-simulation-suite;

      devShell.${system} = appPkgs.mkShellNoCC {
        name = "java";
        buildInputs = [
          appPkgs.jdk17
          appPkgs.maven
          appPkgs.jdt-language-server
          appPkgs.projectile-simulation-suite
        ];
        shellHook = ''
          export JAVA_HOME=${appPkgs.jdk17}
          export JDTLS_PATH=${appPkgs.jdt-language-server}/share/java/
        '';
      };
    };
}
