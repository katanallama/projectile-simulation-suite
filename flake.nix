{
  inputs = {
    mvn2nix.url = "github:fzakaria/mvn2nix";
    utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, mvn2nix, utils, ... }:
    let
      overlay = final: prev: {
        projectile-simulation-suite = final.callPackage ./default.nix { };
      };

      pkgsForSystem = system:
        import nixpkgs {
          overlays = [ mvn2nix.overlay overlay ];
          inherit system;
        };

    in utils.lib.eachSystem utils.lib.defaultSystems (system: rec {
      legacyPackages = pkgsForSystem system;
      packages = utils.lib.flattenTree {
        inherit (legacyPackages) projectile-simulation-suite;
      };

      defaultPackage = legacyPackages.projectile-simulation-suite;

      devShell = legacyPackages.mkShellNoCC {
        name = "java";
        buildInputs = [
          legacyPackages.jdk
          legacyPackages.maven
          legacyPackages.jdt-language-server
          packages.projectile-simulation-suite
        ];
      };
    });
}
