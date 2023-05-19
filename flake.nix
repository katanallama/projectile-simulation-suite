{
  inputs = {
    mvn2nix.url = "github:fzakaria/mvn2nix";
    utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, mvn2nix, utils, ... }:
    let
      pkgsForSystem = system:
        import nixpkgs {
          # ./overlay.nix contains the logic to package local repository
          overlays = [ mvn2nix.overlay (import ./overlay.nix) ];
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
