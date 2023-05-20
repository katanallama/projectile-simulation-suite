# {
#   inputs = {
#     mvn2nix.url = "github:fzakaria/mvn2nix";
#     utils.url = "github:numtide/flake-utils";
#   };

#   outputs = { nixpkgs, mvn2nix, utils, ... }:
#     let
#       overlay = final: prev: {
#         projectile-simulation-suite = final.callPackage ./default.nix { };
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
#           legacyPackages.jdk
#           legacyPackages.maven
#           legacyPackages.jdt-language-server
#           packages.projectile-simulation-suite
#         ];
#       };
#     });
# }
{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    # nixpkgs.url = "github:NixOS/nixpkgs/22.11";
    # nixpkgs.url = "path:/home/bh/projects/nixpkgs"; # for testing
    mvn2nix.url = "github:fzakaria/mvn2nix";
    utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, mvn2nix, utils, ... }:
    let
      overlay = final: prev: {
        projectile-simulation-suite = final.callPackage ./default.nix { };
        jdt-language-server = final.callPackage ./jdt-language-server.nix { };
      };

      pkgsForSystem = system:
        import nixpkgs {
          overlays = [ mvn2nix.overlay overlay ];
          inherit system;
        };
    in utils.lib.eachSystem utils.lib.defaultSystems (system: rec {
      legacyPackages = pkgsForSystem system;
      # packages = utils.lib.flattenTree {
      #   inherit (legacyPackages) projectile-simulation-suite;
      # };
      # defaultPackage = legacyPackages.projectile-simulation-suite;
      devShell = legacyPackages.mkShellNoCC {
        name = "java";
        buildInputs = [
          legacyPackages.jdk17
          legacyPackages.maven
          legacyPackages.jdt-language-server
          # packages.projectile-simulation-suite
        ];
        shellHook = ''
          export JAVA_HOME=${legacyPackages.jdk17}/
          export JDTLS_PATH=${legacyPackages.jdt-language-server}/share/java/
        '';
      };
    });
}
